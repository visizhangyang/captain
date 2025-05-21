package com.porn.client.wallet.enums;

public enum WalletAddressOpTypeEnum {
    PAY_LOCK(1, "支付锁定"),
    PAY_SUCCESS(2, "支付成功解锁"),
    PAYTIMEOUT_UNLOCK(-1, "支付超时解锁");

    private final Integer type;
    private final String description;

    WalletAddressOpTypeEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }
}