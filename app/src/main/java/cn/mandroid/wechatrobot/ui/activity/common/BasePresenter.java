package cn.mandroid.wechatrobot.ui.activity.common;

/**
 * Created by wrBug on 2017/1/1.
 */

public class BasePresenter<T extends IBaseView> implements IBasePresenter {
    protected T mView;

    public BasePresenter(T view) {
        mView = view;
    }
}
