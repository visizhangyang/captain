package com.porn.client.imglib.enums;

public enum ImageTypeEnum {
    MERCHANT(0, "商户图片"),
    ACCOUNT(1, "账户图片");

    private final Integer type;
    private final String description;

    ImageTypeEnum(Integer type, String description) {
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