package cn.mandroid.wechatrobot.ui.activity.home;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.anotation.LayoutId;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.WechatMessageBean;
import cn.mandroid.wechatrobot.ui.activity.common.BaseActivity;
import cn.mandroid.wechatrobot.ui.broadcastreceiver.NewMessageReceiver;
import cn.mandroid.wechatrobot.ui.service.NewWechatMessageListenerLoopService;
import cn.mandroid.wechatrobot.ui.widget.RoundCornerImageView;
import cn.mandroid.wechatrobot.ui.widget.chatview.ChatView;
import cn.mandroid.wechatrobot.ui.widget.faboptions.FabOptions;
import cn.mandroid.wechatrobot.utils.ImageLoader;
import me.drakeet.materialdialog.MaterialDialog;
@LayoutId(R.layout.activity_home)
public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View, NewMessageReceiver.OnNewMessageListener {
    @BindView(R.id.activity_home)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.chatView)
    ChatView mChatView;
    @BindView(R.id.floatMenu)
    FabOptions mFloatMenu;
    RoundCornerImageView avatar;
    private boolean contactorsLoadFinished;
    private WechatAuthenticationBean mWechatAuthenticationBean;
    private NewMessageReceiver mNewMessageReceiver;
    private boolean isServiceStarted;
    private boolean isRobotStart;

    @Override
    protected void afterView() {
        setSupportActionBar(mToolBar);
        mFloatMenu.setButtonsMenu(this, R.menu.menu_home_bottom);
        avatar = mFloatMenu.getChild(0);
        avatar.setRoundCornerRadius(20);
        mPersenter.loadUserInfo();
        mFloatMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.userAvatarMenu: {
                        showOpenMsgListennerDialog();
                        break;
                    }
                    case R.id.robotMenu: {
                        toggleRobot();
                        break;
                    }
                }
            }
        });
        mChatView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 1) {
                    mFloatMenu.hide();
                } else if (newState == 0) {
                    mFloatMenu.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void toggleRobot() {
        if (isServiceStarted) {
            if (!isRobotStart) {
                isRobotStart = true;
                showToast("聊天机器人已启动");
            } else {
                isRobotStart = false;
                showToast("聊天机器人已关闭");
            }
        } else {
            showToast("请先开启消息监听");
        }
    }

    private void initReceiver() {
        mNewMessageReceiver = NewMessageReceiver.getInstance(this);
        mNewMessageReceiver.addNewMessageListener(this);
    }

    @Override
    protected void onDestroy() {
        if (mNewMessageReceiver != null) {
            mNewMessageReceiver.removeNewMessageListener(this);
            unregisterReceiver(mNewMessageReceiver);
        }
        stopService(new Intent(this, NewWechatMessageListenerLoopService.class));
        super.onDestroy();
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        final MaterialDialog dialog = new MaterialDialog(mContext);
        dialog.setTitle("提醒")
                .setMessage("是否退出应用，退出后将无法使用应用的所有功能，可以后台运行该应用")
                .setCanceledOnTouchOutside(true)
                .setPositiveButton("退出", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HomeActivity.super.onBackPressed();
                        dialog.dismiss();
                    }
                }).setNegativeButton("后台运行", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                dialog.dismiss();
            }
        }).show();

    }

    private void showOpenMsgListennerDialog() {
        if (!contactorsLoadFinished) {
            showToast("正在获取用户信息，请稍后再试");
            return;
        } else {
            final MaterialDialog dialog = new MaterialDialog(mContext);
            dialog.setTitle(isServiceStarted ? "关闭消息监听" : "开启消息监听").setMessage(isServiceStarted ? "是否关闭消息监听？关闭后应用无法进行工作" : "是否开启消息监听？开启后应用会获取您的微信消息。")
                    .setPositiveButton(isServiceStarted ? "关闭" : "开启", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isServiceStarted) {
                                NewWechatMessageListenerLoopService.stop(mContext);
                            } else {
                                NewWechatMessageListenerLoopService.start(mContext, mWechatAuthenticationBean);
                            }
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }


    @Override
    protected HomeContract.Presenter setPresenter() {
        HomeContract.Presenter presenter = new HomePresenter(this);
        return presenter;
    }

    @Override
    public void setNickname(String nickname) {
        setActionBarTitle(nickname);
        showToast(nickname + "，欢迎回来！", mCoordinatorLayout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showToast(String msg) {
        showToast(msg, mCoordinatorLayout);
    }

    @Override
    public void setAvatarImage(final String url) {
        ImageLoader.load(url).into(avatar);
    }

    @Override
    public void contactorsLoadFinished(WechatAuthenticationBean bean) {
        contactorsLoadFinished = true;
        mWechatAuthenticationBean = bean;
        initReceiver();
    }

    @Override
    public void showWechatMessageCache(List<WechatMessage> messages) {
        mChatView.setHistoryMessage(messages);
    }

    @Override
    public void showWechatMessage(List<WechatMessage> messages) {
        mChatView.addMessage(messages);
    }

    @Override
    public void onNewMessage(WechatMessageBean vo) {
        mPersenter.saveMessages(vo);
        if (isRobotStart) {
            mPersenter.getTuringResp(vo);
        }
    }

    @Override
    public void onAuthFalid() {
        enterLoginActivity();
    }

    @Override
    public void onServiceStarted() {
        isServiceStarted = true;
        setActionBarSubTitle("正在监听消息");
    }

    @Override
    public void onServiceStoped() {
        isServiceStarted = false;
        setActionBarSubTitle("已停止消息监听");
    }
}
