
package com.porn.client.order.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class OrderFreezeDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof OrderFreezeDTO;
    }



    /* 14 */
    protected OrderFreezeDTO(OrderFreezeDTOBuilder<?, ?> b) {
        super(b);
    }

    public static OrderFreezeDTOBuilder<?, ?> builder() {
        return new OrderFreezeDTOBuilderImpl();
    }

    private static final class OrderFreezeDTOBuilderImpl extends OrderFreezeDTOBuilder<OrderFreezeDTO, OrderFreezeDTOBuilderImpl> {
        protected OrderFreezeDTOBuilderImpl self() {
            return this;
        }

        private OrderFreezeDTOBuilderImpl() {
        }

        public OrderFreezeDTO build() {
            return new OrderFreezeDTO(this);
        }
    }

    public static abstract class OrderFreezeDTOBuilder<C extends OrderFreezeDTO, B extends OrderFreezeDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public OrderFreezeDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/dto/OrderFreezeDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */