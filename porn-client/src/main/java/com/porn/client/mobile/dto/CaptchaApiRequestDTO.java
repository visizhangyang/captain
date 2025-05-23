package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CaptchaApiRequestDTO
        implements Serializable {

    public static final Integer DEFAULT_WIDTH = Integer.valueOf(120);
    public static final Integer DEFAULT_HEIGHT = Integer.valueOf(40);
    public static final Integer DEFAULT_LEN = Integer.valueOf(4);
    private Integer type;
    @ApiModelProperty("宽度")
    private Integer width;
    @ApiModelProperty("高度")
    private Integer height;
    @ApiModelProperty("长度")
    private Integer len;

    protected CaptchaApiRequestDTO(CaptchaApiRequestDTOBuilder<?, ?> b) {

        this.width = DEFAULT_WIDTH;

        this.height = DEFAULT_HEIGHT;

        this.len = DEFAULT_LEN;
        this.type = b.type;
        this.width = b.width;
        this.height = b.height;
        this.len = b.len;
    }

    public CaptchaApiRequestDTO(Integer type, Integer width, Integer height, Integer len) {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.len = DEFAULT_LEN;
        this.type = type;
        this.width = width;
        this.height = height;
        this.len = len;
    }

    public CaptchaApiRequestDTO() {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.len = DEFAULT_LEN;
    }

    public static CaptchaApiRequestDTOBuilder<?, ?> builder() {
        return new CaptchaApiRequestDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof CaptchaApiRequestDTO;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {

        this.type = type;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLen() {
        return this.len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    private static final class CaptchaApiRequestDTOBuilderImpl extends CaptchaApiRequestDTOBuilder<CaptchaApiRequestDTO, CaptchaApiRequestDTOBuilderImpl> {
        private CaptchaApiRequestDTOBuilderImpl() {
        }

        protected CaptchaApiRequestDTOBuilderImpl self() {
            return this;
        }

        public CaptchaApiRequestDTO build() {
            return new CaptchaApiRequestDTO(this);
        }
    }

    public static abstract class CaptchaApiRequestDTOBuilder<C extends CaptchaApiRequestDTO, B extends CaptchaApiRequestDTOBuilder<C, B>> {
        private Integer type;
        private Integer width;
        private Integer height;
        private Integer len;

        public B type(Integer type) {
            this.type = type;

            return self();
        }

        public B width(Integer width) {
            this.width = width;

            return self();
        }

        public B height(Integer height) {
            this.height = height;

            return self();
        }

        public B len(Integer len) {

            this.len = len;

            return self();

        }

        protected abstract B self();

        public abstract C build();

    }

}

