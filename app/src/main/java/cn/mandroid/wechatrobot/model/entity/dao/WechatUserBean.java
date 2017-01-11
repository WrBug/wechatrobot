package cn.mandroid.wechatrobot.model.entity.dao;

import android.text.TextUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

import cn.mandroid.wechatrobot.model.entity.BaseBean;
import cn.mandroid.wechatrobot.utils.MD5;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by wrBug on 2017/1/2.
 */
@Entity
public class WechatUserBean extends BaseBean {
    @Id
    @Unique
    private String UserName;
    private long WebWxPluginSwitch;
    private long HeadImgFlag;
    private long Uin;
    private String NickName;
    private String HeadImgUrl;
    private long ContactFlag;
    private long MemberCount;
    private String RemarkName;
    private long HideInputBarFlag;
    private long Sex;
    private String Signature;
    private long VerifyFlag;
    private long OwnerUin;
    private String PYInitial;
    @OrderBy
    private String PYQuanPin;
    private String RemarkPYInitial;
    private String RemarkPYQuanPin;
    private long StarFriend;
    private long AppAccountFlag;
    private long Statues;
    private long AttrStatus;
    private String Province;
    private String City;
    private String Alias;
    private long SnsFlag;
    private long UniFriend;
    private String DisplayName;
    private long ChatRoomId;
    private String KeyWord;
    private String EncryChatRoomId;
    @Unique
    private String userId;
    @Transient
    private List<WechatUserBean> MemberList;

    @Generated(hash = 712188201)
    public WechatUserBean(String UserName, long WebWxPluginSwitch, long HeadImgFlag, long Uin, String NickName,
                          String HeadImgUrl, long ContactFlag, long MemberCount, String RemarkName, long HideInputBarFlag,
                          long Sex, String Signature, long VerifyFlag, long OwnerUin, String PYInitial, String PYQuanPin,
                          String RemarkPYInitial, String RemarkPYQuanPin, long StarFriend, long AppAccountFlag, long Statues,
                          long AttrStatus, String Province, String City, String Alias, long SnsFlag, long UniFriend,
                          String DisplayName, long ChatRoomId, String KeyWord, String EncryChatRoomId, String userId) {
        this.UserName = UserName;
        this.WebWxPluginSwitch = WebWxPluginSwitch;
        this.HeadImgFlag = HeadImgFlag;
        this.Uin = Uin;
        this.NickName = NickName;
        this.HeadImgUrl = HeadImgUrl;
        this.ContactFlag = ContactFlag;
        this.MemberCount = MemberCount;
        this.RemarkName = RemarkName;
        this.HideInputBarFlag = HideInputBarFlag;
        this.Sex = Sex;
        this.Signature = Signature;
        this.VerifyFlag = VerifyFlag;
        this.OwnerUin = OwnerUin;
        this.PYInitial = PYInitial;
        this.PYQuanPin = PYQuanPin;
        this.RemarkPYInitial = RemarkPYInitial;
        this.RemarkPYQuanPin = RemarkPYQuanPin;
        this.StarFriend = StarFriend;
        this.AppAccountFlag = AppAccountFlag;
        this.Statues = Statues;
        this.AttrStatus = AttrStatus;
        this.Province = Province;
        this.City = City;
        this.Alias = Alias;
        this.SnsFlag = SnsFlag;
        this.UniFriend = UniFriend;
        this.DisplayName = DisplayName;
        this.ChatRoomId = ChatRoomId;
        this.KeyWord = KeyWord;
        this.EncryChatRoomId = EncryChatRoomId;
        this.userId = userId;
    }

    @Generated(hash = 1706827256)
    public WechatUserBean() {
    }

    public long getUin() {
        return Uin;
    }

    public long getMemberCount() {
        return MemberCount;
    }

    public void setMemberCount(int memberCount) {
        MemberCount = memberCount;
    }

    public long getOwnerUin() {
        return OwnerUin;
    }

    public void setOwnerUin(int ownerUin) {
        OwnerUin = ownerUin;
    }

    public long getStatues() {
        return Statues;
    }

    public void setStatues(int statues) {
        Statues = statues;
    }

    public long getAttrStatus() {
        return AttrStatus;
    }

