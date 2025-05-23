package com.porn.client.rhamount.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class RhAmountSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("排序号")
    private Integer sortNo;

    protected RhAmountSaveOrUpdateDTO(RhAmountSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.amount = b.amount;
        this.sortNo = b.sortNo;
    }

    public RhAmountSaveOrUpdateDTO(BigDecimal amount, Integer sortNo) {

        this.amount = amount;
        this.sortNo = sortNo;

    }

    public RhAmountSaveOrUpdateDTO() {
    }

    public static RhAmountSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RhAmountSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RhAmountSaveOrUpdateDTO;
    }

    public BigDecimal getAmount() {

        return this.amount;

    }

    public void setAmount(BigDecimal amount) {

        this.amount = amount;
    }

    public Integer getSortNo() {

        return this.sortNo;

    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    private static final class RhAmountSaveOrUpdateDTOBuilderImpl extends RhAmountSaveOrUpdateDTOBuilder<RhAmountSaveOrUpdateDTO, RhAmountSaveOrUpdateDTOBuilderImpl> {
        private RhAmountSaveOrUpdateDTOBuilderImpl() {
        }

        protected RhAmountSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RhAmountSaveOrUpdateDTO build() {
            return new RhAmountSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RhAmountSaveOrUpdateDTOBuilder<C extends RhAmountSaveOrUpdateDTO, B extends RhAmountSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private BigDecimal amount;
        private Integer sortNo;

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        public B sortNo(Integer sortNo) {
            this.sortNo = sortNo;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

