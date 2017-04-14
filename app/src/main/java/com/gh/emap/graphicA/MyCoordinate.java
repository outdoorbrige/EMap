package com.gh.emap.graphicA;

import com.tianditu.android.maps.GeoPoint;
import com.tianditu.maps.GeoPointEx;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/4/14.
 */

public class MyCoordinate implements Serializable {
    private double mLongitude; // 经度
    private double mLatitude; // 纬度
    private double mAltitude; // 高程

    public MyCoordinate() {

    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    public double getAltitude() {
        return mAltitude;
    }

    public void setAltitude(double altitude) {
        this.mAltitude = altitude;
    }

    public GeoPoint getGeoPoint() {
        return GeoPointEx.Double2GeoPoint(mLongitude, mLatitude);
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        mLongitude = GeoPointEx.getdX(geoPoint);
        mLatitude = GeoPointEx.getdY(geoPoint);
    }
}
