package com.gh.emap.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by GuHeng on 2016/11/9.
 * 地物编辑-画点 配置文件类
 */
public class ShapPoint {
    private HashMap<String, List<String>> mData;

    public ShapPoint() {
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
        return "ShapPoint{" +
                "mData=" + mData +
                '}';
    }
}
