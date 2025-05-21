package com.porn.client.menu.enums;

public enum IconTypeEnum {
    ICON(0, "图标"),
    UPLOAD(1, "上传");

    private final Integer type;
    private final String description;

    IconTypeEnum(Integer type, String description) {
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