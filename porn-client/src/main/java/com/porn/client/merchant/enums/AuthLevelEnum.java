package com.porn.client.merchant.enums;

public enum AuthLevelEnum {
    LEVEL1(0, "一级验证"),
    LEVEL2(1, "二级验证"),
    LEVEL3(2, "三级验证");

    private final Integer level;
    private final String description;

    AuthLevelEnum(Integer level, String description) {
        this.level = level;
        this.description = description;
    }

    public static String queryByLevel(Integer level) {
        for (AuthLevelEnum authLevelEnum : values()) {
            if (authLevelEnum.getLevel().equals(level)) {
                return authLevelEnum.getDescription();
            }
        }
        return null;
    }

    public Integer getLevel() {
        return this.level;
    }

    public String getDescription() {
        return this.description;
    }
}