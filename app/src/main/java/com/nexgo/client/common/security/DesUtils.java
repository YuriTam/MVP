package com.nexgo.client.common.security;

import android.util.Base64;
import com.nexgo.client.common.constant.SysConstant;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加解密
 * @author 谭忠扬-YuriTam
 * @time 2016年11月2日
 */
public class DesUtils {

    private static byte[] ivs = {1,2,3,4,5,6,7,8};
    private static final String keyCode = "SPOS2017";

    /**
     * 明文加密
     * @param strMing
     * @return
     */
    public static String getEncString(String strMing) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        try{
            byteMing = strMing.getBytes(SysConstant.UTF_8);
            byteMi = getEncCode(byteMing);
            strMi = Base64.encodeToString(byteMi, Base64.DEFAULT).trim().replace("\n","").replace("\r","");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }

    /**
     * 密文解密
     * @param strMi
     * @return
     */
    public static String getDesString(String strMi) {
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        try {
            byteMi = Base64.decode(strMi,Base64.DEFAULT);
            byteMing = getDesCode(byteMi);
            strMing = new String(byteMing,SysConstant.UTF_8).trim().replace("\n","").replace("\r","");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }

    /**
     * DES CBC加密
     * @param byteS
     * @return
     */
    private static byte[] getEncCode(byte[] byteS) {
        byte[] byteFina = null;
        Cipher cipher;
        try{
            SecretKeySpec key = new SecretKeySpec(keyCode.getBytes(), "DES");
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec zeroIv = new IvParameterSpec(ivs);
            cipher.init(Cipher.ENCRYPT_MODE,key,zeroIv);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e){
            e.printStackTrace();
        }
        return byteFina;
    }

    /**
     * DES CBC 解密
     * @param byteD
     * @return
     */
    private static byte[] getDesCode(byte[] byteD) {
        Cipher cipher;
        byte[] byteFina = null;
        try{
            SecretKeySpec key = new SecretKeySpec(keyCode.getBytes(), "DES");
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec zeroIv = new IvParameterSpec(ivs);
            cipher.init(Cipher.DECRYPT_MODE,key,zeroIv);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            cipher = null;
        }
        return byteFina;
    }
}
