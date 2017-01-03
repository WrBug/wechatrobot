/*
 * Copyright (C) 2009-2016 Hangzhou 2Dfire Technology Co., Ltd. All rights reserved
 */
package cn.mandroid.wechatrobot.model.entity;

import android.graphics.Bitmap;

/**
 * IImageLoader
 *
 * @author wrbug
 * @since 2016-10-28
 */
public interface IImageLoader {
    void setImage(Bitmap bitmap);

    void setTag(int key, Object object);

    Object getTag(int key);

    int getWidth();

    int getHeight();
}
