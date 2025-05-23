package com.porn.client.wallet.enums;

public enum WalletChainEnum {
    TRON("TRON", "波场"),
    ETH("ETH", "以太坊"),
    BEP("BEP", "币安"),
    SOLANA("SOLANA", "Solana");

    private final String code;
    private final String name;

    WalletChainEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String queryName(String code) {
        for (WalletChainEnum walletChainEnum : values()) {
            if (walletChainEnum.getCode().equals(code)) {
                return walletChainEnum.getName();
            }
        }
        return null;
    }

    public static String queryCode(String name) {
        for (WalletChainEnum walletChainEnum : values()) {
            if (walletChainEnum.getName().equals(name)) {
                return walletChainEnum.getCode();
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
