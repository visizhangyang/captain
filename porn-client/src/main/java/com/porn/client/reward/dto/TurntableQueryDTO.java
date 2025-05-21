
package com.porn.client.reward.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class TurntableQueryDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof TurntableQueryDTO;
    }



    /* 14 */
    protected TurntableQueryDTO(TurntableQueryDTOBuilder<?, ?> b) {
        super(b);
    }

    public static TurntableQueryDTOBuilder<?, ?> builder() {
        return new TurntableQueryDTOBuilderImpl();
    }

    private static final class TurntableQueryDTOBuilderImpl extends TurntableQueryDTOBuilder<TurntableQueryDTO, TurntableQueryDTOBuilderImpl> {
        protected TurntableQueryDTOBuilderImpl self() {
            return this;
        }

        private TurntableQueryDTOBuilderImpl() {
        }

        public TurntableQueryDTO build() {
            return new TurntableQueryDTO(this);
        }
    }

    public static abstract class TurntableQueryDTOBuilder<C extends TurntableQueryDTO, B extends TurntableQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public TurntableQueryDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/dto/TurntableQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */