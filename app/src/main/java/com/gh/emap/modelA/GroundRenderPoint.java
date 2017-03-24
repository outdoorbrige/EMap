package com.gh.emap.modelA;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2016/11/9.
 * 地物编辑-画点 配置文件类
 */
public class GroundRenderPoint {
    private HashMap<String, ArrayList<String>> mData;

    // String：value字符串；ArrayList<Integer>：[0]存放Map中key的索引，[1]存放value在List中的索引
    private HashMap<String, ArrayList<Integer>> mIndexes;

    public GroundRenderPoint() {
        mData = new HashMap<>();
        mIndexes = new HashMap<>();
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

    public void compareIndex() {
        if(!mIndexes.isEmpty()) {
            return;
        }

        ArrayList<String> keys = new ArrayList(mData.keySet());
        for(int i = 0; i < keys.size(); i ++) {
            ArrayList<String> values = mData.get(keys.get(i));
            for(int j = 0; j < values.size(); j ++) {
                String value = values.get(j);
                ArrayList<Integer> indexes = new ArrayList<>();
                indexes.add(new Integer(i));
                indexes.add(new Integer(j));

                mIndexes.put(value, indexes);
            }
        }
    }

    public int getKeyIndex(String value) {
        if(value == null || value.isEmpty()) {
            return 0;
        }

        if(mIndexes.containsKey(value)) {
            return mIndexes.get(value).get(0).intValue();
        }

        return 0;
    }

    public int getValueIndex(String value) {
        if(value == null || value.isEmpty()) {
            return 0;
        }

        if(mIndexes.containsKey(value)) {
            return mIndexes.get(value).get(1).intValue();
        }

        return 0;
    }

    @Override
    public String toString() {
        return "GroundRenderPoint{" +
                "mData=" + mData +
                '}';
    }
}
