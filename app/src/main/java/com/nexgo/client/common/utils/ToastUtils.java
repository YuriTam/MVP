package com.nexgo.client.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 显示Toast
 * @author 谭忠扬-YuriTam
 * @date 2015年10月10日
 */
public class ToastUtils {

	/**
	 * Toast消息提示
	 * @author 谭忠扬-YuriTam
	 * @time 2015年11月23日
	 * @param context
	 * @param msg
	 */
	public static void show(Context context, CharSequence msg, int duration){
		if(msg == null){
			return;
		}
		Toast.makeText(context, msg, duration).show();
	}

	/**
	 * Toast消息提示
	 * @author 谭忠扬-YuriTam
	 * @time 2015年11月23日
	 * @param context
	 * @param msg
	 */
	public static void show(Context context, CharSequence msg){
		showShort(context,msg);
	}

	/**
	 * Toast消息提示
	 * @author 谭忠扬-YuriTam
	 * @time 2015年11月23日
	 * @param context
	 * @param msg
	 */
	public static void showShort(Context context, CharSequence msg){
		if(context == null || TextUtils.isEmpty(msg)){
			return;
		}
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Toast消息提示
	 * @author 谭忠扬-YuriTam
	 * @time 2015年11月23日
	 * @param context
	 * @param msg
	 */
	public static void showLong(Context context, CharSequence msg){
		if(msg == null){
			return;
		}
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
}
