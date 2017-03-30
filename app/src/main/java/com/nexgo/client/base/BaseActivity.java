package com.nexgo.client.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.nexgo.client.common.utils.LogUtils;
import com.nexgo.client.common.utils.ToastUtils;
import com.nexgo.client.core.manager.ActivityManager;

/**
 * 应用的父Activity，之后的相应界面需继承此Activity
 * @author 谭忠扬-YuriTam
 * @time 2016年9月14日
 */
public abstract class BaseActivity extends Activity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    private boolean banBackKey = false;
    private ProgressDialog mProDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);

        initView();
        initEvent();
        initData();
    }

    /**
     * 初始化
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     */
    protected abstract void initView();

    /**
     * 初始化事件
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     */
    protected abstract void initEvent();

    /**
     * 初始化数据
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     */
    protected abstract void initData();

    /**
     * 跳转到其它页面
     * @author 谭忠扬-YuriTam
     * @date 2016年9月14日
     * @param tarActivity
     */
    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(BaseActivity.this, tarActivity);
        startActivity(intent);
    }

    /**
     * 跳转到其它页面-带参数
     * @author 谭忠扬-YuriTam
     * @date 2016年9月14日
     * @param tarActivity
     * @param mBundle
     */
    protected void intent2Activity(Class<? extends Activity> tarActivity, Bundle mBundle) {
        Intent intent = new Intent(BaseActivity.this, tarActivity);
        intent.putExtras(mBundle);
        startActivity(intent);
    }

    /**
     * 弹出提示信息
     * @author 谭忠扬-YuriTam
     * @date 2016年9月14日
     * @param msg
     */
    protected void showToast(String msg) {
        ToastUtils.show(BaseActivity.this, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 弹出提示信息
     * @author 谭忠扬-YuriTam
     * @date 2016年9月14日
     * @param msg
     */
    protected void showToast(String msg, int length) {
        ToastUtils.show(BaseActivity.this, msg, length);
    }

    /**
     * 打印日志
     * @author 谭忠扬-YuriTam
     * @date 2016年9月14日
     * @param msg 输出内容
     */
    protected void showLog(String TAG, String msg){
        LogUtils.show(TAG,msg);
    }

    /**
     * 打印日志
     * @author 谭忠扬-YuriTam
     * @date 2016年9月14日
     * @param msg 输出内容
     * @param objects 对应的值
     */
    protected void showLog(String TAG, String msg, Object... objects){
        LogUtils.show(TAG,msg,objects);
    }

    /**
     * 关闭此Activity
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     */
    protected void finishActivity(){
        ActivityManager.getInstance().removeActivity(this);
        this.finish();
    }

    /**
     * 关闭此Activity
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     */
    protected void finishActivityNoAnim(){
        ActivityManager.getInstance().removeActivity(this);
        this.finish();
    }

    /**
     * 设置是否退出程序
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     * @param isBackExit
     */
    protected void setBanBackKey(boolean isBackExit){
        this.banBackKey = isBackExit;
    }

    /**
     * 显示加载层
     * @author 谭忠扬-YuriTam
     * @time 2016年10月20日
     * @param msg
     */
    protected void showProDialog(Context mContext,String msg){
        //创建加载弹出层
        mProDialog = new ProgressDialog(mContext);
        mProDialog.setCanceledOnTouchOutside(false);
        mProDialog.setMessage(msg);
        mProDialog.show();
    }

    /**
     * 关闭加载层
     * @author 谭忠扬-YuriTam
     * @time 2016年10月9日
     */
    protected void dismissProDialog(){
        if(mProDialog.isShowing()){
            mProDialog.dismiss();
        }
        mProDialog = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if(banBackKey){
                /*if((System.currentTimeMillis() - exitTime) > 2000){
                    showToast("再按一次退出程序");
                    exitTime = System.currentTimeMillis();
                }else{
                    ActivityManager.getInstance().finishAllActivity();
                    System.exit(0);
                }*/
                return true;
            }
            finishActivity();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mProDialog != null){
            mProDialog = null;
        }
    }
}
