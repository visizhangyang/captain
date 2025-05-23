package com.porn.client.reward.enums;

public enum RewardRecordTypeEnum {
    ADD(Integer.valueOf(1), "增加数量"),

    SUB(Integer.valueOf(-1), "减少数量");
    private Integer type;

    private String description;

    RewardRecordTypeEnum(Integer type, String description) {

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

