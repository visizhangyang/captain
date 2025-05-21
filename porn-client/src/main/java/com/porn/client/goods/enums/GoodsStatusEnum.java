package com.porn.client.goods.enums;

public enum GoodsStatusEnum {
    WAIT_WORING(0, "待搬砖"),
    WORKED(1, "已搬砖");

    private final Integer status;
    private final String description;

    GoodsStatusEnum(Integer status, String description) {
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