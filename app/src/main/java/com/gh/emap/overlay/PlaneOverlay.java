package com.gh.emap.overlay;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MapViewRender;
import com.tianditu.android.maps.Overlay;
import com.tianditu.android.maps.renderoption.PlaneOption;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by GuHeng on 2017/1/17.
 * 画面覆盖物
 */

public class PlaneOverlay extends Overlay {
    public MainActivity mMainActivity;
    public PlaneOption mPlaneOption;
    private ArrayList<GeoPoint> mGeoPointArrayList = new ArrayList<>();

    public PlaneOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mPlaneOption = new PlaneOption();
        mPlaneOption.setStrokeWidth(5);
        mPlaneOption.setStrokeColor(0xAA000000);
        mPlaneOption.setDottedLine(false);
        mPlaneOption.setFillColor(0x7F696969);
    }

    public void setPlaneOption(PlaneOption planeOption) {
        mPlaneOption = planeOption;
    }

    public PlaneOption getPlaneOption() {
        return mPlaneOption;
    }

    public void setPoints(ArrayList<GeoPoint> points) {
        mGeoPointArrayList = points;
    }

    public ArrayList<GeoPoint> getPoints() {
        return mGeoPointArrayList;
    }

    public boolean addPoint(GeoPoint point) {
        return mGeoPointArrayList.add(point);
    }

    public boolean addPoints(ArrayList<GeoPoint> points) {
        return mGeoPointArrayList.addAll(points);
    }

    // 单击事件
    @Override
    public boolean onTap(GeoPoint geoPoint, MapView mapView) {
        addPoint(geoPoint);

        return true;
    }

    // 动画叠加绘制调用
    @Override
    public void draw(GL10 gl10, MapView mapView, boolean shadow) {
        super.draw(gl10, mapView, shadow);
        if(!shadow) {
            if(mGeoPointArrayList != null && mGeoPointArrayList.size() >= 2 && !mGeoPointArrayList.contains((Object)null)) {
                MapViewRender render = mapView.getMapViewRender();
                render.drawPolygon(gl10, mPlaneOption, mGeoPointArrayList);
            }
        }
    }
}
