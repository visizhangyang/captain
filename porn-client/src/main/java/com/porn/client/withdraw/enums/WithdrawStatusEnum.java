package com.porn.client.withdraw.enums;

public enum WithdrawStatusEnum {
    EXAMINEING(0, "审核中"),
    EXAMINEPASS(1, "审核通过"),
    COMPLEX(2, "完成"),
    TIMEOUT(-1, "超时");

    private final Integer status;
    private final String description;

    WithdrawStatusEnum(Integer status, String description) {
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