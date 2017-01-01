package cn.mandroid.wechatrobot.ui.activity.common;

import android.content.Context;

/**
 * Created by wrBug on 2016/12/31.
 */

public interface IBaseView {
    /**
     * 显示加载框
     */
    void showProgress();

    /**
     * 显示提示
     *
     * @param msg
     */
    void showToast(String msg);

    /**
     * 退出应用
     */
    void exit();

    Context getActivity();

    /**
     * 打印日志
     *
     * @param log
     */
    void showLog(String log);

    /**
     * 设置标题
     *
     * @param title
     */
    void setActionBarTitle(String title);

    void setActionBarTitle(int resId);

    /**
     * 设置子标题
     *
     * @param subTitle
     */
    void setActionBarSubTitle(String subTitle);

    void setActionBarSubTitle(int resId);
}
