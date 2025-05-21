 package com.porn.common.spring.utils;

 import cn.hutool.core.util.StrUtil;
 import java.net.URLEncoder;















 public class FileNameUtil
 {
   public static String encodeDownFileName(String fileName, String agent) throws Exception {
/* 23 */     String codedfilename = null;
/* 24 */     if (null != agent && -1 != agent.indexOf("MSIE"))

/* 26 */     { String prefix = (fileName.lastIndexOf(".") != -1) ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
/* 27 */       String extension = (fileName.lastIndexOf(".") != -1) ? fileName.substring(fileName.lastIndexOf(".")) : "";
/* 28 */       String name = URLEncoder.encode(prefix, "UTF8");
/* 29 */       if (name.lastIndexOf("%0A") != -1) {
/* 30 */         name = name.substring(0, name.length() - 3);
       }
/* 32 */       codedfilename = name + extension; }
/* 33 */     else if (null != agent && -1 != agent.indexOf("Chrome"))

/* 35 */     { codedfilename = URLEncoder.encode(fileName, "UTF-8");
/* 36 */       codedfilename = StrUtil.replace(codedfilename, "+", "%20");
/* 37 */       codedfilename = StrUtil.replace(codedfilename, "%28", "(").replace("%29", ")");
/* 38 */       codedfilename = StrUtil.replace(codedfilename, "%7B", "{").replace("%7D", "}"); }
/* 39 */     else { if (null != agent && -1 != agent.indexOf("Mozilla")) {
/* 40 */         return new String(fileName.getBytes("gbk"), "iso8859-1");
       }
/* 42 */       codedfilename = fileName; }

/* 44 */     return codedfilename;
   }
 }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-common-spring-3.3.0.jar!/com/porn/common/spring/utils/FileNameUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */