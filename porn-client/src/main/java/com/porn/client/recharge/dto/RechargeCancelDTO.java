
package com.porn.client.recharge.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;








 public class RechargeCancelDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof RechargeCancelDTO;
    }



    /* 15 */
    protected RechargeCancelDTO(RechargeCancelDTOBuilder<?, ?> b) {
        super(b);
    }

    public static RechargeCancelDTOBuilder<?, ?> builder() {
        return new RechargeCancelDTOBuilderImpl();
    }

    private static final class RechargeCancelDTOBuilderImpl extends RechargeCancelDTOBuilder<RechargeCancelDTO, RechargeCancelDTOBuilderImpl> {
        protected RechargeCancelDTOBuilderImpl self() {
            return this;
        }

        private RechargeCancelDTOBuilderImpl() {
        }

        public RechargeCancelDTO build() {
            return new RechargeCancelDTO(this);
        }
    }

    public static abstract class RechargeCancelDTOBuilder<C extends RechargeCancelDTO, B extends RechargeCancelDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public RechargeCancelDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recharge/dto/RechargeCancelDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */