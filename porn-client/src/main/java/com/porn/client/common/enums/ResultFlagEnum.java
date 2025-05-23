package com.porn.client.common.enums;

public enum ResultFlagEnum {
    CODE_SUCCESS(Integer.valueOf(200), "操作成功"),
    CODE_FAIL(Integer.valueOf(400), "操作失败"),

    CODE_AUTHFAIL(Integer.valueOf(500), "鉴权失败");
    private Integer flag;

    private String description;

    ResultFlagEnum(Integer flag, String description) {

        this.flag = flag;

        this.description = description;

    }

    public static ResultFlagEnum findByFlag(Integer flag) {

        for (ResultFlagEnum delFlagEnum : values()) {

            if (delFlagEnum.getFlag().equals(flag)) {

                return delFlagEnum;

            }

        }

        return null;

    }

    public Integer getFlag() {

        return this.flag;

    }

    public String getDescription() {

        return this.description;

    }

}

