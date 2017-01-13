package com.gh.emap.overlay;

import com.tianditu.android.maps.GeoPoint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/9.
 * 地物编辑-线-读写文件对象
 */

public class LineObject implements Serializable {
    String mTitle;
    String mSnippet;

    private int mIndex; // 线-索引
    private String mType; // 线-类型
    private String mName; // 线-名称
    ArrayList<String> mStrPoints = new ArrayList<>(); // 点的集合 数据格式：“纬度*10E6,经度*10E6”

    // LineOption
    private int mStrokeWidth; // 线的宽度
    private int mStrokeColor; // 线的颜色RGBA
    private boolean mDottedLine; // 虚线
    private int[] mIntervals = new int[]{13, 10, 13, 10}; // 虚线点间隔

    public LineObject() {
        this.mTitle = "";
        this.mSnippet = "";
        this.mIndex = -1;
        this.mType = "";
        this.mName = "";
        this.mStrokeWidth = 5;
        this.mStrokeColor = 0xAA000000;
        this.mDottedLine = false;
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

    public void setStrPoints(ArrayList<String> listPoints) {
        this.mStrPoints = listPoints;
    }

    public void setGeoPoints(ArrayList<GeoPoint> listPoints) {
        if(listPoints == null) {
            return;
        }

        mStrPoints.clear();
        addGeoPoints(listPoints);
    }

    public ArrayList<String> getStrPoints() {
        return this.mStrPoints;
    }

    public void setStrokeWidth(int width) {
        if(width > 0) {
            this.mStrokeWidth = width;
        }
    }

    public int getStrokeWidth() {
        return this.mStrokeWidth;
    }

    public void setStrokeColor(int color) {
        this.mStrokeColor = color;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public void setDottedLine(boolean b) {
        this.mDottedLine = b;
    }

    public boolean isDottedLine() {
        return this.mDottedLine;
    }

    public void setIntervals(int[] intervals) {
        if(intervals != null) {
            if(intervals.length >= 2 && intervals.length % 2 == 0) {
                this.mIntervals = intervals;
            }
        }
    }

    public int[] getIntervals() {
        return this.mIntervals;
    }

    public boolean addGeoPoint(GeoPoint geoPoint) {
        return this.mStrPoints.add(GeoPointToStrPoint(geoPoint));
    }

    public void addGeoPoints(ArrayList<GeoPoint> geoPointList) {
        if(geoPointList == null) {
            return;
        }

        for (int i = 0; i < geoPointList.size(); i ++) {
            this.addGeoPoint(geoPointList.get(i));
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

        for (int i = 0; i < this.mStrPoints.size(); i ++) {
            geoPoints.add(StrPointToGeoPoint(this.mStrPoints.get(i)));
        }

        return geoPoints;
    }
}
