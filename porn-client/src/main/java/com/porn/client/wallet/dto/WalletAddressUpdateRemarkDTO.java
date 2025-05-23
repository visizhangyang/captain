package com.porn.client.wallet.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class WalletAddressUpdateRemarkDTO extends BaseDTO {

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("IP")
    private String remoteIP;

    protected WalletAddressUpdateRemarkDTO(WalletAddressUpdateRemarkDTOBuilder<?, ?> b) {
        super(b);
        this.remark = b.remark;
        this.password = b.password;
        this.remoteIP = b.remoteIP;
    }

    public WalletAddressUpdateRemarkDTO(String remark, String password, String remoteIP) {

        this.remark = remark;
        this.password = password;
        this.remoteIP = remoteIP;

    }

    public WalletAddressUpdateRemarkDTO() {
    }

    public static WalletAddressUpdateRemarkDTOBuilder<?, ?> builder() {
        return new WalletAddressUpdateRemarkDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WalletAddressUpdateRemarkDTO;
    }

    public String getRemark() {

        return this.remark;

    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPassword() {

        return this.password;

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteIP() {

        return this.remoteIP;

    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    private static final class WalletAddressUpdateRemarkDTOBuilderImpl extends WalletAddressUpdateRemarkDTOBuilder<WalletAddressUpdateRemarkDTO, WalletAddressUpdateRemarkDTOBuilderImpl> {
        private WalletAddressUpdateRemarkDTOBuilderImpl() {
        }

        protected WalletAddressUpdateRemarkDTOBuilderImpl self() {
            return this;
        }

        public WalletAddressUpdateRemarkDTO build() {
            return new WalletAddressUpdateRemarkDTO(this);
        }
    }

    public static abstract class WalletAddressUpdateRemarkDTOBuilder<C extends WalletAddressUpdateRemarkDTO, B extends WalletAddressUpdateRemarkDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String remark;
        private String password;
        private String remoteIP;

        public B remark(String remark) {
            this.remark = remark;
            return self();
        }

        public B password(String password) {
            this.password = password;
            return self();
        }

        public B remoteIP(String remoteIP) {
            this.remoteIP = remoteIP;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

