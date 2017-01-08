package cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage;

import java.util.ArrayList;
import java.util.List;

import cn.mandroid.wechatrobot.model.entity.BaseBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatBaseResponseBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatSyncKeyBean;

/**
 * Created by WrBug on 2016/12/21.
 */
public class WechatMessageBean extends BaseBean {

    private WechatBaseResponseBean BaseResponse;
    private int AddMsgCount;
    private int ModContactCount;
    private int DelContactCount;
    private int ModChatRoomMemberCount;
    private ProfileBean Profile;
    private int ContinueFlag;
    private WechatSyncKeyBean SyncKey;
    private String SKey;
    private WechatSyncKeyBean SyncCheckKey;
    private ArrayList<WechatMessage> AddMsgList;
    private ArrayList<WechatUserBean> ModContactList;
    private ArrayList<WechatUserBean> DelContactList;
    private ArrayList<WechatUserBean> ModChatRoomMemberList;

    public WechatBaseResponseBean getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(WechatBaseResponseBean BaseResponse) {
        this.BaseResponse = BaseResponse;
    }

    public int getAddMsgCount() {
        return AddMsgCount;
    }

    public void setAddMsgCount(int AddMsgCount) {
        this.AddMsgCount = AddMsgCount;
    }

    public int getModContactCount() {
        return ModContactCount;
    }

    public void setModContactCount(int ModContactCount) {
        this.ModContactCount = ModContactCount;
    }

    public int getDelContactCount() {
        return DelContactCount;
    }

    public void setDelContactCount(int DelContactCount) {
        this.DelContactCount = DelContactCount;
    }

    public int getModChatRoomMemberCount() {
        return ModChatRoomMemberCount;
    }

    public void setModChatRoomMemberCount(int ModChatRoomMemberCount) {
        this.ModChatRoomMemberCount = ModChatRoomMemberCount;
    }

    public ProfileBean getProfile() {
        return Profile;
    }

    public void setProfile(ProfileBean Profile) {
        this.Profile = Profile;
    }

    public int getContinueFlag() {
        return ContinueFlag;
    }

    public void setContinueFlag(int ContinueFlag) {
        this.ContinueFlag = ContinueFlag;
    }

    public WechatSyncKeyBean getSyncKey() {
        return SyncKey;
    }

    public void setSyncKey(WechatSyncKeyBean SyncKey) {
        this.SyncKey = SyncKey;
    }

    public String getSKey() {
        return SKey;
    }

    public void setSKey(String SKey) {
        this.SKey = SKey;
    }

    public WechatSyncKeyBean getSyncCheckKey() {
        return SyncCheckKey;
    }

    public void setSyncCheckKey(WechatSyncKeyBean SyncCheckKey) {
        this.SyncCheckKey = SyncCheckKey;
    }

    public List<WechatMessage> getAddMsgList() {
        return AddMsgList;
    }

    public void setAddMsgList(ArrayList<WechatMessage> AddMsgList) {
        this.AddMsgList = AddMsgList;
    }

    public List<WechatUserBean> getModContactList() {
        return ModContactList;
    }

    public void setModContactList(ArrayList<WechatUserBean> ModContactList) {
        this.ModContactList = ModContactList;
    }

    public List<WechatUserBean> getDelContactList() {
        return DelContactList;
    }

    public void setDelContactList(ArrayList<WechatUserBean> DelContactList) {
        this.DelContactList = DelContactList;
    }

    public List<WechatUserBean> getModChatRoomMemberList() {
        return ModChatRoomMemberList;
    }

    public void setModChatRoomMemberList(ArrayList<WechatUserBean> ModChatRoomMemberList) {
        this.ModChatRoomMemberList = ModChatRoomMemberList;
    }
}
