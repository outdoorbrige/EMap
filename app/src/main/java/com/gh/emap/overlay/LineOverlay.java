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
import com.tianditu.android.maps.renderoption.LineOption;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by GuHeng on 2017/1/10.
 * 画线覆盖物
 */

public class LineOverlay extends Overlay {
    private MainActivity mMainActivity;

    private Drawable mDrawable;
    private DrawableOption mDrawableOption;

    private LineOption mLineOption;
    private ArrayList<GeoPoint> mGeoPointArrayList = new ArrayList<>();

    public LineOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;

        mDrawable = ContextCompat.getDrawable(mMainActivity, R.mipmap.corner_red_bg);
        mDrawableOption = new DrawableOption();
        mDrawableOption.setAnchor(0.5f, 0.5f); // 设置锚点比例，默认（0.5f, 1.0f）水平居中，垂直下对齐

        mLineOption = new LineOption();
        mLineOption.setStrokeWidth(5);
        mLineOption.setStrokeColor(0xAA000000);
        mLineOption.setDottedLine(false);
    }

    public void setOption(LineOption lineOption) {
        mLineOption = lineOption;
    }

    public LineOption getOption() {
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
        if(!shadow) {
            if(mGeoPointArrayList != null && mLineOption != null) {
                MapViewRender render = mapView.getMapViewRender();

                // 画转角点
                for(int i = 0; i < mGeoPointArrayList.size(); i ++) {
                    render.drawDrawable(gl10, mDrawableOption, mDrawable, mGeoPointArrayList.get(i));
                }

                // 画折线
                render.drawPolyLine(gl10, mLineOption, mGeoPointArrayList);
            }
        }
    }
}
