package com.alibaba.cententmyali;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * 测试哨兵的族点链路的流控-流控类型- 关联
 *
 * @author liuwei
 * @date 2020/06/12
 */
@SpringBootTest(classes = MyaliApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestSentinelConnetction {


    @Test
    public void test() throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        while (true){
            Object forObject = restTemplate.getForObject("http://localhost:8010/actuator", Object.class);
            log.info("获取object", forObject);
            Thread.sleep(500);
        }
    }

}
