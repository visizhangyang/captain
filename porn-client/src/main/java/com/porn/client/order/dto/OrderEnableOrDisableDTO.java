
package com.porn.client.order.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class OrderEnableOrDisableDTO
         extends BaseDTO
         {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
     private Integer status;



    public void setStatus(Integer status) {
        /* 15 */
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof OrderEnableOrDisableDTO;
    }



    /* 16 */
    protected OrderEnableOrDisableDTO(OrderEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
    }

    public static OrderEnableOrDisableDTOBuilder<?, ?> builder() {
        return new OrderEnableOrDisableDTOBuilderImpl();
    }

    private static final class OrderEnableOrDisableDTOBuilderImpl extends OrderEnableOrDisableDTOBuilder<OrderEnableOrDisableDTO, OrderEnableOrDisableDTOBuilderImpl> {
        private OrderEnableOrDisableDTOBuilderImpl() {
        }

        protected OrderEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public OrderEnableOrDisableDTO build() {
            return new OrderEnableOrDisableDTO(this);
        }
    }

    public static abstract class OrderEnableOrDisableDTOBuilder<C extends OrderEnableOrDisableDTO, B extends OrderEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B status(Integer status) {
            this.status = status;
            return self();
        }

        private Integer status;

        protected abstract B self();

        public abstract C build();

    }

    public OrderEnableOrDisableDTO(Integer status) {
        /* 17 */
        this.status = status;

    }


    public OrderEnableOrDisableDTO() {
    }



    public Integer getStatus() {
        /* 22 */
        return this.status;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/dto/OrderEnableOrDisableDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */