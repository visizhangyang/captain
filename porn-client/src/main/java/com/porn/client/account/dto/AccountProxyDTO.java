
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;









 public class AccountProxyDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof AccountProxyDTO;
    }



    /* 16 */
    protected AccountProxyDTO(AccountProxyDTOBuilder<?, ?> b) {
        super(b);
    }

    public static AccountProxyDTOBuilder<?, ?> builder() {
        return new AccountProxyDTOBuilderImpl();
    }

    private static final class AccountProxyDTOBuilderImpl extends AccountProxyDTOBuilder<AccountProxyDTO, AccountProxyDTOBuilderImpl> {
        protected AccountProxyDTOBuilderImpl self() {
            return this;
        }

        private AccountProxyDTOBuilderImpl() {
        }

        public AccountProxyDTO build() {
            return new AccountProxyDTO(this);
        }
    }

    public static abstract class AccountProxyDTOBuilder<C extends AccountProxyDTO, B extends AccountProxyDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public AccountProxyDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountProxyDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */