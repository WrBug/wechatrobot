package cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage;

import cn.mandroid.wechatrobot.model.entity.BaseBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatBaseResponseBean;

/**
 * Created by wrBug on 2016/12/21.
 */
public class SendMsgVo extends BaseBean {

    /**
     * BaseResponse : {"Ret":0,"ErrMsg":""}
     * MsgID : 9064459924848038599
     * LocalID : 14823278201490793
     */
    private WechatBaseResponseBean BaseResponse;
    private String MsgID;
    private String LocalID;

    public WechatBaseResponseBean getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(WechatBaseResponseBean BaseResponse) {
        this.BaseResponse = BaseResponse;
    }

    public String getMsgID() {
        return MsgID;
    }

    public void setMsgID(String MsgID) {
        this.MsgID = MsgID;
    }

    public String getLocalID() {
        return LocalID;
    }

    public void setLocalID(String LocalID) {
        this.LocalID = LocalID;
    }

}
