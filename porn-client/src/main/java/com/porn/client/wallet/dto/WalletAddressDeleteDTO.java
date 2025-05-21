
package com.porn.client.wallet.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class WalletAddressDeleteDTO extends BaseDTO {

    @ApiModelProperty("密码")
     private String password;

    @ApiModelProperty("IP")
     private String remoteIP;


    /* 14 */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }


    protected boolean canEqual(Object other) {
        return other instanceof WalletAddressDeleteDTO;
    }



    /* 15 */
    protected WalletAddressDeleteDTO(WalletAddressDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.password = b.password;
        this.remoteIP = b.remoteIP;
    }

    public static WalletAddressDeleteDTOBuilder<?, ?> builder() {
        return new WalletAddressDeleteDTOBuilderImpl();
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

        public B password(String password) {
            this.password = password;
            return self();
        }

        private String remoteIP;

        public B remoteIP(String remoteIP) {
            this.remoteIP = remoteIP;
            return self();
        }

        protected abstract B self();

        public abstract C build();


    }


    public WalletAddressDeleteDTO() {
    }



    public String getPassword() {
        /* 20 */
        return this.password;

    }


    public String getRemoteIP() {
        /* 23 */
        return this.remoteIP;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/wallet/dto/WalletAddressDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */