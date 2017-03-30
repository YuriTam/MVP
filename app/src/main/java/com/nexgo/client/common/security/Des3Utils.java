package com.nexgo.client.common.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3DES加解密，使用CBC模式
 * @author 谭忠扬-YuriTam
 * @time 2016年10月17日
 */
public class Des3Utils {
    private static final String Algorithm = "DESede";    //3DES算法
    private static final String TriDes = "DESede/CBC/NoPadding";//填充模式

    /**
     * 3DES CBC模式加密
     * @param key 加密密钥
     * @param data 加密数据
     * @param ivs 初始向量
     * @return 返回加密数据
     */
    public static byte[] des3EncodeCBC(byte[] key,byte[] data,byte[] ivs){
        try {
            byte[] k = new byte[24];
            if(data == null){
                return null;
            }
            byte[] dstData = new byte[((data.length + 7) / 8) * 8];
            System.arraycopy(data, 0, dstData, 0, data.length);

            if (key.length == 16) {
                System.arraycopy(key, 0, k, 0, key.length);
                System.arraycopy(key, 0, k, 16, 8);
            } else {
                System.arraycopy(key, 0, k, 0, 24);
            }

            DESedeKeySpec spec = new DESedeKeySpec(k);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(Algorithm);
            Key desKey = skf.generateSecret(spec);

            Cipher cipher = Cipher.getInstance(TriDes);
            IvParameterSpec ips = new IvParameterSpec(ivs);
            cipher.init(Cipher.ENCRYPT_MODE,desKey,ips);
            return cipher.doFinal(dstData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3DES CBC模式解密
     * @param key 解密密钥
     * @param data 解密数据
     * @param ivs 初始向量
     * @return 返回解密数据
     */
    public static byte[] des3DecodeCBC(byte[] key,byte[] data,byte[] ivs){
        try {
            byte[] k = new byte[24];
            if (data == null){
                return null;
            }
            byte[] dstData = new byte[((data.length + 7) / 8) * 8];
            System.arraycopy(data, 0, dstData, 0, data.length);

            if (key.length == 16) {
                System.arraycopy(key, 0, k, 0, key.length);
                System.arraycopy(key, 0, k, 16, 8);
            } else {
                System.arraycopy(key, 0, k, 0, 24);
            }

            DESedeKeySpec spec = new DESedeKeySpec(k);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(Algorithm);
            Key desKey = skf.generateSecret(spec);

            Cipher cipher = Cipher.getInstance(TriDes);
            IvParameterSpec ips = new IvParameterSpec(ivs);
            cipher.init(Cipher.DECRYPT_MODE,desKey,ips);
            return cipher.doFinal(dstData);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * CBC加密
     * @param key 加密密钥
     * @param data 加密数据
     * @return 返回加密内容
     */
    public static byte[] encryptMode(byte[] key, byte[] data,byte[] ivs) {
        try {
            SecretKey desKey = new SecretKeySpec(key, Algorithm); // 生成密钥21
            Cipher c1 = Cipher.getInstance(TriDes); // 实例化负责加密/解密的Cipher工具类22
            IvParameterSpec iv = new IvParameterSpec(ivs);
            c1.init(Cipher.ENCRYPT_MODE, desKey, iv); // 初始化为加密模式23
            return c1.doFinal(data);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * CBC解密
     * @param key 解密密钥
     * @param data 解密数据
     * @return 返回解密内容
     */
    public static byte[] decryptMode(byte[] key,byte[] data,byte[] ivs) {
        try {
            SecretKey desKey = new SecretKeySpec(key, Algorithm);
            Cipher c1 = Cipher.getInstance(TriDes);
            IvParameterSpec iv = new IvParameterSpec(ivs);
            c1.init(Cipher.DECRYPT_MODE, desKey, iv); // 初始化为解密模式44
            return c1.doFinal(data);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
}
