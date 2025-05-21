
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountLevelDTO
         extends BaseDTO
         {

    @ApiModelProperty("账户级别")
     private Integer accountLevel;



    public void setAccountLevel(Integer accountLevel) {
        /* 15 */
        this.accountLevel = accountLevel;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountLevelDTO;
    }



    /* 16 */
    protected AccountLevelDTO(AccountLevelDTOBuilder<?, ?> b) {
        super(b);
        this.accountLevel = b.accountLevel;
    }

    public static AccountLevelDTOBuilder<?, ?> builder() {
        return new AccountLevelDTOBuilderImpl();
    }

    private static final class AccountLevelDTOBuilderImpl extends AccountLevelDTOBuilder<AccountLevelDTO, AccountLevelDTOBuilderImpl> {
        private AccountLevelDTOBuilderImpl() {
        }

        protected AccountLevelDTOBuilderImpl self() {
            return this;
        }

        public AccountLevelDTO build() {
            return new AccountLevelDTO(this);
        }
    }

    public static abstract class AccountLevelDTOBuilder<C extends AccountLevelDTO, B extends AccountLevelDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B accountLevel(Integer accountLevel) {
            this.accountLevel = accountLevel;
            return self();
        }

        private Integer accountLevel;

        protected abstract B self();

        public abstract C build();


    }

    public AccountLevelDTO() {
    }

    public AccountLevelDTO(Integer accountLevel) {
        /* 18 */
        this.accountLevel = accountLevel;

    }



    public Integer getAccountLevel() {
        /* 22 */
        return this.accountLevel;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountLevelDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */