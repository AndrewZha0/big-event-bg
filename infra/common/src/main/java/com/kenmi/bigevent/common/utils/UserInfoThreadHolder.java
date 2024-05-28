package com.kenmi.bigevent.common.utils;


import com.kenmi.bigevent.common.domain.SimpleUser;

import java.util.Objects;

public class UserInfoThreadHolder {

    /**
     * 保存用户对象的ThreadLocal
     */
    private static final ThreadLocal<SimpleUser> userThreadLocal = new ThreadLocal<>();

    /**
     * 保存用户对象的ThreadLocal
     */
    private static final InheritableThreadLocal<SimpleUser> inheritableUserThreadLocal = new InheritableThreadLocal<>();

    /**
     * 添加当前登录用户信息
     */
    public static void addCurrentUser(SimpleUser user) {
        userThreadLocal.set(user);
        inheritableUserThreadLocal.set(user);
    }

    /**
     * 获取当前登录用户信息
     */
    public static SimpleUser getCurrentUser() {
        SimpleUser user = userThreadLocal.get();
        if (Objects.isNull(user)) {
            return getParentCurrentUser();
        } else {
            return user;
        }
    }

    /**
     * 获取当前登录用户信息
     */
    public static SimpleUser getParentCurrentUser() {
        return inheritableUserThreadLocal.get();
    }

    /**
     * 防止内存泄漏
     */
    public static void remove() {
        userThreadLocal.remove();
        inheritableUserThreadLocal.remove();
    }
}
