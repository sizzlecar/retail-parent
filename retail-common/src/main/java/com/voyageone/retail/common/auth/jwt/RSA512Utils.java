package com.voyageone.retail.common.auth.jwt;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 */
public class RSA512Utils {

    public static final String KEY_ALGORITHM = "RSA";
    public static final int KEY_SIZE = 512;
    private volatile static KeyPairGenerator keyPairGenerator;

    /**
     * 密钥对map key
     */
    public interface RsaKey{

        String PUBLIC_KEY = "publicKey";
        String PRIVATE_KEY = "privateKey";

    }


    /**
     * 单例获取 keyPairGenerator
     */
    public static KeyPairGenerator getKeyPairGeneratorInstance() throws NoSuchAlgorithmException {
        // 先判断实例是否存在，若不存在再对类对象进行加锁处理
        if (keyPairGenerator == null) {
            synchronized (RSA512Utils.class) {
                if (keyPairGenerator == null) {
                    keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
                    keyPairGenerator.initialize(KEY_SIZE);
                }
            }
        }
        return keyPairGenerator;
    }


    /**
     * 生成密钥对
     *
     * @return
     */
    public static KeyPair generateKeyBytes() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = getKeyPairGeneratorInstance();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成密钥对发生错误", e);
        }

        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 还原公钥
     *
     * @param publicKeyStr
     * @return
     */
    public static PublicKey restorePublicKey(String publicKeyStr) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(hexString2Bytes(publicKeyStr));
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            return factory.generatePublic(x509EncodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("还原公钥", e);
        }
    }

    /**
     * 还原私钥
     *
     * @param privateKeyStr
     * @return
     */
    public static PrivateKey restorePrivateKey(String privateKeyStr) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(hexString2Bytes(privateKeyStr));
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            return factory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("还原私钥", e);
        }
    }


    /**
     * bytes[]换成16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] hexString2Bytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    public static void main(String[] args) {

        KeyPair keyPair = RSA512Utils.generateKeyBytes();
        System.out.println("pub:" + bytesToHexString(keyPair.getPublic().getEncoded()));
        System.out.println("pri:" + bytesToHexString(keyPair.getPrivate().getEncoded()));
    }
}
