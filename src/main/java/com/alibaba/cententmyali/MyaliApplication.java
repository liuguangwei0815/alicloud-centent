package com.alibaba.cententmyali;

import com.alibaba.cententmyali.configure.FeignLogLevelConfiguration;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.alibaba")
@EnableDiscoveryClient
@EnableFeignClients //(defaultConfiguration = FeignLogLevelConfiguration.class)
public class MyaliApplication {

    @Bean
    @LoadBalanced//restTemplate 开启ribboen负载
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MyaliApplication.class);
        //  设置启动图片
        springApplication.setBanner(new ResourceBanner(new ClassPathResource("banner_bak.txt")));
        springApplication.run(args);
    }


}
