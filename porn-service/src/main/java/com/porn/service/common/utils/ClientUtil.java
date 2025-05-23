package com.porn.service.common.utils;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.useragent.UserAgent;

public class ClientUtil {

    public static boolean isBadClient(UserAgent userAgent) {

        if (ObjectUtil.isEmpty(userAgent)) {

            return false;

        }

        if (!userAgent.isMobile() && !userAgent.getPlatform().isUnknown()) {

            return true;

        }

        if (userAgent.isMobile() && userAgent
                .getPlatform().isAndroid()) {

            return true;

        }

        return false;

    }

}

