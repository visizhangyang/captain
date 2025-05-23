package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AccountExtQueryDTO extends BaseDTO {
    @ApiModelProperty("账户id")
    private Long accountId;
    @ApiModelProperty("账户id列表")
    private List<Long> accountIdList;
    @ApiModelProperty("扩展类型, AccountExtTypeEnum")
    private Integer extType;
    @ApiModelProperty("扩展类型, AccountExtTypeEnum")
    private List<Integer> extTypeList;

    @ApiModelProperty("扩展key")
    private String extKey;

    @ApiModelProperty("扩展key列表")
    private List<String> extKeyList;

    @ApiModelProperty("冗余字段1")
    private String attr1;

    @ApiModelProperty("冗余字段2")
    private String attr2;

    @ApiModelProperty("冗余字段3")
    private String attr3;

    protected AccountExtQueryDTO(AccountExtQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountIdList = b.accountIdList;
        this.extType = b.extType;
        this.extTypeList = b.extTypeList;
        this.extKey = b.extKey;
        this.extKeyList = b.extKeyList;
        this.attr1 = b.attr1;
        this.attr2 = b.attr2;
        this.attr3 = b.attr3;
    }

    public AccountExtQueryDTO(Long accountId, List<Long> accountIdList, Integer extType, List<Integer> extTypeList, String extKey, List<String> extKeyList, String attr1, String attr2, String attr3) {

        this.accountId = accountId;
        this.accountIdList = accountIdList;
        this.extType = extType;
        this.extTypeList = extTypeList;
        this.extKey = extKey;
        this.extKeyList = extKeyList;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;

    }

    public AccountExtQueryDTO() {
    }

    public static AccountExtQueryDTOBuilder<?, ?> builder() {
        return new AccountExtQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountExtQueryDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<Long> getAccountIdList() {

        return this.accountIdList;

    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public Integer getExtType() {

        return this.extType;

    }

    public void setExtType(Integer extType) {
        this.extType = extType;
    }

    public List<Integer> getExtTypeList() {

        return this.extTypeList;

    }

    public void setExtTypeList(List<Integer> extTypeList) {
        this.extTypeList = extTypeList;
    }

    public String getExtKey() {

        return this.extKey;

    }

    public void setExtKey(String extKey) {
        this.extKey = extKey;
    }

    public List<String> getExtKeyList() {

        return this.extKeyList;

    }

    public void setExtKeyList(List<String> extKeyList) {
        this.extKeyList = extKeyList;
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

    private static final class AccountExtQueryDTOBuilderImpl extends AccountExtQueryDTOBuilder<AccountExtQueryDTO, AccountExtQueryDTOBuilderImpl> {
        private AccountExtQueryDTOBuilderImpl() {
        }

        protected AccountExtQueryDTOBuilderImpl self() {
            return this;
        }

        public AccountExtQueryDTO build() {
            return new AccountExtQueryDTO(this);
        }
    }

    public static abstract class AccountExtQueryDTOBuilder<C extends AccountExtQueryDTO, B extends AccountExtQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private List<Long> accountIdList;
        private Integer extType;
        private List<Integer> extTypeList;
        private String extKey;
        private List<String> extKeyList;
        private String attr1;
        private String attr2;
        private String attr3;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B accountIdList(List<Long> accountIdList) {
            this.accountIdList = accountIdList;
            return self();
        }

        public B extType(Integer extType) {
            this.extType = extType;
            return self();
        }

        public B extTypeList(List<Integer> extTypeList) {
            this.extTypeList = extTypeList;
            return self();
        }

        public B extKey(String extKey) {
            this.extKey = extKey;
            return self();
        }

        public B extKeyList(List<String> extKeyList) {
            this.extKeyList = extKeyList;
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

