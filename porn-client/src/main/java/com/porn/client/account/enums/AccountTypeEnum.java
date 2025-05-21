package com.porn.client.account.enums;

public enum AccountTypeEnum {
    NORMAL(Integer.valueOf(0), "真人"),
    ROBOT(Integer.valueOf(1), "机器人");

    private Integer type;
    private String description;

    AccountTypeEnum(Integer type, String description) {
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

