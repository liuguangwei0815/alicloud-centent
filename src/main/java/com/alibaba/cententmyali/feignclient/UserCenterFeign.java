package com.alibaba.cententmyali.feignclient;

import com.alibaba.cententmyali.configure.FeignLogLevelConfiguration;
import com.alibaba.cententmyali.domain.dto.UserDto;
import com.alibaba.cententmyali.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

/**
 * 用户中心声明是HTTP
 *
 * @author liuwei
 * @date 2020/06/01
 */
//@FeignClient(name = "user-center",path = "/users",configuration = FeignLogLevelConfiguration.class) //这个是java方式配置feigin的日志级别
@FeignClient(name = "user-center",path = "/users")
public interface UserCenterFeign {

    @GetMapping("/{id}")
    public Result<UserDto> getuser(@PathVariable("id") Integer id);


    /**
     * get方法多参数，。其实就是传递一个dto
     *
     * @return {@link Result<UserDto>}
     */
    @GetMapping("/getmulpam")
    public UserDto getmulpam(@SpringQueryMap  UserDto user);


    /**
     * get方法多参数，。其实就是传递一个dto
     *
     * @return {@link Result<UserDto>}
     */
    @PostMapping("/getmulpam")
    public UserDto getmulpampost(@RequestBody  UserDto user);

}
