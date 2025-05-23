package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MobileExtDTO
        implements Serializable {

    @ApiModelProperty("登录IP")
    private String remoteIP;

    @ApiModelProperty("用户请求头")
    private String userAgent;

    public MobileExtDTO(String remoteIP, String userAgent) {

        this.remoteIP = remoteIP;
        this.userAgent = userAgent;

    }

    public MobileExtDTO() {
    }

    public static MobileExtDTOBuilder builder() {
        return new MobileExtDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MobileExtDTO;
    }

    public String getRemoteIP() {

        return this.remoteIP;

    }

    public void setRemoteIP(String remoteIP) {

        this.remoteIP = remoteIP;
    }

    public String getUserAgent() {

        return this.userAgent;

    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public static class MobileExtDTOBuilder {
        private String remoteIP;
        private String userAgent;

        public MobileExtDTOBuilder remoteIP(String remoteIP) {
            this.remoteIP = remoteIP;
            return this;
        }

        public MobileExtDTOBuilder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public MobileExtDTO build() {
            return new MobileExtDTO(this.remoteIP, this.userAgent);
        }

    }

}

