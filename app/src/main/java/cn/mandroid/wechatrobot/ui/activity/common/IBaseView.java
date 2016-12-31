package cn.mandroid.wechatrobot.ui.activity.common;

import android.content.Context;

/**
 * Created by wrBug on 2016/12/31.
 */

public interface IBaseView {
    void showProgress();

    void showToast(String msg);

    void exit();

    Context getActivity();

    void showLog(String log);
}
