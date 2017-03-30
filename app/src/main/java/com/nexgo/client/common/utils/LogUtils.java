package com.nexgo.client.common.utils;

import android.text.TextUtils;
import android.util.Log;

import com.nexgo.client.common.constant.SysConstant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类，可保存日志到文件
 * @author 谭忠扬-YuriTam
 * @date 2017年3月30日
 */
public class LogUtils {

	private static Logger LOGGER;

	/**
	 * 显示LOG，默认级别DEBUG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 */
	public static void show(String TAG, String msg) {
		showLog(TAG,msg,Log.DEBUG);
	}

	/**
	 * 显示LOG，默认级别DEBUG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 */
	public static void show(String TAG, String msg, Object... objects) {
		showLog(TAG,Log.DEBUG,msg,objects);
	}

	/**
	 * 显示LOG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 */
	public static void info(String TAG, String msg) {
		showLog(TAG,msg,Log.INFO);
	}

	/**
	 * 显示LOG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 */
	public static void info(String TAG, String msg, Object... objects) {
		showLog(TAG,Log.INFO,msg,objects);
	}

	/**
	 * 显示LOG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 */
	public static void debug(String TAG, String msg) {
		showLog(TAG,msg,Log.DEBUG);
	}

	/**
	 * 显示LOG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 */
	public static void debug(String TAG, String msg, Object... objects) {
		showLog(TAG,Log.DEBUG,msg,objects);
	}

	/**
	 * 显示LOG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 */
	public static void warn(String TAG, String msg) {
		showLog(TAG,msg,Log.WARN);
	}

	/**
	 * 显示LOG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 */
	public static void warn(String TAG, String msg, Object... objects) {
		showLog(TAG,Log.WARN,msg,objects);
	}

	/**
	 * 显示LOG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 */
	public static void error(String TAG, String msg) {
		showLog(TAG,msg,Log.ERROR);
	}

	/**
	 * 显示LOG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 */
	public static void error(String TAG, String msg, Object... objects) {
		showLog(TAG,Log.ERROR,msg,objects);
	}

	/**
	 * 显示LOG
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 * @param level 级别
	 *            1-info; 2-debug; 3-verbose
	 */
	private static void showLog(String TAG, String msg, int level) {
		if (!SysConstant.isShowLog || TextUtils.isEmpty(msg)) {
			return;
		}
		LOGGER = LoggerFactory.getLogger(TAG);
		switch (level) {
		case Log.DEBUG:
			LOGGER.debug(msg);
			break;
		case Log.INFO:
			LOGGER.info(msg);
			break;
		case Log.WARN:
			LOGGER.warn(msg);
			break;
		case Log.ERROR:
			LOGGER.error(msg);
			break;
		default:
			LOGGER.info(msg);
			break;
		}
	}

	/**
	 * 日志输出
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 * @param objects 对应的值
     */
	private static void showLog(String TAG, int level, String msg, Object... objects) {
		if (!SysConstant.isShowLog || TextUtils.isEmpty(msg)) {
			return;
		}
		LOGGER = LoggerFactory.getLogger(TAG);
		switch (level) {
			case Log.DEBUG:
				LOGGER.debug(msg,objects);
				break;
			case Log.INFO:
				LOGGER.info(msg,objects);
				break;
			case Log.WARN:
				LOGGER.warn(msg,objects);
				break;
			case Log.ERROR:
				LOGGER.error(msg,objects);
				break;
			default:
				LOGGER.info(msg,objects);
				break;
		}
	}

}
