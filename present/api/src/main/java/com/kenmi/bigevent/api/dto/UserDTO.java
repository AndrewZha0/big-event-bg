package com.kenmi.bigevent.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private Long id;
    private String username;//用户名
    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private String createdBy;
    private Date gmtCreate;
    private String modifiedBy;
    private Date gmtModify;
    private Integer deleted;
}
