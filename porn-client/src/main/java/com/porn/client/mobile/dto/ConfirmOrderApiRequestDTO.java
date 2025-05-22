
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;






 public class ConfirmOrderApiRequestDTO
         implements Serializable
         {

    @ApiModelProperty("订单ID")
     private Long orderId;



    public void setOrderId(Long orderId) {
        /* 15 */
        this.orderId = orderId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ConfirmOrderApiRequestDTO;
    }



    /* 16 */
    public static ConfirmOrderApiRequestDTOBuilder builder() {
        return new ConfirmOrderApiRequestDTOBuilder();
    }

    public static class ConfirmOrderApiRequestDTOBuilder {
        public ConfirmOrderApiRequestDTOBuilder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        private Long orderId;

        public ConfirmOrderApiRequestDTO build() {
            return new ConfirmOrderApiRequestDTO(this.orderId);
        }

    }

    public ConfirmOrderApiRequestDTO(Long orderId) {
        /* 17 */
        this.orderId = orderId;

    }


    public ConfirmOrderApiRequestDTO() {
    }



    public Long getOrderId() {
        /* 22 */
        return this.orderId;

    }

}


