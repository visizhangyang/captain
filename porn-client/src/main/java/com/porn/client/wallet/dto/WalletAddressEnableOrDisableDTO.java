
package com.porn.client.wallet.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class WalletAddressEnableOrDisableDTO extends BaseDTO {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
     private Integer status;

    @ApiModelProperty("密码")
     private String password;

    @ApiModelProperty("IP")
     private String remoteIP;


    /* 15 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }


    protected boolean canEqual(Object other) {
        return other instanceof WalletAddressEnableOrDisableDTO;
    }



    /* 16 */
    protected WalletAddressEnableOrDisableDTO(WalletAddressEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
        this.password = b.password;
        this.remoteIP = b.remoteIP;
    }

    public static WalletAddressEnableOrDisableDTOBuilder<?, ?> builder() {
        return new WalletAddressEnableOrDisableDTOBuilderImpl();
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

        public B status(Integer status) {
            this.status = status;
            return self();
        }

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

    public WalletAddressEnableOrDisableDTO(Integer status, String password, String remoteIP) {
        /* 17 */
        this.status = status;
        this.password = password;
        this.remoteIP = remoteIP;

    }


    public WalletAddressEnableOrDisableDTO() {
    }



    public Integer getStatus() {
        /* 22 */
        return this.status;

    }


    public String getPassword() {
        /* 25 */
        return this.password;

    }


    public String getRemoteIP() {
        /* 28 */
        return this.remoteIP;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/wallet/dto/WalletAddressEnableOrDisableDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */