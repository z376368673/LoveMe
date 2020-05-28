package com.zh.loveme.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author： zh浩
 * @创建时间:19-10-11 下午6:36
 * @描述： Fragment 基础类
 * @更新时间： 19-10-11 下午6:36
 * @更新说明： 无
 * @版本号 1.0
 */
public abstract class LibBaseFragment extends Fragment {
    private boolean isFirstVisible = true;
    private boolean isFirstLoad = false;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected Activity mActivity;
    protected Context mContext;
    protected View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // 让子类实现初始化视图
        mRootView = inflater.inflate(getLayoutId(), null,false);

        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //一些初始化工作
        init();
        //view
        initView(view);
        // 初始化事件
        initEvent();
        // 视图创建完成，将变量置为true
        isFirstLoad = true;
        // 如果Fragment可见进行数据加载
        if (getUserVisibleHint()) {
            onVisible();
            initData();
            isFirstLoad = false;
        }
    }

    private void init() {
        mContext = getContext();
        mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //视图销毁将变量置为false
        isFirstLoad = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //视图变为可见并且是第一次加载
        if (isVisibleToUser) {
            //因为可见就加载所以 必须比 initData 先要执行
            onVisible();
            if (isFirstLoad) {
                initData();
                isFirstLoad = false;
            }
        } else {
            onInvisible();
        }

    }


    /**
     * 可见 时执行
     */
    public void onVisible() {

    }

    /**
     * 不可见 时执行
     */
    public void onInvisible() {

    }


    /**
     * 接受一个layout文件的id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     *
     * @param view
     */
    public abstract void initView(View view);

    /**
     * 只执行一次
     * <p>
     * 数据加载接口，留给子类实现
     */
    public abstract void initData();

    /**
     * 监听 事件
     */
    public abstract void initEvent();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

}
