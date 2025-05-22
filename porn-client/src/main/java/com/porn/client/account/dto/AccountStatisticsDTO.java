
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;








 public class AccountStatisticsDTO
         extends BaseDTO
         {



    protected boolean canEqual(Object other) {
        return other instanceof AccountStatisticsDTO;
    }


    /* 15 */
    public static AccountStatisticsDTOBuilder<?, ?> builder() {
        return new AccountStatisticsDTOBuilderImpl();
    }

    protected AccountStatisticsDTO(AccountStatisticsDTOBuilder<?, ?> b) {
        super(b);
    }

    private static final class AccountStatisticsDTOBuilderImpl extends AccountStatisticsDTOBuilder<AccountStatisticsDTO, AccountStatisticsDTOBuilderImpl> {
        protected AccountStatisticsDTOBuilderImpl self() {
            return this;
        }

        private AccountStatisticsDTOBuilderImpl() {
        }

        public AccountStatisticsDTO build() {
            return new AccountStatisticsDTO(this);
        }
    }

    public static abstract class AccountStatisticsDTOBuilder<C extends AccountStatisticsDTO, B extends AccountStatisticsDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }


}


