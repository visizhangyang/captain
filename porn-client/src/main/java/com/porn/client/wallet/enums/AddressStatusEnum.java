package com.porn.client.wallet.enums;

public enum AddressStatusEnum {
    NORMAL(0, "空闲"),
    PAYING(1, "支付中");

    private final Integer status;
    private final String description;

    AddressStatusEnum(Integer status, String description) {
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