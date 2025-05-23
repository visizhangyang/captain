package com.porn.client.wallet.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class WalletAddressQueryDTO extends BaseDTO {

    @ApiModelProperty("地址名称")
    private String name;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("地址状态")
    private Integer addressStatus;

    @ApiModelProperty("状态, 1-启用, 0-禁用")
    private Integer status;

    protected WalletAddressQueryDTO(WalletAddressQueryDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.code = b.code;
        this.address = b.address;
        this.addressStatus = b.addressStatus;
        this.status = b.status;
    }

    public WalletAddressQueryDTO(String name, String code, String address, Integer addressStatus, Integer status) {

        this.name = name;
        this.code = code;
        this.address = address;
        this.addressStatus = addressStatus;
        this.status = status;

    }

    public WalletAddressQueryDTO() {
    }

    public static WalletAddressQueryDTOBuilder<?, ?> builder() {
        return new WalletAddressQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WalletAddressQueryDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {

        return this.code;

    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {

        return this.address;

    }

    public void setAddress(String address) {
        this.address = address;
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

    private static final class WalletAddressQueryDTOBuilderImpl extends WalletAddressQueryDTOBuilder<WalletAddressQueryDTO, WalletAddressQueryDTOBuilderImpl> {
        private WalletAddressQueryDTOBuilderImpl() {
        }

        protected WalletAddressQueryDTOBuilderImpl self() {
            return this;
        }

        public WalletAddressQueryDTO build() {
            return new WalletAddressQueryDTO(this);
        }
    }

    public static abstract class WalletAddressQueryDTOBuilder<C extends WalletAddressQueryDTO, B extends WalletAddressQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;
        private String code;
        private String address;
        private Integer addressStatus;
        private Integer status;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B code(String code) {
            this.code = code;
            return self();
        }

        public B address(String address) {
            this.address = address;
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

        protected abstract B self();

        public abstract C build();

    }

}

