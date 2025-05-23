package com.porn.client.paramset.vo;

import com.porn.client.common.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ParamsetVo extends BaseVo {
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
    @ApiModelProperty("佣金加成, %")
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
    @ApiModelProperty("默认头像地址")
    private String appLogoUrl;
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

}

