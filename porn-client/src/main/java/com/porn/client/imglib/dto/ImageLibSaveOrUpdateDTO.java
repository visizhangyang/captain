package com.porn.client.imglib.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class ImageLibSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("图片路径")
    private String imgPath;

    @ApiModelProperty("图片全路径")
    private String imgPathUrl;

    @ApiModelProperty("图片类型ImageTypeEnum, 0-商户, 1-账户")
    private Integer imageType;

    protected ImageLibSaveOrUpdateDTO(ImageLibSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.imgPath = b.imgPath;
        this.imgPathUrl = b.imgPathUrl;
        this.imageType = b.imageType;
    }

    public ImageLibSaveOrUpdateDTO(String imgPath, String imgPathUrl, Integer imageType) {

        this.imgPath = imgPath;
        this.imgPathUrl = imgPathUrl;
        this.imageType = imageType;

    }

    public ImageLibSaveOrUpdateDTO() {
    }

    public static ImageLibSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new ImageLibSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ImageLibSaveOrUpdateDTO;
    }

    public String getImgPath() {

        return this.imgPath;

    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPathUrl() {

        return this.imgPathUrl;

    }

    public void setImgPathUrl(String imgPathUrl) {
        this.imgPathUrl = imgPathUrl;
    }

    public Integer getImageType() {

        return this.imageType;

    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
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
        private String imgPathUrl;
        private Integer imageType;

        public B imgPath(String imgPath) {
            this.imgPath = imgPath;
            return self();
        }

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

}

