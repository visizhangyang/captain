package com.porn.client.imglib.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ImageLibBatchDeleteDTO
        extends BaseDTO {

    @ApiModelProperty("id列表")
    private List<Long> idList;

    protected ImageLibBatchDeleteDTO(ImageLibBatchDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public ImageLibBatchDeleteDTO(List<Long> idList) {

        this.idList = idList;

    }

    public ImageLibBatchDeleteDTO() {
    }

    public static ImageLibBatchDeleteDTOBuilder<?, ?> builder() {
        return new ImageLibBatchDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ImageLibBatchDeleteDTO;
    }

    public List<Long> getIdList() {

        return this.idList;

    }

    public void setIdList(List<Long> idList) {

        this.idList = idList;
    }

    private static final class ImageLibBatchDeleteDTOBuilderImpl extends ImageLibBatchDeleteDTOBuilder<ImageLibBatchDeleteDTO, ImageLibBatchDeleteDTOBuilderImpl> {
        private ImageLibBatchDeleteDTOBuilderImpl() {
        }

        protected ImageLibBatchDeleteDTOBuilderImpl self() {
            return this;
        }

        public ImageLibBatchDeleteDTO build() {
            return new ImageLibBatchDeleteDTO(this);
        }
    }

    public static abstract class ImageLibBatchDeleteDTOBuilder<C extends ImageLibBatchDeleteDTO, B extends ImageLibBatchDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

