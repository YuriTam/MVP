package com.nexgo.client.common.constant;

import android.os.Environment;

/**
 * 系统常量
 * @谭忠扬-YuriTam 
 * @2015年10月10日
 *
 */
public class SysConstant {

	//编码格式
	public static final String UTF_8 = "UTF-8";
	public static final String GB_2312 = "GB2312";
	public static final String ISO_8859_1 = "ISO-8859-1";

	//1000*60*60*24 一天毫秒数
	public static final long ONE_DAY_MILLS = 86400000;

	//是否打印日志
	public static final boolean isShowLog = true;
	//SD卡路径
	public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	//日志保存路径
	public static final String LOG_PATH = BASE_PATH + "/AppLog/";
	//外置SD卡路径 /storage/sdcard1
	public static final String EXTERNAL_SD_PATH = "/storage/sdcard1";
	//日志导出保存路径
	public static final String ZIP_LOG_PATH = EXTERNAL_SD_PATH + "/ExportLog";
}
