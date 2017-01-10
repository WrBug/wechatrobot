package cn.mandroid.wechatrobot.model.entity.turing;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.mandroid.wechatrobot.model.entity.BaseBean;

/**
 * Created by wrBug on 2017/1/9.
 */

public class TuringRespVo extends BaseBean{

    /**
     * text : 杭州是个风景如画的城市呐
     * code : 100000
     * emojid : 0
     * app_id : 2300101
     * user_reqid : 0000000000148397293398107251434-6
     * faqAnswerId : 0
     * tableName :
     * task_pos : 1
     * text_after :
     * showtext : 杭州是个风景如画的城市呐
     * show_text_after :
     * text_array : [{"text":"杭州是个风景如画的城市呐","text_speed":"5","text_pitch":"5","text_sound":"0"}]
     */

    private String text;
    private int code;
    private int emojid;
    @SerializedName("app_id")
    private int appId;
    @SerializedName("user_reqid")
    private String userReqid;
    private int faqAnswerId;
    private String tableName;
    @SerializedName("task_pos")
    private int taskPos;
    @SerializedName("text_after")
    private String textAfter;
    private String showtext;
    @SerializedName("show_text_after")
    private String showTextAfter;
    private List<TextArrayBean> text_array;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getEmojid() {
        return emojid;
    }

    public void setEmojid(int emojid) {
        this.emojid = emojid;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getUserReqid() {
        return userReqid;
    }

    public void setUserReqid(String userReqid) {
        this.userReqid = userReqid;
    }

    public int getFaqAnswerId() {
        return faqAnswerId;
    }

    public void setFaqAnswerId(int faqAnswerId) {
        this.faqAnswerId = faqAnswerId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTaskPos() {
        return taskPos;
    }

    public void setTaskPos(int taskPos) {
        this.taskPos = taskPos;
    }

    public String getTextAfter() {
        return textAfter;
    }

    public void setTextAfter(String textAfter) {
        this.textAfter = textAfter;
    }

    public String getShowtext() {
        return showtext;
    }

    public void setShowtext(String showtext) {
        this.showtext = showtext;
    }

    public String getShowTextAfter() {
        return showTextAfter;
    }

    public void setShowTextAfter(String showTextAfter) {
        this.showTextAfter = showTextAfter;
    }

    public List<TextArrayBean> getText_array() {
        return text_array;
    }

    public void setText_array(List<TextArrayBean> text_array) {
        this.text_array = text_array;
    }

    public static class TextArrayBean {
        /**
         * text : 杭州是个风景如画的城市呐
         * text_speed : 5
         * text_pitch : 5
         * text_sound : 0
         */

        private String text;
        private String text_speed;
        private String text_pitch;
        private String text_sound;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getText_speed() {
            return text_speed;
        }

        public void setText_speed(String text_speed) {
            this.text_speed = text_speed;
        }

        public String getText_pitch() {
            return text_pitch;
        }

        public void setText_pitch(String text_pitch) {
            this.text_pitch = text_pitch;
        }

        public String getText_sound() {
            return text_sound;
        }

        public void setText_sound(String text_sound) {
            this.text_sound = text_sound;
        }
    }
}
