package com.porn.client.recharge.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

public class RechargeQueryDTO extends BaseDTO {
    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("状态, RechargeStatusEnum")
    private Integer status;

    @ApiModelProperty("hash值列表")
    private List<String> hashList;

    @ApiModelProperty("存在hash")
    private Boolean hasHash;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    protected RechargeQueryDTO(RechargeQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.walletCode = b.walletCode;
        this.status = b.status;
        this.hashList = b.hashList;
        this.hasHash = b.hasHash;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
    }

    public RechargeQueryDTO(Long accountId, String walletCode, Integer status, List<String> hashList, Boolean hasHash, LocalDateTime startTime, LocalDateTime endTime) {

        this.accountId = accountId;
        this.walletCode = walletCode;
        this.status = status;
        this.hashList = hashList;
        this.hasHash = hasHash;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public RechargeQueryDTO() {
    }

    public static RechargeQueryDTOBuilder<?, ?> builder() {
        return new RechargeQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RechargeQueryDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getWalletCode() {

        return this.walletCode;

    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getHashList() {

        return this.hashList;

    }

    public void setHashList(List<String> hashList) {
        this.hashList = hashList;
    }

    public Boolean getHasHash() {

        return this.hasHash;

    }

    public void setHasHash(Boolean hasHash) {
        this.hasHash = hasHash;
    }

    public LocalDateTime getStartTime() {

        return this.startTime;

    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {

        return this.endTime;

    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    private static final class RechargeQueryDTOBuilderImpl extends RechargeQueryDTOBuilder<RechargeQueryDTO, RechargeQueryDTOBuilderImpl> {
        private RechargeQueryDTOBuilderImpl() {
        }

        protected RechargeQueryDTOBuilderImpl self() {
            return this;
        }

        public RechargeQueryDTO build() {
            return new RechargeQueryDTO(this);
        }
    }

    public static abstract class RechargeQueryDTOBuilder<C extends RechargeQueryDTO, B extends RechargeQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private String walletCode;
        private Integer status;
        private List<String> hashList;
        private Boolean hasHash;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B walletCode(String walletCode) {
            this.walletCode = walletCode;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B hashList(List<String> hashList) {
            this.hashList = hashList;
            return self();
        }

        public B hasHash(Boolean hasHash) {
            this.hasHash = hasHash;
            return self();
        }

        public B startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return self();
        }

        public B endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

