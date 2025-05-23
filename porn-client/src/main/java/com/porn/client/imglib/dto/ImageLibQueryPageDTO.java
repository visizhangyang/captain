package com.porn.client.imglib.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class ImageLibQueryPageDTO
        extends BasePageDTO {

    @ApiModelProperty("图片类型ImageTypeEnum, 0-商户, 1-账户")
    private Integer imageType;

    @ApiModelProperty("使用状态, 1-已使用, 0-未被使用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer status;

    protected ImageLibQueryPageDTO(ImageLibQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.imageType = b.imageType;
        this.status = b.status;
    }

    public ImageLibQueryPageDTO() {
    }

    public ImageLibQueryPageDTO(Integer imageType, Integer status) {

        this.imageType = imageType;
        this.status = status;

    }

    public static ImageLibQueryPageDTOBuilder<?, ?> builder() {
        return new ImageLibQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ImageLibQueryPageDTO;
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

}

