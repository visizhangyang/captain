
package com.porn.client.reward.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

import java.math.BigDecimal;

public class TurntableSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("数字0")
     private BigDecimal num0;
    @ApiModelProperty("数字1")
     private BigDecimal num1;

    @ApiModelProperty("数字2")
     private BigDecimal num2;

    @ApiModelProperty("数字3")
     private BigDecimal num3;

    @ApiModelProperty("数字4")
     private BigDecimal num4;

    @ApiModelProperty("数字5")
     private BigDecimal num5;

    @ApiModelProperty("数字6")
     private BigDecimal num6;

    @ApiModelProperty("数字7")
     private BigDecimal num7;


    /* 17 */
    public void setNum0(BigDecimal num0) {
        this.num0 = num0;
    }

    public void setNum1(BigDecimal num1) {
        this.num1 = num1;
    }

    public void setNum2(BigDecimal num2) {
        this.num2 = num2;
    }

    public void setNum3(BigDecimal num3) {
        this.num3 = num3;
    }

    public void setNum4(BigDecimal num4) {
        this.num4 = num4;
    }

    public void setNum5(BigDecimal num5) {
        this.num5 = num5;
    }

    public void setNum6(BigDecimal num6) {
        this.num6 = num6;
    }

    public void setNum7(BigDecimal num7) {
        this.num7 = num7;
    }


    protected boolean canEqual(Object other) {
        return other instanceof TurntableSaveOrUpdateDTO;
    }



    /* 18 */
    protected TurntableSaveOrUpdateDTO(TurntableSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.num0 = b.num0;
        this.num1 = b.num1;
        this.num2 = b.num2;
        this.num3 = b.num3;
        this.num4 = b.num4;
        this.num5 = b.num5;
        this.num6 = b.num6;
        this.num7 = b.num7;
    }

    public static TurntableSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new TurntableSaveOrUpdateDTOBuilderImpl();
    }

    private static final class TurntableSaveOrUpdateDTOBuilderImpl extends TurntableSaveOrUpdateDTOBuilder<TurntableSaveOrUpdateDTO, TurntableSaveOrUpdateDTOBuilderImpl> {
        private TurntableSaveOrUpdateDTOBuilderImpl() {
        }

        protected TurntableSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public TurntableSaveOrUpdateDTO build() {
            return new TurntableSaveOrUpdateDTO(this);
        }
    }

    public static abstract class TurntableSaveOrUpdateDTOBuilder<C extends TurntableSaveOrUpdateDTO, B extends TurntableSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private BigDecimal num0;
        private BigDecimal num1;
        private BigDecimal num2;
        private BigDecimal num3;

        public B num0(BigDecimal num0) {
            this.num0 = num0;
            return self();
        }

        private BigDecimal num4;
        private BigDecimal num5;
        private BigDecimal num6;
        private BigDecimal num7;

        public B num1(BigDecimal num1) {
            this.num1 = num1;
            return self();
        }

        public B num2(BigDecimal num2) {
            this.num2 = num2;
            return self();
        }

        public B num3(BigDecimal num3) {
            this.num3 = num3;
            return self();
        }

        public B num4(BigDecimal num4) {
            this.num4 = num4;
            return self();
        }

        public B num5(BigDecimal num5) {
            this.num5 = num5;
            return self();
        }

        public B num6(BigDecimal num6) {
            this.num6 = num6;
            return self();
        }

        public B num7(BigDecimal num7) {
            this.num7 = num7;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public TurntableSaveOrUpdateDTO(BigDecimal num0, BigDecimal num1, BigDecimal num2, BigDecimal num3, BigDecimal num4, BigDecimal num5, BigDecimal num6, BigDecimal num7) {
        /* 19 */
        this.num0 = num0;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.num7 = num7;

    }


    public TurntableSaveOrUpdateDTO() {
    }



    public BigDecimal getNum0() {
        /* 24 */
        return this.num0;

    }


    public BigDecimal getNum1() {
        /* 27 */
        return this.num1;

    }


    public BigDecimal getNum2() {
        /* 30 */
        return this.num2;

    }


    public BigDecimal getNum3() {
        /* 33 */
        return this.num3;

    }


    public BigDecimal getNum4() {
        /* 36 */
        return this.num4;

    }


    public BigDecimal getNum5() {
        /* 39 */
        return this.num5;

    }


    public BigDecimal getNum6() {
        /* 42 */
        return this.num6;

    }


    public BigDecimal getNum7() {
        /* 45 */
        return this.num7;

    }
}


