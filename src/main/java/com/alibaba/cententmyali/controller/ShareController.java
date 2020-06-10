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
 * 分享控制器
 *
 * @author liuwei
 * @date 2020/05/13
 */
@RestController
@RequestMapping("/shares")
public class ShareController {

    @Autowired
    private ShareService shareService;


    @PostMapping("/{id}")
    public Result<ShareDto> getByid(@PathVariable("id") int id){
        return Result.success(shareService.getById(id));
    }

    //=================feign 的多参数请求BGN

    //get
    @GetMapping("/getmulpam")
    public Result<UserDto> getmulpam(UserDto dto){
         return Result.success(shareService.getmulpam(dto));
    }

    @Autowired
    private UserCenterFeign userCenterFeign;

    //post
    @PostMapping("/getmulpam")
    public Result<UserDto> getmulpampost(UserDto dto){
        return Result.success(userCenterFeign.getmulpampost(dto));
    }


    //=================feign 的多参数请求END
    @Autowired
    private TestBaiduPageFeign testBaiduPageFeign;
    @GetMapping
    public String getBaiduPgeByFeign(){
       return testBaiduPageFeign.getBaiduPage();
    }





}
