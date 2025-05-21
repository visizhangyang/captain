
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/withdraw/dto/WithdrawDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */