package com.porn.client.order.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class OrderEnableOrDisableDTO
        extends BaseDTO {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
    private Integer status;

    protected OrderEnableOrDisableDTO(OrderEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
    }

    public OrderEnableOrDisableDTO(Integer status) {

        this.status = status;

    }

    public OrderEnableOrDisableDTO() {
    }

    public static OrderEnableOrDisableDTOBuilder<?, ?> builder() {
        return new OrderEnableOrDisableDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OrderEnableOrDisableDTO;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {

        this.status = status;
    }

    private static final class OrderEnableOrDisableDTOBuilderImpl extends OrderEnableOrDisableDTOBuilder<OrderEnableOrDisableDTO, OrderEnableOrDisableDTOBuilderImpl> {
        private OrderEnableOrDisableDTOBuilderImpl() {
        }

        protected OrderEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public OrderEnableOrDisableDTO build() {
            return new OrderEnableOrDisableDTO(this);
        }
    }

    public static abstract class OrderEnableOrDisableDTOBuilder<C extends OrderEnableOrDisableDTO, B extends OrderEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer status;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

