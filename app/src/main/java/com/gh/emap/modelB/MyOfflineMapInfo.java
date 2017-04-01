package com.gh.emap.modelB;

import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.TOfflineMapInfo;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/4/1.
 * 自定义离线地图信息类
 */

public class MyOfflineMapInfo implements Serializable {
    private String mCity = null;
    private int mType = 0;
    private int mVersion = 0;
    private int mSize = 0;
    private int mCenterLatitude = 0;
    private int mCenterLongitude = 0;
    private int mMapLevel;
    private int mDownloadedSize = 0;
    private int mState = 0;
    private String mUrl = null;
    private String mMapName = null;
    private String mMapPath = null;

    public MyOfflineMapInfo() {

    }

    public void setTOfflineMapInfo(TOfflineMapInfo tOfflineMapInfo) {
        if(tOfflineMapInfo == null) {
            return;
        }

        mCity = tOfflineMapInfo.getCity();
        mType = tOfflineMapInfo.getType();
        mVersion = tOfflineMapInfo.getVersion();
        mSize = tOfflineMapInfo.getSize();
        mCenterLatitude = tOfflineMapInfo.getCenter().getLatitudeE6();
        mCenterLongitude = tOfflineMapInfo.getCenter().getLongitudeE6();
        mMapLevel = tOfflineMapInfo.getLevel();
        mDownloadedSize = tOfflineMapInfo.getDownloadedSize();
        mState = tOfflineMapInfo.getState();
        mUrl = getUrl();
        mMapName = getMapName();
        mMapPath = getMapPath();
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public int getVersion() {
        return mVersion;
    }

    public void setVersion(int version) {
        this.mVersion = version;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        this.mSize = size;
    }

    public int getCenterLatitude() {
        return mCenterLatitude;
    }

    public void setCenterLatitude(int centerLatitude) {
        this.mCenterLatitude = centerLatitude;
    }

    public int getCenterLongitude() {
        return mCenterLongitude;
    }

    public void setCenterLongitude(int centerLongitude) {
        this.mCenterLongitude = centerLongitude;
    }

    public GeoPoint getCenter() {
        return new GeoPoint(mCenterLatitude, mCenterLongitude);
    }

    public void setCenter(GeoPoint center) {
        if(center == null) {
            return;
        }

        mCenterLatitude = center.getLatitudeE6();
        mCenterLongitude = center.getLongitudeE6();
    }

    public int getMapLevel() {
        return mMapLevel;
    }

    public void setMapLevel(int mapLevel) {
        this.mMapLevel = mapLevel;
    }

    public int getDownloadedSize() {
        return mDownloadedSize;
    }

    public void setDownloadedSize(int downloadedSize) {
        this.mDownloadedSize = downloadedSize;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        this.mState = state;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getMapName() {
        return mMapName;
    }

    public void setMapName(String mapName) {
        this.mMapName = mapName;
    }

    public String getMapPath() {
        return mMapPath;
    }

    public void setMapPath(String mapPath) {
        this.mMapPath = mapPath;
    }

    @Override
    public String toString() {
        return "MyOfflineMapInfo{" +
                "mCity='" + mCity + '\'' +
                ", mType=" + mType +
                ", mVersion=" + mVersion +
                ", mSize=" + mSize +
                ", mCenterLatitude=" + mCenterLatitude +
                ", mCenterLongitude=" + mCenterLongitude +
                ", mMapLevel=" + mMapLevel +
                ", mDownloadedSize=" + mDownloadedSize +
                ", mState=" + mState +
                ", mUrl='" + mUrl + '\'' +
                ", mMapName='" + mMapName + '\'' +
                ", mMapPath='" + mMapPath + '\'' +
                '}';
    }
}
