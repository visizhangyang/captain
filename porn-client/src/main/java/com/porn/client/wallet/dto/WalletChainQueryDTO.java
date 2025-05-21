
package com.porn.client.wallet.dto;
import io.swagger.annotations.ApiModelProperty;



import java.io.Serializable;






 public class WalletChainQueryDTO
         implements Serializable
         {



    protected boolean canEqual(Object other) {
        return other instanceof WalletChainQueryDTO;
    }


    /* 14 */
    public static WalletChainQueryDTOBuilder builder() {
        return new WalletChainQueryDTOBuilder();
    }

    public static class WalletChainQueryDTOBuilder {
        public WalletChainQueryDTO build() {
            return new WalletChainQueryDTO();
        }


    }


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/wallet/dto/WalletChainQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */