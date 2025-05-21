package com.porn.client.common.enums;

public enum MailTypeEnum {
    QQ(0, "@qq.com"),
    WY163(1, "@163.com"),
    GMAIL(2, "@gmail.com"),
    OUTLOOK(3, "@outlook.com");

    private final Integer type;
    private final String description;

    MailTypeEnum(Integer type, String description) {
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