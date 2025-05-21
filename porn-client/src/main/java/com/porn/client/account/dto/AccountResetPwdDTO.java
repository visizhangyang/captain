
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountResetPwdDTO
         extends BaseDTO {

    @ApiModelProperty("重置类型, 0-登录, 1-交易")
     private Integer resetType;



    public void setResetType(Integer resetType) {
        /* 14 */
        this.resetType = resetType;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountResetPwdDTO;
    }



    /* 15 */
    protected AccountResetPwdDTO(AccountResetPwdDTOBuilder<?, ?> b) {
        super(b);
        this.resetType = b.resetType;
    }

    public static AccountResetPwdDTOBuilder<?, ?> builder() {
        return new AccountResetPwdDTOBuilderImpl();
    }

    private static final class AccountResetPwdDTOBuilderImpl extends AccountResetPwdDTOBuilder<AccountResetPwdDTO, AccountResetPwdDTOBuilderImpl> {
        private AccountResetPwdDTOBuilderImpl() {
        }

        protected AccountResetPwdDTOBuilderImpl self() {
            return this;
        }

        public AccountResetPwdDTO build() {
            return new AccountResetPwdDTO(this);
        }
    }

    public static abstract class AccountResetPwdDTOBuilder<C extends AccountResetPwdDTO, B extends AccountResetPwdDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B resetType(Integer resetType) {
            this.resetType = resetType;
            return self();
        }

        private Integer resetType;

        protected abstract B self();

        public abstract C build();


    }


    public AccountResetPwdDTO() {
    }



    public Integer getResetType() {
        /* 20 */
        return this.resetType;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountResetPwdDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */