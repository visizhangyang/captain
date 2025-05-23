package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AccountDeleteDTO
        extends BaseDTO {

    @ApiModelProperty("id列表")
    private List<Long> idList;

    protected AccountDeleteDTO(AccountDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public AccountDeleteDTO() {
    }

    public AccountDeleteDTO(List<Long> idList) {

        this.idList = idList;

    }

    public static AccountDeleteDTOBuilder<?, ?> builder() {
        return new AccountDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountDeleteDTO;
    }

    public List<Long> getIdList() {

        return this.idList;

    }

    public void setIdList(List<Long> idList) {

        this.idList = idList;
    }

    private static final class AccountDeleteDTOBuilderImpl extends AccountDeleteDTOBuilder<AccountDeleteDTO, AccountDeleteDTOBuilderImpl> {
        private AccountDeleteDTOBuilderImpl() {
        }

        protected AccountDeleteDTOBuilderImpl self() {
            return this;
        }

        public AccountDeleteDTO build() {
            return new AccountDeleteDTO(this);
        }
    }

    public static abstract class AccountDeleteDTOBuilder<C extends AccountDeleteDTO, B extends AccountDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

