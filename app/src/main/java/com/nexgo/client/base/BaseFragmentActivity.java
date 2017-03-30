package com.nexgo.client.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.view.View;

import com.nexgo.client.R;
import com.nexgo.client.common.custom.CustomViewPager;
import com.nexgo.client.common.custom.TitleBuilder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 左右滑动的viewpager中fragment的activity基类
 * @author 谭忠扬-YuriTam
 * @time 2016年10月21日
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    @BindView(R.id.viewpager_base_activity)
    CustomViewPager mViewPager;

    private MyAdapter mAdapter;
    protected ArrayList<Fragment> mList;
    private int currentIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_viewpager);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initView(){
        new TitleBuilder(this)
                .setLeftImage(R.drawable.arrow)
                .setTitleText(setTitle());
    }

    public void addFragment(Fragment fragment) {
        mList.add(fragment);
        mAdapter.notifyDataSetChanged();
    }

    protected abstract String setTitle();

    public void nextPage() {
        mViewPager.setCurrentItem(++currentIndex);
    }

    private void initData() {
        mList = new ArrayList<>();
        initFragment();
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    protected abstract void initFragment();

    @OnClick(R.id.titlebar_iv_left)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.titlebar_iv_left:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            if (mList != null)
                return mList.size();
            return 0;
        }
    }
}
