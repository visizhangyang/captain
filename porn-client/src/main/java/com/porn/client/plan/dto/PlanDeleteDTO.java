
package com.porn.client.plan.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class PlanDeleteDTO
         extends BaseDTO
         {
    

    protected boolean canEqual(Object other) {
        return other instanceof PlanDeleteDTO;
    }



    /* 14 */
    protected PlanDeleteDTO(PlanDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static PlanDeleteDTOBuilder<?, ?> builder() {
        return new PlanDeleteDTOBuilderImpl();
    }

    private static final class PlanDeleteDTOBuilderImpl extends PlanDeleteDTOBuilder<PlanDeleteDTO, PlanDeleteDTOBuilderImpl> {
        protected PlanDeleteDTOBuilderImpl self() {
            return this;
        }

        private PlanDeleteDTOBuilderImpl() {
        }

        public PlanDeleteDTO build() {
            return new PlanDeleteDTO(this);
        }
    }

    public static abstract class PlanDeleteDTOBuilder<C extends PlanDeleteDTO, B extends PlanDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        
        
        
        protected abstract B self();

        
        
        public abstract C build();
    }

    
    
    
    public PlanDeleteDTO() {
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/plan/dto/PlanDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */