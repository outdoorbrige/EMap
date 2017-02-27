package com.gh.emap.overlay;

import com.tianditu.android.maps.GeoPoint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/17.
 * 地物绘制-面-读写文件对象
 */

public class PlaneObject implements Serializable {
    String mTitle;
    String mSnippet;

    private int mIndex; // 面-索引
    private String mType; // 面-类型
    private String mName; // 面-名称
    ArrayList<String> mStrPoints = new ArrayList<>(); // 面边线点的集合 数据格式：“纬度*10E6,经度*10E6”

    public PlaneObject() {
        mTitle = "";
        mSnippet = "";
        mIndex = -1;
        mType = "";
        mName = "";
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

    public void setStrPoints(ArrayList<String> listPoints) {
        mStrPoints = listPoints;
    }

    public void setGeoPoints(ArrayList<GeoPoint> listPoints) {
        if(listPoints == null) {
            return;
        }

        mStrPoints.clear();
        addGeoPoints(listPoints);
    }

    public ArrayList<String> getStrPoints() {
        return mStrPoints;
    }

    public boolean addGeoPoint(GeoPoint geoPoint) {
        return mStrPoints.add(GeoPointToStrPoint(geoPoint));
    }

    public void addGeoPoints(ArrayList<GeoPoint> geoPointList) {
        if(geoPointList == null) {
            return;
        }

        for (int i = 0; i < geoPointList.size(); i ++) {
            addGeoPoint(geoPointList.get(i));
        }
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

    public ArrayList<GeoPoint> getGeoPoints() {
        ArrayList<GeoPoint> geoPoints = new ArrayList<>();

        for (int i = 0; i < mStrPoints.size(); i ++) {
            geoPoints.add(StrPointToGeoPoint(mStrPoints.get(i)));
        }

        return geoPoints;
    }
}
