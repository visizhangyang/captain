
package com.porn.client.recharge.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class RechargeDeleteDTO
         extends BaseDTO
         {
    

    protected boolean canEqual(Object other) {
        return other instanceof RechargeDeleteDTO;
    }



    /* 14 */
    protected RechargeDeleteDTO(RechargeDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static RechargeDeleteDTOBuilder<?, ?> builder() {
        return new RechargeDeleteDTOBuilderImpl();
    }

    private static final class RechargeDeleteDTOBuilderImpl extends RechargeDeleteDTOBuilder<RechargeDeleteDTO, RechargeDeleteDTOBuilderImpl> {
        protected RechargeDeleteDTOBuilderImpl self() {
            return this;
        }

        private RechargeDeleteDTOBuilderImpl() {
        }

        public RechargeDeleteDTO build() {
            return new RechargeDeleteDTO(this);
        }
    }

    public static abstract class RechargeDeleteDTOBuilder<C extends RechargeDeleteDTO, B extends RechargeDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        
        
        
        protected abstract B self();

        
        
        public abstract C build();
    }

    
    
    
    public RechargeDeleteDTO() {
    }
    
}


