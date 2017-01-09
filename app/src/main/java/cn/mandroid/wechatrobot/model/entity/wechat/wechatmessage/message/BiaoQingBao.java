package cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.message;

import android.text.Html;
import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.mandroid.wechatrobot.model.entity.BaseBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;

/**
 * Created by wrBug on 2017/1/9.
 * 表情包类
 */

public class BiaoQingBao extends BaseBean {

    /**
     * designerid :
     * s60v3md5 : 309a94b6ef9a1261f8afdacbe6535fa9
     * fromusername : wangtao7537
     * productid :
     * cdnurl : http://emoji.qpic.cn/wx_emoji/LjGdNJUIicL8WumHHB9eUDnvQmkTTINvDXjxSpfrxubZ0lvCaSczTMA/
     * s60v5md5 : 309a94b6ef9a1261f8afdacbe6535fa9
     * type : 1
     * tousername : wxid_dt3cbh4xoenb22
     * s60v5len : 51262
     * len : 51262
     * androidmd5 : 309a94b6ef9a1261f8afdacbe6535fa9
     * width : 200
     * aeskey : f12ce6d0f1d95470e7c0cba7c032027e
     * s60v3len : 51262
     * thumburl :
     * idbuffer : media:0_0
     * encrypturl : http://emoji.qpic.cn/wx_emoji/LjGdNJUIicL8WumHHB9eUDnvQmkTTINvD1UnDNPKAWqHRE9BgW8UIgA/
     * androidlen : 51262
     * md5 : 309a94b6ef9a1261f8afdacbe6535fa9
     * height : 173
     */

    private String designerid;
    private String s60v3md5;
    private String fromusername;
    private String productid;
    private String cdnurl;
    private String s60v5md5;
    private int type;
    private String tousername;
    private int s60v5len;
    private int len;
    private String androidmd5;
    private int width;
    private String aeskey;
    private int s60v3len;
    private String thumburl;
    private String idbuffer;
    private String encrypturl;
    private int androidlen;
    private String md5;
    private int height;
    //游戏用
    private int content;

    public String getDesignerid() {
        return designerid;
    }

    public String getS60v3md5() {
        return s60v3md5;
    }

    public String getFromusername() {
        return fromusername;
    }

    public String getProductid() {
        return productid;
    }

    public String getCdnurl() {
        return cdnurl;
    }

    public String getS60v5md5() {
        return s60v5md5;
    }

    public int getType() {
        return type;
    }

    public String getTousername() {
        return tousername;
    }

    public int getS60v5len() {
        return s60v5len;
    }

    public int getLen() {
        return len;
    }

    public String getAndroidmd5() {
        return androidmd5;
    }

    public int getWidth() {
        return width;
    }

    public String getAeskey() {
        return aeskey;
    }

    public int getS60v3len() {
        return s60v3len;
    }

    public String getThumburl() {
        return thumburl;
    }

    public String getIdbuffer() {
        return idbuffer;
    }

    public String getEncrypturl() {
        return encrypturl;
    }

    public int getAndroidlen() {
        return androidlen;
    }

    public String getMd5() {
        return md5;
    }

    public int getHeight() {
        return height;
    }

    public int getContent() {
        return content;
    }

    public static BiaoQingBao getBiaoQingBao(WechatMessage wechatMessage) {
        BiaoQingBao biaoQingBao = null;
        if (wechatMessage.getMsgType() == 47 && !TextUtils.isEmpty(wechatMessage.getContent())) {
            Document document = Jsoup.parse(Html.fromHtml(wechatMessage.getContent()).toString());
            Elements elements = document.body().child(0).children();
            Map<String, String> map = new HashMap<>();
            Iterator<Attribute> iterator = null;
            for (int i = 0; i < elements.size(); i++) {
                iterator = elements.get(i).attributes().iterator();
                while (iterator.hasNext()) {
                    Attribute attribute = iterator.next();
                    map.put(attribute.getKey(), attribute.getValue());
                }
            }
            String json = new GsonBuilder().create().toJson(map);
            if (!TextUtils.isEmpty(json)) {
                biaoQingBao = new GsonBuilder().create().fromJson(json, BiaoQingBao.class);
            }
        }
        return biaoQingBao;
    }
}
