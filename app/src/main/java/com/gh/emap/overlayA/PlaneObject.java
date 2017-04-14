package com.gh.emap.overlayA;

import com.gh.emap.graphicA.MyCircleOption;
import com.gh.emap.graphicA.MyCoordinate;
import com.gh.emap.graphicA.MyGraphicAttribute;
import com.gh.emap.graphicA.MyPlaneOption;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.renderoption.CircleOption;
import com.tianditu.android.maps.renderoption.PlaneOption;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/17.
 * 地物绘制-面-读写文件对象
 */

public class PlaneObject implements Serializable {
    private MyGraphicAttribute mMyGraphicAttribute = new MyGraphicAttribute();
    ArrayList<MyCoordinate> mMyCoordinates = new ArrayList<>();
    private MyCircleOption mMyCircleOption = new MyCircleOption();
    private MyPlaneOption mMyPlaneOption = new MyPlaneOption();

    public PlaneObject() {
    }

    public void setMyGraphicAttribute(MyGraphicAttribute myGraphicAttribute) {
        mMyGraphicAttribute = myGraphicAttribute;
    }

    public MyGraphicAttribute getMyGraphicAttribute() {
        return mMyGraphicAttribute;
    }

    public void setMyCoordinates(ArrayList<MyCoordinate> myCoordinates) {
        mMyCoordinates = myCoordinates;
    }

    public ArrayList<MyCoordinate> getMyCoordinates() {
        return mMyCoordinates;
    }

    public void setMyCircleOption(MyCircleOption myCircleOption) {
        mMyCircleOption = myCircleOption;
    }

    public MyCircleOption getMyCircleOption() {
        return mMyCircleOption;
    }

    public void setMyPlaneOption(MyPlaneOption myPlaneOption) {
        mMyPlaneOption = myPlaneOption;
    }

    public MyPlaneOption getMyPlaneOption() {
        return mMyPlaneOption;
    }

    public ArrayList<GeoPoint> getGeoPoints() {
        if(mMyCoordinates == null || mMyCoordinates.isEmpty()) {
            return null;
        }

        ArrayList<GeoPoint> geoPoints = new ArrayList<>();
        for(int i = 0; i < mMyCoordinates.size(); i ++) {
            geoPoints.add(mMyCoordinates.get(i).getGeoPoint());
        }

        return geoPoints;
    }
}
