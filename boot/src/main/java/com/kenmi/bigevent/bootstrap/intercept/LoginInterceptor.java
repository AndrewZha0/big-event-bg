package com.kenmi.bigevent.bootstrap.intercept;


import com.kenmi.bigevent.api.error.ErrorCodeEnum;
import com.kenmi.bigevent.common.domain.SimpleUser;
import com.kenmi.bigevent.common.utils.JwtUtil;
import com.kenmi.bigevent.common.utils.ParamUtils;
import com.kenmi.bigevent.common.utils.RedisUtils;
import com.kenmi.bigevent.common.utils.UserInfoThreadHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("request uri: {}", requestURI);
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasLength(authorization)) {
            Map<String, Object> map = JwtUtil.parseToken(authorization);
            Long userId = (Long) map.get("id");
            String username = (String) map.get("username");
            String token = (String) redisUtils.get(username);
            if (StringUtils.hasLength(token) && token.equals(authorization)) {
                UserInfoThreadHolder.addCurrentUser(new SimpleUser(userId, username));
                return true;
            }
        }
        response.setStatus(401);
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoThreadHolder.remove();
    }
}
