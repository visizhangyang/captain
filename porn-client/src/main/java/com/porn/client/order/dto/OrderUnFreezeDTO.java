
package com.porn.client.order.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class OrderUnFreezeDTO
         extends BaseDTO
         {
    

    protected boolean canEqual(Object other) {
        return other instanceof OrderUnFreezeDTO;
    }



    /* 14 */
    protected OrderUnFreezeDTO(OrderUnFreezeDTOBuilder<?, ?> b) {
        super(b);
    }

    public static OrderUnFreezeDTOBuilder<?, ?> builder() {
        return new OrderUnFreezeDTOBuilderImpl();
    }

    private static final class OrderUnFreezeDTOBuilderImpl extends OrderUnFreezeDTOBuilder<OrderUnFreezeDTO, OrderUnFreezeDTOBuilderImpl> {
        protected OrderUnFreezeDTOBuilderImpl self() {
            return this;
        }

        private OrderUnFreezeDTOBuilderImpl() {
        }

        public OrderUnFreezeDTO build() {
            return new OrderUnFreezeDTO(this);
        }
    }

    public static abstract class OrderUnFreezeDTOBuilder<C extends OrderUnFreezeDTO, B extends OrderUnFreezeDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        
        
        
        protected abstract B self();

        
        
        public abstract C build();
    }

    
    
    
    public OrderUnFreezeDTO() {
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/dto/OrderUnFreezeDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */