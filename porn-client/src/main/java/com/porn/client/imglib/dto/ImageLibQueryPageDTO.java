
package com.porn.client.imglib.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;





 public class ImageLibQueryPageDTO
         extends BasePageDTO {
    
    @ApiModelProperty("图片类型ImageTypeEnum, 0-商户, 1-账户")
     private Integer imageType;
    
    @ApiModelProperty("使用状态, 1-已使用, 0-未被使用 com.porn.client.common.enums.EnableStatusEnum")
     private Integer status;

    
    
    public void setImageType(Integer imageType) {
        /* 16 */
        this.imageType = imageType;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ImageLibQueryPageDTO;
    }



    /* 17 */
    protected ImageLibQueryPageDTO(ImageLibQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.imageType = b.imageType;
        this.status = b.status;
    }

    public static ImageLibQueryPageDTOBuilder<?, ?> builder() {
        return new ImageLibQueryPageDTOBuilderImpl();
    }

    private static final class ImageLibQueryPageDTOBuilderImpl extends ImageLibQueryPageDTOBuilder<ImageLibQueryPageDTO, ImageLibQueryPageDTOBuilderImpl> {
        private ImageLibQueryPageDTOBuilderImpl() {
        }

        protected ImageLibQueryPageDTOBuilderImpl self() {
            return this;
        }

        public ImageLibQueryPageDTO build() {
            return new ImageLibQueryPageDTO(this);
        }
    }

    public static abstract class ImageLibQueryPageDTOBuilder<C extends ImageLibQueryPageDTO, B extends ImageLibQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Integer imageType;

        public B imageType(Integer imageType) {
            this.imageType = imageType;
            return self();
        }

        private Integer status;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

        
    }

    public ImageLibQueryPageDTO() {
    }

    public ImageLibQueryPageDTO(Integer imageType, Integer status) {
        /* 19 */
        this.imageType = imageType;
        this.status = status;
        
    }

    
    
    public Integer getImageType() {
        /* 23 */
        return this.imageType;
        
    }

    
    public Integer getStatus() {
        /* 26 */
        return this.status;
        
    }
    
}


