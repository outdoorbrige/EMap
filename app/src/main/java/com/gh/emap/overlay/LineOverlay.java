package com.gh.emap.overlay;

import android.content.Context;

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
    private Context mContext;
    private LineOption mLineOption;
    private ArrayList<GeoPoint> mGeoPointArrayList;

    public LineOverlay(Context context) {
        this.mContext = context;
        this.mLineOption = new LineOption();
        this.mLineOption.setStrokeWidth(5);
        this.mLineOption.setDottedLine(false);
        this.mLineOption.setStrokeColor(0xAA000000);
        mGeoPointArrayList = new ArrayList<>();
    }

    public LineOverlay(Context context, LineOption lineOption, ArrayList<GeoPoint> geoPoints) {
        this.mContext = context;
        this.mLineOption = lineOption;
        this.mGeoPointArrayList = geoPoints;
    }

    public void setLineOption(LineOption lineOption) {
        this.mLineOption = lineOption;
    }

    public LineOption getLineOption() {
        return this.mLineOption;
    }

    public void setPoints(ArrayList<GeoPoint> points) {
        this.mGeoPointArrayList = points;
    }

    public ArrayList<GeoPoint> getPoints() {
        return this.mGeoPointArrayList;
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
        this.addPoint(geoPoint);

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
        mapViewRender.drawPolyLine(gl10, this.mLineOption, this.mGeoPointArrayList);
    }
}
