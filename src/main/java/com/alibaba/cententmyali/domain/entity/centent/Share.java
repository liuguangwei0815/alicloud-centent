package com.alibaba.cententmyali.domain.entity.centent;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "share")
public class Share {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String title;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否原始
     */
    @Column(name = "is_original")
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

    @Column(name = "download_url")
    private String downloadUrl;

    @Column(name = "buy_count")
    private Integer buyCount;

    /**
     * 展示标识
     */
    @Column(name = "show_flag")
    private Boolean showFlag;

    @Column(name = "audit_status")
    private String auditStatus;

    /**
     * 原因
     */
    private String reason;
}