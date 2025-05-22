
package com.porn.service.mobile.encrypt;


























 public interface EncryptService
         {
       String decrypt(String paramString1, String paramString2);


       String encrypt(String paramString1, String paramString2);



    default String getType() {
        /* 34 */
        return "";

    }

}


