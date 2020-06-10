package com.alibaba.cententmyali.feignclient;

import com.alibaba.cententmyali.domain.dto.UserDto;
import com.alibaba.cententmyali.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 脱离ribbon使用feign
 *
 * @author liuwei
 * @date 2020/06/01
 */
@FeignClient(name = "baidu",url = "http://www.baidu.com")
public interface TestBaiduPageFeign {
    @GetMapping
    public String getBaiduPage();
}
