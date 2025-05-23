package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountChangeParentDTO
        extends BaseDTO {

    @ApiModelProperty("父节点ID")
    private Long parentId;

    protected AccountChangeParentDTO(AccountChangeParentDTOBuilder<?, ?> b) {
        super(b);
        this.parentId = b.parentId;
    }

    public AccountChangeParentDTO(Long parentId) {

        this.parentId = parentId;

    }

    public AccountChangeParentDTO() {
    }

    public static AccountChangeParentDTOBuilder<?, ?> builder() {
        return new AccountChangeParentDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountChangeParentDTO;
    }

    public Long getParentId() {

        return this.parentId;

    }

    public void setParentId(Long parentId) {

        this.parentId = parentId;
    }

    private static final class AccountChangeParentDTOBuilderImpl extends AccountChangeParentDTOBuilder<AccountChangeParentDTO, AccountChangeParentDTOBuilderImpl> {
        private AccountChangeParentDTOBuilderImpl() {
        }

        protected AccountChangeParentDTOBuilderImpl self() {
            return this;
        }

        public AccountChangeParentDTO build() {
            return new AccountChangeParentDTO(this);
        }
    }

    public static abstract class AccountChangeParentDTOBuilder<C extends AccountChangeParentDTO, B extends AccountChangeParentDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long parentId;

        public B parentId(Long parentId) {
            this.parentId = parentId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

