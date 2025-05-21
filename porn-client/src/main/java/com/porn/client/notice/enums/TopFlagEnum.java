package com.porn.client.notice.enums;

public enum TopFlagEnum {
    TOP(1, "置顶"),
    NORMAL(0, "普通");

    private final Integer flag;
    private final String description;

    TopFlagEnum(Integer flag, String description) {
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