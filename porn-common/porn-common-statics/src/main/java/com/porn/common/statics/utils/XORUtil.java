 package com.porn.common.statics.utils;
 
 
 
 
 
 
 
 
 
 
 
 
 public class XORUtil
 {
   public static byte[] deXorEncry(byte[] data, String pwd) {
/* 17 */     if (null == data || pwd == null) {
/* 18 */       throw new IllegalArgumentException("data or pwd is null.");
     }
/* 20 */     byte[] result = new byte[data.length];
/* 21 */     char[] pswArray = pwd.toCharArray();
/* 22 */     int dwPwdLength = pswArray.length;
/* 23 */     for (int i = 0, j = 0; data.length != 0 && i < data.length; i++, j++) {
/* 24 */       if (j >= dwPwdLength) {
/* 25 */         j = 0;
       }
/* 27 */       result[i] = (byte)(data[i] ^ pswArray[j]);
     } 
/* 29 */     return result;
   }
 }


