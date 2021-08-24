package com.example.common;


import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import java.nio.charset.StandardCharsets;


public class CryptoTool {
    private static String key = "k1ks.sadl13/s";
    private static SymmetricCrypto des;

    static {
        des = new SymmetricCrypto(SymmetricAlgorithm.DES, key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 加密
     * @param srcData
     * @return
     */
    public static String encrypt(String srcData){
        System.out.println(key);
        return des.encryptHex(srcData);
    }

    /**
     * 解密
     * @param hexData
     * @return
     */
    public static String decrypt(String hexData){
        return des.decryptStr(hexData);
    }

}
