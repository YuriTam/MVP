package com.nexgo.client.common.logger;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

/**
 * 日志工具类，修改输出日志，不可保存
 * @author 谭忠扬-YuriTam
 * @date 2017年3月30日
 */
public class LogUtils {

	/**
	 * 默认输出日志，级别debug
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param object 输出对象
     */
	public static void show(Object object){
		if (object == null){
			return;
		}
		Logger.d(object);
	}

	/**
	 * 默认输出日志，级别debug
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param object 输出对象
     */
	public static void show(String TAG, Object object) {
		if (object == null){
			return;
		}
		Logger.t(TAG).d(object);
	}

	/**
	 * 默认输出日志
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
     */
	public static void debug(String msg,Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.d(msg,objects);
	}

	/**
	 * 默认输出日志，级别debug
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
     */
	public static void debug(String TAG, String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.t(TAG).d(msg,objects);
	}

	/**
	 * 输出日志，级别INFO
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
     */
	public static void info(String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.i(msg,objects);
	}

	/**
	 * 输出日志，级别INFO
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
     */
	public static void info(String TAG, String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.t(TAG).i(msg,objects);
	}

	/**
	 * 输出日志，级别WARN
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
	 */
	public static void warn(String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.w(msg,objects);
	}

	/**
	 * 输出日志，级别WARN
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
	 */
	public static void warn(String TAG, String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.t(TAG).w(msg,objects);
	}

	/**
	 * 输出日志，级别ERROR
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
	 */
	public static void error(String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.e(msg,objects);
	}

	/**
	 * 输出日志，级别ERROR
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
	 */
	public static void error(String TAG, String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.t(TAG).e(msg,objects);
	}

	/**
	 * 输出日志，级别ERROR
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
	 */
	public static void error(Throwable throwable, String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.e(throwable,msg,objects);
	}

	/**
	 * 输出日志，级别ERROR
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
	 */
	public static void error(String TAG, Throwable throwable, String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.t(TAG).e(throwable,msg,objects);
	}

	/**
	 * 输出日志
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
	 */
	public static void wtf(String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.wtf(msg,objects);
	}

	/**
	 * 输出日志
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param msg 输出内容
	 * @param objects 输出对应的对象
	 */
	public static void wtf(String TAG, String msg, Object... objects){
		if (TextUtils.isEmpty(msg) || objects == null){
			return;
		}
		Logger.t(TAG).wtf(msg,objects);
	}

	/**
	 * 输出日志，级别JSON
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param json 输出json内容
	 */
	public static void json(String json){
		if (TextUtils.isEmpty(json)){
			return;
		}
		Logger.json(json);
	}

	/**
	 * 输出日志，级别JSON
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param json 输出内容
	 */
	public static void json(String TAG, String json){
		if (TextUtils.isEmpty(json)){
			return;
		}
		Logger.t(TAG).json(json);
	}

	/**
	 * 输出日志，级别XML
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param xml 输出xml内容
	 */
	public static void xml(String xml){
		if (TextUtils.isEmpty(xml)){
			return;
		}
		Logger.xml(xml);
	}

	/**
	 * 输出日志，级别XML
	 * @author 谭忠扬-YuriTam
	 * @date 2017年3月30日
	 * @param TAG 标签
	 * @param xml 输出xml内容
	 */
	public static void xml(String TAG, String xml){
		if (TextUtils.isEmpty(xml)){
			return;
		}
		Logger.t(TAG).xml(xml);
	}

}
