package com.porn.client.account.enums;

public enum AccountLevelEnum {
    NORMAL(0, "普通会员"),
    LARGE(1, "大户"),
    PARTNER(2, "合伙人");

    private final Integer level;
    private final String description;

    AccountLevelEnum(Integer level, String description) {
        this.level = level;
        this.description = description;
    }

    public Integer getLevel() {
        return this.level;
    }

    public String getDescription() {
        return this.description;
    }
}