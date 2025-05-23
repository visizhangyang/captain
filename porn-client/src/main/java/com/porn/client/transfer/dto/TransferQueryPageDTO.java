package com.porn.client.transfer.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class TransferQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("源账户ID")
    private Long srcAccountId;
    @ApiModelProperty("源账户名称(模糊)")
    private String srcLkAccountName;

    @ApiModelProperty("接收账户ID")
    private Long dstAccountId;

    @ApiModelProperty("接收账户名称(模糊)")
    private String dstLkAccountName;

    @ApiModelProperty("转账状态, TransferStatusEnum")
    private Integer transferStatus;

    @ApiModelProperty("源账户备注")
    private String lkSrcAccountRemark;

    @ApiModelProperty("目标账户备注")
    private String lkDstAccountRemark;

    protected TransferQueryPageDTO(TransferQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.srcAccountId = b.srcAccountId;
        this.srcLkAccountName = b.srcLkAccountName;
        this.dstAccountId = b.dstAccountId;
        this.dstLkAccountName = b.dstLkAccountName;
        this.transferStatus = b.transferStatus;
        this.lkSrcAccountRemark = b.lkSrcAccountRemark;
        this.lkDstAccountRemark = b.lkDstAccountRemark;
    }

    public TransferQueryPageDTO(Long srcAccountId, String srcLkAccountName, Long dstAccountId, String dstLkAccountName, Integer transferStatus, String lkSrcAccountRemark, String lkDstAccountRemark) {

        this.srcAccountId = srcAccountId;
        this.srcLkAccountName = srcLkAccountName;
        this.dstAccountId = dstAccountId;
        this.dstLkAccountName = dstLkAccountName;
        this.transferStatus = transferStatus;
        this.lkSrcAccountRemark = lkSrcAccountRemark;
        this.lkDstAccountRemark = lkDstAccountRemark;

    }

    public TransferQueryPageDTO() {
    }

    public static TransferQueryPageDTOBuilder<?, ?> builder() {
        return new TransferQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof TransferQueryPageDTO;
    }

    public Long getSrcAccountId() {

        return this.srcAccountId;

    }

    public void setSrcAccountId(Long srcAccountId) {
        this.srcAccountId = srcAccountId;
    }

    public String getSrcLkAccountName() {

        return this.srcLkAccountName;

    }

    public void setSrcLkAccountName(String srcLkAccountName) {
        this.srcLkAccountName = srcLkAccountName;
    }

    public Long getDstAccountId() {

        return this.dstAccountId;

    }

    public void setDstAccountId(Long dstAccountId) {
        this.dstAccountId = dstAccountId;
    }

    public String getDstLkAccountName() {

        return this.dstLkAccountName;

    }

    public void setDstLkAccountName(String dstLkAccountName) {
        this.dstLkAccountName = dstLkAccountName;
    }

    public Integer getTransferStatus() {

        return this.transferStatus;

    }

    public void setTransferStatus(Integer transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getLkSrcAccountRemark() {

        return this.lkSrcAccountRemark;

    }

    public void setLkSrcAccountRemark(String lkSrcAccountRemark) {
        this.lkSrcAccountRemark = lkSrcAccountRemark;
    }

    public String getLkDstAccountRemark() {

        return this.lkDstAccountRemark;

    }

    public void setLkDstAccountRemark(String lkDstAccountRemark) {
        this.lkDstAccountRemark = lkDstAccountRemark;
    }

    private static final class TransferQueryPageDTOBuilderImpl extends TransferQueryPageDTOBuilder<TransferQueryPageDTO, TransferQueryPageDTOBuilderImpl> {
        private TransferQueryPageDTOBuilderImpl() {
        }

        protected TransferQueryPageDTOBuilderImpl self() {
            return this;
        }

        public TransferQueryPageDTO build() {
            return new TransferQueryPageDTO(this);
        }
    }

    public static abstract class TransferQueryPageDTOBuilder<C extends TransferQueryPageDTO, B extends TransferQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Long srcAccountId;
        private String srcLkAccountName;
        private Long dstAccountId;
        private String dstLkAccountName;
        private Integer transferStatus;
        private String lkSrcAccountRemark;
        private String lkDstAccountRemark;

        public B srcAccountId(Long srcAccountId) {
            this.srcAccountId = srcAccountId;
            return self();
        }

        public B srcLkAccountName(String srcLkAccountName) {
            this.srcLkAccountName = srcLkAccountName;
            return self();
        }

        public B dstAccountId(Long dstAccountId) {
            this.dstAccountId = dstAccountId;
            return self();
        }

        public B dstLkAccountName(String dstLkAccountName) {
            this.dstLkAccountName = dstLkAccountName;
            return self();
        }

        public B transferStatus(Integer transferStatus) {
            this.transferStatus = transferStatus;
            return self();
        }

        public B lkSrcAccountRemark(String lkSrcAccountRemark) {
            this.lkSrcAccountRemark = lkSrcAccountRemark;
            return self();
        }

        public B lkDstAccountRemark(String lkDstAccountRemark) {
            this.lkDstAccountRemark = lkDstAccountRemark;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

