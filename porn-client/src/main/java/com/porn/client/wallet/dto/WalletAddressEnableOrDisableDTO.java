package com.porn.client.wallet.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class WalletAddressEnableOrDisableDTO extends BaseDTO {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
    private Integer status;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("IP")
    private String remoteIP;

    protected WalletAddressEnableOrDisableDTO(WalletAddressEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
        this.password = b.password;
        this.remoteIP = b.remoteIP;
    }

    public WalletAddressEnableOrDisableDTO(Integer status, String password, String remoteIP) {

        this.status = status;
        this.password = password;
        this.remoteIP = remoteIP;

    }

    public WalletAddressEnableOrDisableDTO() {
    }

    public static WalletAddressEnableOrDisableDTOBuilder<?, ?> builder() {
        return new WalletAddressEnableOrDisableDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WalletAddressEnableOrDisableDTO;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
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

    private static final class WalletAddressEnableOrDisableDTOBuilderImpl extends WalletAddressEnableOrDisableDTOBuilder<WalletAddressEnableOrDisableDTO, WalletAddressEnableOrDisableDTOBuilderImpl> {
        private WalletAddressEnableOrDisableDTOBuilderImpl() {
        }

        protected WalletAddressEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public WalletAddressEnableOrDisableDTO build() {
            return new WalletAddressEnableOrDisableDTO(this);
        }
    }

    public static abstract class WalletAddressEnableOrDisableDTOBuilder<C extends WalletAddressEnableOrDisableDTO, B extends WalletAddressEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer status;
        private String password;
        private String remoteIP;

        public B status(Integer status) {
            this.status = status;
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

