package com.gh.emap.overlay;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MapViewRender;
import com.tianditu.android.maps.Overlay;
import com.tianditu.android.maps.renderoption.LineOption;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by GuHeng on 2017/1/10.
 * 画线覆盖物
 */

public class LineOverlay extends Overlay {
    private MainActivity mMainActivity;
    private LineOption mLineOption;
    private ArrayList<GeoPoint> mGeoPointArrayList = new ArrayList<>();

    public LineOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mLineOption = new LineOption();
        mLineOption.setStrokeWidth(5);
        mLineOption.setStrokeColor(0xAA000000);
        mLineOption.setDottedLine(false);
    }

    public void setLineOption(LineOption lineOption) {
        mLineOption = lineOption;
    }

    public LineOption getLineOption() {
        return mLineOption;
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
        if (shadow) {
            return;
        }

        // 绘制折线
        MapViewRender mapViewRender = mapView.getMapViewRender();
        mapViewRender.drawPolyLine(gl10, mLineOption, mGeoPointArrayList);
    }
}
