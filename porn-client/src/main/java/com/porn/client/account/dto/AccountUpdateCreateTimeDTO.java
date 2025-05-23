package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class AccountUpdateCreateTimeDTO
        extends BaseDTO {

    @ApiModelProperty("更新时间")
    private LocalDateTime createTime;

    protected AccountUpdateCreateTimeDTO(AccountUpdateCreateTimeDTOBuilder<?, ?> b) {
        super(b);
        this.createTime = b.createTime;
    }

    public AccountUpdateCreateTimeDTO(LocalDateTime createTime) {

        this.createTime = createTime;

    }

    public AccountUpdateCreateTimeDTO() {
    }

    public static AccountUpdateCreateTimeDTOBuilder<?, ?> builder() {
        return new AccountUpdateCreateTimeDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountUpdateCreateTimeDTO;
    }

    public LocalDateTime getCreateTime() {

        return this.createTime;

    }

    public void setCreateTime(LocalDateTime createTime) {

        this.createTime = createTime;
    }

    private static final class AccountUpdateCreateTimeDTOBuilderImpl extends AccountUpdateCreateTimeDTOBuilder<AccountUpdateCreateTimeDTO, AccountUpdateCreateTimeDTOBuilderImpl> {
        private AccountUpdateCreateTimeDTOBuilderImpl() {
        }

        protected AccountUpdateCreateTimeDTOBuilderImpl self() {
            return this;
        }

        public AccountUpdateCreateTimeDTO build() {
            return new AccountUpdateCreateTimeDTO(this);
        }
    }

    public static abstract class AccountUpdateCreateTimeDTOBuilder<C extends AccountUpdateCreateTimeDTO, B extends AccountUpdateCreateTimeDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private LocalDateTime createTime;

        public B createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

