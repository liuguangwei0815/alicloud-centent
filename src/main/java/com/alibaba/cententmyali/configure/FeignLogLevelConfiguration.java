package com.alibaba.cententmyali.configure;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignLogLevelConfiguration {
    /**
     * 只需要配置这个返回这个枚举就可以了
     *
     * @return {@link Level}
     */
    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
