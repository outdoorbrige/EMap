package com.gh.emap.modelB;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/3/27.
 * 离线地图一个城市信息类
 */

public class OneCityInfo implements Serializable {
    private String mCityName; // 当前城市名称
    private long mImageSize; // 当前城市影像图大小
    private long mVectorSize; // 当前城市矢量图大小

    public OneCityInfo() {

    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        this.mCityName = cityName;
    }

    public long getImageSize() {
        return mImageSize;
    }

    public void setImageSize(long imageSize) {
        this.mImageSize = imageSize;
    }

    public long getVectorSize() {
        return mVectorSize;
    }

    public void setVectorSize(long vectorSize) {
        this.mVectorSize = vectorSize;
    }
}
