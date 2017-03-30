package com.nexgo.client.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.nexgo.client.common.security.DesUtils;

/**
 * 存储数据
 * @author 谭忠扬-YuriTam
 * @time 2016年10月10日
 */
public class SharedUtils {
    private static final String TAG = SharedUtils.class.getSimpleName();

    private static final String SYS_NAME = "SharedUtils";

    /**
     * 加密保存数据
     * @param mContext
     * @param key 键
     * @param value 值
     */
    public static void saveEncryptValue(Context mContext, String key, String value){
        if(mContext == null || TextUtils.isEmpty(key) || TextUtils.isEmpty(value)){
            return;
        }
        //单倍DES加密
        saveStringValue(mContext,key, DesUtils.getEncString(value));
    }

    /**
     * 获取明文数据
     * @param mContext
     * @param key 键
     * @return 值
     */
    public static String getDecryptValue(Context mContext, String key){
        if (TextUtils.isEmpty(key) || mContext == null){
            return null;
        }
        String value = getStringValue(mContext,key);
        if (TextUtils.isEmpty(value)){
            return null;
        }
        return DesUtils.getDesString(value);
    }

    /**
     * 保存String类型数据
     * @param mContext
     * @param key 键
     * @param value 值
     */
    public static void saveStringValue(Context mContext, String key, String value){
        if(mContext == null || TextUtils.isEmpty(key) || TextUtils.isEmpty(value)){
            return;
        }
        SharedPreferences pref = mContext.getSharedPreferences(SYS_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putString(key,value);
        editor.commit();
        LogUtils.show(TAG,"保存数据：" + key + " = {}", value);
    }

    /**
     * 根据键获取对应的值
     * @param mContext
     * @param key 键
     * @return 返回值
     */
    public static String getStringValue(Context mContext, String key){
        if (TextUtils.isEmpty(key) || mContext == null){
            return null;
        }
        SharedPreferences pref = mContext.getSharedPreferences(SYS_NAME, Context.MODE_APPEND);
        String value = pref.getString(key,"");
        LogUtils.show(TAG,"取出数据：" + key + " = {}", value);
        return value;
    }

    /**
     * 保存int类型数据
     * @param mContext
     * @param key 键
     * @param value 值
     */
    public static void saveIntValue(Context mContext, String key, int value){
        if(mContext == null || TextUtils.isEmpty(key)){
            return;
        }
        SharedPreferences pref = mContext.getSharedPreferences(SYS_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putInt(key,value);
        editor.commit();
        LogUtils.show(TAG,"保存数据：" + key + " = {}", value);
    }

    /**
     * 根据键获取对应的值
     * @param mContext
     * @param key 键
     * @return 返回值
     */
    public static int getIntValue(Context mContext, String key){
        if (TextUtils.isEmpty(key) || mContext == null){
            return 0;
        }
        SharedPreferences pref = mContext.getSharedPreferences(SYS_NAME, Context.MODE_APPEND);
        int value = pref.getInt(key,0);
        LogUtils.show(TAG,"取出数据：" + key + " = {}", value);
        return value;
    }

    /**
     * 保存int类型数据
     * @param mContext
     * @param key 键
     * @param value 值
     */
    public static void saveLongValue(Context mContext, String key, long value){
        if(mContext == null || TextUtils.isEmpty(key)){
            return;
        }
        SharedPreferences pref = mContext.getSharedPreferences(SYS_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putLong(key,value);
        editor.commit();
        LogUtils.show(TAG,"保存数据：" + key + " = {}", value);
    }

    /**
     * 根据键获取对应的值
     * @param mContext
     * @param key 键
     * @return 返回值
     */
    public static long getLongValue(Context mContext, String key){
        if (TextUtils.isEmpty(key) || mContext == null){
            return 0;
        }
        SharedPreferences pref = mContext.getSharedPreferences(SYS_NAME, Context.MODE_APPEND);
        long value = pref.getLong(key,0);
        LogUtils.show(TAG,"取出数据：" + key + " = {}", value);
        return value;
    }

    /**
     * 保存boolean类型数据
     * @param mContext
     * @param key 键
     * @param value 值
     */
    public static void saveBooleanValue(Context mContext, String key, boolean value){
        if(mContext == null || TextUtils.isEmpty(key)){
            return;
        }
        SharedPreferences pref = mContext.getSharedPreferences(SYS_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putBoolean(key,value);
        editor.commit();
        LogUtils.show(TAG,"保存数据：" + key + " = {}", value);
    }

    /**
     * 根据键获取对应的值
     * @param mContext
     * @param key 键
     * @return 返回值
     */
    public static boolean getBooleanValue(Context mContext, String key){
        if (TextUtils.isEmpty(key) || mContext == null){
            return false;
        }
        SharedPreferences pref = mContext.getSharedPreferences(SYS_NAME, Context.MODE_APPEND);
        boolean value = pref.getBoolean(key,false);
        LogUtils.show(TAG,"取出数据：" + key + " = {}", value);
        return value;
    }

    /**
     * 移除某个数据
     * @param mContext
     * @param key
     */
    public static void remove(Context mContext, String key){
        if(mContext == null || TextUtils.isEmpty(key)){
            return;
        }
        SharedPreferences pref = mContext.getSharedPreferences(SYS_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
        LogUtils.show(TAG,"移除数据：key = {}", key);
    }

    /**
     * 清空所有数据
     * @param mContext
     */
    public static void clear(Context mContext){
        if(mContext == null){
            return;
        }
        SharedPreferences pref = mContext.getSharedPreferences(SYS_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        LogUtils.show(TAG,"清空SysKeeper中的所有数据");
    }
}
