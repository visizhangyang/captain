package com.porn.client.recommendapp.enums;

public enum RecommendTypeEnum {
    DECRECOMMEND(0, "交易所推荐"),
    MARKET(1, "营销");

    private final Integer type;
    private final String description;

    RecommendTypeEnum(Integer type, String description) {
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