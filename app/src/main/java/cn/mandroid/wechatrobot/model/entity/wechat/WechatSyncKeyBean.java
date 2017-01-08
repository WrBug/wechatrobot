package cn.mandroid.wechatrobot.model.entity.wechat;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.mandroid.wechatrobot.model.entity.BaseBean;

/**
 * Created by wrBug on 2016/12/21.
 */
public class WechatSyncKeyBean extends BaseBean {

    /**
     * Count : 4
     * List : [{"Key":1,"Val":662998350},{"Key":2,"Val":662998581},{"Key":3,"Val":662998511},{"Key":1000,"Val":1482228721}]
     */

    private int Count;
    private ArrayList<ListBean> List;

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public List<ListBean> getList() {
        return List;
    }

    public void setList(ArrayList<ListBean> List) {
        this.List = List;
    }

    public static class ListBean implements Serializable {
        /**
         * Key : 1
         * Val : 662998350
         */

        private int Key;
        private int Val;

        public int getKey() {
            return Key;
        }

        public void setKey(int Key) {
            this.Key = Key;
        }

        public int getVal() {
            return Val;
        }

        public void setVal(int Val) {
            this.Val = Val;
        }
    }
}
