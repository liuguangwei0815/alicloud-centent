package com.alibaba.cententmyali.configure;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * nacos权重规则(扩展lubbon ) 你可以局部的 或者是全局@ribbonClients
 *
 * @author liuwei
 * @date 2020/05/31
 */
@Slf4j
public class NacosWeightRule extends AbstractLoadBalancerRule {

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        //具体实现应实现此方法，使配置集通过  读取配置 ，并初始化NacosWeightRule（当前类） 我们这里用不到
    }
    @Override
    public Server choose(Object key) {
        try {
            //这里就是实现权重的算法，这里实现了起来还是有点难度的自己 所以需要 使用的nacose自己写的
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();

            //想要请求的微服务名称
            String name = loadBalancer.getName();

            //拿到服务发现的相关API
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();//naming 命名
            // nacos client 自动通过基于权重的负载均衡算法 给我们一个实例、、 这个方法有若多的重载方法
            Instance instance = namingService.selectOneHealthyInstance(name);

            log.info("选择了 port：{}  instance = {}", instance.getPort(),instance);

            return new NacosServer(instance);

        } catch (NacosException e) {
            e.printStackTrace();
            return null;
        }
    }
}
