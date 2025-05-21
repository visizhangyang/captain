
package com.porn.service.common.utils;



import cn.hutool.core.codec.Base58Codec;
import org.bitcoinj.core.ECKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;














 public class TrcUtil
         {
       static {
        /* 19 */
        Security.addProvider((Provider) new BouncyCastleProvider());
        
    }

    
    
    
    
    
    
    
    
    public static String genTrc20Address() {
        
        try {
            /* 30 */
            ECKey key = new ECKey();
            /* 31 */
            byte[] pubKey = key.getPubKey();
            /* 32 */
            return genTrc20Address(pubKey);
            /* 33 */
        } catch (Exception e) {
            /* 34 */
            return null;
            
        }
        
    }

    
    
    
    
    
    
    
    
    
    public static String genTrc20Address(byte[] pubKey) throws Exception {
        /* 46 */
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        /* 47 */
        byte[] sha256Hash = sha256.digest(pubKey);
        
        
        /* 50 */
        MessageDigest ripemd160 = MessageDigest.getInstance("RIPEMD160");
        /* 51 */
        byte[] ripemd160Hash = ripemd160.digest(sha256Hash);
        
        
        /* 54 */
        byte[] addressBytes = new byte[21];
        /* 55 */
        addressBytes[0] = 65;
        /* 56 */
        System.arraycopy(ripemd160Hash, 0, addressBytes, 1, ripemd160Hash.length);
        
        
        /* 59 */
        byte[] checksum = Arrays.copyOfRange(sha256
/* 60 */.digest(sha256.digest(addressBytes)), 0, 4);
        
        
        
        /* 64 */
        byte[] addressWithChecksum = new byte[25];
        /* 65 */
        System.arraycopy(addressBytes, 0, addressWithChecksum, 0, 21);
        /* 66 */
        System.arraycopy(checksum, 0, addressWithChecksum, 21, 4);
        
        
        /* 69 */
        return Base58Codec.Base58Encoder.ENCODER.encode(addressWithChecksum);
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/common/utils/TrcUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */