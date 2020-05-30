package com.alibaba.cententmyali.service;

import com.alibaba.cententmyali.dao.centent.ShareMapper;
import com.alibaba.cententmyali.domain.dto.ShareDto;
import com.alibaba.cententmyali.domain.dto.UserDto;
import com.alibaba.cententmyali.domain.entity.centent.Share;
import com.alibaba.cententmyali.result.Result;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

/**
 * 共享服务
 *
 * @author liuwei
 * @date 2020/05/14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ShareService {

    private final ShareMapper shareMapper;

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;


    /**
     * 通过id获取分享内容
     *
     * @param id id
     * @return {@link Share}
     */
    public ShareDto getById(Integer id){
        //通过分享id获取分享记录
        Share share = shareMapper.selectByPrimaryKey(id);

        //获取用户id
        Integer userId = share.getUserId();

        //获取服务发现组件api
        //List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
/*        String address = instances.stream()
                //数据转换
                .map(e -> e.getUri().toString()+"/users/"+id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("找不到对应的服务地址"));*/

        //随机获取一个地址
//        List<String> collect = instances.stream()
//                .map(e -> e.getUri().toString() + "/users/" + id)
//                .collect(Collectors.toList());
        //总结一下，在多线程下使用 ThreadLocalRandom 产生随机数时，直接使用 ThreadLocalRandom.current().xxxx  这是线程安全的
        //ramdon 在多线程下也是安全的，但是性能不好？？？
       //int i = ThreadLocalRandom.current().nextInt(collect.size());

        //log.info("对应的地址是：{}",collect.get(i));

        String url = "http://user-center/users/"+id;

        Result userDtoResult = restTemplate.getForObject(url, Result.class);
        //map 转  对象
        LinkedHashMap map = (LinkedHashMap)userDtoResult.getData();
        UserDto userDto = JSON.parseObject(JSON.toJSONString(map), UserDto.class);

        //消息的封装
        ShareDto shareDto = new ShareDto();
        BeanUtils.copyProperties(share, shareDto);
        shareDto.setWxNickname(userDto.getWxNickname());

        return shareDto;
    }


    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        //发起restTmplate 请求 注意这个会返回相应状态码
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/users/{id}", String.class, 1);
        System.out.println(String.format("getEntiy获取用户实体：%s,状态码为：%s",forEntity.getBody(),forEntity.getStatusCode()));

        String forObject = restTemplate.getForObject("http://localhost:8080/users/{id}", String.class, 1);
        System.out.println(String.format("getObject获取用户实体：%s",forEntity.getBody()));

    }








}
