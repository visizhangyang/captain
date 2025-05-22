
package com.porn.client.rhamount.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.math.BigDecimal;







 public class RhAmountSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("金额")
     private BigDecimal amount;

    @ApiModelProperty("排序号")
     private Integer sortNo;



    public void setAmount(BigDecimal amount) {
        /* 16 */
        this.amount = amount;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RhAmountSaveOrUpdateDTO;
    }



    /* 17 */
    protected RhAmountSaveOrUpdateDTO(RhAmountSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.amount = b.amount;
        this.sortNo = b.sortNo;
    }

    public static RhAmountSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RhAmountSaveOrUpdateDTOBuilderImpl();
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

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        private Integer sortNo;

        public B sortNo(Integer sortNo) {
            this.sortNo = sortNo;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public RhAmountSaveOrUpdateDTO(BigDecimal amount, Integer sortNo) {
        /* 18 */
        this.amount = amount;
        this.sortNo = sortNo;

    }


    public RhAmountSaveOrUpdateDTO() {
    }



    public BigDecimal getAmount() {
        /* 23 */
        return this.amount;

    }


    public Integer getSortNo() {
        /* 26 */
        return this.sortNo;

    }

}