    public void setAttrStatus(long attrStatus) {
        AttrStatus = attrStatus;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public long getUniFriend() {
        return UniFriend;
    }

    public void setUniFriend(int uniFriend) {
        UniFriend = uniFriend;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public long getChatRoomId() {
        return ChatRoomId;
    }

    public void setChatRoomId(int chatRoomId) {
        ChatRoomId = chatRoomId;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getEncryChatRoomId() {
        return EncryChatRoomId;
    }

    public void setEncryChatRoomId(String encryChatRoomId) {
        EncryChatRoomId = encryChatRoomId;
    }

    public List<WechatUserBean> getMemberList() {
        return MemberList;
    }

    public void setMemberList(List<WechatUserBean> memberList) {
        MemberList = memberList;
    }

    public void setUin(int Uin) {
        this.Uin = Uin;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getNickName() {
        return TextUtils.isEmpty(DisplayName) ? NickName : DisplayName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getHeadImgUrl() {
        return HeadImgUrl.startsWith("https://wx.qq.com") ? HeadImgUrl : "https://wx.qq.com" + HeadImgUrl;
    }

    public void setHeadImgUrl(String HeadImgUrl) {
        this.HeadImgUrl = HeadImgUrl;
    }

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String RemarkName) {
        this.RemarkName = RemarkName;
    }

    public String getPYInitial() {
        return PYInitial;
    }

    public void setPYInitial(String PYInitial) {
        this.PYInitial = PYInitial;
    }

    public String getPYQuanPin() {
        return PYQuanPin;
    }

    public void setPYQuanPin(String PYQuanPin) {
        this.PYQuanPin = PYQuanPin;
    }

    public String getRemarkPYInitial() {
        return RemarkPYInitial;
    }

    public void setRemarkPYInitial(String RemarkPYInitial) {
        this.RemarkPYInitial = RemarkPYInitial;
    }

    public String getRemarkPYQuanPin() {
        return RemarkPYQuanPin;
    }

    public void setRemarkPYQuanPin(String RemarkPYQuanPin) {
        this.RemarkPYQuanPin = RemarkPYQuanPin;
    }

    public long getHideInputBarFlag() {
        return HideInputBarFlag;
    }

    public void setHideInputBarFlag(int HideInputBarFlag) {
        this.HideInputBarFlag = HideInputBarFlag;
    }

    public long getStarFriend() {
        return StarFriend;
    }

    public void setStarFriend(int StarFriend) {
        this.StarFriend = StarFriend;
    }

    public long getSex() {
        return Sex;
    }

    public void setSex(int Sex) {
        this.Sex = Sex;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String Signature) {
        this.Signature = Signature;
    }

    public long getAppAccountFlag() {
        return AppAccountFlag;
    }

    public void setAppAccountFlag(int AppAccountFlag) {
        this.AppAccountFlag = AppAccountFlag;
    }

    public long getVerifyFlag() {
        return VerifyFlag;
    }

    public void setVerifyFlag(int VerifyFlag) {
        this.VerifyFlag = VerifyFlag;
    }

    public long getContactFlag() {
        return ContactFlag;
    }

    public void setContactFlag(int ContactFlag) {
        this.ContactFlag = ContactFlag;
    }

    public long getWebWxPluginSwitch() {
        return WebWxPluginSwitch;
    }

    public void setWebWxPluginSwitch(int WebWxPluginSwitch) {
        this.WebWxPluginSwitch = WebWxPluginSwitch;
    }

    public long getHeadImgFlag() {
        return HeadImgFlag;
    }

    public void setHeadImgFlag(int HeadImgFlag) {
        this.HeadImgFlag = HeadImgFlag;
    }

    public long getSnsFlag() {
        return SnsFlag;
    }

    public void setSnsFlag(int SnsFlag) {
        this.SnsFlag = SnsFlag;
    }

    public void setWebWxPluginSwitch(long WebWxPluginSwitch) {
        this.WebWxPluginSwitch = WebWxPluginSwitch;
    }

    public void setHeadImgFlag(long HeadImgFlag) {
        this.HeadImgFlag = HeadImgFlag;
    }

    public void setUin(long Uin) {
        this.Uin = Uin;
    }

    public void setContactFlag(long ContactFlag) {
        this.ContactFlag = ContactFlag;
    }

    public void setMemberCount(long MemberCount) {
        this.MemberCount = MemberCount;
    }

    public void setHideInputBarFlag(long HideInputBarFlag) {
        this.HideInputBarFlag = HideInputBarFlag;
    }

    public void setSex(long Sex) {
        this.Sex = Sex;
    }

    public void setVerifyFlag(long VerifyFlag) {
        this.VerifyFlag = VerifyFlag;
    }

    public void setOwnerUin(long OwnerUin) {
        this.OwnerUin = OwnerUin;
    }

    public void setStarFriend(long StarFriend) {
        this.StarFriend = StarFriend;
    }

    public void setAppAccountFlag(long AppAccountFlag) {
        this.AppAccountFlag = AppAccountFlag;
    }

    public void setStatues(long Statues) {
        this.Statues = Statues;
    }

    public void setSnsFlag(long SnsFlag) {
        this.SnsFlag = SnsFlag;
    }

    public void setUniFriend(long UniFriend) {
        this.UniFriend = UniFriend;
    }

    public void setChatRoomId(long ChatRoomId) {
        this.ChatRoomId = ChatRoomId;
    }

    public String getUserId() {
        if (TextUtils.isEmpty(userId)) {
            StringBuilder builder = new StringBuilder();
            builder.append(NickName).append(HeadImgFlag).append(ContactFlag).append(VerifyFlag).append(PYInitial).append(PYQuanPin).append(Alias);
            userId = MD5.encode(builder.toString());
        }
        return this.userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }
}