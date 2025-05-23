package com.porn.client.server.dto;

import com.porn.client.common.req.ServiceRequest;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class GenCaptchaDTO
        extends ServiceRequest
        implements Serializable {

    public static final Integer DEFAULT_WIDTH = Integer.valueOf(120);
    public static final Integer DEFAULT_HEIGHT = Integer.valueOf(40);
    public static final Integer DEFAULT_LEN = Integer.valueOf(6);
    @ApiModelProperty("宽度")
    private Integer width = DEFAULT_WIDTH;
    @ApiModelProperty("高度")
    private Integer height = DEFAULT_HEIGHT;
    @ApiModelProperty("长度")
    private Integer len = DEFAULT_LEN;

    protected GenCaptchaDTO(GenCaptchaDTOBuilder<?, ?> b) {
        super(b);
        this.width = b.width;
        this.height = b.height;
        this.len = b.len;
    }

    public GenCaptchaDTO(Integer width, Integer height, Integer len) {

        this.width = width;
        this.height = height;
        this.len = len;

    }

    public GenCaptchaDTO() {
    }

    public static GenCaptchaDTOBuilder<?, ?> builder() {
        return new GenCaptchaDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof GenCaptchaDTO;
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

    private static final class GenCaptchaDTOBuilderImpl extends GenCaptchaDTOBuilder<GenCaptchaDTO, GenCaptchaDTOBuilderImpl> {
        private GenCaptchaDTOBuilderImpl() {
        }

        protected GenCaptchaDTOBuilderImpl self() {
            return this;
        }

        public GenCaptchaDTO build() {
            return new GenCaptchaDTO(this);
        }
    }

    public static abstract class GenCaptchaDTOBuilder<C extends GenCaptchaDTO, B extends GenCaptchaDTOBuilder<C, B>> extends ServiceRequest.ServiceRequestBuilder<C, B> {
        private Integer width;
        private Integer height;
        private Integer len;

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

