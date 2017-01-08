package cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage;

import java.io.Serializable;

/**
 * Created by wrBug on 2017/1/9.
 */

public class RecommendInfoBean implements Serializable {

    /**
     * UserName :
     * NickName :
     * QQNum : 0
     * Province :
     * City :
     * Content :
     * Signature :
     * Alias :
     * Scene : 0
     * VerifyFlag : 0
     * AttrStatus : 0
     * Sex : 0
     * Ticket :
     * OpCode : 0
     */

    private String UserName;
    private String NickName;
    private int QQNum;
    private String Province;
    private String City;
    private String Content;
    private String Signature;
    private String Alias;
    private int Scene;
    private int VerifyFlag;
    private int AttrStatus;
    private int Sex;
    private String Ticket;
    private int OpCode;

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

    public int getQQNum() {
        return QQNum;
    }

    public void setQQNum(int QQNum) {
        this.QQNum = QQNum;
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

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String Signature) {
        this.Signature = Signature;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

    public int getScene() {
        return Scene;
    }

    public void setScene(int Scene) {
        this.Scene = Scene;
    }

    public int getVerifyFlag() {
        return VerifyFlag;
    }

    public void setVerifyFlag(int VerifyFlag) {
        this.VerifyFlag = VerifyFlag;
    }

    public int getAttrStatus() {
        return AttrStatus;
    }

    public void setAttrStatus(int AttrStatus) {
        this.AttrStatus = AttrStatus;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int Sex) {
        this.Sex = Sex;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String Ticket) {
        this.Ticket = Ticket;
    }

    public int getOpCode() {
        return OpCode;
    }

    public void setOpCode(int OpCode) {
        this.OpCode = OpCode;
    }

}
