package com.gh.emap.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by GuHeng on 2017/1/9.
 * 地物编辑-画线 配置文件类
 */

public class ShapLine {
    private HashMap<String, List<String>> mData;

    public ShapLine() {
        mData = new HashMap<>();
    }

    public HashMap<String, List<String>> getData() {
        return mData;
    }

    public void put(String var1, String var2) {
        List<String> list = new ArrayList<>();
        list.add(var2);

        if(mData.containsKey(var1)) {
            List<String> tmpList = mData.get(var1);
            if(tmpList == null) {
                mData.put(var1, list);
            } else {
                tmpList.add(var2);
            }
        } else {
            mData.put(var1, list);
        }
    }

    @Override
    public String toString() {
        return "ShapLine{" +
                "mData=" + mData +
                '}';
    }
}
