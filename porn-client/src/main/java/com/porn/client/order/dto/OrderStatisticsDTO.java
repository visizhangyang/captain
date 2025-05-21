
package com.porn.client.order.dto;
import io.swagger.annotations.ApiModelProperty;



import java.io.Serializable;





 public class OrderStatisticsDTO
         implements Serializable
         {



    protected boolean canEqual(Object other) {
        return other instanceof OrderStatisticsDTO;
    }


    /* 13 */
    protected OrderStatisticsDTO(OrderStatisticsDTOBuilder<?, ?> b) {
    }

    public static OrderStatisticsDTOBuilder<?, ?> builder() {
        return new OrderStatisticsDTOBuilderImpl();
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/dto/OrderStatisticsDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */