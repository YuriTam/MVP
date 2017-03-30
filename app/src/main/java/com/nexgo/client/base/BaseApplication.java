package com.nexgo.client.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nexgo.client.common.constant.SysConstant;
import com.nexgo.client.core.manager.ActivityManager;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.apache.log4j.Level;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * 应用入口
 * @author 谭忠扬-YuriTam
 * @time 2017年02月14日
 */
public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();

    public static Context mContext;  //全局上下文对象

    @Override
    public void onCreate() {
        super.onCreate();

        //日志保存配置
        sysLogConfig("debug.log");
        //初始化日志输出
        Logger.init("Base")
                .methodCount(0)
                .hideThreadInfo()
                .logLevel(LogLevel.FULL);

        mContext = getApplicationContext();
    }

    /**
     * 系统日志配置
     * @param logFileName 日志保存文件名
     */
    private void sysLogConfig(String logFileName) {
        LogConfigurator logConfigurator = new LogConfigurator();
        try {
            //日志输出路径
            logConfigurator.setFileName(SysConstant.LOG_PATH + this.getPackageName()
                    + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date())
                    + File.separator + (logFileName == null ? "debug.log" : logFileName));
            logConfigurator.setRootLevel(Level.DEBUG);
            logConfigurator.setLevel("org.apache", Level.ERROR);
            logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
            logConfigurator.setMaxFileSize(1024 * 1024 * 5);
            logConfigurator.setImmediateFlush(true);
            logConfigurator.configure();
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"日志配置出错：" + e.getMessage());
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActivityManager.getInstance().finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());//获取PID
    }
}
