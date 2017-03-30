package com.nexgo.client.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.Calendar;

import static android.content.Context.WINDOW_SERVICE;

/**
 * @author 谭忠扬-YuriTam
 * @time 2016年11月12日
 * UI辅助工具类
 */
public class UIUtils {
    /**
     * @return  获取设备的屏幕宽度
     */
    public static int getScreenWidth(Context context){
        WindowManager manager= (WindowManager) context.getSystemService(WINDOW_SERVICE);
        return manager.getDefaultDisplay().getWidth();
    }

    /**
     * @return  获取设备的屏幕高度
     */
    public static int getScreenHeight(Context context){
        WindowManager manager= (WindowManager) context.getSystemService(WINDOW_SERVICE);
        return manager.getDefaultDisplay().getHeight();
    }

    /**
     * @param activity
     *  关闭系统软键盘
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private static long lastClickTime = 0;
    private static final int MIN_CLICK_DELAY_TIME = 1000;

    /**
     * 判断是否重复点击
     * @author 谭忠扬-YuriTam
     * @time 2017年2月21日
     * @return
     */
    public static boolean isDoubleClick() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            return true;
        }else {
            return false;
        }
    }

}
