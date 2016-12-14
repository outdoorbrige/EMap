package com.gh.emap.model;

import java.io.Serializable;

/**
 * Created by GuHeng on 2016/12/14.
 * 点
 */

public class MyUserPoint implements Serializable {
    private String mName; // 名称
    private String mType; // 类型
    private int mLatitude; // 纬度
    private int mLongitude; // 经度

    public MyUserPoint() {

    }

    public MyUserPoint(String name, String type, int latitude, int longitude) {
        this.mName = name;
        this.mType = type;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public int getLatitude() {
        return mLatitude;
    }

    public void setLatitude(int latitude) {
        this.mLatitude = latitude;
    }

    public int getLongitude() {
        return mLongitude;
    }

    public void setLongitude(int longitude) {
        this.mLongitude = longitude;
    }

    @Override
    public String toString() {
        return "MyUserPoint{" +
                "mName='" + mName + '\'' +
                ", mType='" + mType + '\'' +
                ", mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                '}';
    }
}
