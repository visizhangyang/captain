package com.porn.client.imglib.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ImageLibUpdateStatusDTO extends BaseDTO {

    @ApiModelProperty("id列表")
    private List<Long> idList;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer status;

    protected ImageLibUpdateStatusDTO(ImageLibUpdateStatusDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
        this.status = b.status;
    }

    public ImageLibUpdateStatusDTO(List<Long> idList, Integer status) {

        this.idList = idList;
        this.status = status;

    }

    public ImageLibUpdateStatusDTO() {
    }

    public static ImageLibUpdateStatusDTOBuilder<?, ?> builder() {
        return new ImageLibUpdateStatusDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ImageLibUpdateStatusDTO;
    }

    public List<Long> getIdList() {

        return this.idList;

    }

    public void setIdList(List<Long> idList) {

        this.idList = idList;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private static final class ImageLibUpdateStatusDTOBuilderImpl extends ImageLibUpdateStatusDTOBuilder<ImageLibUpdateStatusDTO, ImageLibUpdateStatusDTOBuilderImpl> {
        private ImageLibUpdateStatusDTOBuilderImpl() {
        }

        protected ImageLibUpdateStatusDTOBuilderImpl self() {
            return this;
        }

        public ImageLibUpdateStatusDTO build() {
            return new ImageLibUpdateStatusDTO(this);
        }
    }

    public static abstract class ImageLibUpdateStatusDTOBuilder<C extends ImageLibUpdateStatusDTO, B extends ImageLibUpdateStatusDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;
        private Integer status;

        public B idList(List<Long> idList) {
            this.idList = idList;
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

