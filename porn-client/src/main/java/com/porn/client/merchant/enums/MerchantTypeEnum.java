package com.porn.client.merchant.enums;

public enum MerchantTypeEnum {
    NORMAL(0, "真人"),
    ROBOT(1, "机器人");

    private final Integer type;
    private final String description;

    MerchantTypeEnum(Integer type, String description) {
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