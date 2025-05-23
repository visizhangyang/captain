package com.porn.client.withdraw.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

public class WithdrawQueryDTO extends BaseDTO {
    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("状态, WithdrawStatusEnum")
    private Integer status;

    @ApiModelProperty("状态, WithdrawStatusEnum")
    private List<Integer> statusList;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    protected WithdrawQueryDTO(WithdrawQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.walletCode = b.walletCode;
        this.status = b.status;
        this.statusList = b.statusList;
        this.startTime = b.startTime;
    }

    public WithdrawQueryDTO(Long accountId, String walletCode, Integer status, List<Integer> statusList, LocalDateTime startTime) {
        this.accountId = accountId;
        this.walletCode = walletCode;
        this.status = status;
        this.statusList = statusList;
        this.startTime = startTime;
    }

    public WithdrawQueryDTO() {
    }

    public static WithdrawQueryDTOBuilder<?, ?> builder() {
        return new WithdrawQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WithdrawQueryDTO;
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

    public List<Integer> getStatusList() {
        return this.statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    private static final class WithdrawQueryDTOBuilderImpl extends WithdrawQueryDTOBuilder<WithdrawQueryDTO, WithdrawQueryDTOBuilderImpl> {
        private WithdrawQueryDTOBuilderImpl() {
        }

        protected WithdrawQueryDTOBuilderImpl self() {
            return this;
        }

        public WithdrawQueryDTO build() {
            return new WithdrawQueryDTO(this);
        }
    }

    public static abstract class WithdrawQueryDTOBuilder<C extends WithdrawQueryDTO, B extends WithdrawQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private String walletCode;
        private Integer status;
        private List<Integer> statusList;
        private LocalDateTime startTime;

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

        public B statusList(List<Integer> statusList) {
            this.statusList = statusList;
            return self();
        }

        public B startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

