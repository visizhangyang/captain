
package com.porn.client.rhamount.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class RhAmountQueryDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof RhAmountQueryDTO;
    }



    /* 14 */
    protected RhAmountQueryDTO(RhAmountQueryDTOBuilder<?, ?> b) {
        super(b);
    }

    public static RhAmountQueryDTOBuilder<?, ?> builder() {
        return new RhAmountQueryDTOBuilderImpl();
    }

    private static final class RhAmountQueryDTOBuilderImpl extends RhAmountQueryDTOBuilder<RhAmountQueryDTO, RhAmountQueryDTOBuilderImpl> {
        protected RhAmountQueryDTOBuilderImpl self() {
            return this;
        }

        private RhAmountQueryDTOBuilderImpl() {
        }

        public RhAmountQueryDTO build() {
            return new RhAmountQueryDTO(this);
        }
    }

    public static abstract class RhAmountQueryDTOBuilder<C extends RhAmountQueryDTO, B extends RhAmountQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public RhAmountQueryDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/rhamount/dto/RhAmountQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */