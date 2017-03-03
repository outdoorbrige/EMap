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
 * Created by GuHeng on 2017/1/10.
 * 画线覆盖物
 */

public class LineOverlay extends Overlay {
    private MainActivity mMainActivity;
    private LineObject mLineObject = new LineObject();
    private boolean mEditStatus = true; // 可编辑状态

    public LineOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void setLineObject(LineObject lineObject) {
        mLineObject = lineObject;
    }

    public LineObject getLineObject() {
        return mLineObject;
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
            mLineObject.addGeoPoint(geoPoint);
        }

        return true;
    }

    // 动画叠加绘制调用
    @Override
    public void draw(GL10 gl10, MapView mapView, boolean shadow) {
        if (shadow) {
            return;
        }

        if (mLineObject == null) {
            return;
        }

        ArrayList<GeoPoint> geoPoints = mLineObject.getGeoPoints();
        if(geoPoints == null || geoPoints.isEmpty()) {
            return;
        }

        MapViewRender render = mapView.getMapViewRender();

        // 画折线
        render.drawPolyLine(gl10, mMainActivity.getMainManager().getRenderOptionManager().getLineOption(), geoPoints);

        // 画拐点
        for (int i = 0; i < geoPoints.size(); i++) {
            GeoPoint geoPoint = geoPoints.get(i);
            Point point = mMainActivity.getMainManager().getMapManager().getMapView().getProjection().toPixels(geoPoint, null);

            render.drawRound(gl10, mMainActivity.getMainManager().getRenderOptionManager().getCircleOption(), point,
                    mMainActivity.getMainManager().getRenderOptionManager().getCircleRadius());
        }
    }
}
