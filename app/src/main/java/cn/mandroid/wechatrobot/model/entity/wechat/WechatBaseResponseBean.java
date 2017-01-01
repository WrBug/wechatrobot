package cn.mandroid.wechatrobot.model.entity.wechat;

import cn.mandroid.wechatrobot.model.entity.BaseBean;

/**
 * Created by wrBug on 2016/12/21.
 */
public class WechatBaseResponseBean extends BaseBean {
    /**
     * Ret : 0
     * ErrMsg :
     */

    private int Ret;
    private String ErrMsg;

    public boolean isSuccess() {
        return Ret == 0;
    }

    public int getRet() {
        return Ret;
    }

    public void setRet(int Ret) {
        this.Ret = Ret;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }
}
