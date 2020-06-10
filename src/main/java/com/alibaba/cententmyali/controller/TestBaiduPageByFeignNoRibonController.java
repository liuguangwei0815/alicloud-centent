package com.alibaba.cententmyali.controller;

import com.alibaba.cententmyali.domain.dto.ShareDto;
import com.alibaba.cententmyali.domain.dto.UserDto;
import com.alibaba.cententmyali.feignclient.TestBaiduPageFeign;
import com.alibaba.cententmyali.feignclient.UserCenterFeign;
import com.alibaba.cententmyali.result.Result;
import com.alibaba.cententmyali.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 脱离ribbon使用feign
 *
 * @author liuwei
 * @date 2020/05/13
 */
@RestController
@RequestMapping("/baidu")
public class TestBaiduPageByFeignNoRibonController {

    @Autowired
    private TestBaiduPageFeign testBaiduPageFeign;
    @GetMapping
    public String getBaiduPgeByFeign(){
       return testBaiduPageFeign.getBaiduPage();
    }
}
