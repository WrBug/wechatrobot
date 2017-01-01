package cn.mandroid.wechatrobot.ui.activity.wechatlogin;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wrBug on 2017/1/1.
 */

public class WechatLoginViewPagerAdapter extends PagerAdapter {
    private WechatLoginContract.View mView;
    private List<View> mViews;
    private Context mContext;

    public WechatLoginViewPagerAdapter(WechatLoginContract.View view, List<View> views) {
        mView = view;
        mViews = views;
        mContext = mView.getActivity();
    }

    @Override
    public int getCount() {
        return mViews == null ? 0 : mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }
}
