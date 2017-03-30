package com.nexgo.client.common.security;

import android.util.Base64;

import com.nexgo.client.common.utils.LogUtils;

/**
 * base64加、解密
 * @author 谭忠扬-YuriTam
 * @time 2016年10月13日
 */
public class Base64Utils {
    private static final String TAG = Base64Utils.class.getSimpleName();

    /**
     * 加密
     * @param data 字符串类型
     * @return 字符串类型
     */
    public static String enCodeBase64(String data){
        LogUtils.show(TAG, "Base64加密数据 = {}", data);
        return Base64.encodeToString(data.getBytes(), Base64.DEFAULT);
    }

    /**
     * 加密
     * @param data 字节类型
     * @return 字符串类型
     */
    public static String enCodeBase64(byte[] data){
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    /**
     * 解密
     * @param data 字符串类型
     * @return 字符串类型
     */
    public static byte[] deCodeBase64(String data){
        return Base64.decode(data.getBytes(),Base64.DEFAULT);
    }

    /**
     * 解密
     * @param data 字节类型
     * @return 字节类型
     */
    public static byte[] deCodeBase64(byte[] data){
        return Base64.decode(data, Base64.DEFAULT);
    }

}
