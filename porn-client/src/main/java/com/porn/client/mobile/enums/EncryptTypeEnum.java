package com.porn.client.mobile.enums;

public enum EncryptTypeEnum {
    XOR(0, "异或");

    private final Integer type;
    private final String description;

    EncryptTypeEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public static EncryptTypeEnum queryByType(Integer type) {
        if (null == type) {
            return null;
        }
        for (EncryptTypeEnum encryptTypeEnum : values()) {
            if (encryptTypeEnum.getType().equals(type)) {
                return encryptTypeEnum;
            }
        }
        return null;
    }

    public Integer getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }
}