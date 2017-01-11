package cn.mandroid.wechatrobot.ui.activity.welcome;

import android.content.Intent;

import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.anotation.LayoutId;
import cn.mandroid.wechatrobot.ui.activity.common.BaseActivity;
import cn.mandroid.wechatrobot.ui.activity.home.HomeActivity;
import cn.mandroid.wechatrobot.ui.activity.wechatlogin.WechatLoginActivity;

@LayoutId(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity<WelcomeContract.Presenter> implements WelcomeContract.View {


    @Override
    protected void afterView() {
        mPersenter.checkLoginCache();
    }

    @Override
    protected WelcomeContract.Presenter setPresenter() {
        return new WelcomePresenter(this);
    }

    @Override
    public void enterHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
