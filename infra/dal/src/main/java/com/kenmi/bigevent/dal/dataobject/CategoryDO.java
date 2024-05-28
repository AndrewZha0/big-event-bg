package com.kenmi.bigevent.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "bigevent_b_category")
public class CategoryDO extends CommonTableField {

    @TableField("category_name")
    private String categoryName;//分类名称
    @TableField("category_alias")
    private String categoryAlias;//分类别名
}
