
package com.porn.client.merchant.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BasePageDTO;
 public class MerchantQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("模糊匹配姓名")
     private String lkName;
    
    @ApiModelProperty("商户名称")
     private String name;
    
    @ApiModelProperty("会员级别")
     private Integer memberLevel;
    
    @ApiModelProperty("认证级别")
     private Integer authLevel;
    
    @ApiModelProperty("状态")
     private Integer status;
    
    @ApiModelProperty("商户类型, MerchantTypeEnum")
     private Integer merchantType;

    
    /* 15 */
    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public void setAuthLevel(Integer authLevel) {
        this.authLevel = authLevel;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MerchantQueryPageDTO;
    }



    /* 16 */
    protected MerchantQueryPageDTO(MerchantQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkName = b.lkName;
        this.name = b.name;
        this.memberLevel = b.memberLevel;
        this.authLevel = b.authLevel;
        this.status = b.status;
        this.merchantType = b.merchantType;
    }

    public static MerchantQueryPageDTOBuilder<?, ?> builder() {
        return new MerchantQueryPageDTOBuilderImpl();
    }

    private static final class MerchantQueryPageDTOBuilderImpl extends MerchantQueryPageDTOBuilder<MerchantQueryPageDTO, MerchantQueryPageDTOBuilderImpl> {
        private MerchantQueryPageDTOBuilderImpl() {
        }

        protected MerchantQueryPageDTOBuilderImpl self() {
            return this;
        }

        public MerchantQueryPageDTO build() {
            return new MerchantQueryPageDTO(this);
        }
    }

    public static abstract class MerchantQueryPageDTOBuilder<C extends MerchantQueryPageDTO, B extends MerchantQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String lkName;
        private String name;
        private Integer memberLevel;

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        private Integer authLevel;
        private Integer status;
        private Integer merchantType;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B memberLevel(Integer memberLevel) {
            this.memberLevel = memberLevel;
            return self();
        }

        public B authLevel(Integer authLevel) {
            this.authLevel = authLevel;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B merchantType(Integer merchantType) {
            this.merchantType = merchantType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public MerchantQueryPageDTO(String lkName, String name, Integer memberLevel, Integer authLevel, Integer status, Integer merchantType) {
        /* 17 */
        this.lkName = lkName;
        this.name = name;
        this.memberLevel = memberLevel;
        this.authLevel = authLevel;
        this.status = status;
        this.merchantType = merchantType;
        
    }

    
    public MerchantQueryPageDTO() {
    }

    
    
    public String getLkName() {
        /* 22 */
        return this.lkName;
        
    }

    
    public String getName() {
        /* 25 */
        return this.name;
        
    }

    
    public Integer getMemberLevel() {
        /* 28 */
        return this.memberLevel;
        
    }

    
    public Integer getAuthLevel() {
        /* 31 */
        return this.authLevel;
        
    }

    
    public Integer getStatus() {
        /* 34 */
        return this.status;
        
    }

    
    public Integer getMerchantType() {
        /* 37 */
        return this.merchantType;
        
    }
}


