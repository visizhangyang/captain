package com.porn.service.common.utils;


import cn.hutool.core.codec.Base58Codec;
import org.bitcoinj.core.ECKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;

public class TrcUtil {
    static {

        Security.addProvider((Provider) new BouncyCastleProvider());

    }


    public static String genTrc20Address() {

        try {

            ECKey key = new ECKey();

            byte[] pubKey = key.getPubKey();

            return genTrc20Address(pubKey);

        } catch (Exception e) {

            return null;

        }

    }


    public static String genTrc20Address(byte[] pubKey) throws Exception {

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

        byte[] sha256Hash = sha256.digest(pubKey);


        MessageDigest ripemd160 = MessageDigest.getInstance("RIPEMD160");

        byte[] ripemd160Hash = ripemd160.digest(sha256Hash);


        byte[] addressBytes = new byte[21];

        addressBytes[0] = 65;

        System.arraycopy(ripemd160Hash, 0, addressBytes, 1, ripemd160Hash.length);


        byte[] checksum = Arrays.copyOfRange(sha256
                .digest(sha256.digest(addressBytes)), 0, 4);


        byte[] addressWithChecksum = new byte[25];

        System.arraycopy(addressBytes, 0, addressWithChecksum, 0, 21);

        System.arraycopy(checksum, 0, addressWithChecksum, 21, 4);


        return Base58Codec.Base58Encoder.ENCODER.encode(addressWithChecksum);

    }

}

