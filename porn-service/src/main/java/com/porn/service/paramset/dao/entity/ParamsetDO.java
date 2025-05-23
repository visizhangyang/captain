package com.porn.service.paramset.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@TableName("porn_paramset")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ParamsetDO extends BaseDO {
    @TableField("largelevel_rate")
    private BigDecimal largeLevelRate;
    @TableField("normallevel_rate")
    private BigDecimal normalLevelRate;
    @TableField("work_rate")
    private BigDecimal workRate;
    @TableField("recharge_amount")
    private BigDecimal rechargeAmount;
    @TableField("normalwithdraw_amount")
    private BigDecimal normalWithdrawAmount;
    @TableField("largewithdraw_amount")
    private BigDecimal largeWithdrawAmount;
    @TableField("difficulty_rate")
    private BigDecimal difficultyRate;
    @TableField("freecommission_rate")
    private BigDecimal freeCommissionRate;
    @TableField("minwork_amount")
    private BigDecimal minWorkAmount;
    @TableField("minrecharge_amount")
    private BigDecimal minRechargeAmount;
    @TableField("maxwithdraw_amount")
    private BigDecimal maxWithdrawAmount;
    @TableField("minwithdraw_amount")
    private BigDecimal minWithdrawAmount;
    @TableField("rechargematch_time")
    private Integer rechargeMatchTime;
    @TableField("withdrawmatch_time")
    private Integer withdrawMatchTime;
    @TableField("ordermatch_time")
    private Integer orderMatchTime;
    @TableField("largelevelwork_count")
    private Integer largeLevelWorkCount;
    @TableField("normallevelwork_count")
    private Integer normalLevelWorkCount;
    @TableField("largelevelwork_space")
    private Integer largeLevelWorkSpace;
    @TableField("normallevelwork_space")
    private Integer normalLevelWorkSpace;
    @TableField("largelevelwork_minrange")
    private String largeLevelWorkMinRange;
    @TableField("largelevelwork_maxrange")
    private String largeLevelWorkMaxRange;
    @TableField("normallevelwork_minrange")
    private String normalLevelWorkMinRange;
    @TableField("normallevelwork_maxnrange")
    private String normalLevelWorkMaxRange;
    @TableField("largelevelinc_amount")
    private BigDecimal largeLevelIncAmount;
    @TableField("min_workcount")
    private Integer minWorkCount;
    @TableField("max_workcount")
    private Integer maxWorkCount;
    @TableField("normalwithdraw_minrange")
    private String normalWithdrawMinRange;
    @TableField("normalwithdraw_maxrange")
    private String normalWithdrawMaxRange;
    @TableField("largewithdraw_minrange")
    private String largeWithdrawMinRange;
    @TableField("largewithdraw_maxrange")
    private String largeWithdrawMaxRange;
    @TableField("normalwithdraw_daycount")
    private Integer normalWithdrawDayCount;

    @TableField("largewithdraw_daycount")
    private Integer largeWithdrawDayCount;
    @TableField("normaltransfer_minrange")
    private String normalTransferMinRange;
    @TableField("normaltransfer_maxrange")
    private String normalTransferMaxRange;
    @TableField("largetransfer_minrange")
    private String largeTransferMinRange;
    @TableField("largetransfer_maxrange")
    private String largeTransferMaxRange;
    @TableField("withdrawwork_count")
    private Integer withdrawWorkCount;
    @TableField("proxylevel1_rate")
    private BigDecimal proxyLevel1Rate;
    @TableField("proxylevel2_rate")
    private BigDecimal proxyLevel2Rate;
    @TableField("proxylevel3_rate")
    private BigDecimal proxyLevel3Rate;
    @TableField("normaltreasure_rate")
    private BigDecimal normalTreasureRate;
    @TableField("largetreasure_rate")
    private BigDecimal largeTreasureRate;
    @TableField("workpagerefresh_space")
    private Integer workPageRefreshSpace;
    @TableField("app_logo")
    private String appLogo;
    @TableField("novicetutorial_url")
    private String noviceTutorialUrl;
    @TableField("customerservice_url")
    private String customerServiceUrl;
    @TableField("promotion_url")
    private String promotionUrl;
    @TableField("init_redpack")
    private BigDecimal initRedPack;
    @TableField("register_count")
    private Long registerCount;
    @TableField("min_transferamount")
    private BigDecimal minTransferAmount;
    @TableField("partnerlevel_rate")
    private BigDecimal partnerLevelRate;
    @TableField("partnerlevelwork_count")
    private Integer partnerLevelWorkCount;
    @TableField("partnerlevelwork_space")
    private Integer partnerLevelWorkSpace;
    @TableField("partnerlevelwork_minrange")
    private String partnerLevelWorkMinRange;
    @TableField("partnerlevelwork_maxrange")
    private String partnerLevelWorkMaxRange;
    @TableField("partnerwithdraw_amount")
    private BigDecimal partnerWithdrawAmount;
    @TableField("partnerlevelinc_amount")
    private BigDecimal partnerLevelIncAmount;
    @TableField("partnerwithdraw_minrange")
    private String partnerWithdrawMinRange;
    @TableField("partnerwithdraw_maxrange")
    private String partnerWithdrawMaxRange;
    @TableField("partnerwithdraw_daycount")
    private Integer partnerWithdrawDayCount;
    @TableField("partnertransfer_minrange")
    private String partnerTransferMinRange;
    @TableField("partnertransfer_maxrange")
    private String partnerTransferMaxRange;
    @TableField("partnertreasure_rate")
    private BigDecimal partnerTreasureRate;

}

