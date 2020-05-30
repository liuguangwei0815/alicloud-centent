package com.alibaba.cententmyali;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * 测试请求用户服务
 *
 * @author liuwei
 * @date 2020/05/21
 */
@SpringBootTest(classes = MyaliApplication.class)
@RunWith(SpringRunner.class)
public class TestRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRestT(){
        String url = "http://localhost:8080/users/{id}";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class,1);
        System.out.println(String.format("响应状态为:%s,返回报文为:%s", forEntity.getStatusCode(),forEntity.getBody()));
    }


}
