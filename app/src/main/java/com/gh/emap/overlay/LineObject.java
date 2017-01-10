package com.gh.emap.overlay;

import com.tianditu.android.maps.GeoPoint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/9.
 * 地物编辑-线-读写文件对象
 */

public class LineObject implements Serializable {
    // LineOption
    private int mStrokeWidth = 5; // 线的宽度
    private int mStrokeColor = 0xAA000000; // 线的颜色RGBA
    private boolean mDottedLine = false; // 虚线
    private int[] mIntervals = new int[]{13, 10, 13, 10}; // 虚线点间隔

    ArrayList<String> mListPoints = new ArrayList<>(); // 点的集合 数据格式：“纬度*10E6,经度*10E6”

    public LineObject() {

    }

    public LineObject(int strokeWidth, int strokeColor, boolean dottedLine) {
        this.mStrokeWidth = strokeWidth;
        this.mStrokeColor = strokeColor;
        this.mDottedLine = dottedLine;
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

    public void setListPoints(ArrayList<String> listPoints) {
        this.mListPoints = listPoints;
    }

    public ArrayList<String> getListPoints() {
        return this.mListPoints;
    }

    public boolean addPoint(GeoPoint geoPoint) {
        if(geoPoint == null) {
            return false;
        }

        String value = String.valueOf(geoPoint.getLatitudeE6()) + "," + String.valueOf(geoPoint.getLongitudeE6());

        return this.mListPoints.add(value);
    }

    public void addPoints(ArrayList<GeoPoint> geoPointList) {
        if(geoPointList == null) {
            return;
        }

        for (int i = 0; i < geoPointList.size(); i ++) {
            this.addPoint(geoPointList.get(i));
        }
    }
}
