package cn.mandroid.wechatrobot.ui.activity.home;

import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.ui.activity.common.BaseActivity;
import cn.mandroid.wechatrobot.ui.activity.wechatlogin.WechatLoginActivity;

public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {


    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;
    @BindView(R.id.textView)
    TextView mTextView;

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterView() {
    }

    @Override
    protected HomeContract.Presenter setPresenter() {
        HomeContract.Presenter presenter = new HomePresenter(this);
        return presenter;
    }


}
