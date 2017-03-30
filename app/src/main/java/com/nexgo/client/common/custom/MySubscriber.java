package com.nexgo.client.common.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.nexgo.client.common.utils.LogUtils;
import com.nexgo.client.common.utils.NetworkUtils;
import com.nexgo.client.common.utils.ToastUtils;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;

/**
 * 自定义观察者：统一处理错误
 * @author 谭忠扬-YuriTam
 * @time 2016年10月11日
 */
public abstract class MySubscriber<T> extends Subscriber<T> {
    private static final String TAG = MySubscriber.class.getSimpleName();

    private static final int SHOW_DIALOG = 1;     //显示弹出层
    private static final int DISMISS_DIALOG = 2;  //隐藏弹出层

    private Context mContext;
    private String tipsMsg;
    private ProgressDialog mProDialog;

    Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SHOW_DIALOG:
                    showProDialog();
                    break;
                case DISMISS_DIALOG:
                    dismissProDialog();
                    break;
            }
        }
    };

    public MySubscriber(Context mContext,String tipsMsg){
        this.mContext = mContext;
        this.tipsMsg = tipsMsg;
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandler.sendEmptyMessage(SHOW_DIALOG);
        LogUtils.show(TAG,"******************** 发送请求 *********************");
        LogUtils.show(TAG,"onStart()" );
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.show(TAG,"onError()");
        if (e instanceof UnknownHostException){
            LogUtils.show(TAG,"通讯异常，请检查网络: " + e.getMessage());
            ToastUtils.show(mContext,"通讯异常，请检查网络");
        }else if(e instanceof ConnectException){
            LogUtils.show(TAG,"连接异常: " + e.getMessage());
            ToastUtils.show(mContext,"连接异常，请稍候再试");
        }else if (e instanceof TimeoutException){
            LogUtils.show(TAG,"超时异常: " + e.getMessage());
        }else if(e instanceof Error){
            LogUtils.show(TAG,"交易异常: " + e.getMessage());
            ToastUtils.show(mContext,e.getMessage());
        }else{
            LogUtils.show(TAG,"未知错误: " + e.getMessage());
            if (!NetworkUtils.isConnected(mContext)){
                throw new Error("通讯异常，请检查网络");
            }else {
                ToastUtils.show(mContext,"未知错误");
            }
        }
        mHandler.sendEmptyMessage(DISMISS_DIALOG);
        _onError();
    }

    public abstract void _onError();

    @Override
    public void onNext(T t) {
        LogUtils.show(TAG,"onNext()" );
        _onNext(t);
    }

    public abstract void _onNext(T t);

    @Override
    public void onCompleted() {
        mHandler.sendEmptyMessage(DISMISS_DIALOG);
        LogUtils.show(TAG,"onCompleted()" );
    }

    /**
     * 弹出加载层
     */
    private void showProDialog(){
        if(mProDialog != null){
            mProDialog = null;
        }
        mProDialog = new ProgressDialog(mContext);
        if(TextUtils.isEmpty(tipsMsg)){
            mProDialog.setMessage("正在处理，请稍候...");
        }
        mProDialog.setMessage(tipsMsg);
        mProDialog.setCanceledOnTouchOutside(false);
        mProDialog.setCancelable(false);
        mProDialog.show();
    }

    /**
     * 关闭加载层
     */
    private void dismissProDialog(){
        if(mProDialog.isShowing()){
            mProDialog.dismiss();
        }
        mProDialog = null;
    }

}
