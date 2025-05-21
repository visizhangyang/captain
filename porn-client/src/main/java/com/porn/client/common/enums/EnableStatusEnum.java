package com.porn.client.common.enums;

public enum EnableStatusEnum {
    DISABLED(0, "禁用"),
    ENABLE(1, "启用");

    private final Integer status;
    private final String description;

    EnableStatusEnum(Integer status, String description) {
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