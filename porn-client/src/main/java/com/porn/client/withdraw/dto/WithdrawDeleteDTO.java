
package com.porn.client.withdraw.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class WithdrawDeleteDTO
         extends BaseDTO
         {
    

    protected boolean canEqual(Object other) {
        return other instanceof WithdrawDeleteDTO;
    }



    /* 14 */
    protected WithdrawDeleteDTO(WithdrawDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static WithdrawDeleteDTOBuilder<?, ?> builder() {
        return new WithdrawDeleteDTOBuilderImpl();
    }

    private static final class WithdrawDeleteDTOBuilderImpl extends WithdrawDeleteDTOBuilder<WithdrawDeleteDTO, WithdrawDeleteDTOBuilderImpl> {
        protected WithdrawDeleteDTOBuilderImpl self() {
            return this;
        }

        private WithdrawDeleteDTOBuilderImpl() {
        }

        public WithdrawDeleteDTO build() {
            return new WithdrawDeleteDTO(this);
        }
    }

    public static abstract class WithdrawDeleteDTOBuilder<C extends WithdrawDeleteDTO, B extends WithdrawDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        
        
        
        protected abstract B self();

        
        
        public abstract C build();
    }

    
    
    
    public WithdrawDeleteDTO() {
    }
    
}


