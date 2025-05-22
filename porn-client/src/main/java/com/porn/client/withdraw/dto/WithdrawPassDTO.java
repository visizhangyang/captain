
package com.porn.client.withdraw.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class WithdrawPassDTO
         extends BaseDTO
         {
    

    protected boolean canEqual(Object other) {
        return other instanceof WithdrawPassDTO;
    }



    /* 14 */
    protected WithdrawPassDTO(WithdrawPassDTOBuilder<?, ?> b) {
        super(b);
    }

    public static WithdrawPassDTOBuilder<?, ?> builder() {
        return new WithdrawPassDTOBuilderImpl();
    }

    private static final class WithdrawPassDTOBuilderImpl extends WithdrawPassDTOBuilder<WithdrawPassDTO, WithdrawPassDTOBuilderImpl> {
        protected WithdrawPassDTOBuilderImpl self() {
            return this;
        }

        private WithdrawPassDTOBuilderImpl() {
        }

        public WithdrawPassDTO build() {
            return new WithdrawPassDTO(this);
        }
    }

    public static abstract class WithdrawPassDTOBuilder<C extends WithdrawPassDTO, B extends WithdrawPassDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        
        
        
        protected abstract B self();

        
        
        public abstract C build();
    }

    
    
    
    public WithdrawPassDTO() {
    }
    
}


