package com.porn.common.statics.utils;


public class XORUtil {
    public static byte[] deXorEncry(byte[] data, String pwd) {
        if (null == data || pwd == null) {
            throw new IllegalArgumentException("data or pwd is null.");
        }
        byte[] result = new byte[data.length];
        char[] pswArray = pwd.toCharArray();
        int dwPwdLength = pswArray.length;
        for (int i = 0, j = 0; data.length != 0 && i < data.length; i++, j++) {
            if (j >= dwPwdLength) {
                j = 0;
            }
            result[i] = (byte) (data[i] ^ pswArray[j]);
        }
        return result;
    }
}

