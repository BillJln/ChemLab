package com.jit.funchemistry.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mBind;
    protected static Context context = BaseApplication.getAppContext();

    public static Context getContext(){
        return context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mBind = ButterKnife.bind(this);
        initView();
        initEvent();
        initPresenter();
    }

    protected abstract void initPresenter();

    protected void initEvent() {
    }

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    /**
     * 子类需要就复写
     */
    protected void release() {
    }

    protected abstract int getLayoutResId();

    /**
     * 设置状态栏背景色
     */
    public void setStatusBarBackground(@ColorRes int statusBarColor) {
        ImmersionBar.with(this).autoStatusBarDarkModeEnable(true, 0.2f).statusBarColor(statusBarColor).fitsSystemWindows(true).init();
    }
}
