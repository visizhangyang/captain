package com.porn.client.recharge.enums;

public enum RechargeStatusEnum {
    WAIT_PAY(0, "待支付"),
    PAY_SUCCESS(1, "支付成功"),
    PAY_TIMEOUT(-1, "支付超时");

    private final Integer status;
    private final String description;

    RechargeStatusEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }
}