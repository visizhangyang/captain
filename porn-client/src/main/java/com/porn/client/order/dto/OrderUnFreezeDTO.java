package com.porn.client.order.dto;

import com.porn.client.common.dto.BaseDTO;

public class OrderUnFreezeDTO
        extends BaseDTO {

    protected OrderUnFreezeDTO(OrderUnFreezeDTOBuilder<?, ?> b) {
        super(b);
    }

    public OrderUnFreezeDTO() {
    }

    public static OrderUnFreezeDTOBuilder<?, ?> builder() {
        return new OrderUnFreezeDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OrderUnFreezeDTO;
    }

    private static final class OrderUnFreezeDTOBuilderImpl extends OrderUnFreezeDTOBuilder<OrderUnFreezeDTO, OrderUnFreezeDTOBuilderImpl> {
        private OrderUnFreezeDTOBuilderImpl() {
        }

        protected OrderUnFreezeDTOBuilderImpl self() {
            return this;
        }

        public OrderUnFreezeDTO build() {
            return new OrderUnFreezeDTO(this);
        }
    }

    public static abstract class OrderUnFreezeDTOBuilder<C extends OrderUnFreezeDTO, B extends OrderUnFreezeDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

