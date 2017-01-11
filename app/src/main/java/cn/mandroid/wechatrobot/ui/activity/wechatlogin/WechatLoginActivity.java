package cn.mandroid.wechatrobot.ui.activity.wechatlogin;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.anotation.LayoutId;
import cn.mandroid.wechatrobot.ui.activity.common.BaseActivity;
import cn.mandroid.wechatrobot.ui.activity.home.HomeActivity;
import cn.mandroid.wechatrobot.ui.widget.RoundCornerImageView;
import cn.mandroid.wechatrobot.utils.ImageLoader;

@LayoutId(R.layout.activity_wechat_login)
public class WechatLoginActivity extends BaseActivity<WechatLoginContract.Presenter> implements WechatLoginContract.View {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    RoundCornerImageView mQrcodeImageView;
    TextView mPcOpenQrcodeNoticeTv;
    private WechatLoginViewPagerAdapter mWechatLoginViewPagerAdapter;


    @Override
    protected void afterView() {
        setSupportActionBar(mToolBar);
        setActionBarTitle("微信认证");
        setViewPager();
        mPersenter.getUUID();
    }

    private void setViewPager() {
        List<View> views = new ArrayList<>();
        View qrscanContainer = LayoutInflater.from(this).inflate(R.layout.view_pager_scan_qrcode, null);
        mQrcodeImageView = (RoundCornerImageView) qrscanContainer.findViewById(R.id.qrcodeImageView);
        mPcOpenQrcodeNoticeTv = (TextView) qrscanContainer.findViewById(R.id.pcOpenQrcodeNotice);
        views.add(qrscanContainer);
        mWechatLoginViewPagerAdapter = new WechatLoginViewPagerAdapter(this, views);
        mViewPager.setAdapter(mWechatLoginViewPagerAdapter);
    }

    @Override
    protected WechatLoginContract.Presenter setPresenter() {
        return new WeChatLoginPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wechat_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setQrcodeImage(String url) {
        ImageLoader.load(url, false).into(mQrcodeImageView);
    }

    @Override
    public void setPcOpenNotice(String url) {
        mPcOpenQrcodeNoticeTv.setText(String.format(getResources().getString(R.string.pc_open_qrcode_notice), url));
        mPcOpenQrcodeNoticeTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
