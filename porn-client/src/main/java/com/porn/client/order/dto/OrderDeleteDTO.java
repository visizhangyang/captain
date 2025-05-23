package com.porn.client.order.dto;

import com.porn.client.common.dto.BaseDTO;

public class OrderDeleteDTO
        extends BaseDTO {

    protected OrderDeleteDTO(OrderDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public OrderDeleteDTO() {
    }

    public static OrderDeleteDTOBuilder<?, ?> builder() {
        return new OrderDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OrderDeleteDTO;
    }

    private static final class OrderDeleteDTOBuilderImpl extends OrderDeleteDTOBuilder<OrderDeleteDTO, OrderDeleteDTOBuilderImpl> {
        private OrderDeleteDTOBuilderImpl() {
        }

        protected OrderDeleteDTOBuilderImpl self() {
            return this;
        }

        public OrderDeleteDTO build() {
            return new OrderDeleteDTO(this);
        }
    }

    public static abstract class OrderDeleteDTOBuilder<C extends OrderDeleteDTO, B extends OrderDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

