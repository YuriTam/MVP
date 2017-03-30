package com.nexgo.client.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 继承Fragment
 * @author 谭忠扬-YuriTam
 * @time 2016年10月13日
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
        initData();
    }

    /**
     * 初始化UI
     * @author 谭忠扬-YuriTam
     * @time 2016年10月13日
     * @param inflater
     * @param container
     * @return
     */
    protected abstract View initView(LayoutInflater inflater,ViewGroup container);

    /**
     * 初始化事件
     * @author 谭忠扬-YuriTam
     * @time 2016年10月13日
     */
    protected abstract void initEvent();

    /**
     * 初始化数据
     * @author 谭忠扬-YuriTam
     * @time 2016年10月13日
     */
    protected abstract void initData();
}
