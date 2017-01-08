package cn.mandroid.wechatrobot.ui.widget.chatview;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.ui.widget.RoundCornerImageView;
import cn.mandroid.wechatrobot.utils.DensityUtil;
import cn.mandroid.wechatrobot.utils.ImageLoader;

/**
 * Created by wrBug on 2017/1/8.
 */

public class ChatAvatarView extends LinearLayout {
    private Context mContext;
    private RoundCornerImageView mAvatarIv;
    private TextView mNicknameTv;

    public ChatAvatarView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        initView();
    }

    private void initView() {
        mAvatarIv = new RoundCornerImageView(mContext);
        int w = DensityUtil.dip2px(mContext, 40);
        LayoutParams params = new LayoutParams(w, w);
        mAvatarIv.setLayoutParams(params);
        mAvatarIv.setRoundCornerRadius(5);
        mAvatarIv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mAvatarIv.setImageResource(R.mipmap.ic_launcher);
        addView(mAvatarIv);
        mNicknameTv = new TextView(mContext);
        params = new LayoutParams(w, ViewGroup.LayoutParams.WRAP_CONTENT);
        mNicknameTv.setLayoutParams(params);
        mNicknameTv.setText("未知");
        mNicknameTv.setGravity(Gravity.CENTER);
        mNicknameTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        mNicknameTv.setEllipsize(TextUtils.TruncateAt.END);
        mNicknameTv.setPadding(0, 5, 0, 0);
        mNicknameTv.setTextColor(getResources().getColor(R.color.textCommon));
        addView(mNicknameTv);
    }

    public void setData(WechatUserBean userBean) {
        ImageLoader.load(userBean.getHeadImgUrl()).into(mAvatarIv);
        mNicknameTv.setText(userBean.getNickName());
    }
}
