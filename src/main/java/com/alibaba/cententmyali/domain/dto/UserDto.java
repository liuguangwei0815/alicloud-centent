package com.alibaba.cententmyali.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 用户dto
 *
 * @author liuwei
 * @date 2020/05/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Integer id;

    private String wxId;

    @NotBlank(message = "微信昵称不能为空")
    private String wxNickname;

    @NotBlank(message = "角色不能为空")
    private String roles;

    private String avatarUrl;

    private Integer bonus;

    private String phone;


}
