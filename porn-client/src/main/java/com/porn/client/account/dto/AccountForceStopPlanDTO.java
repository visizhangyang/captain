
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class AccountForceStopPlanDTO
         extends BaseDTO
         {
    

    protected boolean canEqual(Object other) {
        return other instanceof AccountForceStopPlanDTO;
    }



    /* 14 */
    protected AccountForceStopPlanDTO(AccountForceStopPlanDTOBuilder<?, ?> b) {
        super(b);
    }

    public static AccountForceStopPlanDTOBuilder<?, ?> builder() {
        return new AccountForceStopPlanDTOBuilderImpl();
    }

    private static final class AccountForceStopPlanDTOBuilderImpl extends AccountForceStopPlanDTOBuilder<AccountForceStopPlanDTO, AccountForceStopPlanDTOBuilderImpl> {
        protected AccountForceStopPlanDTOBuilderImpl self() {
            return this;
        }

        private AccountForceStopPlanDTOBuilderImpl() {
        }

        public AccountForceStopPlanDTO build() {
            return new AccountForceStopPlanDTO(this);
        }
    }

    public static abstract class AccountForceStopPlanDTOBuilder<C extends AccountForceStopPlanDTO, B extends AccountForceStopPlanDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        
        
        
        protected abstract B self();

        
        
        public abstract C build();
    }

    
    
    
    public AccountForceStopPlanDTO() {
    }
    
}


