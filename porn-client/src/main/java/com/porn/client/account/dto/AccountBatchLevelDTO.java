package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AccountBatchLevelDTO extends BaseDTO {

    @ApiModelProperty("账户ID列表")
    private List<Long> idList;

    @ApiModelProperty("账户级别")
    private Integer accountLevel;

    protected AccountBatchLevelDTO(AccountBatchLevelDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
        this.accountLevel = b.accountLevel;
    }

    public AccountBatchLevelDTO() {
    }

    public AccountBatchLevelDTO(List<Long> idList, Integer accountLevel) {

        this.idList = idList;
        this.accountLevel = accountLevel;

    }

    public static AccountBatchLevelDTOBuilder<?, ?> builder() {
        return new AccountBatchLevelDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountBatchLevelDTO;
    }

    public List<Long> getIdList() {

        return this.idList;

    }

    public void setIdList(List<Long> idList) {

        this.idList = idList;
    }

    public Integer getAccountLevel() {

        return this.accountLevel;

    }

    public void setAccountLevel(Integer accountLevel) {
        this.accountLevel = accountLevel;
    }

    private static final class AccountBatchLevelDTOBuilderImpl extends AccountBatchLevelDTOBuilder<AccountBatchLevelDTO, AccountBatchLevelDTOBuilderImpl> {
        private AccountBatchLevelDTOBuilderImpl() {
        }

        protected AccountBatchLevelDTOBuilderImpl self() {
            return this;
        }

        public AccountBatchLevelDTO build() {
            return new AccountBatchLevelDTO(this);
        }
    }

    public static abstract class AccountBatchLevelDTOBuilder<C extends AccountBatchLevelDTO, B extends AccountBatchLevelDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;
        private Integer accountLevel;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        public B accountLevel(Integer accountLevel) {
            this.accountLevel = accountLevel;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

