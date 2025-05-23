package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AccountEnableOrDisableDTO extends BaseDTO {

    @ApiModelProperty("id列表")
    private List<Long> idList;

    @ApiModelProperty("0-状态, 1-搬砖, 2-提现, 3-转账, 4-搬砖自动审核, 5-重点关注, 6-自动搬砖, 7-上传, 8-计划, 9-抽奖")
    private Integer type;

    @ApiModelProperty("状态, 1-启用, 0-禁用")
    private Integer status;

    protected AccountEnableOrDisableDTO(AccountEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
        this.type = b.type;
        this.status = b.status;
    }

    public AccountEnableOrDisableDTO(List<Long> idList, Integer type, Integer status) {

        this.idList = idList;
        this.type = type;
        this.status = status;

    }

    public AccountEnableOrDisableDTO() {
    }

    public static AccountEnableOrDisableDTOBuilder<?, ?> builder() {
        return new AccountEnableOrDisableDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountEnableOrDisableDTO;
    }

    public List<Long> getIdList() {

        return this.idList;

    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private static final class AccountEnableOrDisableDTOBuilderImpl extends AccountEnableOrDisableDTOBuilder<AccountEnableOrDisableDTO, AccountEnableOrDisableDTOBuilderImpl> {
        private AccountEnableOrDisableDTOBuilderImpl() {
        }

        protected AccountEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public AccountEnableOrDisableDTO build() {
            return new AccountEnableOrDisableDTO(this);
        }
    }

    public static abstract class AccountEnableOrDisableDTOBuilder<C extends AccountEnableOrDisableDTO, B extends AccountEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;
        private Integer type;
        private Integer status;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

