package com.porn.client.order.dto;

import java.io.Serializable;

public class OrderStatisticsDTO
        implements Serializable {

    protected OrderStatisticsDTO(OrderStatisticsDTOBuilder<?, ?> b) {
    }

    public static OrderStatisticsDTOBuilder<?, ?> builder() {
        return new OrderStatisticsDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OrderStatisticsDTO;
    }

    private static final class OrderStatisticsDTOBuilderImpl extends OrderStatisticsDTOBuilder<OrderStatisticsDTO, OrderStatisticsDTOBuilderImpl> {
        private OrderStatisticsDTOBuilderImpl() {
        }

        protected OrderStatisticsDTOBuilderImpl self() {
            return this;
        }

        public OrderStatisticsDTO build() {
            return new OrderStatisticsDTO(this);
        }
    }

    public static abstract class OrderStatisticsDTOBuilder<C extends OrderStatisticsDTO, B extends OrderStatisticsDTOBuilder<C, B>> {
        protected abstract B self();

        public abstract C build();
    }

}

