package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountUpdateRemarkDTO
        extends BaseDTO {

    @ApiModelProperty("备注")
    private String remark;

    protected AccountUpdateRemarkDTO(AccountUpdateRemarkDTOBuilder<?, ?> b) {
        super(b);
        this.remark = b.remark;
    }

    public AccountUpdateRemarkDTO(String remark) {

        this.remark = remark;

    }

    public AccountUpdateRemarkDTO() {
    }

    public static AccountUpdateRemarkDTOBuilder<?, ?> builder() {
        return new AccountUpdateRemarkDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountUpdateRemarkDTO;
    }

    public String getRemark() {

        return this.remark;

    }

    public void setRemark(String remark) {

        this.remark = remark;
    }

    private static final class AccountUpdateRemarkDTOBuilderImpl extends AccountUpdateRemarkDTOBuilder<AccountUpdateRemarkDTO, AccountUpdateRemarkDTOBuilderImpl> {
        private AccountUpdateRemarkDTOBuilderImpl() {
        }

        protected AccountUpdateRemarkDTOBuilderImpl self() {
            return this;
        }

        public AccountUpdateRemarkDTO build() {
            return new AccountUpdateRemarkDTO(this);
        }
    }

    public static abstract class AccountUpdateRemarkDTOBuilder<C extends AccountUpdateRemarkDTO, B extends AccountUpdateRemarkDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String remark;

        public B remark(String remark) {
            this.remark = remark;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

