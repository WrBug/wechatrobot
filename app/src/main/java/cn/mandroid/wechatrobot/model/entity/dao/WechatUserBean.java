package cn.mandroid.wechatrobot.model.entity.dao;

import android.text.TextUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
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
    private String hashCode;
    @Transient
    private List<WechatUserBean> MemberList;

    @Generated(hash = 1224866078)
    public WechatUserBean(String UserName, long WebWxPluginSwitch, long HeadImgFlag, long Uin, String NickName, String HeadImgUrl, long ContactFlag,
            long MemberCount, String RemarkName, long HideInputBarFlag, long Sex, String Signature, long VerifyFlag, long OwnerUin, String PYInitial,
            String PYQuanPin, String RemarkPYInitial, String RemarkPYQuanPin, long StarFriend, long AppAccountFlag, long Statues, long AttrStatus,
            String Province, String City, String Alias, long SnsFlag, long UniFriend, String DisplayName, long ChatRoomId, String KeyWord,
            String EncryChatRoomId, String hashCode) {
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
        this.hashCode = hashCode;
    }

    @Generated(hash = 1706827256)
    public WechatUserBean() {
    }

    public String getHashCode() {
        if (TextUtils.isEmpty(hashCode)) {
            StringBuilder builder = new StringBuilder();
            builder.append(NickName).append(HeadImgFlag).append(ContactFlag).append(VerifyFlag).append(PYInitial).append(PYQuanPin).append(Alias);
            hashCode = MD5.encode(builder.toString());
        }
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {

        this.hashCode = hashCode;
    }

    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public long getWebWxPluginSwitch() {
        return this.WebWxPluginSwitch;
    }

    public void setWebWxPluginSwitch(long WebWxPluginSwitch) {
        this.WebWxPluginSwitch = WebWxPluginSwitch;
    }

    public long getHeadImgFlag() {
        return this.HeadImgFlag;
    }

    public void setHeadImgFlag(long HeadImgFlag) {
        this.HeadImgFlag = HeadImgFlag;
    }

    public long getUin() {
        return this.Uin;
    }

    public void setUin(long Uin) {
        this.Uin = Uin;
    }

    public String getNickName() {
        return this.NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getHeadImgUrl() {
        return this.HeadImgUrl;
    }

    public void setHeadImgUrl(String HeadImgUrl) {
        this.HeadImgUrl = HeadImgUrl;
    }

    public long getContactFlag() {
        return this.ContactFlag;
    }

    public void setContactFlag(long ContactFlag) {
        this.ContactFlag = ContactFlag;
    }

    public long getMemberCount() {
        return this.MemberCount;
    }

    public void setMemberCount(long MemberCount) {
        this.MemberCount = MemberCount;
    }

    public String getRemarkName() {
        return this.RemarkName;
    }

    public void setRemarkName(String RemarkName) {
        this.RemarkName = RemarkName;
    }

    public long getHideInputBarFlag() {
        return this.HideInputBarFlag;
    }

    public void setHideInputBarFlag(long HideInputBarFlag) {
        this.HideInputBarFlag = HideInputBarFlag;
    }

    public long getSex() {
        return this.Sex;
    }

    public void setSex(long Sex) {
        this.Sex = Sex;
    }

    public String getSignature() {
        return this.Signature;
    }

    public void setSignature(String Signature) {
        this.Signature = Signature;
    }

    public long getVerifyFlag() {
        return this.VerifyFlag;
    }

    public void setVerifyFlag(long VerifyFlag) {
        this.VerifyFlag = VerifyFlag;
    }

    public long getOwnerUin() {
        return this.OwnerUin;
    }

    public void setOwnerUin(long OwnerUin) {
        this.OwnerUin = OwnerUin;
    }

    public String getPYInitial() {
        return this.PYInitial;
    }

    public void setPYInitial(String PYInitial) {
        this.PYInitial = PYInitial;
    }

    public String getPYQuanPin() {
        return this.PYQuanPin;
    }

    public void setPYQuanPin(String PYQuanPin) {
        this.PYQuanPin = PYQuanPin;
    }

    public String getRemarkPYInitial() {
        return this.RemarkPYInitial;
    }

    public void setRemarkPYInitial(String RemarkPYInitial) {
        this.RemarkPYInitial = RemarkPYInitial;
    }

    public String getRemarkPYQuanPin() {
        return this.RemarkPYQuanPin;
    }

    public void setRemarkPYQuanPin(String RemarkPYQuanPin) {
        this.RemarkPYQuanPin = RemarkPYQuanPin;
    }

    public long getStarFriend() {
        return this.StarFriend;
    }

    public void setStarFriend(long StarFriend) {
        this.StarFriend = StarFriend;
    }

    public long getAppAccountFlag() {
        return this.AppAccountFlag;
    }

    public void setAppAccountFlag(long AppAccountFlag) {
        this.AppAccountFlag = AppAccountFlag;
    }

    public long getStatues() {
        return this.Statues;
    }

    public void setStatues(long Statues) {
        this.Statues = Statues;
    }

    public long getAttrStatus() {
        return this.AttrStatus;
    }

    public void setAttrStatus(long AttrStatus) {
        this.AttrStatus = AttrStatus;
    }

    public String getProvince() {
        return this.Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getCity() {
        return this.City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getAlias() {
        return this.Alias;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

    public long getSnsFlag() {
        return this.SnsFlag;
    }

    public void setSnsFlag(long SnsFlag) {
        this.SnsFlag = SnsFlag;
    }

    public long getUniFriend() {
        return this.UniFriend;
    }

    public void setUniFriend(long UniFriend) {
        this.UniFriend = UniFriend;
    }

    public String getDisplayName() {
        return this.DisplayName;
    }

    public void setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
    }

    public long getChatRoomId() {
        return this.ChatRoomId;
    }

    public void setChatRoomId(long ChatRoomId) {
        this.ChatRoomId = ChatRoomId;
    }

    public String getKeyWord() {
        return this.KeyWord;
    }

    public void setKeyWord(String KeyWord) {
        this.KeyWord = KeyWord;
    }

    public String getEncryChatRoomId() {
        return this.EncryChatRoomId;
    }

    public void setEncryChatRoomId(String EncryChatRoomId) {
        this.EncryChatRoomId = EncryChatRoomId;
    }
}