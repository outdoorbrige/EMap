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

    public PointOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mDrawable = ContextCompat.getDrawable(mMainActivity, R.mipmap.added_icon);
        mDrawableOption = new DrawableOption();
        mGeoPoint = null;
    }

    // 设置覆盖物的位置
    public void setGeoPoint(GeoPoint geoPoint) {
        mGeoPoint = geoPoint;
    }

    // 获取覆盖物的位置
    public GeoPoint getGeoPoint() {
        return mGeoPoint;
    }

    // 单击事件
    @Override
    public boolean onTap(GeoPoint geoPoint, MapView mapView) {
        mGeoPoint = geoPoint;

        return true;
    }

    // 动画叠加绘制调用
    @Override
    public void draw(GL10 gl10, MapView mapView, boolean shadow) {
        if(shadow) {
            return;
        }

        // 绘制点击位置
        MapViewRender mapViewRender = mapView.getMapViewRender();
        mapViewRender.drawDrawable(gl10, mDrawableOption, mDrawable, mGeoPoint);
    }
}
