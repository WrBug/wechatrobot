package cn.mandroid.wechatrobot.model.entity.wechat;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.mandroid.wechatrobot.model.entity.BaseBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;

/**
 * Created by wrBug on 2016/12/21.
 */
public class WechatContactVo extends BaseBean {

    private int Uin;
    private String UserName;
    private String NickName;
    private String HeadImgUrl;
    private int ContactFlag;
    @SerializedName(value = "MemberCount", alternate = {"Count"})
    private int MemberCount;
    private String RemarkName;
    private int HideInputBarFlag;
    private int Sex;
    private String Signature;
    private int VerifyFlag;
    private int OwnerUin;
    private String PYInitial;
    private String PYQuanPin;
    private String RemarkPYInitial;
    private String RemarkPYQuanPin;
    private int StarFriend;
    private int AppAccountFlag;
    private int Statues;
    private int AttrStatus;
    private String Province;
    private String City;
    private String Alias;
    private int SnsFlag;
    private int UniFriend;
    private String DisplayName;
    private int ChatRoomId;
    private String KeyWord;
    private String EncryChatRoomId;
    private int Seq;
    private WechatBaseResponseBean BaseResponse;
    @SerializedName(value = "ContactList", alternate = {"MemberList"})
    private List<WechatUserBean> MemberList;

    public int getUin() {
        return Uin;
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
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getHeadImgUrl() {
        return HeadImgUrl;
    }

    public void setHeadImgUrl(String HeadImgUrl) {
        this.HeadImgUrl = HeadImgUrl;
    }

    public int getContactFlag() {
        return ContactFlag;
    }

    public void setContactFlag(int ContactFlag) {
        this.ContactFlag = ContactFlag;
    }

    public int getMemberCount() {
        return MemberCount;
    }

    public void setMemberCount(int MemberCount) {
        this.MemberCount = MemberCount;
    }

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String RemarkName) {
        this.RemarkName = RemarkName;
    }

    public int getHideInputBarFlag() {
        return HideInputBarFlag;
    }

    public void setHideInputBarFlag(int HideInputBarFlag) {
        this.HideInputBarFlag = HideInputBarFlag;
    }

    public int getSex() {
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

    public int getVerifyFlag() {
        return VerifyFlag;
    }

    public void setVerifyFlag(int VerifyFlag) {
        this.VerifyFlag = VerifyFlag;
    }

    public int getOwnerUin() {
        return OwnerUin;
    }

    public void setOwnerUin(int OwnerUin) {
        this.OwnerUin = OwnerUin;
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

    public int getStarFriend() {
        return StarFriend;
    }

    public void setStarFriend(int StarFriend) {
        this.StarFriend = StarFriend;
    }

    public int getAppAccountFlag() {
        return AppAccountFlag;
    }

    public void setAppAccountFlag(int AppAccountFlag) {
        this.AppAccountFlag = AppAccountFlag;
    }

    public int getStatues() {
        return Statues;
    }

    public void setStatues(int Statues) {
        this.Statues = Statues;
    }

    public int getAttrStatus() {
        return AttrStatus;
    }

    public void setAttrStatus(int AttrStatus) {
        this.AttrStatus = AttrStatus;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

    public int getSnsFlag() {
        return SnsFlag;
    }

    public void setSnsFlag(int SnsFlag) {
        this.SnsFlag = SnsFlag;
    }

    public int getUniFriend() {
        return UniFriend;
    }

    public void setUniFriend(int UniFriend) {
        this.UniFriend = UniFriend;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
    }

    public int getChatRoomId() {
        return ChatRoomId;
    }

    public void setChatRoomId(int ChatRoomId) {
        this.ChatRoomId = ChatRoomId;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String KeyWord) {
        this.KeyWord = KeyWord;
    }

    public String getEncryChatRoomId() {
        return EncryChatRoomId;
    }

    public void setEncryChatRoomId(String EncryChatRoomId) {
        this.EncryChatRoomId = EncryChatRoomId;
    }

    public int getSeq() {
        return Seq;
    }

    public void setSeq(int seq) {
        Seq = seq;
    }

    public WechatBaseResponseBean getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(WechatBaseResponseBean baseResponse) {
        BaseResponse = baseResponse;
    }

    public List<WechatUserBean> getMemberList() {
        return MemberList;
    }

    public void setMemberList(List<WechatUserBean> MemberList) {
        this.MemberList = MemberList;
    }
}
