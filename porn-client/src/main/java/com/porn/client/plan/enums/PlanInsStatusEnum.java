package com.porn.client.plan.enums;

public enum PlanInsStatusEnum {
    PROGRESSING(0, "进行中"),
    COMPLETED(1, "已完成");

    private final Integer status;
    private final String description;

    PlanInsStatusEnum(Integer status, String description) {
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