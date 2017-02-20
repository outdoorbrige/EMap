package com.gh.emap.overlay;

import com.tianditu.android.maps.GeoPoint;

import java.io.Serializable;

/**
 * Created by GuHeng on 2016/12/22.
 * 地物绘制-点-读写文件对象
 */

public class PointObject implements Serializable {
    String mTitle;
    String mSnippet;

    private int mIndex; // 点-索引
    private String mType; // 点-类型
    private String mName; // 点-名称
    private String mStrPoint; // 点 数据格式：“纬度*10E6,经度*10E6”

    public PointObject() {
        mTitle = "";
        mSnippet = "";
        mIndex = -1;
        mType = "";
        mName = "";
        mStrPoint = "";
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSnippet() {
        return mSnippet;
    }

    public void setSnippet(String snippet) {
        mSnippet = snippet;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStrPoint() {
        return mStrPoint;
    }

    public void setStrPoint(String strPoint) {
        mStrPoint = strPoint;
    }

    public GeoPoint getGeoPoint() {
        return StrPointToGeoPoint(mStrPoint);
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        mStrPoint = GeoPointToStrPoint(geoPoint);
    }

    public static String GeoPointToStrPoint(GeoPoint geo) {
        if(geo == null) {
            return "";
        }

        return String.valueOf(geo.getLatitudeE6()) + "," + String.valueOf(geo.getLongitudeE6());
    }

    public static GeoPoint StrPointToGeoPoint(String str) {
        if(str == null || str.isEmpty()) {
            return null;
        }

        String[] latitudeAndLongitudeArray = str.split(",");
        int latitude = Integer.parseInt(latitudeAndLongitudeArray[0]);
        int longitude = Integer.parseInt(latitudeAndLongitudeArray[1]);

        return new GeoPoint(latitude, longitude);
    }
}
