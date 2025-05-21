
package com.porn.client.imglib.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class ImageLibQueryDTO extends BaseDTO {

    @ApiModelProperty("图片路径")
     private String imgPath;

    @ApiModelProperty("图片类型ImageTypeEnum, 0-商户, 1-账户")
     private Integer imageType;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
     private Integer status;


    /* 15 */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ImageLibQueryDTO;
    }



    /* 16 */
    protected ImageLibQueryDTO(ImageLibQueryDTOBuilder<?, ?> b) {
        super(b);
        this.imgPath = b.imgPath;
        this.imageType = b.imageType;
        this.status = b.status;
    }

    public static ImageLibQueryDTOBuilder<?, ?> builder() {
        return new ImageLibQueryDTOBuilderImpl();
    }

    private static final class ImageLibQueryDTOBuilderImpl extends ImageLibQueryDTOBuilder<ImageLibQueryDTO, ImageLibQueryDTOBuilderImpl> {
        private ImageLibQueryDTOBuilderImpl() {
        }

        protected ImageLibQueryDTOBuilderImpl self() {
            return this;
        }

        public ImageLibQueryDTO build() {
            return new ImageLibQueryDTO(this);
        }
    }

    public static abstract class ImageLibQueryDTOBuilder<C extends ImageLibQueryDTO, B extends ImageLibQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String imgPath;

        public B imgPath(String imgPath) {
            this.imgPath = imgPath;
            return self();
        }

        private Integer imageType;
        private Integer status;

        public B imageType(Integer imageType) {
            this.imageType = imageType;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public ImageLibQueryDTO(String imgPath, Integer imageType, Integer status) {
        /* 17 */
        this.imgPath = imgPath;
        this.imageType = imageType;
        this.status = status;

    }


    public ImageLibQueryDTO() {
    }



    public String getImgPath() {
        /* 22 */
        return this.imgPath;

    }


    public Integer getImageType() {
        /* 25 */
        return this.imageType;

    }


    public Integer getStatus() {
        /* 28 */
        return this.status;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/imglib/dto/ImageLibQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */