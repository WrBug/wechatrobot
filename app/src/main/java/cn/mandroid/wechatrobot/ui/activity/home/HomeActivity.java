package cn.mandroid.wechatrobot.ui.activity.home;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.WechatMessageBean;
import cn.mandroid.wechatrobot.ui.activity.common.BaseActivity;
import cn.mandroid.wechatrobot.ui.broadcastreceiver.NewMessageReceiver;
import cn.mandroid.wechatrobot.ui.service.NewWechatMessageListenerLoopService;
import cn.mandroid.wechatrobot.ui.widget.chatview.ChatView;
import cn.mandroid.wechatrobot.ui.widget.faboptions.FabOptions;
import cn.mandroid.wechatrobot.utils.ImageLoader;
import cn.mandroid.wechatrobot.utils.MLog;
import me.drakeet.materialdialog.MaterialDialog;

public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View, NewMessageReceiver.OnNewMessageListener {
    @BindView(R.id.activity_home)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.chatView)
    ChatView mChatView;
    @BindView(R.id.floatMenu)
    FabOptions mFloatMenu;
    AppCompatImageView avatar;
    private boolean contactorsLoadFinished;
    private WechatAuthenticationBean mWechatAuthenticationBean;
    private NewMessageReceiver mNewMessageReceiver;

    @Override
    protected int setContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void afterView() {
        mFloatMenu.setButtonsMenu(this, R.menu.menu_home_bottom);
        avatar = mFloatMenu.getChild(0);
        mPersenter.loadUserInfo();
        mFloatMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.userAvatarMenu: {
                        showOpenMsgListennerDialog();
                        break;
                    }
                }
            }
        });

    }

    @Override
    protected void beforeInject() {
        initReceiver();
    }

    private void initReceiver() {
        mNewMessageReceiver = NewMessageReceiver.getInstance(this);
        mNewMessageReceiver.addNewMessageListener(this);
    }

    @Override
    protected void onDestroy() {
        mNewMessageReceiver.removeNewMessageListener(this);
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
            dialog.setTitle("开启消息监听").setMessage("是否开启消息监听？开启后应用会获取您的微信消息。")
                    .setPositiveButton("开启", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NewWechatMessageListenerLoopService.start(mContext, mWechatAuthenticationBean);
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
        mToolBar.setTitle(nickname);
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
            case R.id.questionMenu: {
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showToast(String msg) {
        showToast(msg, mCoordinatorLayout);
    }

    @Override
    public void setAvatarImage(final String url) {
        ImageLoader.load(url).cropCircle(true).into(avatar);
    }

    @Override
    public void contactorsLoadFinished(WechatAuthenticationBean bean) {
        contactorsLoadFinished = true;
        mWechatAuthenticationBean = bean;
        mChatView.setHistoryMessage(bean.getUin(), null);

    }

    @Override
    public void onNewMessage(WechatMessageBean vo) {
        List<WechatMessage> wechatMessages = vo.getAddMsgList();
        for (int i = 0; i < vo.getAddMsgList().size(); i++) {
            wechatMessages.get(i).setUin(mWechatAuthenticationBean.getUin());
        }
        mChatView.addMessage(vo.getAddMsgList());
    }

    @Override
    public void onAuthFalid() {
        MLog.i(" 重新登录");
    }
}
