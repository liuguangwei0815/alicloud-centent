package com.alibaba.cententmyali.controller;

import com.alibaba.cententmyali.domain.dto.ShareDto;
import com.alibaba.cententmyali.result.Result;
import com.alibaba.cententmyali.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
