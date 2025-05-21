package com.porn.client.goods.enums;

public enum GoodsTypeEnum {
    NORMAL(0, "普通商品"),
    SPECIAL(1, "特殊商品(重点关注)");

    private final Integer type;
    private final String description;

    GoodsTypeEnum(Integer type, String description) {
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