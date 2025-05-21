package com.porn.client.order.enums;

public enum OrderStatusEnum {
    WAIT_PAY(0, "待支付"),
    PAY_SUCCESS(1, "支付成功"),
    PAY_TIMEOUT(-1, "支付超时"),
    CONFIRED(2, "已确认"),
    FREEZE(3, "已冻结");

    private final Integer status;
    private final String description;

    OrderStatusEnum(Integer status, String description) {
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