
package com.porn.client.order.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class OrderAuditDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof OrderAuditDTO;
    }



    /* 14 */
    protected OrderAuditDTO(OrderAuditDTOBuilder<?, ?> b) {
        super(b);
    }

    public static OrderAuditDTOBuilder<?, ?> builder() {
        return new OrderAuditDTOBuilderImpl();
    }

    private static final class OrderAuditDTOBuilderImpl extends OrderAuditDTOBuilder<OrderAuditDTO, OrderAuditDTOBuilderImpl> {
        protected OrderAuditDTOBuilderImpl self() {
            return this;
        }

        private OrderAuditDTOBuilderImpl() {
        }

        public OrderAuditDTO build() {
            return new OrderAuditDTO(this);
        }
    }

    public static abstract class OrderAuditDTOBuilder<C extends OrderAuditDTO, B extends OrderAuditDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public OrderAuditDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/dto/OrderAuditDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */