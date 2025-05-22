
package com.porn.service.common.utils;



import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.useragent.UserAgent;
















 public class ClientUtil
         {

    public static boolean isBadClient(UserAgent userAgent) {
        /* 21 */
        if (ObjectUtil.isEmpty(userAgent)) {
            /* 22 */
            return false;

        }

        /* 25 */
        if (!userAgent.isMobile() && !userAgent.getPlatform().isUnknown()) {
            /* 26 */
            return true;

        }

        /* 29 */
        if (userAgent.isMobile() && userAgent
/* 30 */.getPlatform().isAndroid()) {
            /* 31 */
            return true;

        }
        /* 33 */
        return false;

    }

}


