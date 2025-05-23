package com.porn.client.nickname.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class NickNameUpdateStatusDTO
        extends BaseDTO {

    @ApiModelProperty("id列表")
    private List<Long> idList;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer status;

    protected NickNameUpdateStatusDTO(NickNameUpdateStatusDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
        this.status = b.status;
    }

    public NickNameUpdateStatusDTO(List<Long> idList, Integer status) {

        this.idList = idList;
        this.status = status;

    }

    public NickNameUpdateStatusDTO() {
    }

    public static NickNameUpdateStatusDTOBuilder<?, ?> builder() {
        return new NickNameUpdateStatusDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NickNameUpdateStatusDTO;
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

    private static final class NickNameUpdateStatusDTOBuilderImpl extends NickNameUpdateStatusDTOBuilder<NickNameUpdateStatusDTO, NickNameUpdateStatusDTOBuilderImpl> {
        private NickNameUpdateStatusDTOBuilderImpl() {
        }

        protected NickNameUpdateStatusDTOBuilderImpl self() {
            return this;
        }

        public NickNameUpdateStatusDTO build() {
            return new NickNameUpdateStatusDTO(this);
        }
    }

    public static abstract class NickNameUpdateStatusDTOBuilder<C extends NickNameUpdateStatusDTO, B extends NickNameUpdateStatusDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
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

