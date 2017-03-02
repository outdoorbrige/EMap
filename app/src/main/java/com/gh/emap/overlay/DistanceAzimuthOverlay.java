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

    // Stack<ArrayList<String>>中每个ArrayList<String>有两个元素(元素一：数据；元素二：标签)
    private Stack<ArrayList<String>> mDistances = new Stack<>(); // 距离集合
    private Stack<ArrayList<String>> mAzimuths = new Stack<>(); // 方位角集合

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

    public double getDistance() {
        if(mDistances.isEmpty()) {
            return 0.0f;
        }

        return  Double.valueOf(mDistances.lastElement().get(0)).doubleValue();
    }

    public double getAzimuth() {
        if(mAzimuths.isEmpty()) {
            return 0.0f;
        }

        return Double.valueOf(mAzimuths.lastElement().get(0)).doubleValue();
    }

    public void setEditStatus(boolean status) {
        mEditStatus = status;
    }

    public boolean isEditStatus() {
        return mEditStatus;
    }

    // 计算两点之间的距离
    private double distanceBetween(ArrayList<GeoPoint> geoPointArrayList) {
        double distance = 0.0f;

        if(geoPointArrayList.size() > 1) {
            GeoPoint p1 = mGeoPointArrayList.get(mGeoPointArrayList.size() - 2);
            GeoPoint p2 = mGeoPointArrayList.get(mGeoPointArrayList.size() - 1);

            // 计算距离
            float[] results = new float[1];
            Location.distanceBetween(GeoPointEx.getdY(p1), GeoPointEx.getdX(p1), GeoPointEx.getdY(p2), GeoPointEx.getdX(p2), results);
            distance = results[0];
        }

        return distance;
    }

    // 计算方位角
    private double calculateAzimuth(ArrayList<GeoPoint> geoPointArrayList) {
        if(geoPointArrayList.size() < 2) {
            return 0.0f;
        }

        GeoPoint p1 = mGeoPointArrayList.get(mGeoPointArrayList.size() - 2);
        GeoPoint p2 = mGeoPointArrayList.get(mGeoPointArrayList.size() - 1);

        double d = 0;
        double lat_a = GeoPointEx.getdY(p1) * Math.PI / 180;
        double lng_a = GeoPointEx.getdX(p1) * Math.PI / 180;
        double lat_b = GeoPointEx.getdY(p2) * Math.PI / 180;
        double lng_b = GeoPointEx.getdX(p2) * Math.PI / 180;
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

    public void add(String strDistance, String strAzimuth) {
        if(strDistance == null || strDistance.isEmpty()) {
            ArrayList<String> al1 = new ArrayList<>();
            al1.add("0");
            al1.add("");
            mDistances.push(al1);
        } else {
            // 生成距离标签
            final int ONE_THOUSAND = 1000;

            String strDistanceLab = "";

            double distance = Double.valueOf(strDistance);

            if((int)distance < ONE_THOUSAND) {
                BigDecimal distanceLab = new BigDecimal(strDistance);
                strDistanceLab = distanceLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "米";
            } else {
                BigDecimal distanceLab = new BigDecimal(String.valueOf(distance / ONE_THOUSAND));
                strDistanceLab = distanceLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "公里";
            }

            ArrayList<String> al1 = new ArrayList<>();
            al1.add(strDistance);
            al1.add(strDistanceLab);

            mDistances.push(al1);
        }

        if(strAzimuth == null || strAzimuth.isEmpty()) {
            ArrayList<String> al2 = new ArrayList<>();
            al2.add("0");
            al2.add("");
            mAzimuths.push(al2);
        } else {
            // 生成方位角标签
            BigDecimal azimuthLab = new BigDecimal(strAzimuth);
            String strAzimuthLab = azimuthLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "°";

            ArrayList<String> al2 = new ArrayList<>();
            al2.add(strAzimuth);
            al2.add(strAzimuthLab);

            mAzimuths.push(al2);
        }
    }

    // 单击事件
    @Override
    public boolean onTap(GeoPoint geoPoint, MapView mapView) {
        if (!isEditStatus()) {
            return true;
        }

        addPoint(geoPoint);

        String strDistance = "";
        String strAzimuth = "";

        if(mGeoPointArrayList.size() < 2) {
            strDistance = "";
            strAzimuth = "";
        } else {
            // 计算距离
            strDistance = String.valueOf(getDistance() + distanceBetween(mGeoPointArrayList));

            // 计算方位角
            strAzimuth = String.valueOf(calculateAzimuth(mGeoPointArrayList));
        }

        add(strDistance, strAzimuth);

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

        // 画折线
        render.drawPolyLine(gl10, mMainActivity.getMainManager().getRenderOptionManager().getLineOption(), mGeoPointArrayList);

        String strLab; // 标注内容

        for (int i = 0; i < mGeoPointArrayList.size(); i++) {
            GeoPoint geoPoint = mGeoPointArrayList.get(i);
            Point point = mMainActivity.getMainManager().getMapManager().getMapView().getProjection().toPixels(geoPoint, null);

            // 画拐点
            render.drawRound(gl10, mMainActivity.getMainManager().getRenderOptionManager().getCircleOption(), point,
                    mMainActivity.getMainManager().getRenderOptionManager().getCircleRadius());

            if(i == 0) { // 起点
                strLab = "起点";
//            } else if (i == (mGeoPointArrayList.size() - 1)) { // 终点
//                strLab = "终点";
            } else{ // 拐点
                strLab = mDistances.get(i).get(1) + "，" + mAzimuths.get(i).get(1);
            }

            strLab = strLab + mMainActivity.getMainManager().getRenderOptionManager().getFillChars();

            // 画标注
            render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPoint);
        }
    }
}
