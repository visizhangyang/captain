
package com.porn.client.merchant.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class MerchantEnableOrDisableDTO
         extends BaseDTO
         {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
     private Integer status;



    public void setStatus(Integer status) {
        /* 15 */
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MerchantEnableOrDisableDTO;
    }



    /* 16 */
    protected MerchantEnableOrDisableDTO(MerchantEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
    }

    public static MerchantEnableOrDisableDTOBuilder<?, ?> builder() {
        return new MerchantEnableOrDisableDTOBuilderImpl();
    }

    private static final class MerchantEnableOrDisableDTOBuilderImpl extends MerchantEnableOrDisableDTOBuilder<MerchantEnableOrDisableDTO, MerchantEnableOrDisableDTOBuilderImpl> {
        private MerchantEnableOrDisableDTOBuilderImpl() {
        }

        protected MerchantEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public MerchantEnableOrDisableDTO build() {
            return new MerchantEnableOrDisableDTO(this);
        }
    }

    public static abstract class MerchantEnableOrDisableDTOBuilder<C extends MerchantEnableOrDisableDTO, B extends MerchantEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B status(Integer status) {
            this.status = status;
            return self();
        }

        private Integer status;

        protected abstract B self();

        public abstract C build();

    }

    public MerchantEnableOrDisableDTO(Integer status) {
        /* 17 */
        this.status = status;

    }


    public MerchantEnableOrDisableDTO() {
    }



    public Integer getStatus() {
        /* 22 */
        return this.status;

    }

}


