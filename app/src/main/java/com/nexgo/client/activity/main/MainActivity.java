package com.nexgo.client.activity.main;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.nexgo.client.R;
import com.nexgo.client.base.BaseActivity;
import com.nexgo.client.common.custom.TitleBuilder;
import com.nexgo.client.common.utils.LogUtils;

/**
 * 主界面
 * @author 谭忠扬-YuriTam
 * @time 2017年3月29日
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        //初始化标题
        new TitleBuilder(this)
                .setTitleText("首页")
                .build();
    }

    @Override
    protected void initView() {
        LogUtils.show(TAG,"result code = {} ,成功：0，失败：-1","21");
        LogUtils.error(TAG,"result code = {} ,成功：0，失败：-1",12);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
