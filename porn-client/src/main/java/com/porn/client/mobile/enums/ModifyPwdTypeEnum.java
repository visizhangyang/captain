package com.porn.client.mobile.enums;

public enum ModifyPwdTypeEnum {
    LOGINPWD(2, "登录密码"),
    TRADEPWD(3, "交易密码");

    private final Integer type;
    private final String description;

    ModifyPwdTypeEnum(Integer type, String description) {
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