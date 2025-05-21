
package com.porn.client.imglib.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class ImageLibSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("图片路径")
     private String imgPath;

    @ApiModelProperty("图片全路径")
     private String imgPathUrl;

    @ApiModelProperty("图片类型ImageTypeEnum, 0-商户, 1-账户")
     private Integer imageType;


    /* 15 */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setImgPathUrl(String imgPathUrl) {
        this.imgPathUrl = imgPathUrl;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ImageLibSaveOrUpdateDTO;
    }



    /* 16 */
    protected ImageLibSaveOrUpdateDTO(ImageLibSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.imgPath = b.imgPath;
        this.imgPathUrl = b.imgPathUrl;
        this.imageType = b.imageType;
    }

    public static ImageLibSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new ImageLibSaveOrUpdateDTOBuilderImpl();
    }

    private static final class ImageLibSaveOrUpdateDTOBuilderImpl extends ImageLibSaveOrUpdateDTOBuilder<ImageLibSaveOrUpdateDTO, ImageLibSaveOrUpdateDTOBuilderImpl> {
        private ImageLibSaveOrUpdateDTOBuilderImpl() {
        }

        protected ImageLibSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public ImageLibSaveOrUpdateDTO build() {
            return new ImageLibSaveOrUpdateDTO(this);
        }
    }

    public static abstract class ImageLibSaveOrUpdateDTOBuilder<C extends ImageLibSaveOrUpdateDTO, B extends ImageLibSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String imgPath;

        public B imgPath(String imgPath) {
            this.imgPath = imgPath;
            return self();
        }

        private String imgPathUrl;
        private Integer imageType;

        public B imgPathUrl(String imgPathUrl) {
            this.imgPathUrl = imgPathUrl;
            return self();
        }

        public B imageType(Integer imageType) {
            this.imageType = imageType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public ImageLibSaveOrUpdateDTO(String imgPath, String imgPathUrl, Integer imageType) {
        /* 17 */
        this.imgPath = imgPath;
        this.imgPathUrl = imgPathUrl;
        this.imageType = imageType;

    }


    public ImageLibSaveOrUpdateDTO() {
    }



    public String getImgPath() {
        /* 22 */
        return this.imgPath;

    }


    public String getImgPathUrl() {
        /* 25 */
        return this.imgPathUrl;

    }


    public Integer getImageType() {
        /* 28 */
        return this.imageType;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/imglib/dto/ImageLibSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */