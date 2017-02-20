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

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by GuHeng on 2016/11/14.
 * 画点覆盖物
 */
public class PointOverlay extends Overlay {
    private MainActivity mMainActivity;
    private Drawable mDrawable;
    private DrawableOption mDrawableOption;
    private GeoPoint mGeoPoint;

    private boolean mEditStatus; // 可编辑状态

    public PointOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;

        mDrawable = ContextCompat.getDrawable(mMainActivity, R.mipmap.point_bg);
        mDrawableOption = new DrawableOption();
        mDrawableOption.setAnchor(0.5f, 0.5f); // 设置锚点比例，默认（0.5f, 1.0f）水平居中，垂直下对齐

        mGeoPoint = null;

        mEditStatus = true;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        mGeoPoint = geoPoint;
    }

    public GeoPoint getGeoPoint() {
        return mGeoPoint;
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
            setGeoPoint(geoPoint);
        }

        return true;
    }

    // 动画叠加绘制调用
    @Override
    public void draw(GL10 gl10, MapView mapView, boolean shadow) {
        if(shadow) {
            return;
        }

        if(mDrawableOption == null || mDrawable == null || mGeoPoint == null) {
            return;
        }

        MapViewRender render = mapView.getMapViewRender();

        render.drawDrawable(gl10, mDrawableOption, mDrawable, mGeoPoint);
    }
}
