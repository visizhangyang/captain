
package com.porn.client.withdraw.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class WithdrawCancelDTO
         extends BaseDTO
         {
    

    protected boolean canEqual(Object other) {
        return other instanceof WithdrawCancelDTO;
    }



    /* 14 */
    protected WithdrawCancelDTO(WithdrawCancelDTOBuilder<?, ?> b) {
        super(b);
    }

    public static WithdrawCancelDTOBuilder<?, ?> builder() {
        return new WithdrawCancelDTOBuilderImpl();
    }

    private static final class WithdrawCancelDTOBuilderImpl extends WithdrawCancelDTOBuilder<WithdrawCancelDTO, WithdrawCancelDTOBuilderImpl> {
        protected WithdrawCancelDTOBuilderImpl self() {
            return this;
        }

        private WithdrawCancelDTOBuilderImpl() {
        }

        public WithdrawCancelDTO build() {
            return new WithdrawCancelDTO(this);
        }
    }

    public static abstract class WithdrawCancelDTOBuilder<C extends WithdrawCancelDTO, B extends WithdrawCancelDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        
        
        
        protected abstract B self();

        
        
        public abstract C build();
    }

    
    
    
    public WithdrawCancelDTO() {
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/withdraw/dto/WithdrawCancelDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */