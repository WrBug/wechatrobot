package cn.mandroid.wechatrobot.ui.activity.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

    @LayoutRes
    protected abstract int setContentView();

    protected abstract void afterView();

    protected abstract void beforeInject();

    protected abstract T setPresenter();

    @Override
    public void exit() {
        for (BaseActivity activity : mActivitylist) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    @Override
    public void showLog(String log) {
        MLog.i(log);
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
