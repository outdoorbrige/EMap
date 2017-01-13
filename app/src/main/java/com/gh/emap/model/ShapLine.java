package com.gh.emap.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2017/1/9.
 * 地物编辑-画线 配置文件类
 */

public class ShapLine {
    private HashMap<String, ArrayList<String>> mData;

    public ShapLine() {
        mData = new HashMap<>();
    }

    public HashMap<String, ArrayList<String>> getData() {
        return mData;
    }

    public void put(String var1, String var2) {
        ArrayList<String> list = new ArrayList<>();
        list.add(var2);

        if(mData.containsKey(var1)) {
            ArrayList<String> tmpList = mData.get(var1);
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
