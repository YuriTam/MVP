package com.nexgo.client.core.manager;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * 管理activity类
 * @author 谭忠扬-YuriTam
 * @time 2016年10月09日
 */
public class ActivityManager {
    private static final String TAG = ActivityManager.class.getSimpleName();

    private static ActivityManager mManager;
    private static Set<Activity> mSets;

    private ActivityManager(){
        if(mSets == null){
            mSets = new HashSet<>();
        }
    }

    /**
     * 单例模式
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     * @return
     */
    public static ActivityManager getInstance(){
        if(mManager == null){
            synchronized (ActivityManager.class){
                if(mManager == null){
                    mManager = new ActivityManager();
                }
            }
        }
        return mManager;
    }

    /**
     * 添加新Activity到mSets中
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     * @param mActivity
     */
    public void addActivity(Activity mActivity){
        //判断是否含有此Activity
        if (!mSets.contains(mActivity)){
            mSets.add(mActivity);
        }
    }

    /**
     * 移除mSets中的Activity
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     * @param mActivity
     */
    public void removeActivity(Activity mActivity){
        //判断是否含有此Activity
        if(mSets.contains(mActivity)){
            mActivity.finish();
            mSets.remove(mActivity);
            mActivity = null;
        }
    }

    /**
     * 关闭mSets中所有的Activity
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     */
    public void finishAllActivity(){
        if(!mSets.isEmpty()){
            for (Activity mActivity : mSets){
                if (mActivity != null){
                    mActivity.finish();
                }
            }
            mSets.clear();
        }
    }
}
