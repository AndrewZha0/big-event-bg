package com.kenmi.bigevent.application;

import com.kenmi.bigevent.api.dto.UserDTO;
import com.kenmi.bigevent.api.request.UpdateUserRequest;

import java.util.Map;

public interface UserService {
    // 通过用户名查询
    UserDTO findByUsername(String username);

    UserDTO currentUser();

    // 注册用户
    void register(String username, String password);

    String login(String username, String password);

    void updateById(UpdateUserRequest request);

    void updatePwd(Map<String, String> params);

    void updatePic(String avatarUrl);
}
