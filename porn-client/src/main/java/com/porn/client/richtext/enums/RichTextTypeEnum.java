package com.porn.client.richtext.enums;

public enum RichTextTypeEnum {
    NOVICETEACHING(0, "新手教学");

    private final Integer type;
    private final String description;

    RichTextTypeEnum(Integer type, String description) {
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