package com.porn.client.photo.enums;

public enum PhotoStatusEnum {
    WAIT_GRANT(0, "待授权"),
    GRANTED(1, "已授权"),
    UN_GRANT(-1, "已拒绝");

    private final Integer status;
    private final String description;

    PhotoStatusEnum(Integer status, String description) {
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