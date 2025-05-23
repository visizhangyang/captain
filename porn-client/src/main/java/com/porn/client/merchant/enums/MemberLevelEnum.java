package com.porn.client.merchant.enums;

public enum MemberLevelEnum {
    BRONZE(Integer.valueOf(0), "青铜VIP"),

    SILVER(Integer.valueOf(1), "白银VIP"),

    GOLD(Integer.valueOf(2), "黄金VIP"),

    DIAMOND(Integer.valueOf(3), "钻石VIP");
    private Integer level;

    private String description;

    MemberLevelEnum(Integer level, String description) {

        this.level = level;

        this.description = description;

    }

    public static String queryByLevel(Integer level) {

        for (MemberLevelEnum memberLevelEnum : values()) {

            if (memberLevelEnum.getLevel().equals(level)) {

                return memberLevelEnum.getDescription();

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

