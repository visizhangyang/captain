package com.porn.client.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountValidateTypeEnum {
    LOGIN_PWD(0, "登录密码"),
    TRADE_PWD(1, "交易密码");

    private final Integer type;
    private final String description;

}