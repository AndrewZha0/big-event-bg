package com.kenmi.bigevent.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kenmi.bigevent.api.dto.UserDTO;
import com.kenmi.bigevent.api.error.ErrorCodeEnum;
import com.kenmi.bigevent.api.request.UpdateUserRequest;
import com.kenmi.bigevent.application.UserService;
import com.kenmi.bigevent.application.convert.UserConverter;
import com.kenmi.bigevent.common.domain.SimpleUser;
import com.kenmi.bigevent.common.utils.*;
import com.kenmi.bigevent.core.repository.UserRepository;
import com.kenmi.bigevent.dal.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public UserDTO findByUsername(String username) {
        return UserConverter.INSTANCE.convert(userRepository.getOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getUsername, username)));
    }

    @Override
    public UserDTO currentUser() {
        SimpleUser currentUser = UserInfoThreadHolder.getCurrentUser();
        return UserConverter.INSTANCE.convert(userRepository.getById(currentUser.getUserId()));
    }

    @Override
    public void register(String username, String password) {
        // 通过用户名查询
        UserDO userDO = userRepository.getOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getUsername, username));
        if (Objects.nonNull(userDO)) {
            ParamUtils.fail(ErrorCodeEnum.REGISTER_USERNAME_EXIST);
        }
        String md5String = Md5Util.getMD5String(password);
        userDO = new UserDO();
        userDO.setId(new IdWorker().nextId());
        userDO.setUsername(username);
        userDO.setPassword(md5String);
        userRepository.save(userDO);
    }

    @Override
    public String login(String username, String password) {
        // 通过用户名查询
        UserDO userDO = userRepository.getOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getUsername, username));
        if (Objects.isNull(userDO)) {
            ParamUtils.fail(ErrorCodeEnum.LOGIN_USERNAME_ERROR);
        }
        // 判断密码
        if (!Md5Util.getMD5String(password).equals(userDO.getPassword())) {
            ParamUtils.fail(ErrorCodeEnum.LOGIN_USERNAME_ERROR);
        }
        UserDTO userDTO = UserConverter.INSTANCE.convert(userDO);
        // 生产jwt token
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", userDTO.getId());
        claim.put("username", userDTO.getUsername());
        String token = JwtUtil.genToken(claim);
        // 存入redis
        redisUtils.set(username, token, 1L, TimeUnit.HOURS);
        return token;
    }

    @Override
    public void updateById(UpdateUserRequest user) {
        SimpleUser currentUser = UserInfoThreadHolder.getCurrentUser();
        UserDO userDO = UserConverter.INSTANCE.convert(user);
        userDO.setId(currentUser.getUserId());
        userRepository.updateById(userDO);
    }

    @Override
    public void updatePwd(Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd))
            ParamUtils.fail(ErrorCodeEnum.PARAM_ILLEGAL);

        if (!newPwd.equals(rePwd))
            ParamUtils.fail(ErrorCodeEnum.PARAM_ILLEGAL, "两次输入的新密码不一致");

        SimpleUser currentUser = UserInfoThreadHolder.getCurrentUser();
        UserDO userDO = userRepository.getById(currentUser.getUserId());
        if (!Md5Util.getMD5String(oldPwd).equals(userDO.getPassword())) {
            ParamUtils.fail(ErrorCodeEnum.PARAM_ILLEGAL, "旧密码输入不正确");
        }
        userRepository.update(new UpdateWrapper<UserDO>().lambda()
                .set(UserDO::getPassword, Md5Util.getMD5String(newPwd)).eq(UserDO::getId, currentUser.getUserId()));
    }

    @Override
    public void updatePic(String avatarUrl) {
        SimpleUser currentUser = UserInfoThreadHolder.getCurrentUser();
        userRepository.update(new UpdateWrapper<UserDO>().lambda()
                .set(UserDO::getUserPic, avatarUrl).eq(UserDO::getId, currentUser.getUserId()));
    }
}
