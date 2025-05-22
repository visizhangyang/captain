
package com.porn.client.server.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.req.ServiceRequest;


import java.io.Serializable;











 public class GenCaptchaDTO
         extends ServiceRequest
         implements Serializable
         {
    
    public void setWidth(Integer width) {
        /* 18 */
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setLen(Integer len) {
        this.len = len;
    }


    protected boolean canEqual(Object other) {
        return other instanceof GenCaptchaDTO;
    }



    /* 19 */
    protected GenCaptchaDTO(GenCaptchaDTOBuilder<?, ?> b) {
        super(b);
        this.width = b.width;
        this.height = b.height;
        this.len = b.len;
    }

    public static GenCaptchaDTOBuilder<?, ?> builder() {
        return new GenCaptchaDTOBuilderImpl();
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

        public B width(Integer width) {
            this.width = width;
            return self();
        }

        private Integer height;
        private Integer len;

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

    public GenCaptchaDTO(Integer width, Integer height, Integer len) {
        /* 20 */
        this.width = width;
        this.height = height;
        this.len = len;
        
    }

    
    
    
    /* 25 */   public static final Integer DEFAULT_WIDTH = Integer.valueOf(120);
    /* 26 */   public static final Integer DEFAULT_HEIGHT = Integer.valueOf(40);
    /* 27 */   public static final Integer DEFAULT_LEN = Integer.valueOf(6);
    
    @ApiModelProperty("宽度")
    /* 29 */ private Integer width = DEFAULT_WIDTH;

    public Integer getWidth() {
        /* 30 */
        return this.width;
        
    }

    @ApiModelProperty("高度")
    /* 32 */ private Integer height = DEFAULT_HEIGHT;

    public Integer getHeight() {
        /* 33 */
        return this.height;
        
    }

    @ApiModelProperty("长度")
    /* 35 */ private Integer len = DEFAULT_LEN;

    public Integer getLen() {
        /* 36 */
        return this.len;
        
    }

    
    
    public GenCaptchaDTO() {
    }
    
}


