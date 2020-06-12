package com.alibaba.cententmyali.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService {

    @SentinelResource("commont")
    public String common(){
        log.info("commont");
        return "common";
    }
}
