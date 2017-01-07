package cn.mandroid.wechatrobot.ui.activity.home;

import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.ui.activity.common.BaseActivity;

public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {


    @BindView(R.id.toolBar)
    Toolbar mToolBar;

    @Override
    protected int setContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void afterView() {
        mPersenter.loadUserInfo();
    }


    @Override
    protected HomeContract.Presenter setPresenter() {
        HomeContract.Presenter presenter = new HomePresenter(this);
        return presenter;
    }

    @Override
    public void setNickname(String nickname) {
        mToolBar.setTitle(nickname);
    }

}
