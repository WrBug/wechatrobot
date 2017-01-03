package cn.mandroid.wechatrobot.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.WechatRobotApp;
import cn.mandroid.wechatrobot.model.common.HttpBody;
import cn.mandroid.wechatrobot.model.entity.IImageLoader;

/**
 * ImageLoader
 *
 * @author wrbug
 * @since 2016/12/13
 */
public class ImageLoader {
    /**
     * bitmap缓存
     */
    private static BitmapCache cache = new BitmapCache();

    private static Context mContext;
    /**
     * 我也不知道为啥。用多线程就是一个坑
     */
    private static ExecutorService sNetworkExecutorService;
    private static ExecutorService sDiskExecutorService;

    /**
     * 避免重复下载
     */
    private static List<String> downloadingUrlCache;
    private static String cacheDir;

    static {
        sNetworkExecutorService = Executors.newSingleThreadExecutor();
        sDiskExecutorService = Executors.newFixedThreadPool(5);
        downloadingUrlCache = new ArrayList<>();
        mContext = WechatRobotApp.getApplication();
        cacheDir = mContext.getCacheDir().getPath() + "/imageloader";
        File file = new File(cacheDir);
        if (file == null || !file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
    }

    public static ImageHelper load(String url) {
        return load(url, true);
    }

    public static ImageHelper load(String url, boolean cacheable) {
        return load(url, 0, cacheable);
    }

    public static ImageHelper load(String url, @DrawableRes int defaultImg) {
        return load(url, defaultImg, true);
    }

    private static ImageHelper load(final String url, @DrawableRes int defaultImg, boolean cacheable) {
        final BitmapHelper imageHelper = new ImageHelperImpl();
        imageHelper.setDefaultImage(defaultImg);
        if (!TextUtils.isEmpty(url)) {
            imageHelper.setBitmapUrl(url);
            if (isBitmapExist(url)) {
                imageHelper.loadFinished();
            } else {
                if (cacheable) {
                    sDiskExecutorService.submit(fromDiskCache(url, imageHelper));
                } else {
                    sNetworkExecutorService.submit(fromNetwork(url, imageHelper, cacheable));
                }
            }
        } else {
            cache.put(url, new SoftReference<>(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher)));
            imageHelper.setIsEmptyBitmap(true);
            imageHelper.loadFinished();
        }
        return imageHelper;
    }

