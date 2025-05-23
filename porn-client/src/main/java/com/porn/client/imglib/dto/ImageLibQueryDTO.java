package com.porn.client.imglib.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class ImageLibQueryDTO extends BaseDTO {

    @ApiModelProperty("图片路径")
    private String imgPath;

    @ApiModelProperty("图片类型ImageTypeEnum, 0-商户, 1-账户")
    private Integer imageType;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer status;

    protected ImageLibQueryDTO(ImageLibQueryDTOBuilder<?, ?> b) {
        super(b);
        this.imgPath = b.imgPath;
        this.imageType = b.imageType;
        this.status = b.status;
    }

    public ImageLibQueryDTO(String imgPath, Integer imageType, Integer status) {

        this.imgPath = imgPath;
        this.imageType = imageType;
        this.status = status;

    }

    public ImageLibQueryDTO() {
    }

    public static ImageLibQueryDTOBuilder<?, ?> builder() {
        return new ImageLibQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ImageLibQueryDTO;
    }

    public String getImgPath() {

        return this.imgPath;

    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getImageType() {

        return this.imageType;

    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
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
        private Integer imageType;
        private Integer status;

        public B imgPath(String imgPath) {
            this.imgPath = imgPath;
            return self();
        }

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

}

