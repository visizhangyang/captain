package com.porn.client.transfer.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

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

    protected TransferSaveOrUpdateDTO(TransferSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.srcAccountId = b.srcAccountId;
        this.srcAccountName = b.srcAccountName;
        this.dstAccountId = b.dstAccountId;
        this.dstAccountName = b.dstAccountName;
        this.amount = b.amount;
        this.tradePwd = b.tradePwd;
    }

    public TransferSaveOrUpdateDTO(Long srcAccountId, String srcAccountName, Long dstAccountId, String dstAccountName, BigDecimal amount, String tradePwd) {

        this.srcAccountId = srcAccountId;
        this.srcAccountName = srcAccountName;
        this.dstAccountId = dstAccountId;
        this.dstAccountName = dstAccountName;
        this.amount = amount;
        this.tradePwd = tradePwd;

    }

    public TransferSaveOrUpdateDTO() {
    }

    public static TransferSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new TransferSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof TransferSaveOrUpdateDTO;
    }

    public Long getSrcAccountId() {

        return this.srcAccountId;

    }

    public void setSrcAccountId(Long srcAccountId) {
        this.srcAccountId = srcAccountId;
    }

    public String getSrcAccountName() {

        return this.srcAccountName;

    }

    public void setSrcAccountName(String srcAccountName) {
        this.srcAccountName = srcAccountName;
    }

    public Long getDstAccountId() {

        return this.dstAccountId;

    }

    public void setDstAccountId(Long dstAccountId) {
        this.dstAccountId = dstAccountId;
    }

    public String getDstAccountName() {

        return this.dstAccountName;

    }

    public void setDstAccountName(String dstAccountName) {
        this.dstAccountName = dstAccountName;
    }

    public BigDecimal getAmount() {

        return this.amount;

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTradePwd() {

        return this.tradePwd;

    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
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
        private String dstAccountName;
        private BigDecimal amount;
        private String tradePwd;

        public B srcAccountId(Long srcAccountId) {
            this.srcAccountId = srcAccountId;
            return self();
        }

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

}

