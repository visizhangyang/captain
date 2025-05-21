
package com.porn.client.transfer.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

import java.math.BigDecimal;

 public class TransferSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("发起转账的ID")
     private Long srcAccountId;

    @ApiModelProperty("发起转账的名称")
     private String srcAccountName;

    @ApiModelProperty("目标转账的ID")
     private Long dstAccountId;

    @ApiModelProperty("目标转账的名称")
     private String dstAccountName;

    @ApiModelProperty("金额")
     private BigDecimal amount;

    @ApiModelProperty("交易密码")
     private String tradePwd;


    /* 16 */
    public void setSrcAccountId(Long srcAccountId) {
        this.srcAccountId = srcAccountId;
    }

    public void setSrcAccountName(String srcAccountName) {
        this.srcAccountName = srcAccountName;
    }

    public void setDstAccountId(Long dstAccountId) {
        this.dstAccountId = dstAccountId;
    }

    public void setDstAccountName(String dstAccountName) {
        this.dstAccountName = dstAccountName;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }


    protected boolean canEqual(Object other) {
        return other instanceof TransferSaveOrUpdateDTO;
    }



    /* 17 */
    protected TransferSaveOrUpdateDTO(TransferSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.srcAccountId = b.srcAccountId;
        this.srcAccountName = b.srcAccountName;
        this.dstAccountId = b.dstAccountId;
        this.dstAccountName = b.dstAccountName;
        this.amount = b.amount;
        this.tradePwd = b.tradePwd;
    }

    public static TransferSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new TransferSaveOrUpdateDTOBuilderImpl();
    }

    private static final class TransferSaveOrUpdateDTOBuilderImpl extends TransferSaveOrUpdateDTOBuilder<TransferSaveOrUpdateDTO, TransferSaveOrUpdateDTOBuilderImpl> {
        private TransferSaveOrUpdateDTOBuilderImpl() {
        }

        protected TransferSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public TransferSaveOrUpdateDTO build() {
            return new TransferSaveOrUpdateDTO(this);
        }
    }

    public static abstract class TransferSaveOrUpdateDTOBuilder<C extends TransferSaveOrUpdateDTO, B extends TransferSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long srcAccountId;
        private String srcAccountName;
        private Long dstAccountId;

        public B srcAccountId(Long srcAccountId) {
            this.srcAccountId = srcAccountId;
            return self();
        }

        private String dstAccountName;
        private BigDecimal amount;
        private String tradePwd;

        public B srcAccountName(String srcAccountName) {
            this.srcAccountName = srcAccountName;
            return self();
        }

        public B dstAccountId(Long dstAccountId) {
            this.dstAccountId = dstAccountId;
            return self();
        }

        public B dstAccountName(String dstAccountName) {
            this.dstAccountName = dstAccountName;
            return self();
        }

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        public B tradePwd(String tradePwd) {
            this.tradePwd = tradePwd;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public TransferSaveOrUpdateDTO(Long srcAccountId, String srcAccountName, Long dstAccountId, String dstAccountName, BigDecimal amount, String tradePwd) {
        /* 18 */
        this.srcAccountId = srcAccountId;
        this.srcAccountName = srcAccountName;
        this.dstAccountId = dstAccountId;
        this.dstAccountName = dstAccountName;
        this.amount = amount;
        this.tradePwd = tradePwd;

    }


    public TransferSaveOrUpdateDTO() {
    }



    public Long getSrcAccountId() {
        /* 23 */
        return this.srcAccountId;

    }


    public String getSrcAccountName() {
        /* 26 */
        return this.srcAccountName;

    }


    public Long getDstAccountId() {
        /* 29 */
        return this.dstAccountId;

    }


    public String getDstAccountName() {
        /* 32 */
        return this.dstAccountName;

    }


    public BigDecimal getAmount() {
        /* 35 */
        return this.amount;

    }


    public String getTradePwd() {
        /* 38 */
        return this.tradePwd;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/transfer/dto/TransferSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */