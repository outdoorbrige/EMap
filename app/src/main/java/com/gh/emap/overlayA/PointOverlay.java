package com.gh.emap.overlayA;

import android.graphics.Point;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MapViewRender;
import com.tianditu.android.maps.Overlay;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by GuHeng on 2016/11/14.
 * 画点覆盖物
 */
public class PointOverlay extends Overlay {
    private MainActivity mMainActivity;
    private PointObject mPointObject = new PointObject();
    private boolean mEditStatus = true; // 可编辑状态

    public PointOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void setPointObject(PointObject pointObject) {
        mPointObject = pointObject;
    }

    public PointObject getPointObject() {
        return mPointObject;
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
            mPointObject.setGeoPoint(geoPoint);
        }

        return true;
    }

    // 动画叠加绘制调用
    @Override
    public void draw(GL10 gl10, MapView mapView, boolean shadow) {
        if(shadow) {
            return;
        }

        GeoPoint geoPoint = mPointObject.getGeoPoint();
        if(geoPoint == null) {
            return;
        }

        Point point = mMainActivity.getMainManager().getMapManager().getMapView().getProjection().toPixels(geoPoint, null);

        MapViewRender render = mapView.getMapViewRender();

        // 画点
        render.drawRound(gl10, mMainActivity.getMainManager().getRenderOptionManager().getCircleOption(), point,
                mMainActivity.getMainManager().getRenderOptionManager().getCircleRadius());
    }
}
