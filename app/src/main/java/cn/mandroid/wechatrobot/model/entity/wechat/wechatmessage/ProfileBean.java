package cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage;

import java.io.Serializable;

/**
 * Created by WrBug on 2016/12/21.
 */
public class ProfileBean implements Serializable{

    /**
     * BitFlag : 0
     * UserName : {"Buff":""}
     * NickName : {"Buff":""}
     * BindUin : 0
     * BindEmail : {"Buff":""}
     * BindMobile : {"Buff":""}
     * Status : 0
     * Sex : 0
     * PersonalCard : 0
     * Alias :
     * HeadImgUpdateFlag : 0
     * HeadImgUrl :
     * Signature :
     */

    private int BitFlag;
    private UserNameBean UserName;
    private NickNameBean NickName;
    private int BindUin;
    private BindEmailBean BindEmail;
    private BindMobileBean BindMobile;
    private int Status;
    private int Sex;
    private int PersonalCard;
    private String Alias;
    private int HeadImgUpdateFlag;
    private String HeadImgUrl;
    private String Signature;

    public int getBitFlag() {
        return BitFlag;
    }

    public void setBitFlag(int BitFlag) {
        this.BitFlag = BitFlag;
    }

    public UserNameBean getUserName() {
        return UserName;
    }

    public void setUserName(UserNameBean UserName) {
        this.UserName = UserName;
    }

    public NickNameBean getNickName() {
        return NickName;
    }

    public void setNickName(NickNameBean NickName) {
        this.NickName = NickName;
    }

    public int getBindUin() {
        return BindUin;
    }

    public void setBindUin(int BindUin) {
        this.BindUin = BindUin;
    }

    public BindEmailBean getBindEmail() {
        return BindEmail;
    }

    public void setBindEmail(BindEmailBean BindEmail) {
        this.BindEmail = BindEmail;
    }

    public BindMobileBean getBindMobile() {
        return BindMobile;
    }

    public void setBindMobile(BindMobileBean BindMobile) {
        this.BindMobile = BindMobile;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int Sex) {
        this.Sex = Sex;
    }

    public int getPersonalCard() {
        return PersonalCard;
    }

    public void setPersonalCard(int PersonalCard) {
        this.PersonalCard = PersonalCard;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

    public int getHeadImgUpdateFlag() {
        return HeadImgUpdateFlag;
    }

    public void setHeadImgUpdateFlag(int HeadImgUpdateFlag) {
        this.HeadImgUpdateFlag = HeadImgUpdateFlag;
    }

    public String getHeadImgUrl() {
        return HeadImgUrl;
    }

    public void setHeadImgUrl(String HeadImgUrl) {
        this.HeadImgUrl = HeadImgUrl;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String Signature) {
        this.Signature = Signature;
    }

    public static class UserNameBean implements Serializable{
        /**
         * Buff :
         */

        private String Buff;

        public String getBuff() {
            return Buff;
        }

        public void setBuff(String Buff) {
            this.Buff = Buff;
        }
    }

    public static class NickNameBean implements Serializable{
        /**
         * Buff :
         */

        private String Buff;

        public String getBuff() {
            return Buff;
        }

        public void setBuff(String Buff) {
            this.Buff = Buff;
        }
    }

    public static class BindEmailBean implements Serializable{
        /**
         * Buff :
         */

        private String Buff;

        public String getBuff() {
            return Buff;
        }

        public void setBuff(String Buff) {
            this.Buff = Buff;
        }
    }

    public static class BindMobileBean implements Serializable{
        /**
         * Buff :
         */

        private String Buff;

        public String getBuff() {
            return Buff;
        }

        public void setBuff(String Buff) {
            this.Buff = Buff;
        }
    }

}
