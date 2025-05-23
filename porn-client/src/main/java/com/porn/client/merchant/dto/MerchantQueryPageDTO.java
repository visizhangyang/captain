package com.porn.client.merchant.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

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

    protected MerchantQueryPageDTO(MerchantQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkName = b.lkName;
        this.name = b.name;
        this.memberLevel = b.memberLevel;
        this.authLevel = b.authLevel;
        this.status = b.status;
        this.merchantType = b.merchantType;
    }

    public MerchantQueryPageDTO(String lkName, String name, Integer memberLevel, Integer authLevel, Integer status, Integer merchantType) {

        this.lkName = lkName;
        this.name = name;
        this.memberLevel = memberLevel;
        this.authLevel = authLevel;
        this.status = status;
        this.merchantType = merchantType;

    }

    public MerchantQueryPageDTO() {
    }

    public static MerchantQueryPageDTOBuilder<?, ?> builder() {
        return new MerchantQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MerchantQueryPageDTO;
    }

    public String getLkName() {

        return this.lkName;

    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMemberLevel() {

        return this.memberLevel;

    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Integer getAuthLevel() {

        return this.authLevel;

    }

    public void setAuthLevel(Integer authLevel) {
        this.authLevel = authLevel;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMerchantType() {

        return this.merchantType;

    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
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
        private Integer authLevel;
        private Integer status;
        private Integer merchantType;

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

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
}

