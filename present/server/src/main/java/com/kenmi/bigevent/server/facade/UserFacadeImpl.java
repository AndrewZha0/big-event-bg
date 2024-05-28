package com.kenmi.bigevent.server.facade;

import com.kenmi.bigevent.api.dto.UserDTO;
import com.kenmi.bigevent.api.facade.UserFacade;
import com.kenmi.bigevent.api.request.UpdateUserRequest;
import com.kenmi.bigevent.api.response.ResultResponse;
import com.kenmi.bigevent.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    ServerFacadeTemplate serverFacadeTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultResponse<Void> register(String username, String password) {
        return serverFacadeTemplate.execute(() -> userService.register(username, password));
    }

    @Override
    public ResultResponse<String> login(String username, String password) {
        return serverFacadeTemplate.execute(() -> userService.login(username, password));
    }

    @Override
    public ResultResponse<UserDTO> getUser() {
        return serverFacadeTemplate.execute(() -> userService.currentUser());
    }

    @Override
    public ResultResponse<Void> updateUser(UpdateUserRequest user) {
        return serverFacadeTemplate.execute(() -> userService.updateById(user));
    }

    @Override
    public ResultResponse<Void> updateUserPwd(Map<String, String> params) {
        return serverFacadeTemplate.execute(() -> userService.updatePwd(params));
    }

    @Override
    public ResultResponse<Void> updateUserPic(String avatarUrl) {
        return serverFacadeTemplate.execute(() -> userService.updatePic(avatarUrl));
    }
}
