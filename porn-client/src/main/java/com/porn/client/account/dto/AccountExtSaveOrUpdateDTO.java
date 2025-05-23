package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountExtSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("账户id")
    private Long accountId;
    @ApiModelProperty("扩展类型, AccountExtTypeEnum")
    private Integer extType;

    @ApiModelProperty("扩展key")
    private String extKey;

    @ApiModelProperty("扩展值")
    private String extValue;

    @ApiModelProperty("冗余字段1")
    private String attr1;

    @ApiModelProperty("冗余字段2")
    private String attr2;

    @ApiModelProperty("冗余字段3")
    private String attr3;

    protected AccountExtSaveOrUpdateDTO(AccountExtSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.extType = b.extType;
        this.extKey = b.extKey;
        this.extValue = b.extValue;
        this.attr1 = b.attr1;
        this.attr2 = b.attr2;
        this.attr3 = b.attr3;
    }

    public AccountExtSaveOrUpdateDTO(Long accountId, Integer extType, String extKey, String extValue, String attr1, String attr2, String attr3) {

        this.accountId = accountId;
        this.extType = extType;
        this.extKey = extKey;
        this.extValue = extValue;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;

    }

    public AccountExtSaveOrUpdateDTO() {
    }

    public static AccountExtSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new AccountExtSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountExtSaveOrUpdateDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getExtType() {

        return this.extType;

    }

    public void setExtType(Integer extType) {
        this.extType = extType;
    }

    public String getExtKey() {

        return this.extKey;

    }

    public void setExtKey(String extKey) {
        this.extKey = extKey;
    }

    public String getExtValue() {

        return this.extValue;

    }

    public void setExtValue(String extValue) {
        this.extValue = extValue;
    }

    public String getAttr1() {

        return this.attr1;

    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {

        return this.attr2;

    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {

        return this.attr3;

    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    private static final class AccountExtSaveOrUpdateDTOBuilderImpl extends AccountExtSaveOrUpdateDTOBuilder<AccountExtSaveOrUpdateDTO, AccountExtSaveOrUpdateDTOBuilderImpl> {
        private AccountExtSaveOrUpdateDTOBuilderImpl() {
        }

        protected AccountExtSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public AccountExtSaveOrUpdateDTO build() {
            return new AccountExtSaveOrUpdateDTO(this);
        }
    }

    public static abstract class AccountExtSaveOrUpdateDTOBuilder<C extends AccountExtSaveOrUpdateDTO, B extends AccountExtSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private Integer extType;
        private String extKey;
        private String extValue;
        private String attr1;
        private String attr2;
        private String attr3;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B extType(Integer extType) {
            this.extType = extType;
            return self();
        }

        public B extKey(String extKey) {
            this.extKey = extKey;
            return self();
        }

        public B extValue(String extValue) {
            this.extValue = extValue;
            return self();
        }

        public B attr1(String attr1) {
            this.attr1 = attr1;
            return self();
        }

        public B attr2(String attr2) {
            this.attr2 = attr2;
            return self();
        }

        public B attr3(String attr3) {
            this.attr3 = attr3;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

