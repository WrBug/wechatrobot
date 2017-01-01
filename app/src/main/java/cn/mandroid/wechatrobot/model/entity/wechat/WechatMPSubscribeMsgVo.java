package cn.mandroid.wechatrobot.model.entity.wechat;

import java.util.List;

import cn.mandroid.wechatrobot.model.entity.BaseBean;

/**
 * Created by wrBug on 2016/12/21.
 */
public class WechatMPSubscribeMsgVo extends BaseBean {

    private String UserName;
    private int MPArticleCount;
    private int Time;
    private String NickName;
    private List<MPArticleListBean> MPArticleList;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getMPArticleCount() {
        return MPArticleCount;
    }

    public void setMPArticleCount(int MPArticleCount) {
        this.MPArticleCount = MPArticleCount;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int Time) {
        this.Time = Time;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public List<MPArticleListBean> getMPArticleList() {
        return MPArticleList;
    }

    public void setMPArticleList(List<MPArticleListBean> MPArticleList) {
        this.MPArticleList = MPArticleList;
    }

    public static class MPArticleListBean {

        private String Title;
        private String Digest;
        private String Cover;
        private String Url;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getDigest() {
            return Digest;
        }

        public void setDigest(String Digest) {
            this.Digest = Digest;
        }

        public String getCover() {
            return Cover;
        }

        public void setCover(String Cover) {
            this.Cover = Cover;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }
}
