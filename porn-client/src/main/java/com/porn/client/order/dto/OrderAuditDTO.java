package com.porn.client.order.dto;

import com.porn.client.common.dto.BaseDTO;

public class OrderAuditDTO
        extends BaseDTO {

    protected OrderAuditDTO(OrderAuditDTOBuilder<?, ?> b) {
        super(b);
    }

    public OrderAuditDTO() {
    }

    public static OrderAuditDTOBuilder<?, ?> builder() {
        return new OrderAuditDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OrderAuditDTO;
    }

    private static final class OrderAuditDTOBuilderImpl extends OrderAuditDTOBuilder<OrderAuditDTO, OrderAuditDTOBuilderImpl> {
        private OrderAuditDTOBuilderImpl() {
        }

        protected OrderAuditDTOBuilderImpl self() {
            return this;
        }

        public OrderAuditDTO build() {
            return new OrderAuditDTO(this);
        }
    }

    public static abstract class OrderAuditDTOBuilder<C extends OrderAuditDTO, B extends OrderAuditDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

