package com.porn.client.account.enums;

public enum AccountExtTypeEnum {
    PLANRATE(0, "计划利率, 计划每次搬砖的上限, 下限利率"),
    TRANSFERLIMIT(1, "转账限制, 最大金额"),
    EXTRAREBATE(2, "佣金加成");

    private final Integer type;
    private final String description;

    AccountExtTypeEnum(Integer type, String description) {
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