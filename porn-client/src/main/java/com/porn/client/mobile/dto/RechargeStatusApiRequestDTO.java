
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;






 public class RechargeStatusApiRequestDTO
         implements Serializable
         {
    
    @ApiModelProperty("充值ID")
     private Long rechargeId;

    
    
    public void setRechargeId(Long rechargeId) {
        /* 15 */
        this.rechargeId = rechargeId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RechargeStatusApiRequestDTO;
    }



    /* 16 */
    public static RechargeStatusApiRequestDTOBuilder builder() {
        return new RechargeStatusApiRequestDTOBuilder();
    }

    public static class RechargeStatusApiRequestDTOBuilder {
        public RechargeStatusApiRequestDTOBuilder rechargeId(Long rechargeId) {
            this.rechargeId = rechargeId;
            return this;
        }

        private Long rechargeId;

        public RechargeStatusApiRequestDTO build() {
            return new RechargeStatusApiRequestDTO(this.rechargeId);
        }

    }

    public RechargeStatusApiRequestDTO(Long rechargeId) {
        /* 17 */
        this.rechargeId = rechargeId;
        
    }

    
    public RechargeStatusApiRequestDTO() {
    }

    
    
    public Long getRechargeId() {
        /* 22 */
        return this.rechargeId;
        
    }
    
}


