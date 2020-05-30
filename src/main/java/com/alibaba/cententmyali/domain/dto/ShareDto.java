package com.alibaba.cententmyali.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 分享dto
 *
 * @author liuwei
 * @date 2020/05/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareDto {

    private Integer id;

    @NotNull(message = "用户id不能为空")
    private Integer userId;

    private String title;

    /**
     * 是否原始
     */
    private Boolean isOriginal;

    private String author;

    /**
     * 报导
     */
    private String cover;

    /**
     * 摘要
     */
    private String summary;

    private Integer price;

    private String downloadUrl;

    private Integer buyCount;

    /**
     * 展示标识
     */
    private Boolean showFlag;

    private String auditStatus;

    /**
     * 原因
     */
    private String reason;

    /**
     * 发布人昵称
     */
    private String wxNickname;
}
