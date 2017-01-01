package cn.mandroid.wechatrobot.ui.activity.home;

import android.Manifest;
import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.ui.activity.common.BaseActivity;
import cn.mandroid.wechatrobot.ui.activity.wechatlogin.WechatLoginActivity;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

public class HomeActivity extends BaseActivity<HomeConstract.Presenter> implements HomeConstract.View {


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
        Intent intent = new Intent(this, WechatLoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void beforeInject() {
    }



    @Override
    protected HomeConstract.Presenter setPresenter() {
        HomeConstract.Presenter presenter = new HomePresenter(this);
        return presenter;
    }


}
