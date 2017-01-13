package com.gh.emap.overlay;

import com.tianditu.android.maps.GeoPoint;

import java.io.Serializable;

/**
 * Created by GuHeng on 2016/12/22.
 * 地物编辑-点-读写文件对象
 */

public class PointObject implements Serializable {
    String mTitle;
    String mSnippet;

    private int mIndex; // 点-索引
    private String mType; // 点-类型
    private String mName; // 点-名称
    private int mLatitude; // 点-纬度
    private int mLongitude; // 点-经度

    public PointObject() {
        this.mTitle = "";
        this.mSnippet = "";
        this.mIndex = -1;
        this.mType = "";
        this.mName = "";
        this.mLatitude = -1;
        this.mLongitude = -1;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getSnippet() {
        return mSnippet;
    }

    public void setSnippet(String snippet) {
        this.mSnippet = snippet;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
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
}
