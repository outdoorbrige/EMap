package com.gh.emap.overlay;

import android.graphics.Point;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MapViewRender;
import com.tianditu.android.maps.Overlay;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by GuHeng on 2017/1/17.
 * 画面覆盖物
 */

public class PlaneOverlay extends Overlay {
    public MainActivity mMainActivity;
    private PlaneObject mPlaneObject = new PlaneObject();
    private boolean mEditStatus = true; // 可编辑状态

    public PlaneOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void setPlaneObject(PlaneObject planeObject) {
        mPlaneObject = planeObject;
    }

    public PlaneObject getPlaneObject() {
        return mPlaneObject;
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
            mPlaneObject.addGeoPoint(geoPoint);
        }

        return true;
    }

    // 动画叠加绘制调用
    @Override
    public void draw(GL10 gl10, MapView mapView, boolean shadow) {
        if(shadow) {
            return;
        }

        if(mPlaneObject == null)
        {
            return;
        }

        ArrayList<GeoPoint> geoPoints = mPlaneObject.getGeoPoints();
        if(geoPoints == null || geoPoints.isEmpty()) {
            return;
        }

        MapViewRender render = mapView.getMapViewRender();

        // 画面
        render.drawPolygon(gl10, mMainActivity.getMainManager().getRenderOptionManager().getPlaneOption(), geoPoints);

        // 画拐点
        for(int i = 0; i < geoPoints.size(); i ++) {
            GeoPoint geoPoint = geoPoints.get(i);
            Point point = mMainActivity.getMainManager().getMapManager().getMapView().getProjection().toPixels(geoPoint, null);

            render.drawRound(gl10, mMainActivity.getMainManager().getRenderOptionManager().getCircleOption(), point,
                    mMainActivity.getMainManager().getRenderOptionManager().getCircleRadius());
        }
    }
}
