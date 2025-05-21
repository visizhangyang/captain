
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/withdraw/dto/WithdrawPassDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */