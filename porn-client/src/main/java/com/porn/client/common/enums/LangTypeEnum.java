package com.porn.client.common.enums;

public enum LangTypeEnum {
    ZH(Integer.valueOf(0), "中文"),

    EN(Integer.valueOf(1), "英语");
    private Integer type;

    private String description;

    LangTypeEnum(Integer type, String description) {

        this.type = type;

        this.description = description;

    }

    public static LangTypeEnum queryByTag(String tag) {

        if (tag == null) {

            return null;

        }

        for (LangTypeEnum langTypeEnum : values()) {

            if (langTypeEnum.name().toLowerCase().equals(tag.toLowerCase())) {

                return langTypeEnum;

            }

        }

        return null;

    }

    public static LangTypeEnum queryByType(Integer type) {

        if (type == null) {

            return null;

        }

        for (LangTypeEnum langTypeEnum : values()) {

            if (langTypeEnum.type.equals(type)) {

                return langTypeEnum;

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

