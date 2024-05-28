package com.kenmi.bigevent.dal.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 数据库公共字段
 * @author: andrew
 */
@Data
public class CommonTableField implements Serializable {


    @Serial
    private static final long serialVersionUID = -7535505690586597264L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createdBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModify;

    /**
     * 是否删除(0:否,1:是)
     */
    @TableLogic()
    @TableField(fill = FieldFill.INSERT, select = false)
    private Integer deleted;
}
