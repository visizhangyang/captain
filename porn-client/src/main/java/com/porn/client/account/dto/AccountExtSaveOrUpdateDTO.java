
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

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


    /* 15 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setExtType(Integer extType) {
        this.extType = extType;
    }

    public void setExtKey(String extKey) {
        this.extKey = extKey;
    }

    public void setExtValue(String extValue) {
        this.extValue = extValue;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountExtSaveOrUpdateDTO;
    }



    /* 16 */
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

    public static AccountExtSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new AccountExtSaveOrUpdateDTOBuilderImpl();
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

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private String extValue;
        private String attr1;
        private String attr2;
        private String attr3;

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

    public AccountExtSaveOrUpdateDTO(Long accountId, Integer extType, String extKey, String extValue, String attr1, String attr2, String attr3) {
        /* 17 */
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



    public Long getAccountId() {
        /* 22 */
        return this.accountId;

    }


    public Integer getExtType() {
        /* 25 */
        return this.extType;

    }


    public String getExtKey() {
        /* 28 */
        return this.extKey;

    }


    public String getExtValue() {
        /* 31 */
        return this.extValue;

    }


    public String getAttr1() {
        /* 34 */
        return this.attr1;

    }


    public String getAttr2() {
        /* 37 */
        return this.attr2;

    }


    public String getAttr3() {
        /* 40 */
        return this.attr3;

    }
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountExtSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */