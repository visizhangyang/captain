package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class RechargeStatusApiRequestDTO
        implements Serializable {

    @ApiModelProperty("充值ID")
    private Long rechargeId;

    public RechargeStatusApiRequestDTO(Long rechargeId) {

        this.rechargeId = rechargeId;

    }

    public RechargeStatusApiRequestDTO() {
    }

    public static RechargeStatusApiRequestDTOBuilder builder() {
        return new RechargeStatusApiRequestDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RechargeStatusApiRequestDTO;
    }

    public Long getRechargeId() {

        return this.rechargeId;

    }

    public void setRechargeId(Long rechargeId) {

        this.rechargeId = rechargeId;
    }

    public static class RechargeStatusApiRequestDTOBuilder {
        private Long rechargeId;

        public RechargeStatusApiRequestDTOBuilder rechargeId(Long rechargeId) {
            this.rechargeId = rechargeId;
            return this;
        }

        public RechargeStatusApiRequestDTO build() {
            return new RechargeStatusApiRequestDTO(this.rechargeId);
        }

    }

}

