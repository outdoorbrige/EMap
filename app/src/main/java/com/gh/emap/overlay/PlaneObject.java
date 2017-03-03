package com.gh.emap.overlay;

import com.tianditu.android.maps.GeoPoint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/17.
 * 地物绘制-面-读写文件对象
 */

public class PlaneObject implements Serializable {
    private String mName; // 面-名称
    ArrayList<String> mStrPoints = new ArrayList<>(); // 面边线点的集合 数据格式：“mLon,mLat”

    public PlaneObject() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setStrPoints(ArrayList<String> strPoints) {
        mStrPoints = strPoints;
    }

    public ArrayList<String> getStrPoints() {
        return mStrPoints;
    }

    public boolean addStrPoint(String strPoint) {
        return mStrPoints.add(strPoint);
    }

    public boolean addStrPoints(ArrayList<String> strPoints) {
        return mStrPoints.addAll(strPoints);
    }

    public boolean addGeoPoint(GeoPoint geoPoint) {
        if(geoPoint == null) {
            return false;
        }

        return mStrPoints.add(geoPoint.toString());
    }

    public boolean addGeoPoints(ArrayList<GeoPoint> geoPoints) {
        if(geoPoints == null) {
            return false;
        }

        boolean bAdd = true;

        for(int i = 0; i < geoPoints.size(); i ++) {
            bAdd = bAdd && addGeoPoint(geoPoints.get(i));
        }

        return bAdd;
    }

    public GeoPoint getGeoPoint(int index) {
        if(index < 0 || index > (mStrPoints.size() - 1)) {
            return null;
        }

        String strPoint = mStrPoints.get(index);
        String[] latitudeAndLongitudeArray = strPoint.split(",");
        int longitude = Integer.parseInt(latitudeAndLongitudeArray[0]);
        int latitude = Integer.parseInt(latitudeAndLongitudeArray[1]);

        return new GeoPoint(latitude, longitude);
    }

    public ArrayList<GeoPoint> getGeoPoints() {
        ArrayList<GeoPoint> geoPoints = new ArrayList<>();
        for(int i = 0; i < mStrPoints.size(); i ++) {
            String strPoint = mStrPoints.get(i);
            String[] latitudeAndLongitudeArray = strPoint.split(",");
            int longitude = Integer.parseInt(latitudeAndLongitudeArray[0]);
            int latitude = Integer.parseInt(latitudeAndLongitudeArray[1]);

            geoPoints.add(new GeoPoint(latitude, longitude));
        }

        return geoPoints;
    }
}
