package com.gh.emap.overlayA;

import com.gh.emap.graphicA.MyCircleOption;
import com.gh.emap.graphicA.MyCoordinate;
import com.gh.emap.graphicA.MyGraphicAttribute;
import com.gh.emap.graphicA.MyLineOption;
import com.tianditu.android.maps.GeoPoint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/9.
 * 地物绘制-线-读写文件对象
 */

public class LineObject implements Serializable {
    private MyGraphicAttribute mMyGraphicAttribute = new MyGraphicAttribute();
    ArrayList<MyCoordinate> mMyCoordinates = new ArrayList<>();
    private MyCircleOption mMyCircleOption = new MyCircleOption();
    private MyLineOption mMyLineOption = new MyLineOption();

    public LineObject() {
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

    public void setMyLineOption(MyLineOption myLineOption) {
        mMyLineOption = myLineOption;
    }

    public MyLineOption getMyLineOption() {
        return mMyLineOption;
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
