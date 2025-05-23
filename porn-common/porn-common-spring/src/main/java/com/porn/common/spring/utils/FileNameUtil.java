package com.porn.common.spring.utils;

import cn.hutool.core.util.StrUtil;

import java.net.URLEncoder;


public class FileNameUtil {
    public static String encodeDownFileName(String fileName, String agent) throws Exception {
        String codedfilename = null;
        if (null != agent && -1 != agent.indexOf("MSIE")) {
            String prefix = (fileName.lastIndexOf(".") != -1) ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
            String extension = (fileName.lastIndexOf(".") != -1) ? fileName.substring(fileName.lastIndexOf(".")) : "";
            String name = URLEncoder.encode(prefix, "UTF8");
            if (name.lastIndexOf("%0A") != -1) {
                name = name.substring(0, name.length() - 3);
            }
            codedfilename = name + extension;
        } else if (null != agent && -1 != agent.indexOf("Chrome")) {
            codedfilename = URLEncoder.encode(fileName, "UTF-8");
            codedfilename = StrUtil.replace(codedfilename, "+", "%20");
            codedfilename = StrUtil.replace(codedfilename, "%28", "(").replace("%29", ")");
            codedfilename = StrUtil.replace(codedfilename, "%7B", "{").replace("%7D", "}");
        } else {
            if (null != agent && -1 != agent.indexOf("Mozilla")) {
                return new String(fileName.getBytes("gbk"), "iso8859-1");
            }
            codedfilename = fileName;
        }

        return codedfilename;
    }
}

