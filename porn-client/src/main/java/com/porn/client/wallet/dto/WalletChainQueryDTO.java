package com.porn.client.wallet.dto;

import java.io.Serializable;

public class WalletChainQueryDTO
        implements Serializable {

    public static WalletChainQueryDTOBuilder builder() {
        return new WalletChainQueryDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WalletChainQueryDTO;
    }

    public static class WalletChainQueryDTOBuilder {
        public WalletChainQueryDTO build() {
            return new WalletChainQueryDTO();
        }

    }

}

