package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CancelRechargeApiRequestDTO
        implements Serializable {

    @ApiModelProperty("充值ID")
    private Long rechargeId;

    public CancelRechargeApiRequestDTO(Long rechargeId) {

        this.rechargeId = rechargeId;

    }

    public CancelRechargeApiRequestDTO() {
    }

    public static CancelRechargeApiRequestDTOBuilder builder() {
        return new CancelRechargeApiRequestDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof CancelRechargeApiRequestDTO;
    }

    public Long getRechargeId() {

        return this.rechargeId;

    }

    public void setRechargeId(Long rechargeId) {

        this.rechargeId = rechargeId;
    }

    public static class CancelRechargeApiRequestDTOBuilder {
        private Long rechargeId;

        public CancelRechargeApiRequestDTOBuilder rechargeId(Long rechargeId) {
            this.rechargeId = rechargeId;
            return this;
        }

        public CancelRechargeApiRequestDTO build() {
            return new CancelRechargeApiRequestDTO(this.rechargeId);
        }

    }

}

