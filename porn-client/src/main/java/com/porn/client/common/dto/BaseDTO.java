package com.porn.client.common.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public abstract class BaseDTO
        extends AbstractDTO
        implements Serializable {

    @ApiModelProperty("主键")
    private Long id;

    protected BaseDTO(BaseDTOBuilder<?, ?> b) {
        super(b);
        this.id = b.id;
    }

    public BaseDTO(Long id) {

        this.id = id;

    }

    public BaseDTO() {
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseDTO;
    }

    public Long getId() {

        return this.id;

    }

    public void setId(Long id) {

        this.id = id;
    }

    public static abstract class BaseDTOBuilder<C extends BaseDTO, B extends BaseDTOBuilder<C, B>> extends AbstractDTO.AbstractDTOBuilder<C, B> {
        private Long id;

        public B id(Long id) {
            this.id = id;
            return self();
        }

        protected abstract B self();

        public abstract C build();
    }

}

