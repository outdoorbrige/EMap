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
import java.util.Stack;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by GuHeng on 2017/2/22.
 */

public class DistanceAzimuthOverlay extends Overlay {
    private MainActivity mMainActivity;
    private ArrayList<GeoPoint> mGeoPointArrayList = new ArrayList<>();
    private boolean mEditStatus; // 可编辑状态

    public DistanceAzimuthOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mEditStatus = true;
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

    // 获取距离集合带单位(四舍五入3位小数)
    public ArrayList<String> getDistances() {
        ArrayList<String> strDistanceList = new ArrayList<>();
        if(mGeoPointArrayList.size() < 1) {

        } else {
            ArrayList<Double> dDistanceList = new ArrayList<>();

            dDistanceList.add(Double.valueOf("0"));
            strDistanceList.add("0");

            for(int i = 1; i < mGeoPointArrayList.size(); i ++) {
                GeoPoint p1 = mGeoPointArrayList.get(i - 1);
                GeoPoint p2 = mGeoPointArrayList.get(i);

                // 计算距离
                float[] results = new float[1];
                Location.distanceBetween(GeoPointEx.getdY(p1), GeoPointEx.getdX(p1), GeoPointEx.getdY(p2), GeoPointEx.getdX(p2), results);

                double dDistance = dDistanceList.get(dDistanceList.size() - 1) + results[0];

                dDistanceList.add(Double.valueOf(dDistance));

                // 生成距离标签

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

                strDistanceList.add(strDistanceLab);
            }
        }

        return strDistanceList;
    }

    // 获取方位角集合带单位(四舍五入3位小数)
    public ArrayList<String> getAzimuths() {
        ArrayList<String> strAzimuthList = new ArrayList<>();
        if(mGeoPointArrayList.size() < 1) {

        } else {
            strAzimuthList.add("0");

            for(int i = 1; i < mGeoPointArrayList.size(); i ++) {
                GeoPoint p1 = mGeoPointArrayList.get(i - 1);
                GeoPoint p2 = mGeoPointArrayList.get(i);

                // 计算方位角
                double dAzimuth = calculateAzimuth(GeoPointEx.getdY(p1), GeoPointEx.getdX(p1), GeoPointEx.getdY(p2), GeoPointEx.getdX(p2));
                String strAzimuth = String.valueOf(dAzimuth);
                String strAzimuthLab = "";

                // 生成方位角标签
                BigDecimal azimuthLab = new BigDecimal(strAzimuth);
                strAzimuthLab = azimuthLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "°";

                strAzimuthList.add(strAzimuthLab);
            }
        }

        return strAzimuthList;
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
            addPoint(geoPoint);
        }

        return true;
    }

    // 动画叠加绘制调用
    @Override
    public void draw(GL10 gl10, MapView mapView, boolean shadow) {
        if (shadow) {
            return;
        }

        if (mGeoPointArrayList == null || mGeoPointArrayList.size() == 0) {
            return;
        }

        MapViewRender render = mapView.getMapViewRender();

        // 折线
        render.drawPolyLine(gl10, mMainActivity.getMainManager().getRenderOptionManager().getLineOption(), mGeoPointArrayList);

        ArrayList<String> strDistanceArray = getDistances();
        ArrayList<String> strAzimuthArray = getAzimuths();

        for (int i = 0; i < mGeoPointArrayList.size(); i++) {
            GeoPoint geoPoint = mGeoPointArrayList.get(i);
            Point point = mMainActivity.getMainManager().getMapManager().getMapView().getProjection().toPixels(geoPoint, null);

            // 拐点
            render.drawRound(gl10, mMainActivity.getMainManager().getRenderOptionManager().getCircleOption(), point,
                    mMainActivity.getMainManager().getRenderOptionManager().getCircleRadius());

            if(i == 0) { // 起点标注
                String strLab = "起点"+ mMainActivity.getMainManager().getRenderOptionManager().getFillChars();
                render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPoint);
            } else{ // 拐点标注
                String strLab = strDistanceArray.get(i) + "，" + strAzimuthArray.get(i) + mMainActivity.getMainManager().getRenderOptionManager().getFillChars();
                render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPoint);
            }
        }
    }
}
