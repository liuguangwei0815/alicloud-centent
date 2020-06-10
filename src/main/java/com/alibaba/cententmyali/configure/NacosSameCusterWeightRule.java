package com.alibaba.cententmyali.configure;


import com.alibaba.cententmyali.utils.StringUtil;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.internal.bytebuddy.matcher.CollectionSizeMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * nacos相同集群下 权重的规则规则
 * 1/比如南京机房的内容中心请求南京机房的用户中心
 * 2/不同的版本进行不同的版本调用
 * @author liuwei
 * @date 2020/05/31
 */
@Slf4j
public class NacosSameCusterWeightRule extends AbstractLoadBalancerRule {


    /**
     * nacos api 组件属性
     */
    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        try {
            //1、获取配置文件的集群名称 BJ
            String clusterName = nacosDiscoveryProperties.getClusterName();
            //获取负载句衡器
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            //获取当前需要请求的微服务名称
            String name = loadBalancer.getName();
            //拿到nacos api
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();

            //2、找到所有实例 A
            List<Instance> instances = namingService.selectInstances(name, true);

            //实现版本指定调用指定的版本 只能调用该verson
            String targetversion = nacosDiscoveryProperties.getMetadata().get("target-version");

            //判断该实例是否配置了版本，如果配置了 则需要一定匹配上否者异常
            if(StringUtils.isNotBlank(targetversion)){
                List<Instance> versionList = instances.stream().filter(instance -> StringUtils.equals(targetversion, instance.getMetadata().get("version")))
                        .collect(Collectors.toList());
                if(CollectionUtils.isEmpty(versionList)){
                    log.error("服务匹配到:{}"+targetversion);
                    return null;
                }
                //从新赋值 值匹配给对应版本的
                instances = versionList;
            }

            //3、过滤出相同集群下的所有实例 B
            List<Instance> sameNameCluster = instances.stream().filter(instance -> StringUtils.equals(instance.getClusterName(), clusterName))
                    .collect(Collectors.toList());

            //4、 如果 B为空 就用A
            //即将被盗用的集群
            List<Instance> toBenCluster = new ArrayList<>();
            if (CollectionUtils.isEmpty(sameNameCluster)) {
                toBenCluster = instances;
                //发送跨机房调用
                log.warn("发生跨集群调用 ,name:{}, clusterName :{} instance:{}",
                        name,
                        clusterName,
                        instances
                );
            } else {
                toBenCluster = sameNameCluster;
            }

            //5、集群nacos权重负载聚恒算法 返回1个实例
            Instance hostByRandomWeight2 = ExtClass.getHostByRandomWeight2(toBenCluster);

            log.info("选择了 port：{}  instance = {}", hostByRandomWeight2.getPort(),hostByRandomWeight2);

            return new NacosServer(hostByRandomWeight2);

        } catch (NacosException e) {
            e.printStackTrace();
        }

        return null;
    }
}

class ExtClass extends  Balancer{
    public static Instance getHostByRandomWeight2 (List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }
}
