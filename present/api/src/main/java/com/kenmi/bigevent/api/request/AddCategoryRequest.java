package com.kenmi.bigevent.api.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddCategoryRequest {
    @NotEmpty
    private String categoryName;//分类名称
    @NotEmpty
    private String categoryAlias;//分类别名
}