    private static Runnable fromNetwork(final String url, final BitmapHelper imageHelper, final boolean cacheable) {
        return new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                ByteArrayOutputStream baos = null;
                InputStream cacheStream = null;
                InputStream btStream = null;
                try {
                    if (cacheable && downloadingUrlCache.contains(url)) {
                        fromDiskCache(url, imageHelper);
                        return;
                    }
                    downloadingUrlCache.add(url);
                    HttpBody.Query query = new HttpBody.Query();
                    is = query.downloadAsInputStream(url);
                    if (is != null) {
                        baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = is.read(buffer)) > -1) {
                            baos.write(buffer, 0, len);
                        }
                        baos.flush();
                        cacheStream = new ByteArrayInputStream(baos.toByteArray());
                        btStream = new ByteArrayInputStream(baos.toByteArray());
                        Bitmap bitmap = BitmapFactory.decodeStream(btStream);
                        if (bitmap != null) {
                            imageHelper.setBitmap(bitmap);
                            if (cacheable) {
                                inputstreamtofile(cacheStream, url);
                            } else {
                                cacheStream.close();
                            }
                            btStream.close();
                        } else {
                            imageHelper.setIsEmptyBitmap(true);
                        }
                        imageHelper.loadFinished();
                        is.close();
                    }
                } catch (Exception e) {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (baos != null) {
                            baos.close();
                        }
                        if (cacheStream != null) {
                            cacheStream.close();
                        }
                        if (btStream != null) {
                            btStream.close();
                        }
                    } catch (IOException e1) {
                        MLog.e(e1.getMessage());
                    }
                    imageHelper.setIsEmptyBitmap(true);
                    imageHelper.loadFinished();
                }
            }
        };
    }

    private static File getCacheFile(String url) {
        String urlstr = MD5.encode(url);
        return new File(cacheDir, urlstr);
    }

    private static Runnable fromDiskCache(final String url, final BitmapHelper imageHelper) {
        return new Runnable() {
            @Override
            public void run() {
                File file = getCacheFile(url);
                if (file.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                    if (bitmap != null) {
                        imageHelper.setBitmap(bitmap);
                        imageHelper.loadFinished();
                    } else {
                        sNetworkExecutorService.submit(fromNetwork(url, imageHelper, true));
                    }
                } else {
                    sNetworkExecutorService.submit(fromNetwork(url, imageHelper, true));
                }
            }
        };
    }

    public static void inputstreamtofile(InputStream ins, String url) {
        File file = getCacheFile(url);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return;
            }
        }
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isBitmapExist(String url) {
        return cache.containsKey(url);
    }

    private static class ImageHelperImpl implements BitmapHelper {
        private ImageView mImageView;
        private String url;
        private boolean isEmptyBitmap;
        private boolean finished;
        private int sDefaultImg;
        private IImageLoader mImageLoader;
        private int scaleWidth = -1;
        private int scaleHeight = -1;
        private Bitmap mBitmap;

        @Override
        public void into(ImageView imageView) {
            this.mImageView = imageView;
            imageView.setTag(R.id.image_loader_tag, url);
            imageView.setImageResource(sDefaultImg);
            setBitmap();
        }

        @Override
        public void into(IImageLoader imageLoader) {
            mImageLoader = imageLoader;
            imageLoader.setTag(R.id.image_loader_tag, url);
            setBitmap();
        }

        @Override
        public void into(ImageView imageView, int width, int height) {
            scaleWidth = width;
            scaleHeight = height;
            into(imageView);
        }

        @Override
        public void into(IImageLoader imageLoader, int width, int height) {
            scaleWidth = width;
            scaleHeight = height;
            into(imageLoader);
        }

        @Override
        public void setBitmapUrl(String url) {
            this.url = url;
        }

        @Override
        public void setIsEmptyBitmap(boolean isEmptyBitmap) {
            this.isEmptyBitmap = isEmptyBitmap;
            setBitmapUrl(null);
        }

        @Override
        public void loadFinished() {
            finished = true;
            setBitmap();
        }

        @Override
        public void setDefaultImage(int resId) {
            sDefaultImg = resId;
        }

        @Override
        public void setBitmap(Bitmap bitmap) {
            mBitmap = bitmap;
        }

        private void setBitmap() {
            if (check()) {
                Message message = new Message();
                message.obj = this;
                sHandler.sendMessage(message);
            }
        }

        private boolean check() {
            if (!TextUtils.isEmpty(url) && finished && mBitmap != null && !isEmptyBitmap && (mImageView != null || mImageLoader != null)) {
                float bmWidth = mBitmap.getWidth();
                float bmHeight = mBitmap.getHeight();
                int ivWidth = scaleWidth > 0 ? scaleWidth : mImageView == null ? mImageLoader.getWidth() : mImageView.getWidth();
                int ivHeight = scaleHeight > 0 ? scaleHeight : mImageView == null ? mImageLoader.getHeight() : mImageView.getHeight();
                if (mImageView != null && (ivWidth <= 0 || ivHeight <= 0)) {
                    int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    mImageView.measure(w, h);
                    ivWidth = mImageView.getMeasuredHeight();
                    ivHeight = mImageView.getMeasuredWidth();
                }
                if (ivWidth > 0 && ivHeight > 0 && bmWidth > ivWidth && bmHeight > ivHeight) {
                    float scale = Math.max(ivWidth / bmWidth, ivHeight / bmHeight);
                    Matrix matrix = new Matrix();
                    matrix.setScale(scale, scale);
                    Bitmap bm = Bitmap.createBitmap(mBitmap, 0, 0, (int) bmWidth, (int) bmHeight, matrix, true);
                    mBitmap.recycle();
                    mBitmap = bm;
                }
                if (!cache.containsKey(url)) {
                    cache.put(url, new SoftReference<>(mBitmap));
                }
            } else {
                mBitmap = cache.containsKey(url) ? cache.get(url).get() : null;
            }
            return (mImageView != null || mImageLoader != null) && finished;
        }
    }

    public interface ImageHelper {

        void into(ImageView imageView);

        void into(IImageLoader imageLoader);

        void into(ImageView imageView, int width, int height);

        void into(IImageLoader imageLoader, int width, int height);

    }

    private static Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ImageHelperImpl imageHelper = (ImageHelperImpl) msg.obj;
            String tag = String.valueOf(imageHelper.mImageView == null ? imageHelper.mImageLoader.getTag(R.id.image_loader_tag) : imageHelper.mImageView.getTag(R.id.image_loader_tag));
            String urlStr = String.valueOf(imageHelper.url);
            if (TextUtils.equals(tag, urlStr)) {
                if (imageHelper.isEmptyBitmap) {
                    if (TextUtils.isEmpty(imageHelper.url) && imageHelper.sDefaultImg != -1) {
                        if (imageHelper.mImageView != null) {
                            imageHelper.mImageView.setImageResource(imageHelper.sDefaultImg);
                        }
                    }
                } else {
                    if (imageHelper.mImageView != null) {
                        imageHelper.mImageView.setImageBitmap(imageHelper.mBitmap);
                    } else {
                        imageHelper.mImageLoader.setImage(imageHelper.mBitmap);
                    }
                }
            }
            super.handleMessage(msg);
        }
    };

    private interface BitmapHelper extends ImageHelper {
        void setBitmapUrl(String url);

        void setIsEmptyBitmap(boolean isEmptyBitmap);

        void loadFinished();

        void setDefaultImage(int resId);

        void setBitmap(Bitmap bitmap);
    }

    private static class BitmapCache extends LinkedHashMap<String, SoftReference<Bitmap>> {
        @Override
        public SoftReference<Bitmap> put(String key, SoftReference<Bitmap> value) {
            key = String.valueOf(key);
            return super.put(key, value);
        }

        @Override
        public boolean containsKey(Object key) {
            if (super.containsKey(key)) {
                SoftReference<Bitmap> bitmapSoftReference = super.get(key);
                if (bitmapSoftReference.get() == null || bitmapSoftReference.get().isRecycled()) {
                    bitmapSoftReference.clear();
                    remove(key);
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }
    }
}