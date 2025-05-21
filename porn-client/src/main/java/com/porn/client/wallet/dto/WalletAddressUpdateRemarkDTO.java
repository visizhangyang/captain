
package com.porn.client.wallet.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class WalletAddressUpdateRemarkDTO extends BaseDTO {

    @ApiModelProperty("备注")
     private String remark;

    @ApiModelProperty("密码")
     private String password;

    @ApiModelProperty("IP")
     private String remoteIP;


    /* 15 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }


    protected boolean canEqual(Object other) {
        return other instanceof WalletAddressUpdateRemarkDTO;
    }



    /* 16 */
    protected WalletAddressUpdateRemarkDTO(WalletAddressUpdateRemarkDTOBuilder<?, ?> b) {
        super(b);
        this.remark = b.remark;
        this.password = b.password;
        this.remoteIP = b.remoteIP;
    }

    public static WalletAddressUpdateRemarkDTOBuilder<?, ?> builder() {
        return new WalletAddressUpdateRemarkDTOBuilderImpl();
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

        public B remark(String remark) {
            this.remark = remark;
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

    public WalletAddressUpdateRemarkDTO(String remark, String password, String remoteIP) {
        /* 17 */
        this.remark = remark;
        this.password = password;
        this.remoteIP = remoteIP;

    }


    public WalletAddressUpdateRemarkDTO() {
    }



    public String getRemark() {
        /* 22 */
        return this.remark;

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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/wallet/dto/WalletAddressUpdateRemarkDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */