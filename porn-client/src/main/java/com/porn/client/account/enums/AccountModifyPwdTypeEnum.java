package com.porn.client.account.enums;

public enum AccountModifyPwdTypeEnum {
    LOGIN_PWD(0, "登录密码"),
    TRADE_PWD(1, "交易密码");

    private final Integer type;
    private final String description;

    AccountModifyPwdTypeEnum(Integer type, String description) {
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