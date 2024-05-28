package com.kenmi.bigevent.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {

    private Long id;
    private String categoryName;//分类名称
    private String categoryAlias;//分类别名
    private String createdBy;
    private Date gmtCreate;
    private String modifiedBy;
    private Date gmtModify;
    private Integer deleted;
}
