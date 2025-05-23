package com.porn.client.order.dto;

import com.porn.client.common.dto.BaseDTO;

public class OrderFreezeDTO
        extends BaseDTO {

    protected OrderFreezeDTO(OrderFreezeDTOBuilder<?, ?> b) {
        super(b);
    }

    public OrderFreezeDTO() {
    }

    public static OrderFreezeDTOBuilder<?, ?> builder() {
        return new OrderFreezeDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OrderFreezeDTO;
    }

    private static final class OrderFreezeDTOBuilderImpl extends OrderFreezeDTOBuilder<OrderFreezeDTO, OrderFreezeDTOBuilderImpl> {
        private OrderFreezeDTOBuilderImpl() {
        }

        protected OrderFreezeDTOBuilderImpl self() {
            return this;
        }

        public OrderFreezeDTO build() {
            return new OrderFreezeDTO(this);
        }
    }

    public static abstract class OrderFreezeDTOBuilder<C extends OrderFreezeDTO, B extends OrderFreezeDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

