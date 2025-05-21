
package com.porn.client.order.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class OrderDeleteDTO
         extends BaseDTO
         {
    

    protected boolean canEqual(Object other) {
        return other instanceof OrderDeleteDTO;
    }



    /* 14 */
    protected OrderDeleteDTO(OrderDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static OrderDeleteDTOBuilder<?, ?> builder() {
        return new OrderDeleteDTOBuilderImpl();
    }

    private static final class OrderDeleteDTOBuilderImpl extends OrderDeleteDTOBuilder<OrderDeleteDTO, OrderDeleteDTOBuilderImpl> {
        protected OrderDeleteDTOBuilderImpl self() {
            return this;
        }

        private OrderDeleteDTOBuilderImpl() {
        }

        public OrderDeleteDTO build() {
            return new OrderDeleteDTO(this);
        }
    }

    public static abstract class OrderDeleteDTOBuilder<C extends OrderDeleteDTO, B extends OrderDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        
        
        
        protected abstract B self();

        
        
        public abstract C build();
    }

    
    
    
    public OrderDeleteDTO() {
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/dto/OrderDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */