
package com.porn.client.merchant.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class MerchantDeleteDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof MerchantDeleteDTO;
    }



    /* 14 */
    protected MerchantDeleteDTO(MerchantDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static MerchantDeleteDTOBuilder<?, ?> builder() {
        return new MerchantDeleteDTOBuilderImpl();
    }

    private static final class MerchantDeleteDTOBuilderImpl extends MerchantDeleteDTOBuilder<MerchantDeleteDTO, MerchantDeleteDTOBuilderImpl> {
        protected MerchantDeleteDTOBuilderImpl self() {
            return this;
        }

        private MerchantDeleteDTOBuilderImpl() {
        }

        public MerchantDeleteDTO build() {
            return new MerchantDeleteDTO(this);
        }
    }

    public static abstract class MerchantDeleteDTOBuilder<C extends MerchantDeleteDTO, B extends MerchantDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public MerchantDeleteDTO() {
    }

}


