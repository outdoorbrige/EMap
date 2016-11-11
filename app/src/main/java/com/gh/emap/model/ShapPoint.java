package com.gh.emap.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by GuHeng on 2016/11/9.
 */
public class ShapPoint {
    private HashMap<String, List<String>> mData;

    public HashMap<String, List<String>> getData() {
        return mData;
    }

    public void put(String var1, String var2) {
        List<String> list = new ArrayList<>();
        list.add(var2);

        if(mData == null) {
            mData = new HashMap<>();

            mData.put(var1, list);
        } else {
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
    }

    public String toString() {
        String str = "";

        if(mData != null) {
            Set<String> keys = mData.keySet();
            if(keys != null) {
                for (String key : keys) {
                    str += String.format("%s,%s\n", key, mData.get(key));
                }
            }
        }

        return str;
    }
}
