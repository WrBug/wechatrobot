package cn.mandroid.wechatrobot.model.repository.wechatinfo;

import java.util.List;

import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;

/**
 * Created by wrBug on 2017/1/8.
 */

public interface IWechatInfoLocalSource {
    void saveWechatContactors(List<WechatUserBean> userBeens);

    WechatUserBean getLocalWechatContactor(String username);
}
