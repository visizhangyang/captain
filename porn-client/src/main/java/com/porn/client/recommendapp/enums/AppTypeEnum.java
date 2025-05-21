package com.porn.client.recommendapp.enums;

public enum AppTypeEnum {
    ANDROID(0, "安卓"),
    IOS(1, "苹果");

    private final Integer type;
    private final String description;

    AppTypeEnum(Integer type, String description) {
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