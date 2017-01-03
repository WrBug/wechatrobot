package cn.mandroid.wechatrobot.ui.activity.wechatlogin;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.ui.activity.common.BaseActivity;

public class WechatLoginActivity extends BaseActivity<WechatLoginContract.Presenter> implements WechatLoginContract.View {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    ImageView mQrcodeImageView;
    TextView mPcOpenQrcodeNoticeTv;
    private WechatLoginViewPagerAdapter mWechatLoginViewPagerAdapter;

    @Override
    protected int setContentView() {
        return R.layout.activity_wechat_login;
    }

    @Override
    protected void afterView() {
        setSupportActionBar(mToolBar);
        setActionBarTitle("微信认证11");
        setViewPager();
        mPersenter.getUUID();
        showToast("卧槽");
    }

    private void setViewPager() {
        List<View> views = new ArrayList<>();
        View qrscanContainer = LayoutInflater.from(this).inflate(R.layout.view_pager_scan_qrcode, null);
        mQrcodeImageView = (ImageView) qrscanContainer.findViewById(R.id.qrcodeImageView);
        mPcOpenQrcodeNoticeTv = (TextView) qrscanContainer.findViewById(R.id.pcOpenQrcodeNotice);
        views.add(qrscanContainer);
        mWechatLoginViewPagerAdapter = new WechatLoginViewPagerAdapter(this, views);
        mViewPager.setAdapter(mWechatLoginViewPagerAdapter);
    }

    @Override
    protected void beforeInject() {

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
    public void setQrcodeImage(Bitmap bitmap) {
        mQrcodeImageView.setImageBitmap(bitmap);
    }

    @Override
    public void setPcOpenNotice(String url) {
        mPcOpenQrcodeNoticeTv.setText(String.format(getResources().getString(R.string.pc_open_qrcode_notice), url));
        mPcOpenQrcodeNoticeTv.setVisibility(View.VISIBLE);
    }

}
