
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


