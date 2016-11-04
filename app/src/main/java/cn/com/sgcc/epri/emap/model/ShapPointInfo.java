package cn.com.sgcc.epri.emap.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by GuHeng on 2016/11/3.
 * 地物绘制-画点信息
 */
public class ShapPointInfo {
    private Map<String/*一级数据*/, Map<String/*二级数据*/, String/*三级数据*/>> mData;

    public void add(String oneKey, String twoKey, String threeKey) {
        Map<String, String> twoDataMap = new HashMap<>();
        twoDataMap.put(twoKey, threeKey);

        if(mData == null) {
            mData = new HashMap<>();
            mData.put(oneKey, twoDataMap);
        } else {
            if(isOneDataExist(oneKey)) {
                if(isTwoDataExist(oneKey, twoKey)) {
                    // 记录已存在
                } else {
                    Map<String, String> tempMap = getTwoData(oneKey);
                    if(tempMap != null) {
                        tempMap.put(twoKey, threeKey);
                    }
                }
            } else {
                mData.put(oneKey, twoDataMap);
            }
        }
    }

    // 判断一级数据是否存在
    public boolean isOneDataExist(String oneKey) {
        boolean exist = false;

        if(mData != null) {
            exist = mData.containsKey(oneKey);
        }

        return exist;
    }

    // 判断二级数据是否存在
    public boolean isTwoDataExist(String oneKey, String twoKey) {
        boolean exist = false;

        if(mData != null) {
            Map<String, String> twoMap = mData.get(oneKey);
            if(twoMap != null) {
                exist = twoMap.containsKey(twoKey);
            }
        }

        return exist;
    }

    // 获取一级数据
    public Map<String, Map<String, String>> getOneData() {
        return mData;
    }

    // 获取二级数据
    public Map<String, String> getTwoData(String oneKey) {
        return mData.get(oneKey);
    }

    // 获取三级数据
    public String getThreeData(String oneKey, String twoKey) {
        String str = null;
        if(mData != null) {
            Map<String, String> tempData = mData.get(oneKey);
            if(tempData != null) {
                str = tempData.get(twoKey);
            }
        }

        return str;
    }

    // 获取一级Key集合
    public Set<String> getOneKeys() {
        return mData.keySet();
    }

    // 获取二级Key集合
    public Set<String> getTwoKeys(String oneKey) {
        Map<String, String> tempMap = getTwoData(oneKey);
        if(tempMap != null) {
            return tempMap.keySet();
        } else {
            return null;
        }
    }

    public String toString() {
        String str = "";

        if(mData != null) {
            Set<String> oneKeys = getOneKeys();
            Set<String> twoKeys = null;

            if(oneKeys != null) {
                for (String oneKey : oneKeys) {
                    twoKeys = getTwoKeys(oneKey);
                    if(twoKeys != null) {
                        for(String twoKey : twoKeys) {
                            String threeKey = getThreeData(oneKey, twoKey);

                            if(threeKey != null) {
                                str += String.format("%s,%s,%s\n", oneKey, twoKey, threeKey);
                            } else {
                                str += String.format("%s,%s\n", oneKey, twoKey);
                            }
                        }
                    } else {
                        str += String.format("%s\n", oneKey);
                    }
                }
            }
        }

        return str;
    }
}
