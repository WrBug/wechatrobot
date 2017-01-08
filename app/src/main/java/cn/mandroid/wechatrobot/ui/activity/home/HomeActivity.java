package cn.mandroid.wechatrobot.ui.activity.home;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.ui.activity.common.BaseActivity;
import cn.mandroid.wechatrobot.ui.widget.chatview.LeftChatBubble;
import cn.mandroid.wechatrobot.ui.widget.faboptions.FabOptions;
import cn.mandroid.wechatrobot.utils.ImageLoader;

public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {
    @BindView(R.id.activity_home)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.chat)
    LeftChatBubble mLeftChatBubble;
    @BindView(R.id.floatMenu)
    FabOptions mFloatMenu;
    AppCompatImageView avatar;

    @Override
    protected int setContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void afterView() {
        mFloatMenu.setButtonsMenu(this, R.menu.menu_home_bottom);
        avatar = mFloatMenu.getChild(0);
        mPersenter.loadUserInfo();
        mLeftChatBubble.setMessage("呵呵呵");
        mFloatMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.userAvatarMenu: {
                        break;
                    }
                }
            }
        });
    }


    @Override
    protected HomeContract.Presenter setPresenter() {
        HomeContract.Presenter presenter = new HomePresenter(this);
        return presenter;
    }

    @Override
    public void setNickname(String nickname) {
        mToolBar.setTitle(nickname);
        showToast(nickname + "，欢迎回来！", mCoordinatorLayout);
    }

    @Override
    public void test(WechatUserBean userBean) {
        mLeftChatBubble.setUser(userBean);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.questionMenu: {
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setAvatarImage(final String url) {
        ImageLoader.load(url).cropCircle(true).into(avatar);
    }


}
