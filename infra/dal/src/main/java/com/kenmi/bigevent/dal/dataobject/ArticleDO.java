package com.kenmi.bigevent.dal.dataobject;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "bigevent_b_article")
public class ArticleDO extends CommonTableField {

    @TableField("title")
    private String title;//文章标题
    @TableField("content")
    private String content;//文章内容
    @TableField("cover_img")
    private String coverImg;//封面图像
    @TableField("state")
    private String state;//发布状态 已发布|草稿
    @TableField("category_id")
    private Long categoryId;//文章分类id
}
