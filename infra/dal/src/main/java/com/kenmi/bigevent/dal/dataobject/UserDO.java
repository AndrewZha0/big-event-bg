package com.kenmi.bigevent.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "bigevent_s_user")
public class UserDO extends CommonTableField {

    @TableField("username")
    private String username;//用户名
    @TableField("password")
    private String password;//密码
    @TableField("nickname")
    private String nickname;//昵称
    @TableField("email")
    private String email;//邮箱
    @TableField("user_pic")
    private String userPic;//用户头像地址
}
