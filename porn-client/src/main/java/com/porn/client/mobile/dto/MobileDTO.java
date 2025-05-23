package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MobileDTO implements Serializable {

    @ApiModelProperty("类型, EncryptTypeEnum")
    private Integer type;

    @ApiModelProperty("请求方法体")
    private String body;

    @ApiModelProperty("扩展数据")
    private MobileExtDTO mobileExtDTO;

    public MobileDTO(Integer type, String body, MobileExtDTO mobileExtDTO) {

        this.type = type;
        this.body = body;
        this.mobileExtDTO = mobileExtDTO;

    }

    public MobileDTO() {
    }

    public static MobileDTOBuilder builder() {
        return new MobileDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MobileDTO;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {

        this.type = type;
    }

    public String getBody() {

        return this.body;

    }

    public void setBody(String body) {
        this.body = body;
    }

    public MobileExtDTO getMobileExtDTO() {

        return this.mobileExtDTO;

    }

    public void setMobileExtDTO(MobileExtDTO mobileExtDTO) {
        this.mobileExtDTO = mobileExtDTO;
    }

    public static class MobileDTOBuilder {
        private Integer type;
        private String body;
        private MobileExtDTO mobileExtDTO;

        public MobileDTOBuilder type(Integer type) {
            this.type = type;
            return this;
        }

        public MobileDTOBuilder body(String body) {
            this.body = body;
            return this;
        }

        public MobileDTOBuilder mobileExtDTO(MobileExtDTO mobileExtDTO) {
            this.mobileExtDTO = mobileExtDTO;
            return this;
        }

        public MobileDTO build() {
            return new MobileDTO(this.type, this.body, this.mobileExtDTO);
        }

    }

}

