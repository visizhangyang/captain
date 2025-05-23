package com.porn.client.order.dto;

import com.porn.client.common.dto.BaseDTO;

public class OrderTimeOutDTO
        extends BaseDTO {

    protected OrderTimeOutDTO(OrderTimeOutDTOBuilder<?, ?> b) {
        super(b);
    }

    public OrderTimeOutDTO() {
    }

    public static OrderTimeOutDTOBuilder<?, ?> builder() {
        return new OrderTimeOutDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OrderTimeOutDTO;
    }

    private static final class OrderTimeOutDTOBuilderImpl extends OrderTimeOutDTOBuilder<OrderTimeOutDTO, OrderTimeOutDTOBuilderImpl> {
        private OrderTimeOutDTOBuilderImpl() {
        }

        protected OrderTimeOutDTOBuilderImpl self() {
            return this;
        }

        public OrderTimeOutDTO build() {
            return new OrderTimeOutDTO(this);
        }
    }

    public static abstract class OrderTimeOutDTOBuilder<C extends OrderTimeOutDTO, B extends OrderTimeOutDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

