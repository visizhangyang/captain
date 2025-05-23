package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MultistageLevelFreeApiRequestDTO
        implements Serializable {

    @ApiModelProperty("1-一级, 2-二级, 3-三级")
    private Integer levelType;

    protected MultistageLevelFreeApiRequestDTO(MultistageLevelFreeApiRequestDTOBuilder<?, ?> b) {
        this.levelType = b.levelType;
    }

    public MultistageLevelFreeApiRequestDTO(Integer levelType) {

        this.levelType = levelType;

    }

    public MultistageLevelFreeApiRequestDTO() {
    }

    public static MultistageLevelFreeApiRequestDTOBuilder<?, ?> builder() {
        return new MultistageLevelFreeApiRequestDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MultistageLevelFreeApiRequestDTO;
    }

    public Integer getLevelType() {

        return this.levelType;

    }

    public void setLevelType(Integer levelType) {

        this.levelType = levelType;
    }

    private static final class MultistageLevelFreeApiRequestDTOBuilderImpl extends MultistageLevelFreeApiRequestDTOBuilder<MultistageLevelFreeApiRequestDTO, MultistageLevelFreeApiRequestDTOBuilderImpl> {
        private MultistageLevelFreeApiRequestDTOBuilderImpl() {
        }

        protected MultistageLevelFreeApiRequestDTOBuilderImpl self() {
            return this;
        }

        public MultistageLevelFreeApiRequestDTO build() {
            return new MultistageLevelFreeApiRequestDTO(this);
        }
    }

    public static abstract class MultistageLevelFreeApiRequestDTOBuilder<C extends MultistageLevelFreeApiRequestDTO, B extends MultistageLevelFreeApiRequestDTOBuilder<C, B>> {
        private Integer levelType;

        public B levelType(Integer levelType) {
            this.levelType = levelType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

