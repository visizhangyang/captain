
package com.porn.client.order.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class OrderTimeOutDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof OrderTimeOutDTO;
    }



    /* 14 */
    protected OrderTimeOutDTO(OrderTimeOutDTOBuilder<?, ?> b) {
        super(b);
    }

    public static OrderTimeOutDTOBuilder<?, ?> builder() {
        return new OrderTimeOutDTOBuilderImpl();
    }

    private static final class OrderTimeOutDTOBuilderImpl extends OrderTimeOutDTOBuilder<OrderTimeOutDTO, OrderTimeOutDTOBuilderImpl> {
        protected OrderTimeOutDTOBuilderImpl self() {
            return this;
        }

        private OrderTimeOutDTOBuilderImpl() {
        }

        public OrderTimeOutDTO build() {
            return new OrderTimeOutDTO(this);
        }
    }

    public static abstract class OrderTimeOutDTOBuilder<C extends OrderTimeOutDTO, B extends OrderTimeOutDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public OrderTimeOutDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/dto/OrderTimeOutDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */