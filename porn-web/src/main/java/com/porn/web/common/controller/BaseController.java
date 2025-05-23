package com.porn.web.common.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.common.spring.utils.FileNameUtil;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URLEncoder;


public abstract class BaseController {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public Cookie getCookie(String name) {
        if (ObjectUtil.isEmpty(name)) {
            return null;
        }
        HttpServletRequest req = getRequest();
        Cookie[] cookies = req.getCookies();
        if (ObjectUtil.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (name.trim().equalsIgnoreCase(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public String getCookieValue(String name) {
        Cookie cookie = getCookie(name);
        return ObjectUtil.isEmpty(cookie) ? null : cookie.getValue();
    }


    public String getRemoteIP() {
        HttpServletRequest req = getRequest();
        String ipAddress = null;
        try {
            ipAddress = this.request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = this.request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = this.request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = this.request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {

                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (Exception exception) {
                    }


                    ipAddress = inet.getHostAddress();
                }
            }
            if (ipAddress != null && ipAddress.length() > 15 &&
                    ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        } catch (Exception e) {
            ipAddress = getRequest().getRemoteAddr();
        }
        return ipAddress;
    }


    public void downloadFile(String fileName, byte[] bytes) throws Exception {
        ServletOutputStream servletOutputStream = null;
        OutputStream out = null;
        try {
            getResponse().reset();
            getResponse().setCharacterEncoding("UTF-8");
            getResponse().setContentType("application/x-download");
            getResponse().setHeader("filename", URLEncoder.encode(fileName, "UTF-8"));
            getResponse().setHeader("Content-disposition", "attachment; filename*=utf-8'zh_cn'" + FileNameUtil.encodeDownFileName(fileName, getRequest().getHeader("User-Agent")));
            servletOutputStream = getResponse().getOutputStream();
            servletOutputStream.write(bytes);
            servletOutputStream.flush();
        } finally {
            IoUtil.close(servletOutputStream);
        }
    }


    public void doPrev(String contentType, byte[] bytes) throws Exception {
        ServletOutputStream servletOutputStream = null;
        OutputStream out = null;
        try {
            getResponse().reset();
            getResponse().setHeader("Pragma", "no-cache");
            getResponse().setHeader("Cache-Control", "no-cache");
            getResponse().setDateHeader("Expires", 0L);
            getResponse().setHeader("Content-Type", contentType);
            servletOutputStream = getResponse().getOutputStream();
            servletOutputStream.write(bytes);
            servletOutputStream.flush();
        } finally {
            IoUtil.close((Closeable) servletOutputStream);
        }
    }

    @ModelAttribute
    public void setReqAndRsp(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
}

