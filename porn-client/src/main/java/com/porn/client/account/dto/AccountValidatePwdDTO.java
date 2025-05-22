
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountValidatePwdDTO extends BaseDTO {

    @ApiModelProperty("验证类型, AccountValidateTypeEnum")
     private Integer type;

    @ApiModelProperty("密码")
     private String pwd;



    public void setType(Integer type) {
        /* 15 */
        this.type = type;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountValidatePwdDTO;
    }



    /* 16 */
    protected AccountValidatePwdDTO(AccountValidatePwdDTOBuilder<?, ?> b) {
        super(b);
        this.type = b.type;
        this.pwd = b.pwd;
    }

    public static AccountValidatePwdDTOBuilder<?, ?> builder() {
        return new AccountValidatePwdDTOBuilderImpl();
    }

    private static final class AccountValidatePwdDTOBuilderImpl extends AccountValidatePwdDTOBuilder<AccountValidatePwdDTO, AccountValidatePwdDTOBuilderImpl> {
        private AccountValidatePwdDTOBuilderImpl() {
        }

        protected AccountValidatePwdDTOBuilderImpl self() {
            return this;
        }

        public AccountValidatePwdDTO build() {
            return new AccountValidatePwdDTO(this);
        }
    }

    public static abstract class AccountValidatePwdDTOBuilder<C extends AccountValidatePwdDTO, B extends AccountValidatePwdDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer type;

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        private String pwd;

        public B pwd(String pwd) {
            this.pwd = pwd;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public AccountValidatePwdDTO(Integer type, String pwd) {
        /* 17 */
        this.type = type;
        this.pwd = pwd;

    }


    public AccountValidatePwdDTO() {
    }



    public Integer getType() {
        /* 22 */
        return this.type;

    }


    public String getPwd() {
        /* 25 */
        return this.pwd;

    }

}


