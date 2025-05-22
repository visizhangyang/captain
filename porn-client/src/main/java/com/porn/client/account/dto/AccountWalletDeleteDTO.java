
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class AccountWalletDeleteDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof AccountWalletDeleteDTO;
    }



    /* 14 */
    protected AccountWalletDeleteDTO(AccountWalletDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static AccountWalletDeleteDTOBuilder<?, ?> builder() {
        return new AccountWalletDeleteDTOBuilderImpl();
    }

    private static final class AccountWalletDeleteDTOBuilderImpl extends AccountWalletDeleteDTOBuilder<AccountWalletDeleteDTO, AccountWalletDeleteDTOBuilderImpl> {
        protected AccountWalletDeleteDTOBuilderImpl self() {
            return this;
        }

        private AccountWalletDeleteDTOBuilderImpl() {
        }

        public AccountWalletDeleteDTO build() {
            return new AccountWalletDeleteDTO(this);
        }
    }

    public static abstract class AccountWalletDeleteDTOBuilder<C extends AccountWalletDeleteDTO, B extends AccountWalletDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public AccountWalletDeleteDTO() {
    }

}


