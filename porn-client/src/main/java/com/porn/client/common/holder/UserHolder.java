package com.porn.client.common.holder;

import com.porn.client.user.vo.UserLoginVo;

public class UserHolder {
    private static final ThreadLocal<UserLoginVo> userHolder = new ThreadLocal<>();

    public static UserLoginVo getUser() {

        return userHolder.get();

    }

    public static void setUser(UserLoginVo user) {

        userHolder.set(user);

    }

    public static void removeUser() {

        userHolder.remove();

    }

}

