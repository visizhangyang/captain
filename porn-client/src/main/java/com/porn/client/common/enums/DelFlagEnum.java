package com.porn.client.common.enums;

public enum DelFlagEnum {
    NORMAL(0, "正常"),
    DELETED(1, "已删除");

    private final Integer flag;
    private final String description;

    DelFlagEnum(Integer flag, String description) {
        this.flag = flag;
        this.description = description;
    }

    public Integer getFlag() {
        return this.flag;
    }

    public String getDescription() {
        return this.description;
    }
}