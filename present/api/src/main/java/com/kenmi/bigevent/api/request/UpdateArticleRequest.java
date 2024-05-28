package com.kenmi.bigevent.api.request;

import com.kenmi.bigevent.api.annotation.ArticleState;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateArticleRequest {
    @NotNull
    private Long id;
    @NotEmpty
    private String title;//文章标题
    @NotEmpty
    private String content;//文章内容
    private String coverImg;//封面图像
    @NotEmpty
    @ArticleState
    private String state;//发布状态 已发布|草稿
    @NotNull
    private Long categoryId;//文章分类id
}
