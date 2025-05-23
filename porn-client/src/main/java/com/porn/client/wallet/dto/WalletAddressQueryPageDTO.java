package com.porn.client.wallet.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class WalletAddressQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("名称模糊")
    private String lkName;
    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("编码模糊")
    private String lkCode;

    @ApiModelProperty("地址")
    private String lkAddress;

    @ApiModelProperty("地址状态")
    private Integer addressStatus;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String lkRemark;

    protected WalletAddressQueryPageDTO(WalletAddressQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.lkName = b.lkName;
        this.code = b.code;
        this.lkCode = b.lkCode;
        this.lkAddress = b.lkAddress;
        this.addressStatus = b.addressStatus;
        this.status = b.status;
        this.lkRemark = b.lkRemark;
    }

    public WalletAddressQueryPageDTO(String name, String lkName, String code, String lkCode, String lkAddress, Integer addressStatus, Integer status, String lkRemark) {

        this.name = name;
        this.lkName = lkName;
        this.code = code;
        this.lkCode = lkCode;
        this.lkAddress = lkAddress;
        this.addressStatus = addressStatus;
        this.status = status;
        this.lkRemark = lkRemark;

    }

    public WalletAddressQueryPageDTO() {
    }

    public static WalletAddressQueryPageDTOBuilder<?, ?> builder() {
        return new WalletAddressQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WalletAddressQueryPageDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLkName() {

        return this.lkName;

    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public String getCode() {

        return this.code;

    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLkCode() {

        return this.lkCode;

    }

    public void setLkCode(String lkCode) {
        this.lkCode = lkCode;
    }

    public String getLkAddress() {

        return this.lkAddress;

    }

    public void setLkAddress(String lkAddress) {
        this.lkAddress = lkAddress;
    }

    public Integer getAddressStatus() {

        return this.addressStatus;

    }

    public void setAddressStatus(Integer addressStatus) {
        this.addressStatus = addressStatus;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLkRemark() {

        return this.lkRemark;

    }

    public void setLkRemark(String lkRemark) {
        this.lkRemark = lkRemark;
    }

    private static final class WalletAddressQueryPageDTOBuilderImpl extends WalletAddressQueryPageDTOBuilder<WalletAddressQueryPageDTO, WalletAddressQueryPageDTOBuilderImpl> {
        private WalletAddressQueryPageDTOBuilderImpl() {
        }

        protected WalletAddressQueryPageDTOBuilderImpl self() {
            return this;
        }

        public WalletAddressQueryPageDTO build() {
            return new WalletAddressQueryPageDTO(this);
        }
    }

    public static abstract class WalletAddressQueryPageDTOBuilder<C extends WalletAddressQueryPageDTO, B extends WalletAddressQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String name;
        private String lkName;
        private String code;
        private String lkCode;
        private String lkAddress;
        private Integer addressStatus;
        private Integer status;
        private String lkRemark;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        public B code(String code) {
            this.code = code;
            return self();
        }

        public B lkCode(String lkCode) {
            this.lkCode = lkCode;
            return self();
        }

        public B lkAddress(String lkAddress) {
            this.lkAddress = lkAddress;
            return self();
        }

        public B addressStatus(Integer addressStatus) {
            this.addressStatus = addressStatus;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B lkRemark(String lkRemark) {
            this.lkRemark = lkRemark;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

