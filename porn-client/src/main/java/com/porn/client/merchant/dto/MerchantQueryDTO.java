
package com.porn.client.merchant.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class MerchantQueryDTO extends BaseDTO {

    @ApiModelProperty("商户")
     private String name;

    @ApiModelProperty("商户id列表")
     private List<Long> merchantIdList;

    @ApiModelProperty("商户类型, MerchantTypeEnum")
     private Integer merchantType;


    /* 17 */
    public void setName(String name) {
        this.name = name;
    }

    public void setMerchantIdList(List<Long> merchantIdList) {
        this.merchantIdList = merchantIdList;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MerchantQueryDTO;
    }



    /* 18 */
    protected MerchantQueryDTO(MerchantQueryDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.merchantIdList = b.merchantIdList;
        this.merchantType = b.merchantType;
    }

    public static MerchantQueryDTOBuilder<?, ?> builder() {
        return new MerchantQueryDTOBuilderImpl();
    }

    private static final class MerchantQueryDTOBuilderImpl extends MerchantQueryDTOBuilder<MerchantQueryDTO, MerchantQueryDTOBuilderImpl> {
        private MerchantQueryDTOBuilderImpl() {
        }

        protected MerchantQueryDTOBuilderImpl self() {
            return this;
        }

        public MerchantQueryDTO build() {
            return new MerchantQueryDTO(this);
        }
    }

    public static abstract class MerchantQueryDTOBuilder<C extends MerchantQueryDTO, B extends MerchantQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;

        public B name(String name) {
            this.name = name;
            return self();
        }

        private List<Long> merchantIdList;
        private Integer merchantType;

        public B merchantIdList(List<Long> merchantIdList) {
            this.merchantIdList = merchantIdList;
            return self();
        }

        public B merchantType(Integer merchantType) {
            this.merchantType = merchantType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public MerchantQueryDTO(String name, List<Long> merchantIdList, Integer merchantType) {
        /* 19 */
        this.name = name;
        this.merchantIdList = merchantIdList;
        this.merchantType = merchantType;

    }


    public MerchantQueryDTO() {
    }



    public String getName() {
        /* 24 */
        return this.name;

    }


    public List<Long> getMerchantIdList() {
        /* 27 */
        return this.merchantIdList;

    }


    public Integer getMerchantType() {
        /* 30 */
        return this.merchantType;

    }

}


