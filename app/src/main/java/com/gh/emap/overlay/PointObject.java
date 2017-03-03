package com.gh.emap.overlay;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.GeoPoint;

import java.io.Serializable;

/**
 * Created by GuHeng on 2016/12/22.
 * 地物绘制-点-读写文件对象
 */

public class PointObject implements Serializable {
    private String mType; // 点-类型
    private String mName; // 点-名称
    private String mStrPoint; // 点 数据格式：“mLon,mLat”

    public PointObject() {
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
        if(mStrPoint == null || mStrPoint.isEmpty()) {
            return null;
        }

        String[] latitudeAndLongitudeArray = mStrPoint.split(",");
        int longitude = Integer.parseInt(latitudeAndLongitudeArray[0]);
        int latitude = Integer.parseInt(latitudeAndLongitudeArray[1]);

        return new GeoPoint(latitude, longitude);
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        mStrPoint = geoPoint.toString();
    }
}
