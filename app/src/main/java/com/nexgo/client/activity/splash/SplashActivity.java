package com.nexgo.client.activity.splash;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.nexgo.client.R;
import com.nexgo.client.activity.main.MainActivity;
import com.nexgo.client.base.BaseActivity;
import com.nexgo.client.common.utils.LogUtils;

/**
 * 启动页面
 * @author 谭忠扬-YuriTam
 * @time 2017年3月29日
 */
public class SplashActivity extends BaseActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    private static final String LOGIN_ACTION = "android.intent.action.com.boc.spos.client.login";

    private Context mContext;
    private String startAction;

    Handler mHandler = new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    if (LOGIN_ACTION.equals(startAction)){
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("goToLogin",true);
                        intent2Activity(MainActivity.class,bundle);
                    }else {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("goToLogin",false);
                        intent2Activity(MainActivity.class,bundle);
                    }
                    finishActivity();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = this;
        startAction = getIntent().getAction();
        LogUtils.show(TAG,"-------------------------------------");
        LogUtils.show(TAG,"startAction : {}",startAction);
        LogUtils.show(TAG,"-------------------------------------");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        mHandler.sendEmptyMessage(1000);
    }
}
