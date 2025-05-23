package com.porn.client.goods.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class GoodsDeleteDTO
        extends BaseDTO {

    @ApiModelProperty("批量删除")
    private List<Long> idList;

    protected GoodsDeleteDTO(GoodsDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public GoodsDeleteDTO() {
    }

    public GoodsDeleteDTO(List<Long> idList) {

        this.idList = idList;

    }

    public static GoodsDeleteDTOBuilder<?, ?> builder() {
        return new GoodsDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof GoodsDeleteDTO;
    }

    public List<Long> getIdList() {

        return this.idList;

    }

    public void setIdList(List<Long> idList) {

        this.idList = idList;
    }

    private static final class GoodsDeleteDTOBuilderImpl extends GoodsDeleteDTOBuilder<GoodsDeleteDTO, GoodsDeleteDTOBuilderImpl> {
        private GoodsDeleteDTOBuilderImpl() {
        }

        protected GoodsDeleteDTOBuilderImpl self() {
            return this;
        }

        public GoodsDeleteDTO build() {
            return new GoodsDeleteDTO(this);
        }
    }

    public static abstract class GoodsDeleteDTOBuilder<C extends GoodsDeleteDTO, B extends GoodsDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

