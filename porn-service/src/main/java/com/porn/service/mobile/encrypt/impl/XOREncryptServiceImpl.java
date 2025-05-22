
package com.porn.service.mobile.encrypt.impl;



import com.porn.client.mobile.enums.EncryptTypeEnum;
import com.porn.common.statics.utils.XORUtil;
import com.porn.service.mobile.encrypt.EncryptService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;













@Service
 public class XOREncryptServiceImpl
         implements EncryptService
         {
       private static final String PWD = "csdMVlkfmJbxaX+%489325,/411111^&&#~2";



    public String decrypt(String type, String srcData) {
        /* 21 */
        return new String(XORUtil.deXorEncry(srcData.getBytes(StandardCharsets.UTF_8), "csdMVlkfmJbxaX+%489325,/411111^&&#~2"), StandardCharsets.UTF_8);

    }



    public String encrypt(String type, String srcData) {
        /* 25 */
        return new String(XORUtil.deXorEncry(srcData.getBytes(StandardCharsets.UTF_8), "csdMVlkfmJbxaX+%489325,/411111^&&#~2"), StandardCharsets.UTF_8);

    }



    public String getType() {
        /* 29 */
        return EncryptTypeEnum.XOR.toString();

    }

}
