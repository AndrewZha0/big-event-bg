package com.kenmi.bigevent.api.dto;


import lombok.Data;

import java.util.Date;


@Data
public class ArticleDTO {

    private Long id;
    private String title;//文章标题
    private String content;//文章内容
    private String coverImg;//封面图像
    private String state;//发布状态 已发布|草稿
    private Long categoryId;//文章分类id
    private String createdBy;
    private Date gmtCreate;
    private String modifiedBy;
    private Date gmtModify;
    private Integer deleted;
}
