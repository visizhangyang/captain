
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class AccountclearReceiveAddrDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof AccountclearReceiveAddrDTO;
    }



    /* 14 */
    protected AccountclearReceiveAddrDTO(AccountclearReceiveAddrDTOBuilder<?, ?> b) {
        super(b);
    }

    public static AccountclearReceiveAddrDTOBuilder<?, ?> builder() {
        return new AccountclearReceiveAddrDTOBuilderImpl();
    }

    private static final class AccountclearReceiveAddrDTOBuilderImpl extends AccountclearReceiveAddrDTOBuilder<AccountclearReceiveAddrDTO, AccountclearReceiveAddrDTOBuilderImpl> {
        protected AccountclearReceiveAddrDTOBuilderImpl self() {
            return this;
        }

        private AccountclearReceiveAddrDTOBuilderImpl() {
        }

        public AccountclearReceiveAddrDTO build() {
            return new AccountclearReceiveAddrDTO(this);
        }
    }

    public static abstract class AccountclearReceiveAddrDTOBuilder<C extends AccountclearReceiveAddrDTO, B extends AccountclearReceiveAddrDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public AccountclearReceiveAddrDTO() {
    }

}


