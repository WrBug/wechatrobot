package cn.mandroid.wechatrobot.model.entity.wechat;

import java.util.List;

import cn.mandroid.wechatrobot.model.entity.BaseBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;

/**
 * Created by wrBug on 2016/12/21.
 */
public class WechatUiDataBean extends BaseBean {

    private WechatBaseResponseBean BaseResponse;
    private int Count;
    private WechatSyncKeyBean SyncKey;
    private WechatUserBean User;
    private String ChatSet;
    private String SKey;
    private int ClientVersion;
    private int SystemTime;
    private int GrayScale;
    private int InviteStartCount;
    private int MPSubscribeMsgCount;
    private int ClickReportInterval;
    private List<WechatContactVo> ContactList;
    private List<WechatMPSubscribeMsgVo> MPSubscribeMsgList;

    public WechatBaseResponseBean getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(WechatBaseResponseBean BaseResponse) {
        this.BaseResponse = BaseResponse;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public WechatSyncKeyBean getSyncKey() {
        return SyncKey;
    }

    public void setSyncKey(WechatSyncKeyBean SyncKey) {
        this.SyncKey = SyncKey;
    }

    public WechatUserBean getUser() {
        return User;
    }

    public void setUser(WechatUserBean User) {
        this.User = User;
    }

    public String getChatSet() {
        return ChatSet;
    }

    public void setChatSet(String ChatSet) {
        this.ChatSet = ChatSet;
    }

    public String getSKey() {
        return SKey;
    }

    public void setSKey(String SKey) {
        this.SKey = SKey;
    }

    public int getClientVersion() {
        return ClientVersion;
    }

    public void setClientVersion(int ClientVersion) {
        this.ClientVersion = ClientVersion;
    }

    public int getSystemTime() {
        return SystemTime;
    }

    public void setSystemTime(int SystemTime) {
        this.SystemTime = SystemTime;
    }

    public int getGrayScale() {
        return GrayScale;
    }

    public void setGrayScale(int GrayScale) {
        this.GrayScale = GrayScale;
    }

    public int getInviteStartCount() {
        return InviteStartCount;
    }

    public void setInviteStartCount(int InviteStartCount) {
        this.InviteStartCount = InviteStartCount;
    }

    public int getMPSubscribeMsgCount() {
        return MPSubscribeMsgCount;
    }

    public void setMPSubscribeMsgCount(int MPSubscribeMsgCount) {
        this.MPSubscribeMsgCount = MPSubscribeMsgCount;
    }

    public int getClickReportInterval() {
        return ClickReportInterval;
    }

    public void setClickReportInterval(int ClickReportInterval) {
        this.ClickReportInterval = ClickReportInterval;
    }

    public List<WechatContactVo> getContactList() {
        return ContactList;
    }

    public void setContactList(List<WechatContactVo> ContactList) {
        this.ContactList = ContactList;
    }

    public List<WechatMPSubscribeMsgVo> getMPSubscribeMsgList() {
        return MPSubscribeMsgList;
    }

    public void setMPSubscribeMsgList(List<WechatMPSubscribeMsgVo> MPSubscribeMsgList) {
        this.MPSubscribeMsgList = MPSubscribeMsgList;
    }

}
