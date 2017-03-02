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
 * Created by GuHeng on 2017/3/2.
 */

public class AreaGirthOverlay extends Overlay {
    private MainActivity mMainActivity;

    private ArrayList<GeoPoint> mGeoPointArrayList = new ArrayList<>();

    // Stack<ArrayList<String>>中每个ArrayList<String>有两个元素(元素一：数据；元素二：标签)
    private Stack<ArrayList<String>> mAreas = new Stack<>(); // 面积集合
    private Stack<ArrayList<String>> mGirths = new Stack<>(); // 周长集合

    private boolean mEditStatus; // 可编辑状态

    public AreaGirthOverlay(MainActivity mainActivity) {
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

    public double getArea() {
        if(mAreas.isEmpty()) {
            return 0.0f;
        }

        return  Double.valueOf(mAreas.lastElement().get(0)).doubleValue();
    }

    public double getGirth() {
        if(mGirths.isEmpty()) {
            return 0.0f;
        }

        return Double.valueOf(mGirths.lastElement().get(0)).doubleValue();
    }

    public void setEditStatus(boolean status) {
        mEditStatus = status;
    }

    public boolean isEditStatus() {
        return mEditStatus;
    }

    // 计算多边形面积
    private double calculateArea(ArrayList<GeoPoint> geoPointArrayList) {
        int Count = geoPointArrayList.size();
        if (Count < 3) {
            return 0.0f;
        }

        double mtotalArea = 0;

        double LowX = 0.0;
        double LowY = 0.0;
        double MiddleX = 0.0;
        double MiddleY = 0.0;
        double HighX = 0.0;
        double HighY = 0.0;

        double AM = 0.0;
        double BM = 0.0;
        double CM = 0.0;

        double AL = 0.0;
        double BL = 0.0;
        double CL = 0.0;

        double AH = 0.0;
        double BH = 0.0;
        double CH = 0.0;

        double CoefficientL = 0.0;
        double CoefficientH = 0.0;

        double ALtangent = 0.0;
        double BLtangent = 0.0;
        double CLtangent = 0.0;

        double AHtangent = 0.0;
        double BHtangent = 0.0;
        double CHtangent = 0.0;

        double ANormalLine = 0.0;
        double BNormalLine = 0.0;
        double CNormalLine = 0.0;

        double OrientationValue = 0.0;

        double AngleCos = 0.0;

        double Sum1 = 0.0;
        double Sum2 = 0.0;
        double Count2 = 0;
        double Count1 = 0;

        double Sum = 0.0;
        double Radius = 6378000;

        for (int i = 0; i < Count; i++) {
            if (i == 0) {
                LowX =(double) GeoPointEx.getdY(mGeoPointArrayList.get(Count - 1)) * Math.PI / 180;
                LowY = (double)GeoPointEx.getdX(mGeoPointArrayList.get(Count - 1)) * Math.PI / 180;
                MiddleX = (double)GeoPointEx.getdY(mGeoPointArrayList.get(0)) * Math.PI / 180;
                MiddleY = (double)GeoPointEx.getdX(mGeoPointArrayList.get(0)) * Math.PI / 180;
                HighX = (double)GeoPointEx.getdY(mGeoPointArrayList.get(1)) * Math.PI / 180;
                HighY = (double)GeoPointEx.getdX(mGeoPointArrayList.get(1)) * Math.PI / 180;
            } else if (i == Count - 1) {
                LowX = (double)GeoPointEx.getdY(mGeoPointArrayList.get(Count - 2)) * Math.PI / 180;
                LowY = (double)GeoPointEx.getdX(mGeoPointArrayList.get(Count - 2)) * Math.PI / 180;
                MiddleX = (double)GeoPointEx.getdY(mGeoPointArrayList.get(Count - 1)) * Math.PI / 180;
                MiddleY = (double)GeoPointEx.getdX(mGeoPointArrayList.get(Count - 1)) * Math.PI / 180;
                HighX = (double)GeoPointEx.getdY(mGeoPointArrayList.get(0)) * Math.PI / 180;
                HighY = (double)GeoPointEx.getdX(mGeoPointArrayList.get(0)) * Math.PI / 180;
            } else {
                LowX = (double)GeoPointEx.getdY(mGeoPointArrayList.get(i - 1)) * Math.PI / 180;
                LowY = (double)GeoPointEx.getdX(mGeoPointArrayList.get(i - 1)) * Math.PI / 180;
                MiddleX = (double)GeoPointEx.getdY(mGeoPointArrayList.get(i)) * Math.PI / 180;
                MiddleY = (double)GeoPointEx.getdX(mGeoPointArrayList.get(i)) * Math.PI / 180;
                HighX = (double)GeoPointEx.getdY(mGeoPointArrayList.get(i + 1)) * Math.PI / 180;
                HighY = (double)GeoPointEx.getdX(mGeoPointArrayList.get(i + 1)) * Math.PI / 180;
            }

            AM = Math.cos(MiddleY) * Math.cos(MiddleX);
            BM = Math.cos(MiddleY) * Math.sin(MiddleX);
            CM = Math.sin(MiddleY);
            AL = Math.cos(LowY) * Math.cos(LowX);
            BL = Math.cos(LowY) * Math.sin(LowX);
            CL = Math.sin(LowY);
            AH = Math.cos(HighY) * Math.cos(HighX);
            BH = Math.cos(HighY) * Math.sin(HighX);
            CH = Math.sin(HighY);

            CoefficientL = (AM * AM + BM * BM + CM * CM) / (AM * AL + BM * BL + CM * CL);
            CoefficientH = (AM * AM + BM * BM + CM * CM) / (AM * AH + BM * BH + CM * CH);

            ALtangent = CoefficientL * AL - AM;
            BLtangent = CoefficientL * BL - BM;
            CLtangent = CoefficientL * CL - CM;
            AHtangent = CoefficientH * AH - AM;
            BHtangent = CoefficientH * BH - BM;
            CHtangent = CoefficientH * CH - CM;

            AngleCos = (AHtangent * ALtangent + BHtangent * BLtangent + CHtangent * CLtangent) /
                    (Math.sqrt(AHtangent * AHtangent + BHtangent * BHtangent + CHtangent * CHtangent) *
                            Math.sqrt(ALtangent * ALtangent + BLtangent * BLtangent + CLtangent * CLtangent));

            AngleCos = Math.acos(AngleCos);

            ANormalLine = BHtangent * CLtangent - CHtangent * BLtangent;
            BNormalLine = 0 - (AHtangent * CLtangent - CHtangent * ALtangent);
            CNormalLine = AHtangent * BLtangent - BHtangent * ALtangent;

            if (AM != 0) {
                OrientationValue = ANormalLine / AM;
            } else if (BM != 0) {
                OrientationValue = BNormalLine / BM;
            } else {
                OrientationValue = CNormalLine / CM;
            }

            if (OrientationValue > 0) {
                Sum1 += AngleCos;
                Count1++;
            } else {
                Sum2 += AngleCos;
                Count2++;
                //Sum +=2*Math.PI-AngleCos;
            }
        }

        if (Sum1 > Sum2) {
            Sum = Sum1 + (2 * Math.PI * Count2 - Sum2);
        } else {
            Sum = (2 * Math.PI * Count1 - Sum1) + Sum2;
        }

        // 平方米
        mtotalArea = (Sum - (Count - 2) * Math.PI) * Radius * Radius;

        return mtotalArea;
    }

    // 计算多边形周长
    private double calculateGirth(ArrayList<GeoPoint> geoPointArrayList) {
        if(geoPointArrayList.size() < 2) {
            return 0.0f;
        }

        double girth = 0.0f;

        for(int i = 1; i < geoPointArrayList.size(); i ++) {
            GeoPoint p1 = geoPointArrayList.get(i - 1);
            GeoPoint p2 = geoPointArrayList.get(i);

            float[] results = new float[1];
            Location.distanceBetween(GeoPointEx.getdY(p1), GeoPointEx.getdX(p1), GeoPointEx.getdY(p2), GeoPointEx.getdX(p2), results);

            girth = girth + results[0];
        }

        if(geoPointArrayList.size() > 2) { // 再加上起点到终端的距离
            GeoPoint p1 = geoPointArrayList.get(0);
            GeoPoint p2 = geoPointArrayList.get(geoPointArrayList.size() - 1);

            float[] results = new float[1];
            Location.distanceBetween(GeoPointEx.getdY(p1), GeoPointEx.getdX(p1), GeoPointEx.getdY(p2), GeoPointEx.getdX(p2), results);

            girth = girth + results[0];
        }

        return girth;
    }

    // 计算多边形的中心点
    private GeoPoint calculateCenter(ArrayList<GeoPoint> geoPointArrayList) {
        int total = geoPointArrayList.size();

        double X = 0.0;
        double Y = 0.0;
        double Z = 0.0;

        for(int index = 0; index < total; index ++) {
            GeoPoint geoPoint = geoPointArrayList.get(index);

            double lat = GeoPointEx.getdY(geoPoint) * Math.PI / 180;
            double lng = GeoPointEx.getdX(geoPoint) * Math.PI / 180;

            double x = Math.cos(lat) * Math.cos(lng);
            double y = Math.cos(lat) * Math.sin(lng);
            double z = Math.sin(lat);

            X = X + x;
            Y = Y + y;
            Z = Z + z;
        }

        X = X / total;
        Y = Y / total;
        Z = Z / total;

        double Lng = Math.atan2(Y, X);
        double Hyp = Math.sqrt(X * X + Y * Y);
        double Lat = Math.atan2(Z, Hyp);

        return GeoPointEx.Double2GeoPoint(Lng * 180 / Math.PI, Lat * 180 / Math.PI);
    }

    public void add(String strArea, String strGirth) {
        if(strArea == null || strArea.isEmpty()) {
            ArrayList<String> al1 = new ArrayList<>();
            al1.add("0");
            al1.add("");
            mAreas.push(al1);
        } else {
            // 生成面积标签
            final int ONE_MILLION = 1000000;

            String strAreaLab = "";

            double area = Double.valueOf(strArea);

            if((int)area < ONE_MILLION) {
                BigDecimal areaLab = new BigDecimal(strArea);
                strAreaLab = areaLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "平方米";
            } else {
                BigDecimal areaLab = new BigDecimal(String.valueOf(area / ONE_MILLION));
                strAreaLab = areaLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "平方公里";
            }

            ArrayList<String> al1 = new ArrayList<>();
            al1.add(strArea);
            al1.add(strAreaLab);
            mAreas.push(al1);
        }

        if(strGirth == null || strGirth.isEmpty()) {
            ArrayList<String> al2 = new ArrayList<>();
            al2.add("0");
            al2.add("");
            mGirths.push(al2);
        } else {
            // 生成周长标签
            final int ONE_THOUSAND = 1000;

            String strGirthLab = "";

            double girth = Double.valueOf(strGirth);

            if((int)girth < ONE_THOUSAND) {
                BigDecimal girthLab = new BigDecimal(strGirth);
                strGirthLab = girthLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "米";
            } else {
                BigDecimal girthLab = new BigDecimal(String.valueOf(girth / ONE_THOUSAND));
                strGirthLab = girthLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "公里";
            }

            ArrayList<String> al2 = new ArrayList<>();
            al2.add(strGirth);
            al2.add(strGirthLab);
            mGirths.push(al2);
        }
    }

    // 单击事件
    @Override
    public boolean onTap(GeoPoint geoPoint, MapView mapView) {
        if (!isEditStatus()) {
            return true;
        }

        addPoint(geoPoint);

        String strArea = "";
        String strGirth = "";

        if(mGeoPointArrayList.size() < 2) {
            strArea = "";
            strGirth = "";
        } else if(mGeoPointArrayList.size() == 2) {
            strArea = "";

            // 计算周长
            strGirth = String.valueOf(calculateGirth(mGeoPointArrayList));
        } else {
            // 计算面积
            strArea = String.valueOf(calculateArea(mGeoPointArrayList));

            // 计算周长
            strGirth = String.valueOf(calculateGirth(mGeoPointArrayList));
        }

        add(strArea, strGirth);

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

        // 画面
        render.drawPolygon(gl10, mMainActivity.getMainManager().getRenderOptionManager().getPlaneOption(), mGeoPointArrayList);

        String strLab; // 标注内容

        for (int i = 0; i < mGeoPointArrayList.size(); i++) {
            GeoPoint geoPoint = mGeoPointArrayList.get(i);
            Point point = mMainActivity.getMainManager().getMapManager().getMapView().getProjection().toPixels(geoPoint, null);

            // 画拐点
            render.drawRound(gl10, mMainActivity.getMainManager().getRenderOptionManager().getCircleOption(), point,
                    mMainActivity.getMainManager().getRenderOptionManager().getCircleRadius());

            if(i == 0) { // 起点
                strLab = "起点";
                strLab = strLab + mMainActivity.getMainManager().getRenderOptionManager().getFillChars();
                render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPoint);
            } else if (i == (mGeoPointArrayList.size() - 1)) { // 终点
                strLab = "终点";
                strLab = strLab + mMainActivity.getMainManager().getRenderOptionManager().getFillChars();
                render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPoint);
            } else{ // 拐点
//                strLab = mAreas.get(i).get(1) + "，" + mGirths.get(i).get(1);
            }
        }

        // 获取多边形中心点
        GeoPoint geoPointCenter = calculateCenter(mGeoPointArrayList);

        // 画面积和周长
        strLab = mAreas.get(mAreas.size() - 1).get(1) + "，" + mGirths.get(mGirths.size() - 1).get(1);
        strLab = strLab + mMainActivity.getMainManager().getRenderOptionManager().getFillChars();
        render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPointCenter);
    }
}
