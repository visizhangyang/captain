
package com.porn.client.paramset.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class ParamsetQueryDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof ParamsetQueryDTO;
    }



    /* 14 */
    protected ParamsetQueryDTO(ParamsetQueryDTOBuilder<?, ?> b) {
        super(b);
    }

    public static ParamsetQueryDTOBuilder<?, ?> builder() {
        return new ParamsetQueryDTOBuilderImpl();
    }

    private static final class ParamsetQueryDTOBuilderImpl extends ParamsetQueryDTOBuilder<ParamsetQueryDTO, ParamsetQueryDTOBuilderImpl> {
        protected ParamsetQueryDTOBuilderImpl self() {
            return this;
        }

        private ParamsetQueryDTOBuilderImpl() {
        }

        public ParamsetQueryDTO build() {
            return new ParamsetQueryDTO(this);
        }
    }

    public static abstract class ParamsetQueryDTOBuilder<C extends ParamsetQueryDTO, B extends ParamsetQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public ParamsetQueryDTO() {
    }

}


