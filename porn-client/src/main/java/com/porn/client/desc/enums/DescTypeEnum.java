package com.porn.client.desc.enums;

public enum DescTypeEnum {
    RECHARGE(Integer.valueOf(0), "充值"),

    WITHDRAW(Integer.valueOf(1), "提现"),

    TRANSFER(Integer.valueOf(2), "转账"),

    TREASURE(Integer.valueOf(3), "余U宝"),

    RECOMMEND(Integer.valueOf(4), "买币推荐"),

    DATA(Integer.valueOf(5), " 数据"),

    ACCOUNTINFO(Integer.valueOf(6), " 个人信息"),

    INDEX(Integer.valueOf(7), " 首页"),

    DOUBLEFOCUS(Integer.valueOf(8), "重点关注"),

    MARKETNITICE(Integer.valueOf(9), "市场公告"),

    LOTTERY(Integer.valueOf(10), "抽奖公告"),

    PLAN(Integer.valueOf(11), "计划说明"),

    SHARE(Integer.valueOf(12), "推广说明"),

    WEBSITE(Integer.valueOf(13), "官网"),

    CUSTOMSERVICE(Integer.valueOf(14), "客服");
    private Integer type;

    private String description;

    DescTypeEnum(Integer type, String description) {

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

