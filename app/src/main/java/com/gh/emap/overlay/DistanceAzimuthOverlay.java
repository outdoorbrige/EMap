package com.gh.emap.overlay;

import android.graphics.Point;
import android.location.Location;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MapViewRender;
import com.tianditu.android.maps.Overlay;
import com.tianditu.maps.GeoPointEx;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by GuHeng on 2017/2/22.
 */

public class DistanceAzimuthOverlay extends Overlay {
    private MainActivity mMainActivity;
    private ArrayList<GeoPoint> mGeoPoints = new ArrayList<>();
    ArrayList<Double> mDoubleDistances = new ArrayList<>();
    ArrayList<Double> mDoubleAzimuths = new ArrayList<>();
    private boolean mEditStatus; // 可编辑状态

    public DistanceAzimuthOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mEditStatus = true;
    }

    public boolean setGeoPoints(ArrayList<GeoPoint> geoPoints) {
        boolean bReturn = true;

        clearGeoPoints();

        for(int i = 0; i < geoPoints.size(); i ++) {
            bReturn = bReturn & addGeoPoint(geoPoints.get(i));
        }

        return bReturn;
    }

    public ArrayList<GeoPoint> getGeoPoints() {
        return mGeoPoints;
    }

    public boolean addGeoPoint(GeoPoint geoPoint) {
        boolean bReturn = mGeoPoints.add(geoPoint);

        if(mGeoPoints.size() < 1) {
            bReturn = bReturn & true;
        } else if(mGeoPoints.size() == 1) {
            bReturn = bReturn & mDoubleDistances.add(0.0);
            bReturn = bReturn & mDoubleAzimuths.add(0.0);
        } else {
            GeoPoint p1 = mGeoPoints.get(mGeoPoints.size() - 2);
            GeoPoint p2 = mGeoPoints.get(mGeoPoints.size() - 1);

            // 计算距离
            float[] results = new float[1];
            Location.distanceBetween(GeoPointEx.getdY(p1), GeoPointEx.getdX(p1), GeoPointEx.getdY(p2), GeoPointEx.getdX(p2), results);
            double dDistance = mDoubleDistances.get(mDoubleDistances.size() - 1) + results[0];
            bReturn = bReturn & mDoubleDistances.add(dDistance);

            // 计算方位角
            double dAzimuth = calculateAzimuth(GeoPointEx.getdY(p1), GeoPointEx.getdX(p1), GeoPointEx.getdY(p2), GeoPointEx.getdX(p2));
            bReturn = bReturn & mDoubleAzimuths.add(dAzimuth);
        }

        return bReturn;
    }

    public boolean addGeoPoints(ArrayList<GeoPoint> geoPoints) {
        boolean bReturn = true;

        for(int i = 0; i < geoPoints.size(); i ++) {
            bReturn = bReturn & addGeoPoint(geoPoints.get(i));
        }

        return bReturn;
    }

    public boolean removeLastGeoPoint() {
        int size = mGeoPoints.size();
        if(size == 0) {
            return false;
        }

        int index = size - 1;

        return mGeoPoints.remove(mGeoPoints.get(index)) &
                mDoubleDistances.remove(mDoubleDistances.get(index)) &
                mDoubleAzimuths.remove(mDoubleAzimuths.get(index));
    }

    public void clearGeoPoints() {
        mGeoPoints.clear();
        mDoubleDistances.clear();
        mDoubleAzimuths.clear();
    }

    // 生成距离标签带单位(四舍五入3位小数)
    String getDistanceLabel(double dDistance) {
        final int ONE_THOUSAND = 1000;
        String strDistance = String.valueOf(dDistance);
        String strDistanceLab = "";

        if ((int) dDistance < ONE_THOUSAND) {
            BigDecimal distanceLab = new BigDecimal(strDistance);
            strDistanceLab = distanceLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "米";
        } else {
            BigDecimal distanceLab = new BigDecimal(String.valueOf(dDistance / ONE_THOUSAND));
            strDistanceLab = distanceLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "公里";
        }

        return strDistanceLab;
    }

    // 生成方位角标签带单位(四舍五入3位小数)
    String getAzimuthLabel(double dAzimuth) {
        String strAzimuth = String.valueOf(dAzimuth);
        String strAzimuthLab = "";

        // 生成方位角标签
        BigDecimal azimuthLab = new BigDecimal(strAzimuth);
        strAzimuthLab = azimuthLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "°";

        return strAzimuthLab;
    }

    public void setEditStatus(boolean status) {
        mEditStatus = status;
    }

    public boolean isEditStatus() {
        return mEditStatus;
    }

    // 计算方位角
    private double calculateAzimuth(double lat_a, double lng_a, double lat_b, double lng_b) {
        double d = 0;
        lat_a = lat_a * Math.PI / 180;
        lng_a = lng_a * Math.PI / 180;
        lat_b = lat_b * Math.PI / 180;
        lng_b = lng_b * Math.PI / 180;
        d = Math.sin(lat_a) * Math.sin(lat_b) + Math.cos(lat_a) * Math.cos(lat_b) * Math.cos(lng_b - lng_a);
        d = Math.sqrt(1 - d * d);
        d = Math.cos(lat_b) * Math.sin(lng_b - lng_a) / d;
        d = Math.asin(d) * 180 / Math.PI;
        //d = Math.round(d * 10000);

        double degree = 0;

        double dx = lng_b - lng_a;
        double dy = lat_b - lat_a;

        if(dx > 0) {
            if(dy > 0) { // 第一象限
                degree = d;
            } else if(dy == 0) { // X轴正方向
                degree = 90;
            } else { // 第二象限
                degree = 180 - Math.abs(d);
            }
        } else if(dx == 0) {
            if(dy > 0) { // Y轴正方向
                degree = 360;
            } else if(dy == 0) { // 原点O
                degree = 0;
            } else { // Y轴负方向
                degree = 180;
            }
        } else {
            if(dy > 0) { // 第四象限
                degree = 360 - Math.abs(d);
            } else if(dy == 0) { // X轴负方向
                degree = 270;
            } else { // 第三象限
                degree = 180 + Math.abs(d);
            }
        }

        return degree;
    }

    // 单击事件
    @Override
    public boolean onTap(GeoPoint geoPoint, MapView mapView) {
        if (isEditStatus()) {
            addGeoPoint(geoPoint);
        }

        return true;
    }

    // 动画叠加绘制调用
    @Override
    public void draw(GL10 gl10, MapView mapView, boolean shadow) {
        if (shadow) {
            return;
        }

        if (mGeoPoints == null || mGeoPoints.size() == 0) {
            return;
        }


        if(mDoubleDistances.size() != mGeoPoints.size()) {
            return;
        }

        double dDistance = mDoubleDistances.get(mDoubleDistances.size() - 1);
        double mAzimuth = mDoubleAzimuths.get(mDoubleAzimuths.size() - 1);

        String strDistance = getDistanceLabel(dDistance);
        String strAzimuth = getAzimuthLabel(mAzimuth);

        MapViewRender render = mapView.getMapViewRender();

        // 折线
        render.drawPolyLine(gl10, mMainActivity.getMainManager().getRenderOptionManager().getLineOption(), mGeoPoints);

        for (int i = 0; i < mGeoPoints.size(); i++) {
            GeoPoint geoPoint = mGeoPoints.get(i);
            Point point = mMainActivity.getMainManager().getMapManager().getMapView().getProjection().toPixels(geoPoint, null);

            // 拐点
            render.drawRound(gl10, mMainActivity.getMainManager().getRenderOptionManager().getCircleOption(), point,
                    mMainActivity.getMainManager().getRenderOptionManager().getCircleRadius());

            if(i == 0) { // 起点标注
                String strLab = "起点"+ mMainActivity.getMainManager().getRenderOptionManager().getFillChars();
                render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPoint);
            } else{ // 拐点标注
                String strLab = strDistance + "，" + strAzimuth + mMainActivity.getMainManager().getRenderOptionManager().getFillChars();
                render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPoint);
            }
        }
    }
}
