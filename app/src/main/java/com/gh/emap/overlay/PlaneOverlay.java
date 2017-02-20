package com.gh.emap.overlay;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MapViewRender;
import com.tianditu.android.maps.Overlay;
import com.tianditu.android.maps.renderoption.DrawableOption;
import com.tianditu.android.maps.renderoption.PlaneOption;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by GuHeng on 2017/1/17.
 * 画面覆盖物
 */

public class PlaneOverlay extends Overlay {
    public MainActivity mMainActivity;

    private Drawable mDrawable;
    private DrawableOption mDrawableOption;

    public PlaneOption mPlaneOption;
    private ArrayList<GeoPoint> mGeoPointArrayList = new ArrayList<>();

    private boolean mEditStatus; // 可编辑状态

    public PlaneOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;

        mDrawable = ContextCompat.getDrawable(mMainActivity, R.mipmap.corner_blue_bg);
        mDrawableOption = new DrawableOption();
        mDrawableOption.setAnchor(0.5f, 0.5f); // 设置锚点比例，默认（0.5f, 1.0f）水平居中，垂直下对齐

        mPlaneOption = new PlaneOption();
        mPlaneOption.setStrokeWidth(5);
        mPlaneOption.setStrokeColor(0xAA000000);
        mPlaneOption.setDottedLine(false);
        mPlaneOption.setFillColor(0x7F696969);

        mEditStatus = true;
    }

    public void setOption(PlaneOption planeOption) {
        mPlaneOption = planeOption;
    }

    public PlaneOption getOption() {
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

    public void setEditStatus(boolean status) {
        mEditStatus = status;
    }

    public boolean isEditStatus() {
        return mEditStatus;
    }

    // 单击事件
    @Override
    public boolean onTap(GeoPoint geoPoint, MapView mapView) {
        if(isEditStatus()) {
            addPoint(geoPoint);
        }

        return true;
    }

    // 动画叠加绘制调用
    @Override
    public void draw(GL10 gl10, MapView mapView, boolean shadow) {
        if(shadow) {
            return;
        }

        if(mGeoPointArrayList == null)
        {
            return;
        }

        MapViewRender render = mapView.getMapViewRender();

        for(int i = 0; i < mGeoPointArrayList.size(); i ++) {
            render.drawDrawable(gl10, mDrawableOption, mDrawable, mGeoPointArrayList.get(i));
        }

        if(mGeoPointArrayList.size() < 2 || mGeoPointArrayList.contains((Object)null))
        {
            return;
        }

        render.drawPolygon(gl10, mPlaneOption, mGeoPointArrayList);
    }
}
