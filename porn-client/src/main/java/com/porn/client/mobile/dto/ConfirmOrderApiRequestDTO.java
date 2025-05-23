package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ConfirmOrderApiRequestDTO
        implements Serializable {

    @ApiModelProperty("订单ID")
    private Long orderId;

    public ConfirmOrderApiRequestDTO(Long orderId) {

        this.orderId = orderId;

    }

    public ConfirmOrderApiRequestDTO() {
    }

    public static ConfirmOrderApiRequestDTOBuilder builder() {
        return new ConfirmOrderApiRequestDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ConfirmOrderApiRequestDTO;
    }

    public Long getOrderId() {

        return this.orderId;

    }

    public void setOrderId(Long orderId) {

        this.orderId = orderId;
    }

    public static class ConfirmOrderApiRequestDTOBuilder {
        private Long orderId;

        public ConfirmOrderApiRequestDTOBuilder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public ConfirmOrderApiRequestDTO build() {
            return new ConfirmOrderApiRequestDTO(this.orderId);
        }

    }

}

