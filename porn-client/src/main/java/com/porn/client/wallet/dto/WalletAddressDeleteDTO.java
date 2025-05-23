package com.porn.client.wallet.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class WalletAddressDeleteDTO extends BaseDTO {

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("IP")
    private String remoteIP;

    protected WalletAddressDeleteDTO(WalletAddressDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.password = b.password;
        this.remoteIP = b.remoteIP;
    }

    public WalletAddressDeleteDTO() {
    }

    public static WalletAddressDeleteDTOBuilder<?, ?> builder() {
        return new WalletAddressDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WalletAddressDeleteDTO;
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

    private static final class WalletAddressDeleteDTOBuilderImpl extends WalletAddressDeleteDTOBuilder<WalletAddressDeleteDTO, WalletAddressDeleteDTOBuilderImpl> {
        private WalletAddressDeleteDTOBuilderImpl() {
        }

        protected WalletAddressDeleteDTOBuilderImpl self() {
            return this;
        }

        public WalletAddressDeleteDTO build() {
            return new WalletAddressDeleteDTO(this);
        }
    }

    public static abstract class WalletAddressDeleteDTOBuilder<C extends WalletAddressDeleteDTO, B extends WalletAddressDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String password;
        private String remoteIP;

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

