package com.porn.client.nickname.enums;

public enum NickNameTypeEnum {
    ROBOT(0, "词库"),
    CUSTOM(1, "导入");

    private final Integer type;
    private final String description;

    NickNameTypeEnum(Integer type, String description) {
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