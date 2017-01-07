package cn.mandroid.wechatrobot.ui.activity.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import butterknife.ButterKnife;
import cn.mandroid.wechatrobot.BuildConfig;
import cn.mandroid.wechatrobot.utils.MLog;

/**
 * Created by wrBug on 2016/12/31.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    protected Context mContext;
    private static List<BaseActivity> mActivitylist = new ArrayList<>();
    protected T mPersenter;
    protected ActionBar mActionBar;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitylist.add(this);
        mContext = this;
        beforeInject();
        setContentView(setContentView());
        ButterKnife.bind(this);
        mPersenter = setPresenter();
        if (mPersenter == null) {
            throw new NullPointerException("presenter is null");
        }
        afterView();
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
    }

    @LayoutRes
    protected abstract int setContentView();

    protected abstract void afterView();

    protected void beforeInject() {
    }

    protected abstract T setPresenter();

    @Override
    public void exit() {
        for (BaseActivity activity : mActivitylist) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 设置子标题
     *
     * @param subTitle
     */
    @Override
    public void setActionBarSubTitle(String subTitle) {
        mActionBar.setSubtitle(subTitle);
    }

    @Override
    public void setActionBarSubTitle(int resId) {
        mActionBar.setSubtitle(resId);
    }

    @Override
    public void showLog(String log) {
        MLog.i(log);
    }

    @Override
    public void setActionBarTitle(String title) {
        mActionBar.setTitle(title);
    }

    @Override
    public void setActionBarTitle(int resId) {
        mActionBar.setTitle(resId);
    }

    @Override
    public Context getActivity() {
        return mContext;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showToast(String msg) {
        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }
}
