
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


 public class AccountModifyPwdDTO extends BaseDTO {

    @ApiModelProperty("类型, AccountModifyPwdTypeEnum")
     private Integer type;

    @ApiModelProperty("是否验证密码")
     private boolean checkPwd;

    @ApiModelProperty("旧密码")
     private String oldPwd;

    @ApiModelProperty("新密码")
     private String newPwd;


    /* 15 */
    public void setType(Integer type) {
        this.type = type;
    }

    public void setCheckPwd(boolean checkPwd) {
        this.checkPwd = checkPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountModifyPwdDTO;
    }



    /* 16 */
    protected AccountModifyPwdDTO(AccountModifyPwdDTOBuilder<?, ?> b) {
        super(b);
        this.type = b.type;
        this.checkPwd = b.checkPwd;
        this.oldPwd = b.oldPwd;
        this.newPwd = b.newPwd;
    }

    public static AccountModifyPwdDTOBuilder<?, ?> builder() {
        return new AccountModifyPwdDTOBuilderImpl();
    }

    private static final class AccountModifyPwdDTOBuilderImpl extends AccountModifyPwdDTOBuilder<AccountModifyPwdDTO, AccountModifyPwdDTOBuilderImpl> {
        private AccountModifyPwdDTOBuilderImpl() {
        }

        protected AccountModifyPwdDTOBuilderImpl self() {
            return this;
        }

        public AccountModifyPwdDTO build() {
            return new AccountModifyPwdDTO(this);
        }
    }

    public static abstract class AccountModifyPwdDTOBuilder<C extends AccountModifyPwdDTO, B extends AccountModifyPwdDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer type;
        private boolean checkPwd;

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        private String oldPwd;
        private String newPwd;

        public B checkPwd(boolean checkPwd) {
            this.checkPwd = checkPwd;
            return self();
        }

        public B oldPwd(String oldPwd) {
            this.oldPwd = oldPwd;
            return self();
        }

        public B newPwd(String newPwd) {
            this.newPwd = newPwd;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public AccountModifyPwdDTO(Integer type, boolean checkPwd, String oldPwd, String newPwd) {
        /* 17 */
        this.type = type;
        this.checkPwd = checkPwd;
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;

    }


    public AccountModifyPwdDTO() {
    }



    public Integer getType() {
        /* 22 */
        return this.type;

    }


    public boolean isCheckPwd() {
        /* 25 */
        return this.checkPwd;

    }


    public String getOldPwd() {
        /* 28 */
        return this.oldPwd;

    }


    public String getNewPwd() {
        /* 31 */
        return this.newPwd;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountModifyPwdDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */