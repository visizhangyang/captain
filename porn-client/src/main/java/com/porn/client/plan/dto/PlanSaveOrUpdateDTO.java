package com.porn.client.plan.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("计划保存/更新DTO")
public class PlanSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("范围值(小)")
    private BigDecimal minRange;

    @ApiModelProperty("范围值(大)")
    private BigDecimal maxRange;

    @ApiModelProperty("费用")
    private BigDecimal free;

    @ApiModelProperty("描述")
    private String desc;

    @ApiModelProperty("预计收益, %")
    private BigDecimal revenue;

    @ApiModelProperty("天数")
    private Integer days;

    @ApiModelProperty("语言类型, LangTypeEnum")
    private Integer langType;

    @ApiModelProperty("排序号")
    private Integer sortNo;

    @ApiModelProperty("额外加成")
    private BigDecimal extraBonus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getMinRange() {
        return this.minRange;
    }

    public void setMinRange(BigDecimal minRange) {
        this.minRange = minRange;
    }

    public BigDecimal getMaxRange() {
        return this.maxRange;
    }

    public void setMaxRange(BigDecimal maxRange) {
        this.maxRange = maxRange;
    }

    public BigDecimal getFree() {
        return this.free;
    }

    public void setFree(BigDecimal free) {
        this.free = free;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getRevenue() {
        return this.revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public Integer getDays() {
        return this.days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getLangType() {
        return this.langType;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public Integer getSortNo() {
        return this.sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public BigDecimal getExtraBonus() {
        return this.extraBonus;
    }

    public void setExtraBonus(BigDecimal extraBonus) {
        this.extraBonus = extraBonus;
    }

    // 保留原有的builder模式代码
//    protected PlanSaveOrUpdateDTO(PlanSaveOrUpdateDTOBuilder<?, ?> b) {
//        super(b);
//        this.title = b.title;
//        this.minRange = b.minRange;
//        this.maxRange = b.maxRange;
//        this.free = b.free;
//        this.desc = b.desc;
//        this.revenue = b.revenue;
//        this.days = b.days;
//        this.langType = b.langType;
//        this.sortNo = b.sortNo;
//        this.extraBonus = b.extraBonus;
//    }
}