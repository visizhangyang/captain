package com.porn.client.transfer.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class TransferQueryDTO
        extends BaseDTO {

    @ApiModelProperty("源账户ID")
    private Long srcAccountId;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    protected TransferQueryDTO(TransferQueryDTOBuilder<?, ?> b) {
        super(b);
        this.srcAccountId = b.srcAccountId;
        this.startTime = b.startTime;
    }

    public TransferQueryDTO(Long srcAccountId, LocalDateTime startTime) {

        this.srcAccountId = srcAccountId;
        this.startTime = startTime;

    }

    public TransferQueryDTO() {
    }

    public static TransferQueryDTOBuilder<?, ?> builder() {
        return new TransferQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof TransferQueryDTO;
    }

    public Long getSrcAccountId() {

        return this.srcAccountId;

    }

    public void setSrcAccountId(Long srcAccountId) {

        this.srcAccountId = srcAccountId;
    }

    public LocalDateTime getStartTime() {

        return this.startTime;

    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    private static final class TransferQueryDTOBuilderImpl extends TransferQueryDTOBuilder<TransferQueryDTO, TransferQueryDTOBuilderImpl> {
        private TransferQueryDTOBuilderImpl() {
        }

        protected TransferQueryDTOBuilderImpl self() {
            return this;
        }

        public TransferQueryDTO build() {
            return new TransferQueryDTO(this);
        }
    }

    public static abstract class TransferQueryDTOBuilder<C extends TransferQueryDTO, B extends TransferQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long srcAccountId;
        private LocalDateTime startTime;

        public B srcAccountId(Long srcAccountId) {
            this.srcAccountId = srcAccountId;
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

