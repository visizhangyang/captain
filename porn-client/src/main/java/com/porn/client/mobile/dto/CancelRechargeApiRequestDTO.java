
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;






 public class CancelRechargeApiRequestDTO
         implements Serializable
         {

    @ApiModelProperty("充值ID")
     private Long rechargeId;



    public void setRechargeId(Long rechargeId) {
        /* 15 */
        this.rechargeId = rechargeId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof CancelRechargeApiRequestDTO;
    }



    /* 16 */
    public static CancelRechargeApiRequestDTOBuilder builder() {
        return new CancelRechargeApiRequestDTOBuilder();
    }

    public static class CancelRechargeApiRequestDTOBuilder {
        public CancelRechargeApiRequestDTOBuilder rechargeId(Long rechargeId) {
            this.rechargeId = rechargeId;
            return this;
        }

        private Long rechargeId;

        public CancelRechargeApiRequestDTO build() {
            return new CancelRechargeApiRequestDTO(this.rechargeId);
        }

    }

    public CancelRechargeApiRequestDTO(Long rechargeId) {
        /* 17 */
        this.rechargeId = rechargeId;

    }


    public CancelRechargeApiRequestDTO() {
    }



    public Long getRechargeId() {
        /* 22 */
        return this.rechargeId;

    }

}


