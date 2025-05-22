
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


