package com.porn.client.stream.enums;

public enum StreamTypeEnum {
    WORKING_LOCK(0, 0, "搬砖锁定"),
    WORKING_UNLOCK(1, 0, "搬砖解锁"),
    WORKING_SUB(2, -1, "搬砖扣款"),
    WORKING_ADD(3, 1, "搬砖利润"),
    REDENVELOPE(100, 1, "系统红包"),
    RECHARGE(200, 1, "充值"),
    WITHDRAW_LOCK(300, 0, "提现锁定"),
    WITHDRAW_PASS(301, -1, "提现到账"),
    WITHDRAW_UNLOCK(302, 0, "提现超时"),
    TRANSFER_SRC(400, -1, "发起转账"),
    TRANSFER_DST(401, 1, "接收转账"),
    TRANSFER_WAIT_AUDIT(402, 0, "发起转账待审核"),
    TRANSFER_REJECT(403, 0, "转账驳回"),
    PROXY_1(500, 1, "一级代理分佣"),
    PROXY_2(501, 1, "二级代理分佣"),
    PROXY_3(502, 1, "三级代理分佣"),
    TREASURE(600, 1, "余U宝"),
    LOTTERY(700, 1, "抽奖"),
    KEEP(800, 1, "留本"),
    PLAN_LOCK(900, -1, "计划锁定"),
    PLAN_FREE(901, -1, "计划手续费"),
    PLAN_APPENDLOCK(902, -1, "计划追加锁定"),
    PLAN_PROFIT(903, 0, "计划利润"),
    PLAN_UNLOCK(950, 1, "计划解除锁定"),
    SYSTEM_LOCK(10000, 0, "系统锁定"),
    SYSTEM_UNLOCK(10001, 0, "系统解锁");

    private final Integer type;
    private final Integer flag;
    private final String description;

    StreamTypeEnum(Integer type, Integer flag, String description) {
        this.type = type;
        this.flag = flag;
        this.description = description;
    }

    public Integer getType() {
        return this.type;
    }

    public Integer getFlag() {
        return this.flag;
    }

    public String getDescription() {
        return this.description;
    }
}