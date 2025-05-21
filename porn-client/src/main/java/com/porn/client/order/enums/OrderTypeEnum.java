package com.porn.client.order.enums;

public enum OrderTypeEnum {
    NORMAL(0, "真人"),
    ROBOT(1, "机器人");

    private final Integer type;
    private final String description;

    OrderTypeEnum(Integer type, String description) {
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