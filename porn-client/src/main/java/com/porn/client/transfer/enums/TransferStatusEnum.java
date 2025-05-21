package com.porn.client.transfer.enums;

public enum TransferStatusEnum {
    WAIT_AUDIT(0, "等待审核"),
    TRANSFER_SUCCESS(1, "转账成功"),
    REJECT(-1, "驳回");

    private final Integer status;
    private final String description;

    TransferStatusEnum(Integer status, String description) {
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