package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountRobotCreateDTO
        extends BaseDTO {

    @ApiModelProperty("创建次数")
    private Integer createCount;

    protected AccountRobotCreateDTO(AccountRobotCreateDTOBuilder<?, ?> b) {
        super(b);
        this.createCount = b.createCount;
    }

    public AccountRobotCreateDTO(Integer createCount) {

        this.createCount = createCount;

    }

    public AccountRobotCreateDTO() {
    }

    public static AccountRobotCreateDTOBuilder<?, ?> builder() {
        return new AccountRobotCreateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountRobotCreateDTO;
    }

    public Integer getCreateCount() {

        return this.createCount;

    }

    public void setCreateCount(Integer createCount) {

        this.createCount = createCount;
    }

    private static final class AccountRobotCreateDTOBuilderImpl extends AccountRobotCreateDTOBuilder<AccountRobotCreateDTO, AccountRobotCreateDTOBuilderImpl> {
        private AccountRobotCreateDTOBuilderImpl() {
        }

        protected AccountRobotCreateDTOBuilderImpl self() {
            return this;
        }

        public AccountRobotCreateDTO build() {
            return new AccountRobotCreateDTO(this);
        }
    }

    public static abstract class AccountRobotCreateDTOBuilder<C extends AccountRobotCreateDTO, B extends AccountRobotCreateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer createCount;

        public B createCount(Integer createCount) {
            this.createCount = createCount;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

