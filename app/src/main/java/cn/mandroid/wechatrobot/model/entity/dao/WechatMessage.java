package cn.mandroid.wechatrobot.model.entity.dao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

import cn.mandroid.wechatrobot.model.entity.BaseBean;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.AppInfoBean;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.RecommendInfoBean;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by WrBug on 2016/12/21.
 */
@Entity
public class WechatMessage extends BaseBean {
    @Id
    private String MsgId;
    private long uin;
    private boolean isFromMine;
    private String FromUserName;
    private String ToUserName;
    private long MsgType;
    private String Content;
    private long Status;
    private long ImgStatus;
    private long CreateTime;
    private long VoiceLength;
    private long PlayLength;
    private String FileName;
    private String FileSize;
    private String MediaId;
    private String Url;
    private long AppMsgType;
    private long StatusNotifyCode;
    private String StatusNotifyUserName;
    @Transient
    private RecommendInfoBean RecommendInfo;
    private long ForwardFlag;
    @Transient
    private AppInfoBean AppInfo;
    private long HasProductId;
    private String Ticket;
    private long ImgHeight;
    private long ImgWidth;
    private long SubMsgType;
    private long NewMsgId;

    @Generated(hash = 843128925)
    public WechatMessage(String MsgId, long uin, boolean isFromMine,
            String FromUserName, String ToUserName, long MsgType, String Content,
            long Status, long ImgStatus, long CreateTime, long VoiceLength,
            long PlayLength, String FileName, String FileSize, String MediaId,
            String Url, long AppMsgType, long StatusNotifyCode,
            String StatusNotifyUserName, long ForwardFlag, long HasProductId,
            String Ticket, long ImgHeight, long ImgWidth, long SubMsgType,
            long NewMsgId) {
        this.MsgId = MsgId;
        this.uin = uin;
        this.isFromMine = isFromMine;
        this.FromUserName = FromUserName;
        this.ToUserName = ToUserName;
        this.MsgType = MsgType;
        this.Content = Content;
        this.Status = Status;
        this.ImgStatus = ImgStatus;
        this.CreateTime = CreateTime;
        this.VoiceLength = VoiceLength;
        this.PlayLength = PlayLength;
        this.FileName = FileName;
        this.FileSize = FileSize;
        this.MediaId = MediaId;
        this.Url = Url;
        this.AppMsgType = AppMsgType;
        this.StatusNotifyCode = StatusNotifyCode;
        this.StatusNotifyUserName = StatusNotifyUserName;
        this.ForwardFlag = ForwardFlag;
        this.HasProductId = HasProductId;
        this.Ticket = Ticket;
        this.ImgHeight = ImgHeight;
        this.ImgWidth = ImgWidth;
        this.SubMsgType = SubMsgType;
        this.NewMsgId = NewMsgId;
    }

    @Generated(hash = 83776159)
    public WechatMessage() {
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String MsgId) {
        this.MsgId = MsgId;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String FromUserName) {
        this.FromUserName = FromUserName;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String ToUserName) {
        this.ToUserName = ToUserName;
    }

    public long getMsgType() {
        return MsgType;
    }

    public void setMsgType(long MsgType) {
        this.MsgType = MsgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public long getStatus() {
        return Status;
    }

    public void setStatus(long Status) {
        this.Status = Status;
    }

    public long getImgStatus() {
        return ImgStatus;
    }

    public void setImgStatus(int ImgStatus) {
        this.ImgStatus = ImgStatus;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(int CreateTime) {
        this.CreateTime = CreateTime;
    }

    public long getVoiceLength() {
        return VoiceLength;
    }

    public void setVoiceLength(int VoiceLength) {
        this.VoiceLength = VoiceLength;
    }

    public long getPlayLength() {
        return PlayLength;
    }

    public void setPlayLength(int PlayLength) {
        this.PlayLength = PlayLength;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String FileSize) {
        this.FileSize = FileSize;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String MediaId) {
        this.MediaId = MediaId;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public long getAppMsgType() {
        return AppMsgType;
    }

    public void setAppMsgType(int AppMsgType) {
        this.AppMsgType = AppMsgType;
    }

    public long getStatusNotifyCode() {
        return StatusNotifyCode;
    }

    public void setStatusNotifyCode(int StatusNotifyCode) {
        this.StatusNotifyCode = StatusNotifyCode;
    }

    public String getStatusNotifyUserName() {
        return StatusNotifyUserName;
    }

    public void setStatusNotifyUserName(String StatusNotifyUserName) {
        this.StatusNotifyUserName = StatusNotifyUserName;
    }

    public RecommendInfoBean getRecommendInfo() {
        return RecommendInfo;
    }

    public void setRecommendInfo(RecommendInfoBean RecommendInfo) {
        this.RecommendInfo = RecommendInfo;
    }

    public long getForwardFlag() {
        return ForwardFlag;
    }

    public void setForwardFlag(int ForwardFlag) {
        this.ForwardFlag = ForwardFlag;
    }

    public AppInfoBean getAppInfo() {
        return AppInfo;
    }

    public void setAppInfo(AppInfoBean AppInfo) {
        this.AppInfo = AppInfo;
    }

    public long getHasProductId() {
        return HasProductId;
    }

    public void setHasProductId(int HasProductId) {
        this.HasProductId = HasProductId;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String Ticket) {
        this.Ticket = Ticket;
    }

    public long getImgHeight() {
        return ImgHeight;
    }

    public void setImgHeight(int ImgHeight) {
        this.ImgHeight = ImgHeight;
    }

    public long getImgWidth() {
        return ImgWidth;
    }

    public void setImgWidth(int ImgWidth) {
        this.ImgWidth = ImgWidth;
    }

    public long getSubMsgType() {
        return SubMsgType;
    }

    public void setSubMsgType(int SubMsgType) {
        this.SubMsgType = SubMsgType;
    }

    public long getNewMsgId() {
        return NewMsgId;
    }

    public void setNewMsgId(long NewMsgId) {
        this.NewMsgId = NewMsgId;
    }

    public long getUin() {
        return this.uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public boolean getIsFromMine() {
        return this.isFromMine;
    }

    public void setIsFromMine(boolean isFromMine) {
        this.isFromMine = isFromMine;
    }

    public void setImgStatus(long ImgStatus) {
        this.ImgStatus = ImgStatus;
    }

    public void setCreateTime(long CreateTime) {
        this.CreateTime = CreateTime;
    }

    public void setVoiceLength(long VoiceLength) {
        this.VoiceLength = VoiceLength;
    }

    public void setPlayLength(long PlayLength) {
        this.PlayLength = PlayLength;
    }

    public void setAppMsgType(long AppMsgType) {
        this.AppMsgType = AppMsgType;
    }

    public void setStatusNotifyCode(long StatusNotifyCode) {
        this.StatusNotifyCode = StatusNotifyCode;
    }

    public void setForwardFlag(long ForwardFlag) {
        this.ForwardFlag = ForwardFlag;
    }

    public void setHasProductId(long HasProductId) {
        this.HasProductId = HasProductId;
    }

    public void setImgHeight(long ImgHeight) {
        this.ImgHeight = ImgHeight;
    }

    public void setImgWidth(long ImgWidth) {
        this.ImgWidth = ImgWidth;
    }

    public void setSubMsgType(long SubMsgType) {
        this.SubMsgType = SubMsgType;
    }

}
