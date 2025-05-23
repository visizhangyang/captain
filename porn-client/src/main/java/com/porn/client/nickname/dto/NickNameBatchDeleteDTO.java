package com.porn.client.nickname.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class NickNameBatchDeleteDTO
        extends BaseDTO {

    @ApiModelProperty("id列表")
    private List<Long> idList;

    protected NickNameBatchDeleteDTO(NickNameBatchDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public NickNameBatchDeleteDTO(List<Long> idList) {

        this.idList = idList;

    }

    public NickNameBatchDeleteDTO() {
    }

    public static NickNameBatchDeleteDTOBuilder<?, ?> builder() {
        return new NickNameBatchDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NickNameBatchDeleteDTO;
    }

    public List<Long> getIdList() {

        return this.idList;

    }

    public void setIdList(List<Long> idList) {

        this.idList = idList;
    }

    private static final class NickNameBatchDeleteDTOBuilderImpl extends NickNameBatchDeleteDTOBuilder<NickNameBatchDeleteDTO, NickNameBatchDeleteDTOBuilderImpl> {
        private NickNameBatchDeleteDTOBuilderImpl() {
        }

        protected NickNameBatchDeleteDTOBuilderImpl self() {
            return this;
        }

        public NickNameBatchDeleteDTO build() {
            return new NickNameBatchDeleteDTO(this);
        }
    }

    public static abstract class NickNameBatchDeleteDTOBuilder<C extends NickNameBatchDeleteDTO, B extends NickNameBatchDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

