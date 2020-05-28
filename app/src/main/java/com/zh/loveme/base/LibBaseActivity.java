package com.zh.loveme.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author： zh浩
 * @创建时间:19-10-11 下午6:35
 * @描述： activity 基础类
 * @更新时间： 19-10-11 下午6:35
 * @更新说明： 无
 * @版本号 1.0
 */
public abstract class LibBaseActivity extends AppCompatActivity {
    public String TAG = null;
    public Activity mActivity;
    public Context mContext;
    protected Bundle savedInstanceState;
//    public TitleView titleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
        TAG = getClass().getSimpleName();
        this.savedInstanceState = savedInstanceState;

        init(savedInstanceState);
    }


    /**
     * 获取 ContentView 的 ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化工作
     */
    protected void init(Bundle savedInstanceState) {
        if (getLayoutId() != 0) {
            ViewGroup view = (ViewGroup) LayoutInflater.from(this).inflate(getLayoutId(), null);
            setContentView(view);
            initStatusBar();
            initTitleView();
            initView();
            initEvent();
            initData();
        }
    }
    protected void  initStatusBar(){

    }
    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化 事件
     */
    protected abstract void initEvent();

    /**
     * 初始化 TitleView
     */
    protected void initTitleView() {
//        titleView = new TitleView(mActivity);
//        titleView.setBackListener();
    }

    public void startAct(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
    public void startAct(Class cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startAct(Class cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent,requestCode,bundle);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
