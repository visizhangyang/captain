package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountOfflineDTO
        extends BaseDTO {

    @ApiModelProperty("活跃的token")
    private String activeToken;

    protected AccountOfflineDTO(AccountOfflineDTOBuilder<?, ?> b) {
        super(b);
        this.activeToken = b.activeToken;
    }

    public AccountOfflineDTO(String activeToken) {

        this.activeToken = activeToken;

    }

    public AccountOfflineDTO() {
    }

    public static AccountOfflineDTOBuilder<?, ?> builder() {
        return new AccountOfflineDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountOfflineDTO;
    }

    public String getActiveToken() {

        return this.activeToken;

    }

    public void setActiveToken(String activeToken) {

        this.activeToken = activeToken;
    }

    private static final class AccountOfflineDTOBuilderImpl extends AccountOfflineDTOBuilder<AccountOfflineDTO, AccountOfflineDTOBuilderImpl> {
        private AccountOfflineDTOBuilderImpl() {
        }

        protected AccountOfflineDTOBuilderImpl self() {
            return this;
        }

        public AccountOfflineDTO build() {
            return new AccountOfflineDTO(this);
        }
    }

    public static abstract class AccountOfflineDTOBuilder<C extends AccountOfflineDTO, B extends AccountOfflineDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String activeToken;

        public B activeToken(String activeToken) {
            this.activeToken = activeToken;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

