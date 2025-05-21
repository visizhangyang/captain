package com.porn.client.reward.enums;

import java.util.Arrays;
import java.util.List;

public enum RuleTypeEnum {
    WORK_RULE(0, "搬砖", "每天用户搬砖达到指定额度，奖励抽奖多少次"),
    PROMOTION_RULE(1, "直推", "每天直推用户，搬砖总累计额度达到多少，将奖励多少次"),
    RECHARGE_RULE(2, "充值", "每日充值累计金额达到多少，奖励多少次"),
    BALANCE_RULE(3, "余额", "每日余额达到多少，奖励多少次"),
    LOTTERY_RULE(-1, "抽奖", "抽奖");

    private final Integer type;
    private final String name;
    private final String description;

    RuleTypeEnum(Integer type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public Integer getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public static List<Integer> getRuleTypes() {
        return Arrays.asList(WORK_RULE.type, PROMOTION_RULE.type, RECHARGE_RULE.type, BALANCE_RULE.type);
    }
}