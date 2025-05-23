package com.porn.client.paramset.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class ParamsetSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("大户返佣比例, %")
    private BigDecimal largeLevelRate;
    @ApiModelProperty("普通返佣比例, %")
    private BigDecimal normalLevelRate;
    @ApiModelProperty("搬砖额度, (1-workRate) * 可用余额  为实际可以搬砖的余额")
    private BigDecimal workRate;
    @ApiModelProperty("充值手续费, (充值金额 - rechargeAmount) == 到账金额")
    private BigDecimal rechargeAmount;
    @ApiModelProperty("普通提现手续费, (提现金额 - normalWithdrawAmount) == 到账金额")
    private BigDecimal normalWithdrawAmount;
    @ApiModelProperty("大户提现手续费, (提现金额 - largeWithdrawAmount) == 到账金额")
    private BigDecimal largeWithdrawAmount;
    @ApiModelProperty("搬砖难度 %, 难度越大, 就经常找不到符合自己的单子")
    private BigDecimal difficultyRate;
    @ApiModelProperty("佣金提成")
    private BigDecimal freeCommissionRate;
    @ApiModelProperty("最低搬砖额度")
    private BigDecimal minWorkAmount;
    @ApiModelProperty("最低充值额度")
    private BigDecimal minRechargeAmount;
    @ApiModelProperty("最大提现额度")
    private BigDecimal maxWithdrawAmount;
    @ApiModelProperty("最小提现额度")
    private BigDecimal minWithdrawAmount;
    @ApiModelProperty("充值超时时间, 单位分钟")
    private Integer rechargeMatchTime;
    @ApiModelProperty("提现匹配时间, 单位分钟")
    private Integer withdrawMatchTime;
    @ApiModelProperty("订单匹配时间, 单位分钟")
    private Integer orderMatchTime;
    @ApiModelProperty("大户搬砖次数")
    private Integer largeLevelWorkCount;
    @ApiModelProperty("普通搬砖次数")
    private Integer normalLevelWorkCount;
    @ApiModelProperty("大户搬砖时间间隔, 分钟")
    private Integer largeLevelWorkSpace;
    @ApiModelProperty("普通搬砖时间间隔, 分钟")
    private Integer normalLevelWorkSpace;
    @ApiModelProperty("大户搬砖时间段")
    private String largeLevelWorkMinRange;
    @ApiModelProperty("大户搬砖时间段")
    private String largeLevelWorkMaxRange;
    @ApiModelProperty("普通搬砖时间段")
    private String normalLevelWorkMinRange;
    @ApiModelProperty("普通搬砖时间段")
    private String normalLevelWorkMaxRange;
    @ApiModelProperty("大户升级额度")
    private BigDecimal largeLevelIncAmount;
    @ApiModelProperty("最少搬砖人数")
    private Integer minWorkCount;
    @ApiModelProperty("最大搬砖人数")
    private Integer maxWorkCount;
    @ApiModelProperty("普通用户提现时间段-开始")
    private String normalWithdrawMinRange;
    @ApiModelProperty("普通用户提现时间段-结束")
    private String normalWithdrawMaxRange;
    @ApiModelProperty("大户用户提现时间段-开始")
    private String largeWithdrawMinRange;
    @ApiModelProperty("大户用户提现时间段-结束")
    private String largeWithdrawMaxRange;
    @ApiModelProperty("普通提现次数")
    private Integer normalWithdrawDayCount;
    @ApiModelProperty("大户提现次数")
    private Integer largeWithdrawDayCount;
    @ApiModelProperty("普通用户转账时间段-开始")
    private String normalTransferMinRange;
    @ApiModelProperty("普通用户转账时间段-结束")
    private String normalTransferMaxRange;
    @ApiModelProperty("大户用户转账时间段-开始")
    private String largeTransferMinRange;
    @ApiModelProperty("大户用户转账时间段-结束")
    private String largeTransferMaxRange;
    @ApiModelProperty("提现最低搬砖次数")
    private Integer withdrawWorkCount;
    @ApiModelProperty("一级分佣利率")
    private BigDecimal proxyLevel1Rate;
    @ApiModelProperty("二级分佣利率")
    private BigDecimal proxyLevel2Rate;
    @ApiModelProperty("三级分佣利率")
    private BigDecimal proxyLevel3Rate;
    @ApiModelProperty("余额宝, 普通的利率")
    private BigDecimal normalTreasureRate;
    @ApiModelProperty("余额宝, 大户利率")
    private BigDecimal largeTreasureRate;
    @ApiModelProperty("搬砖页面刷新时间间隔, 单位秒")
    private Integer workPageRefreshSpace;
    @ApiModelProperty("默认头像")
    private String appLogo;
    @ApiModelProperty("新手教程Url")
    private String noviceTutorialUrl;
    @ApiModelProperty("客服URL")
    private String customerServiceUrl;
    @ApiModelProperty("推广URL")
    private String promotionUrl;
    @ApiModelProperty("初始化的红包金额")
    private BigDecimal initRedPack;
    @ApiModelProperty("注册人数")
    private Long registerCount;
    @ApiModelProperty("最低转账金额")
    private BigDecimal minTransferAmount;
    @ApiModelProperty("合伙人返佣比例, %")
    private BigDecimal partnerLevelRate;
    @ApiModelProperty("合伙人搬砖次数")
    private Integer partnerLevelWorkCount;
    @ApiModelProperty("合伙人搬砖时间间隔, 分钟")
    private Integer partnerLevelWorkSpace;
    @ApiModelProperty("合伙人搬砖时间段(始)")
    private String partnerLevelWorkMinRange;
    @ApiModelProperty("合伙人搬砖时间段(终)")
    private String partnerLevelWorkMaxRange;
    @ApiModelProperty("合伙人提现手续费, (提现金额 - normalWithdrawAmount) == 到账金额")
    private BigDecimal partnerWithdrawAmount;
    @ApiModelProperty("合伙人升级额度")
    private BigDecimal partnerLevelIncAmount;
    @ApiModelProperty("合伙人提现时间段(始)")
    private String partnerWithdrawMinRange;
    @ApiModelProperty("合伙人提现时间段(终)")
    private String partnerWithdrawMaxRange;
    @ApiModelProperty("合伙人提现次数")
    private Integer partnerWithdrawDayCount;
    @ApiModelProperty("合伙人转账时间段(始)")
    private String partnerTransferMinRange;
    @ApiModelProperty("合伙人转账时间段(终)")
    private String partnerTransferMaxRange;
    @ApiModelProperty("余额宝, 合伙人的利率")
    private BigDecimal partnerTreasureRate;

    protected ParamsetSaveOrUpdateDTO(ParamsetSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.largeLevelRate = b.largeLevelRate;
        this.normalLevelRate = b.normalLevelRate;
        this.workRate = b.workRate;
        this.rechargeAmount = b.rechargeAmount;
        this.normalWithdrawAmount = b.normalWithdrawAmount;
        this.largeWithdrawAmount = b.largeWithdrawAmount;
        this.difficultyRate = b.difficultyRate;
        this.freeCommissionRate = b.freeCommissionRate;
        this.minWorkAmount = b.minWorkAmount;
        this.minRechargeAmount = b.minRechargeAmount;
        this.maxWithdrawAmount = b.maxWithdrawAmount;
        this.minWithdrawAmount = b.minWithdrawAmount;
        this.rechargeMatchTime = b.rechargeMatchTime;
        this.withdrawMatchTime = b.withdrawMatchTime;
        this.orderMatchTime = b.orderMatchTime;
        this.largeLevelWorkCount = b.largeLevelWorkCount;
        this.normalLevelWorkCount = b.normalLevelWorkCount;
        this.largeLevelWorkSpace = b.largeLevelWorkSpace;
        this.normalLevelWorkSpace = b.normalLevelWorkSpace;
        this.largeLevelWorkMinRange = b.largeLevelWorkMinRange;
        this.largeLevelWorkMaxRange = b.largeLevelWorkMaxRange;
        this.normalLevelWorkMinRange = b.normalLevelWorkMinRange;
        this.normalLevelWorkMaxRange = b.normalLevelWorkMaxRange;
        this.largeLevelIncAmount = b.largeLevelIncAmount;
        this.minWorkCount = b.minWorkCount;
        this.maxWorkCount = b.maxWorkCount;
        this.normalWithdrawMinRange = b.normalWithdrawMinRange;
        this.normalWithdrawMaxRange = b.normalWithdrawMaxRange;
        this.largeWithdrawMinRange = b.largeWithdrawMinRange;
        this.largeWithdrawMaxRange = b.largeWithdrawMaxRange;
        this.normalWithdrawDayCount = b.normalWithdrawDayCount;
        this.largeWithdrawDayCount = b.largeWithdrawDayCount;
        this.normalTransferMinRange = b.normalTransferMinRange;
        this.normalTransferMaxRange = b.normalTransferMaxRange;
        this.largeTransferMinRange = b.largeTransferMinRange;
        this.largeTransferMaxRange = b.largeTransferMaxRange;
        this.withdrawWorkCount = b.withdrawWorkCount;
        this.proxyLevel1Rate = b.proxyLevel1Rate;
        this.proxyLevel2Rate = b.proxyLevel2Rate;
        this.proxyLevel3Rate = b.proxyLevel3Rate;
        this.normalTreasureRate = b.normalTreasureRate;
        this.largeTreasureRate = b.largeTreasureRate;
        this.workPageRefreshSpace = b.workPageRefreshSpace;
        this.appLogo = b.appLogo;
        this.noviceTutorialUrl = b.noviceTutorialUrl;
        this.customerServiceUrl = b.customerServiceUrl;
        this.promotionUrl = b.promotionUrl;
        this.initRedPack = b.initRedPack;
        this.registerCount = b.registerCount;
        this.minTransferAmount = b.minTransferAmount;
        this.partnerLevelRate = b.partnerLevelRate;
        this.partnerLevelWorkCount = b.partnerLevelWorkCount;
        this.partnerLevelWorkSpace = b.partnerLevelWorkSpace;
        this.partnerLevelWorkMinRange = b.partnerLevelWorkMinRange;
        this.partnerLevelWorkMaxRange = b.partnerLevelWorkMaxRange;
        this.partnerWithdrawAmount = b.partnerWithdrawAmount;
        this.partnerLevelIncAmount = b.partnerLevelIncAmount;
        this.partnerWithdrawMinRange = b.partnerWithdrawMinRange;
        this.partnerWithdrawMaxRange = b.partnerWithdrawMaxRange;
        this.partnerWithdrawDayCount = b.partnerWithdrawDayCount;
        this.partnerTransferMinRange = b.partnerTransferMinRange;
        this.partnerTransferMaxRange = b.partnerTransferMaxRange;
        this.partnerTreasureRate = b.partnerTreasureRate;
    }

    public ParamsetSaveOrUpdateDTO(BigDecimal largeLevelRate, BigDecimal normalLevelRate, BigDecimal workRate, BigDecimal rechargeAmount, BigDecimal normalWithdrawAmount, BigDecimal largeWithdrawAmount, BigDecimal difficultyRate, BigDecimal freeCommissionRate, BigDecimal minWorkAmount, BigDecimal minRechargeAmount, BigDecimal maxWithdrawAmount, BigDecimal minWithdrawAmount, Integer rechargeMatchTime, Integer withdrawMatchTime, Integer orderMatchTime, Integer largeLevelWorkCount, Integer normalLevelWorkCount, Integer largeLevelWorkSpace, Integer normalLevelWorkSpace, String largeLevelWorkMinRange, String largeLevelWorkMaxRange, String normalLevelWorkMinRange, String normalLevelWorkMaxRange, BigDecimal largeLevelIncAmount, Integer minWorkCount, Integer maxWorkCount, String normalWithdrawMinRange, String normalWithdrawMaxRange, String largeWithdrawMinRange, String largeWithdrawMaxRange, Integer normalWithdrawDayCount, Integer largeWithdrawDayCount, String normalTransferMinRange, String normalTransferMaxRange, String largeTransferMinRange, String largeTransferMaxRange, Integer withdrawWorkCount, BigDecimal proxyLevel1Rate, BigDecimal proxyLevel2Rate, BigDecimal proxyLevel3Rate, BigDecimal normalTreasureRate, BigDecimal largeTreasureRate, Integer workPageRefreshSpace, String appLogo, String noviceTutorialUrl, String customerServiceUrl, String promotionUrl, BigDecimal initRedPack, Long registerCount, BigDecimal minTransferAmount, BigDecimal partnerLevelRate, Integer partnerLevelWorkCount, Integer partnerLevelWorkSpace, String partnerLevelWorkMinRange, String partnerLevelWorkMaxRange, BigDecimal partnerWithdrawAmount, BigDecimal partnerLevelIncAmount, String partnerWithdrawMinRange, String partnerWithdrawMaxRange, Integer partnerWithdrawDayCount, String partnerTransferMinRange, String partnerTransferMaxRange, BigDecimal partnerTreasureRate) {

        this.largeLevelRate = largeLevelRate;
        this.normalLevelRate = normalLevelRate;
        this.workRate = workRate;
        this.rechargeAmount = rechargeAmount;
        this.normalWithdrawAmount = normalWithdrawAmount;
        this.largeWithdrawAmount = largeWithdrawAmount;
        this.difficultyRate = difficultyRate;
        this.freeCommissionRate = freeCommissionRate;
        this.minWorkAmount = minWorkAmount;
        this.minRechargeAmount = minRechargeAmount;
        this.maxWithdrawAmount = maxWithdrawAmount;
        this.minWithdrawAmount = minWithdrawAmount;
        this.rechargeMatchTime = rechargeMatchTime;
        this.withdrawMatchTime = withdrawMatchTime;
        this.orderMatchTime = orderMatchTime;
        this.largeLevelWorkCount = largeLevelWorkCount;
        this.normalLevelWorkCount = normalLevelWorkCount;
        this.largeLevelWorkSpace = largeLevelWorkSpace;
        this.normalLevelWorkSpace = normalLevelWorkSpace;
        this.largeLevelWorkMinRange = largeLevelWorkMinRange;
        this.largeLevelWorkMaxRange = largeLevelWorkMaxRange;
        this.normalLevelWorkMinRange = normalLevelWorkMinRange;
        this.normalLevelWorkMaxRange = normalLevelWorkMaxRange;
        this.largeLevelIncAmount = largeLevelIncAmount;
        this.minWorkCount = minWorkCount;
        this.maxWorkCount = maxWorkCount;
        this.normalWithdrawMinRange = normalWithdrawMinRange;
        this.normalWithdrawMaxRange = normalWithdrawMaxRange;
        this.largeWithdrawMinRange = largeWithdrawMinRange;
        this.largeWithdrawMaxRange = largeWithdrawMaxRange;
        this.normalWithdrawDayCount = normalWithdrawDayCount;
        this.largeWithdrawDayCount = largeWithdrawDayCount;
        this.normalTransferMinRange = normalTransferMinRange;
        this.normalTransferMaxRange = normalTransferMaxRange;
        this.largeTransferMinRange = largeTransferMinRange;
        this.largeTransferMaxRange = largeTransferMaxRange;
        this.withdrawWorkCount = withdrawWorkCount;
        this.proxyLevel1Rate = proxyLevel1Rate;
        this.proxyLevel2Rate = proxyLevel2Rate;
        this.proxyLevel3Rate = proxyLevel3Rate;
        this.normalTreasureRate = normalTreasureRate;
        this.largeTreasureRate = largeTreasureRate;
        this.workPageRefreshSpace = workPageRefreshSpace;
        this.appLogo = appLogo;
        this.noviceTutorialUrl = noviceTutorialUrl;
        this.customerServiceUrl = customerServiceUrl;
        this.promotionUrl = promotionUrl;
        this.initRedPack = initRedPack;
        this.registerCount = registerCount;
        this.minTransferAmount = minTransferAmount;
        this.partnerLevelRate = partnerLevelRate;
        this.partnerLevelWorkCount = partnerLevelWorkCount;
        this.partnerLevelWorkSpace = partnerLevelWorkSpace;
        this.partnerLevelWorkMinRange = partnerLevelWorkMinRange;
        this.partnerLevelWorkMaxRange = partnerLevelWorkMaxRange;
        this.partnerWithdrawAmount = partnerWithdrawAmount;
        this.partnerLevelIncAmount = partnerLevelIncAmount;
        this.partnerWithdrawMinRange = partnerWithdrawMinRange;
        this.partnerWithdrawMaxRange = partnerWithdrawMaxRange;
        this.partnerWithdrawDayCount = partnerWithdrawDayCount;
        this.partnerTransferMinRange = partnerTransferMinRange;
        this.partnerTransferMaxRange = partnerTransferMaxRange;
        this.partnerTreasureRate = partnerTreasureRate;

    }

    public ParamsetSaveOrUpdateDTO() {
    }

    public static ParamsetSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new ParamsetSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ParamsetSaveOrUpdateDTO;
    }

    public BigDecimal getLargeLevelRate() {

        return this.largeLevelRate;

    }

    public void setLargeLevelRate(BigDecimal largeLevelRate) {
        this.largeLevelRate = largeLevelRate;
    }

    public BigDecimal getNormalLevelRate() {

        return this.normalLevelRate;

    }

    public void setNormalLevelRate(BigDecimal normalLevelRate) {
        this.normalLevelRate = normalLevelRate;
    }

    public BigDecimal getWorkRate() {

        return this.workRate;

    }

    public void setWorkRate(BigDecimal workRate) {
        this.workRate = workRate;
    }

    public BigDecimal getRechargeAmount() {

        return this.rechargeAmount;

    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getNormalWithdrawAmount() {

        return this.normalWithdrawAmount;

    }

    public void setNormalWithdrawAmount(BigDecimal normalWithdrawAmount) {
        this.normalWithdrawAmount = normalWithdrawAmount;
    }

    public BigDecimal getLargeWithdrawAmount() {

        return this.largeWithdrawAmount;

    }

    public void setLargeWithdrawAmount(BigDecimal largeWithdrawAmount) {
        this.largeWithdrawAmount = largeWithdrawAmount;
    }

    public BigDecimal getDifficultyRate() {

        return this.difficultyRate;

    }

    public void setDifficultyRate(BigDecimal difficultyRate) {
        this.difficultyRate = difficultyRate;
    }

    public BigDecimal getFreeCommissionRate() {

        return this.freeCommissionRate;

    }

    public void setFreeCommissionRate(BigDecimal freeCommissionRate) {
        this.freeCommissionRate = freeCommissionRate;
    }

    public BigDecimal getMinWorkAmount() {

        return this.minWorkAmount;

    }

    public void setMinWorkAmount(BigDecimal minWorkAmount) {
        this.minWorkAmount = minWorkAmount;
    }

    public BigDecimal getMinRechargeAmount() {

        return this.minRechargeAmount;

    }

    public void setMinRechargeAmount(BigDecimal minRechargeAmount) {
        this.minRechargeAmount = minRechargeAmount;
    }

    public BigDecimal getMaxWithdrawAmount() {

        return this.maxWithdrawAmount;

    }

    public void setMaxWithdrawAmount(BigDecimal maxWithdrawAmount) {
        this.maxWithdrawAmount = maxWithdrawAmount;
    }

    public BigDecimal getMinWithdrawAmount() {

        return this.minWithdrawAmount;

    }

    public void setMinWithdrawAmount(BigDecimal minWithdrawAmount) {
        this.minWithdrawAmount = minWithdrawAmount;
    }


    public Integer getRechargeMatchTime() {

        return this.rechargeMatchTime;

    }

    public void setRechargeMatchTime(Integer rechargeMatchTime) {
        this.rechargeMatchTime = rechargeMatchTime;
    }


    public Integer getWithdrawMatchTime() {

        return this.withdrawMatchTime;

    }

    public void setWithdrawMatchTime(Integer withdrawMatchTime) {
        this.withdrawMatchTime = withdrawMatchTime;
    }


    public Integer getOrderMatchTime() {

        return this.orderMatchTime;

    }

    public void setOrderMatchTime(Integer orderMatchTime) {
        this.orderMatchTime = orderMatchTime;
    }


    public Integer getLargeLevelWorkCount() {

        return this.largeLevelWorkCount;

    }

    public void setLargeLevelWorkCount(Integer largeLevelWorkCount) {
        this.largeLevelWorkCount = largeLevelWorkCount;
    }


    public Integer getNormalLevelWorkCount() {

        return this.normalLevelWorkCount;

    }

    public void setNormalLevelWorkCount(Integer normalLevelWorkCount) {
        this.normalLevelWorkCount = normalLevelWorkCount;
    }


    public Integer getLargeLevelWorkSpace() {

        return this.largeLevelWorkSpace;

    }

    public void setLargeLevelWorkSpace(Integer largeLevelWorkSpace) {
        this.largeLevelWorkSpace = largeLevelWorkSpace;
    }


    public Integer getNormalLevelWorkSpace() {

        return this.normalLevelWorkSpace;

    }

    public void setNormalLevelWorkSpace(Integer normalLevelWorkSpace) {
        this.normalLevelWorkSpace = normalLevelWorkSpace;
    }


    public String getLargeLevelWorkMinRange() {

        return this.largeLevelWorkMinRange;

    }

    public void setLargeLevelWorkMinRange(String largeLevelWorkMinRange) {
        this.largeLevelWorkMinRange = largeLevelWorkMinRange;
    }


    public String getLargeLevelWorkMaxRange() {

        return this.largeLevelWorkMaxRange;

    }

    public void setLargeLevelWorkMaxRange(String largeLevelWorkMaxRange) {
        this.largeLevelWorkMaxRange = largeLevelWorkMaxRange;
    }


    public String getNormalLevelWorkMinRange() {

        return this.normalLevelWorkMinRange;

    }

    public void setNormalLevelWorkMinRange(String normalLevelWorkMinRange) {
        this.normalLevelWorkMinRange = normalLevelWorkMinRange;
    }


    public String getNormalLevelWorkMaxRange() {

        return this.normalLevelWorkMaxRange;

    }

    public void setNormalLevelWorkMaxRange(String normalLevelWorkMaxRange) {
        this.normalLevelWorkMaxRange = normalLevelWorkMaxRange;
    }


    public BigDecimal getLargeLevelIncAmount() {

        return this.largeLevelIncAmount;

    }

    public void setLargeLevelIncAmount(BigDecimal largeLevelIncAmount) {
        this.largeLevelIncAmount = largeLevelIncAmount;
    }


    public Integer getMinWorkCount() {

        return this.minWorkCount;

    }

    public void setMinWorkCount(Integer minWorkCount) {
        this.minWorkCount = minWorkCount;
    }


    public Integer getMaxWorkCount() {

        return this.maxWorkCount;

    }

    public void setMaxWorkCount(Integer maxWorkCount) {
        this.maxWorkCount = maxWorkCount;
    }


    public String getNormalWithdrawMinRange() {

        return this.normalWithdrawMinRange;

    }

    public void setNormalWithdrawMinRange(String normalWithdrawMinRange) {
        this.normalWithdrawMinRange = normalWithdrawMinRange;
    }


    public String getNormalWithdrawMaxRange() {

        return this.normalWithdrawMaxRange;

    }

    public void setNormalWithdrawMaxRange(String normalWithdrawMaxRange) {
        this.normalWithdrawMaxRange = normalWithdrawMaxRange;
    }


    public String getLargeWithdrawMinRange() {

        return this.largeWithdrawMinRange;

    }

    public void setLargeWithdrawMinRange(String largeWithdrawMinRange) {
        this.largeWithdrawMinRange = largeWithdrawMinRange;
    }


    public String getLargeWithdrawMaxRange() {

        return this.largeWithdrawMaxRange;

    }

    public void setLargeWithdrawMaxRange(String largeWithdrawMaxRange) {
        this.largeWithdrawMaxRange = largeWithdrawMaxRange;
    }


    public Integer getNormalWithdrawDayCount() {

        return this.normalWithdrawDayCount;

    }

    public void setNormalWithdrawDayCount(Integer normalWithdrawDayCount) {
        this.normalWithdrawDayCount = normalWithdrawDayCount;
    }


    public Integer getLargeWithdrawDayCount() {

        return this.largeWithdrawDayCount;

    }

    public void setLargeWithdrawDayCount(Integer largeWithdrawDayCount) {
        this.largeWithdrawDayCount = largeWithdrawDayCount;
    }


    public String getNormalTransferMinRange() {

        return this.normalTransferMinRange;

    }

    public void setNormalTransferMinRange(String normalTransferMinRange) {
        this.normalTransferMinRange = normalTransferMinRange;
    }


    public String getNormalTransferMaxRange() {

        return this.normalTransferMaxRange;

    }

    public void setNormalTransferMaxRange(String normalTransferMaxRange) {
        this.normalTransferMaxRange = normalTransferMaxRange;
    }


    public String getLargeTransferMinRange() {

        return this.largeTransferMinRange;

    }

    public void setLargeTransferMinRange(String largeTransferMinRange) {
        this.largeTransferMinRange = largeTransferMinRange;
    }


    public String getLargeTransferMaxRange() {

        return this.largeTransferMaxRange;

    }

    public void setLargeTransferMaxRange(String largeTransferMaxRange) {
        this.largeTransferMaxRange = largeTransferMaxRange;
    }


    public Integer getWithdrawWorkCount() {

        return this.withdrawWorkCount;

    }

    public void setWithdrawWorkCount(Integer withdrawWorkCount) {
        this.withdrawWorkCount = withdrawWorkCount;
    }


    public BigDecimal getProxyLevel1Rate() {

        return this.proxyLevel1Rate;

    }

    public void setProxyLevel1Rate(BigDecimal proxyLevel1Rate) {
        this.proxyLevel1Rate = proxyLevel1Rate;
    }


    public BigDecimal getProxyLevel2Rate() {

        return this.proxyLevel2Rate;

    }

    public void setProxyLevel2Rate(BigDecimal proxyLevel2Rate) {
        this.proxyLevel2Rate = proxyLevel2Rate;
    }


    public BigDecimal getProxyLevel3Rate() {

        return this.proxyLevel3Rate;

    }

    public void setProxyLevel3Rate(BigDecimal proxyLevel3Rate) {
        this.proxyLevel3Rate = proxyLevel3Rate;
    }


    public BigDecimal getNormalTreasureRate() {

        return this.normalTreasureRate;

    }

    public void setNormalTreasureRate(BigDecimal normalTreasureRate) {
        this.normalTreasureRate = normalTreasureRate;
    }


    public BigDecimal getLargeTreasureRate() {

        return this.largeTreasureRate;

    }

    public void setLargeTreasureRate(BigDecimal largeTreasureRate) {
        this.largeTreasureRate = largeTreasureRate;
    }


    public Integer getWorkPageRefreshSpace() {

        return this.workPageRefreshSpace;

    }

    public void setWorkPageRefreshSpace(Integer workPageRefreshSpace) {
        this.workPageRefreshSpace = workPageRefreshSpace;
    }


    public String getAppLogo() {

        return this.appLogo;

    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }


    public String getNoviceTutorialUrl() {

        return this.noviceTutorialUrl;

    }

    public void setNoviceTutorialUrl(String noviceTutorialUrl) {
        this.noviceTutorialUrl = noviceTutorialUrl;
    }


    public String getCustomerServiceUrl() {

        return this.customerServiceUrl;

    }

    public void setCustomerServiceUrl(String customerServiceUrl) {
        this.customerServiceUrl = customerServiceUrl;
    }


    public String getPromotionUrl() {

        return this.promotionUrl;

    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl;
    }


    public BigDecimal getInitRedPack() {

        return this.initRedPack;

    }

    public void setInitRedPack(BigDecimal initRedPack) {
        this.initRedPack = initRedPack;
    }


    public Long getRegisterCount() {

        return this.registerCount;

    }

    public void setRegisterCount(Long registerCount) {
        this.registerCount = registerCount;
    }


    public BigDecimal getMinTransferAmount() {

        return this.minTransferAmount;

    }

    public void setMinTransferAmount(BigDecimal minTransferAmount) {
        this.minTransferAmount = minTransferAmount;
    }


    public BigDecimal getPartnerLevelRate() {

        return this.partnerLevelRate;

    }

    public void setPartnerLevelRate(BigDecimal partnerLevelRate) {
        this.partnerLevelRate = partnerLevelRate;
    }


    public Integer getPartnerLevelWorkCount() {

        return this.partnerLevelWorkCount;

    }

    public void setPartnerLevelWorkCount(Integer partnerLevelWorkCount) {
        this.partnerLevelWorkCount = partnerLevelWorkCount;
    }


    public Integer getPartnerLevelWorkSpace() {

        return this.partnerLevelWorkSpace;

    }

    public void setPartnerLevelWorkSpace(Integer partnerLevelWorkSpace) {
        this.partnerLevelWorkSpace = partnerLevelWorkSpace;
    }


    public String getPartnerLevelWorkMinRange() {

        return this.partnerLevelWorkMinRange;

    }

    public void setPartnerLevelWorkMinRange(String partnerLevelWorkMinRange) {
        this.partnerLevelWorkMinRange = partnerLevelWorkMinRange;
    }


    public String getPartnerLevelWorkMaxRange() {

        return this.partnerLevelWorkMaxRange;

    }

    public void setPartnerLevelWorkMaxRange(String partnerLevelWorkMaxRange) {
        this.partnerLevelWorkMaxRange = partnerLevelWorkMaxRange;
    }


    public BigDecimal getPartnerWithdrawAmount() {

        return this.partnerWithdrawAmount;

    }

    public void setPartnerWithdrawAmount(BigDecimal partnerWithdrawAmount) {
        this.partnerWithdrawAmount = partnerWithdrawAmount;
    }


    public BigDecimal getPartnerLevelIncAmount() {

        return this.partnerLevelIncAmount;

    }

    public void setPartnerLevelIncAmount(BigDecimal partnerLevelIncAmount) {
        this.partnerLevelIncAmount = partnerLevelIncAmount;
    }


    public String getPartnerWithdrawMinRange() {

        return this.partnerWithdrawMinRange;

    }

    public void setPartnerWithdrawMinRange(String partnerWithdrawMinRange) {
        this.partnerWithdrawMinRange = partnerWithdrawMinRange;
    }


    public String getPartnerWithdrawMaxRange() {

        return this.partnerWithdrawMaxRange;

    }

    public void setPartnerWithdrawMaxRange(String partnerWithdrawMaxRange) {
        this.partnerWithdrawMaxRange = partnerWithdrawMaxRange;
    }


    public Integer getPartnerWithdrawDayCount() {

        return this.partnerWithdrawDayCount;

    }

    public void setPartnerWithdrawDayCount(Integer partnerWithdrawDayCount) {
        this.partnerWithdrawDayCount = partnerWithdrawDayCount;
    }


    public String getPartnerTransferMinRange() {

        return this.partnerTransferMinRange;

    }

    public void setPartnerTransferMinRange(String partnerTransferMinRange) {
        this.partnerTransferMinRange = partnerTransferMinRange;
    }


    public String getPartnerTransferMaxRange() {

        return this.partnerTransferMaxRange;

    }

    public void setPartnerTransferMaxRange(String partnerTransferMaxRange) {
        this.partnerTransferMaxRange = partnerTransferMaxRange;
    }


    public BigDecimal getPartnerTreasureRate() {

        return this.partnerTreasureRate;

    }

    public void setPartnerTreasureRate(BigDecimal partnerTreasureRate) {
        this.partnerTreasureRate = partnerTreasureRate;
    }

    private static final class ParamsetSaveOrUpdateDTOBuilderImpl extends ParamsetSaveOrUpdateDTOBuilder<ParamsetSaveOrUpdateDTO, ParamsetSaveOrUpdateDTOBuilderImpl> {
        private ParamsetSaveOrUpdateDTOBuilderImpl() {
        }

        protected ParamsetSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public ParamsetSaveOrUpdateDTO build() {
            return new ParamsetSaveOrUpdateDTO(this);
        }
    }

    public static abstract class ParamsetSaveOrUpdateDTOBuilder<C extends ParamsetSaveOrUpdateDTO, B extends ParamsetSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private BigDecimal largeLevelRate;
        private BigDecimal normalLevelRate;
        private BigDecimal workRate;
        private BigDecimal rechargeAmount;
        private BigDecimal normalWithdrawAmount;
        private BigDecimal largeWithdrawAmount;
        private BigDecimal difficultyRate;
        private BigDecimal freeCommissionRate;
        private BigDecimal minWorkAmount;
        private BigDecimal minRechargeAmount;
        private BigDecimal maxWithdrawAmount;
        private BigDecimal minWithdrawAmount;
        private Integer rechargeMatchTime;
        private Integer withdrawMatchTime;
        private Integer orderMatchTime;
        private Integer largeLevelWorkCount;
        private Integer normalLevelWorkCount;
        private Integer largeLevelWorkSpace;
        private Integer normalLevelWorkSpace;
        private String largeLevelWorkMinRange;
        private String largeLevelWorkMaxRange;
        private String normalLevelWorkMinRange;
        private String normalLevelWorkMaxRange;
        private BigDecimal largeLevelIncAmount;
        private Integer minWorkCount;
        private Integer maxWorkCount;
        private String normalWithdrawMinRange;
        private String normalWithdrawMaxRange;
        private String largeWithdrawMinRange;
        private String largeWithdrawMaxRange;
        private Integer normalWithdrawDayCount;
        private Integer largeWithdrawDayCount;
        private String normalTransferMinRange;
        private String normalTransferMaxRange;
        private String largeTransferMinRange;
        private String largeTransferMaxRange;
        private Integer withdrawWorkCount;
        private BigDecimal proxyLevel1Rate;
        private BigDecimal proxyLevel2Rate;
        private BigDecimal proxyLevel3Rate;
        private BigDecimal normalTreasureRate;
        private BigDecimal largeTreasureRate;
        private Integer workPageRefreshSpace;
        private String appLogo;
        private String noviceTutorialUrl;
        private String customerServiceUrl;
        private String promotionUrl;
        private BigDecimal initRedPack;
        private Long registerCount;
        private BigDecimal minTransferAmount;
        private BigDecimal partnerLevelRate;
        private Integer partnerLevelWorkCount;
        private Integer partnerLevelWorkSpace;
        private String partnerLevelWorkMinRange;
        private String partnerLevelWorkMaxRange;
        private BigDecimal partnerWithdrawAmount;
        private BigDecimal partnerLevelIncAmount;
        private String partnerWithdrawMinRange;
        private String partnerWithdrawMaxRange;
        private Integer partnerWithdrawDayCount;
        private String partnerTransferMinRange;
        private String partnerTransferMaxRange;
        private BigDecimal partnerTreasureRate;

        public B largeLevelRate(BigDecimal largeLevelRate) {
            this.largeLevelRate = largeLevelRate;
            return self();
        }

        public B normalLevelRate(BigDecimal normalLevelRate) {
            this.normalLevelRate = normalLevelRate;
            return self();
        }

        public B workRate(BigDecimal workRate) {
            this.workRate = workRate;
            return self();
        }

        public B rechargeAmount(BigDecimal rechargeAmount) {
            this.rechargeAmount = rechargeAmount;
            return self();
        }

        public B normalWithdrawAmount(BigDecimal normalWithdrawAmount) {
            this.normalWithdrawAmount = normalWithdrawAmount;
            return self();
        }

        public B largeWithdrawAmount(BigDecimal largeWithdrawAmount) {
            this.largeWithdrawAmount = largeWithdrawAmount;
            return self();
        }

        public B difficultyRate(BigDecimal difficultyRate) {
            this.difficultyRate = difficultyRate;
            return self();
        }

        public B freeCommissionRate(BigDecimal freeCommissionRate) {
            this.freeCommissionRate = freeCommissionRate;
            return self();
        }

        public B minWorkAmount(BigDecimal minWorkAmount) {
            this.minWorkAmount = minWorkAmount;
            return self();
        }

        public B minRechargeAmount(BigDecimal minRechargeAmount) {
            this.minRechargeAmount = minRechargeAmount;
            return self();
        }

        public B maxWithdrawAmount(BigDecimal maxWithdrawAmount) {
            this.maxWithdrawAmount = maxWithdrawAmount;
            return self();
        }

        public B minWithdrawAmount(BigDecimal minWithdrawAmount) {
            this.minWithdrawAmount = minWithdrawAmount;
            return self();
        }

        public B rechargeMatchTime(Integer rechargeMatchTime) {
            this.rechargeMatchTime = rechargeMatchTime;
            return self();
        }

        public B withdrawMatchTime(Integer withdrawMatchTime) {
            this.withdrawMatchTime = withdrawMatchTime;
            return self();
        }

        public B orderMatchTime(Integer orderMatchTime) {
            this.orderMatchTime = orderMatchTime;
            return self();
        }

        public B largeLevelWorkCount(Integer largeLevelWorkCount) {
            this.largeLevelWorkCount = largeLevelWorkCount;
            return self();
        }

        public B normalLevelWorkCount(Integer normalLevelWorkCount) {
            this.normalLevelWorkCount = normalLevelWorkCount;
            return self();
        }

        public B largeLevelWorkSpace(Integer largeLevelWorkSpace) {
            this.largeLevelWorkSpace = largeLevelWorkSpace;
            return self();
        }

        public B normalLevelWorkSpace(Integer normalLevelWorkSpace) {
            this.normalLevelWorkSpace = normalLevelWorkSpace;
            return self();
        }

        public B largeLevelWorkMinRange(String largeLevelWorkMinRange) {
            this.largeLevelWorkMinRange = largeLevelWorkMinRange;
            return self();
        }

        public B largeLevelWorkMaxRange(String largeLevelWorkMaxRange) {
            this.largeLevelWorkMaxRange = largeLevelWorkMaxRange;
            return self();
        }

        public B normalLevelWorkMinRange(String normalLevelWorkMinRange) {
            this.normalLevelWorkMinRange = normalLevelWorkMinRange;
            return self();
        }

        public B normalLevelWorkMaxRange(String normalLevelWorkMaxRange) {
            this.normalLevelWorkMaxRange = normalLevelWorkMaxRange;
            return self();
        }

        public B largeLevelIncAmount(BigDecimal largeLevelIncAmount) {
            this.largeLevelIncAmount = largeLevelIncAmount;
            return self();
        }

        public B minWorkCount(Integer minWorkCount) {
            this.minWorkCount = minWorkCount;
            return self();
        }

        public B maxWorkCount(Integer maxWorkCount) {
            this.maxWorkCount = maxWorkCount;
            return self();
        }

        public B normalWithdrawMinRange(String normalWithdrawMinRange) {
            this.normalWithdrawMinRange = normalWithdrawMinRange;
            return self();
        }

        public B normalWithdrawMaxRange(String normalWithdrawMaxRange) {
            this.normalWithdrawMaxRange = normalWithdrawMaxRange;
            return self();
        }

        public B largeWithdrawMinRange(String largeWithdrawMinRange) {
            this.largeWithdrawMinRange = largeWithdrawMinRange;
            return self();
        }

        public B largeWithdrawMaxRange(String largeWithdrawMaxRange) {
            this.largeWithdrawMaxRange = largeWithdrawMaxRange;
            return self();
        }

        public B normalWithdrawDayCount(Integer normalWithdrawDayCount) {
            this.normalWithdrawDayCount = normalWithdrawDayCount;
            return self();
        }

        public B largeWithdrawDayCount(Integer largeWithdrawDayCount) {
            this.largeWithdrawDayCount = largeWithdrawDayCount;
            return self();
        }

        public B normalTransferMinRange(String normalTransferMinRange) {
            this.normalTransferMinRange = normalTransferMinRange;
            return self();
        }

        public B normalTransferMaxRange(String normalTransferMaxRange) {
            this.normalTransferMaxRange = normalTransferMaxRange;
            return self();
        }

        public B largeTransferMinRange(String largeTransferMinRange) {
            this.largeTransferMinRange = largeTransferMinRange;
            return self();
        }

        public B largeTransferMaxRange(String largeTransferMaxRange) {
            this.largeTransferMaxRange = largeTransferMaxRange;
            return self();
        }

        public B withdrawWorkCount(Integer withdrawWorkCount) {
            this.withdrawWorkCount = withdrawWorkCount;
            return self();
        }

        public B proxyLevel1Rate(BigDecimal proxyLevel1Rate) {
            this.proxyLevel1Rate = proxyLevel1Rate;
            return self();
        }

        public B proxyLevel2Rate(BigDecimal proxyLevel2Rate) {
            this.proxyLevel2Rate = proxyLevel2Rate;
            return self();
        }

        public B proxyLevel3Rate(BigDecimal proxyLevel3Rate) {
            this.proxyLevel3Rate = proxyLevel3Rate;
            return self();
        }

        public B normalTreasureRate(BigDecimal normalTreasureRate) {
            this.normalTreasureRate = normalTreasureRate;
            return self();
        }

        public B largeTreasureRate(BigDecimal largeTreasureRate) {
            this.largeTreasureRate = largeTreasureRate;
            return self();
        }

        public B workPageRefreshSpace(Integer workPageRefreshSpace) {
            this.workPageRefreshSpace = workPageRefreshSpace;
            return self();
        }

        public B appLogo(String appLogo) {
            this.appLogo = appLogo;
            return self();
        }

        public B noviceTutorialUrl(String noviceTutorialUrl) {
            this.noviceTutorialUrl = noviceTutorialUrl;
            return self();
        }

        public B customerServiceUrl(String customerServiceUrl) {
            this.customerServiceUrl = customerServiceUrl;
            return self();
        }

        public B promotionUrl(String promotionUrl) {
            this.promotionUrl = promotionUrl;
            return self();
        }

        public B initRedPack(BigDecimal initRedPack) {
            this.initRedPack = initRedPack;
            return self();
        }

        public B registerCount(Long registerCount) {
            this.registerCount = registerCount;
            return self();
        }

        public B minTransferAmount(BigDecimal minTransferAmount) {
            this.minTransferAmount = minTransferAmount;
            return self();
        }

        public B partnerLevelRate(BigDecimal partnerLevelRate) {
            this.partnerLevelRate = partnerLevelRate;
            return self();
        }

        public B partnerLevelWorkCount(Integer partnerLevelWorkCount) {
            this.partnerLevelWorkCount = partnerLevelWorkCount;
            return self();
        }

        public B partnerLevelWorkSpace(Integer partnerLevelWorkSpace) {
            this.partnerLevelWorkSpace = partnerLevelWorkSpace;
            return self();
        }

        public B partnerLevelWorkMinRange(String partnerLevelWorkMinRange) {
            this.partnerLevelWorkMinRange = partnerLevelWorkMinRange;
            return self();
        }

        public B partnerLevelWorkMaxRange(String partnerLevelWorkMaxRange) {
            this.partnerLevelWorkMaxRange = partnerLevelWorkMaxRange;
            return self();
        }

        public B partnerWithdrawAmount(BigDecimal partnerWithdrawAmount) {
            this.partnerWithdrawAmount = partnerWithdrawAmount;
            return self();
        }

        public B partnerLevelIncAmount(BigDecimal partnerLevelIncAmount) {
            this.partnerLevelIncAmount = partnerLevelIncAmount;
            return self();
        }

        public B partnerWithdrawMinRange(String partnerWithdrawMinRange) {
            this.partnerWithdrawMinRange = partnerWithdrawMinRange;
            return self();
        }

        public B partnerWithdrawMaxRange(String partnerWithdrawMaxRange) {
            this.partnerWithdrawMaxRange = partnerWithdrawMaxRange;
            return self();
        }

        public B partnerWithdrawDayCount(Integer partnerWithdrawDayCount) {
            this.partnerWithdrawDayCount = partnerWithdrawDayCount;
            return self();
        }

        public B partnerTransferMinRange(String partnerTransferMinRange) {
            this.partnerTransferMinRange = partnerTransferMinRange;
            return self();
        }

        public B partnerTransferMaxRange(String partnerTransferMaxRange) {
            this.partnerTransferMaxRange = partnerTransferMaxRange;
            return self();
        }

        public B partnerTreasureRate(BigDecimal partnerTreasureRate) {
            this.partnerTreasureRate = partnerTreasureRate;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

