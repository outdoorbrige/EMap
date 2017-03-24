package com.gh.emap.overlayA;

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
 * Created by GuHeng on 2017/3/2.
 */

public class AreaGirthOverlay extends Overlay {
    private MainActivity mMainActivity;
    private ArrayList<GeoPoint> mGeoPoints = new ArrayList<>();
    private boolean mEditStatus; // 可编辑状态

    public AreaGirthOverlay(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mEditStatus = true;
    }

    public void setGeoPoints(ArrayList<GeoPoint> geoPoints) {
        mGeoPoints = geoPoints;
    }

    public ArrayList<GeoPoint> getGeoPoints() {
        return mGeoPoints;
    }

    public boolean addGeoPoint(GeoPoint geoPoint) {
        return mGeoPoints.add(geoPoint);
    }

    public boolean addGeoPoints(ArrayList<GeoPoint> geoPoints) {
        return mGeoPoints.addAll(geoPoints);
    }

    // 获取面积带单位(四舍五入3位小数)
    public String getArea(ArrayList<GeoPoint> geoPoints) {
        if(geoPoints.size() < 3){
            return null;
        }

        // 生成面积标签

        final int ONE_MILLION = 1000000;

        // 计算面积
        double dArea = calculateArea(geoPoints);
        String strArea = String.valueOf(dArea);
        String strAreaLab = null;

        if((int)dArea < ONE_MILLION) {
            BigDecimal areaLab = new BigDecimal(strArea);
            strAreaLab = areaLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "平方米";
        } else {
            BigDecimal areaLab = new BigDecimal(String.valueOf(dArea / ONE_MILLION));
            strAreaLab = areaLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "平方公里";
        }

        return strAreaLab;
    }

    // 获取周长带单位(四舍五入3位小数)
    public String getGirth(ArrayList<GeoPoint> geoPoints) {
        if(geoPoints.size() < 2){
            return null;
        }

        // 生成周长标签

        final int ONE_THOUSAND = 1000;

        // 计算周长
        double dGirth = calculateGirth(geoPoints);
        String strGirth = String.valueOf(dGirth);
        String strGirthLab = "";

        if((int)dGirth < ONE_THOUSAND) {
            BigDecimal girthLab = new BigDecimal(strGirth);
            strGirthLab = girthLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "米";
        } else {
            BigDecimal girthLab = new BigDecimal(String.valueOf(dGirth / ONE_THOUSAND));
            strGirthLab = girthLab.setScale(3, BigDecimal.ROUND_HALF_UP).toString() + "公里";
        }

        return strGirthLab;
    }

    public void setEditStatus(boolean status) {
        mEditStatus = status;
    }

    public boolean isEditStatus() {
        return mEditStatus;
    }

    // 计算多边形面积
    private double calculateArea(ArrayList<GeoPoint> geoPoints) {
        int Count = geoPoints.size();
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
                LowX =(double) GeoPointEx.getdY(geoPoints.get(Count - 1)) * Math.PI / 180;
                LowY = (double)GeoPointEx.getdX(geoPoints.get(Count - 1)) * Math.PI / 180;
                MiddleX = (double)GeoPointEx.getdY(geoPoints.get(0)) * Math.PI / 180;
                MiddleY = (double)GeoPointEx.getdX(geoPoints.get(0)) * Math.PI / 180;
                HighX = (double)GeoPointEx.getdY(geoPoints.get(1)) * Math.PI / 180;
                HighY = (double)GeoPointEx.getdX(geoPoints.get(1)) * Math.PI / 180;
            } else if (i == Count - 1) {
                LowX = (double)GeoPointEx.getdY(geoPoints.get(Count - 2)) * Math.PI / 180;
                LowY = (double)GeoPointEx.getdX(geoPoints.get(Count - 2)) * Math.PI / 180;
                MiddleX = (double)GeoPointEx.getdY(geoPoints.get(Count - 1)) * Math.PI / 180;
                MiddleY = (double)GeoPointEx.getdX(geoPoints.get(Count - 1)) * Math.PI / 180;
                HighX = (double)GeoPointEx.getdY(geoPoints.get(0)) * Math.PI / 180;
                HighY = (double)GeoPointEx.getdX(geoPoints.get(0)) * Math.PI / 180;
            } else {
                LowX = (double)GeoPointEx.getdY(geoPoints.get(i - 1)) * Math.PI / 180;
                LowY = (double)GeoPointEx.getdX(geoPoints.get(i - 1)) * Math.PI / 180;
                MiddleX = (double)GeoPointEx.getdY(geoPoints.get(i)) * Math.PI / 180;
                MiddleY = (double)GeoPointEx.getdX(geoPoints.get(i)) * Math.PI / 180;
                HighX = (double)GeoPointEx.getdY(geoPoints.get(i + 1)) * Math.PI / 180;
                HighY = (double)GeoPointEx.getdX(geoPoints.get(i + 1)) * Math.PI / 180;
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
    private double calculateGirth(ArrayList<GeoPoint> geoPoints) {
        if(geoPoints.size() < 2) {
            return 0.0f;
        }

        double girth = 0.0f;

        for(int i = 1; i < geoPoints.size(); i ++) {
            GeoPoint p1 = geoPoints.get(i - 1);
            GeoPoint p2 = geoPoints.get(i);

            float[] results = new float[1];
            Location.distanceBetween(GeoPointEx.getdY(p1), GeoPointEx.getdX(p1), GeoPointEx.getdY(p2), GeoPointEx.getdX(p2), results);

            girth = girth + results[0];
        }

        if(geoPoints.size() > 2) { // 再加上起点到终端的距离
            GeoPoint p1 = geoPoints.get(0);
            GeoPoint p2 = geoPoints.get(geoPoints.size() - 1);

            float[] results = new float[1];
            Location.distanceBetween(GeoPointEx.getdY(p1), GeoPointEx.getdX(p1), GeoPointEx.getdY(p2), GeoPointEx.getdX(p2), results);

            girth = girth + results[0];
        }

        return girth;
    }

    // 计算多边形的中心点
    private GeoPoint calculateCenter(ArrayList<GeoPoint> geoPoints) {
        int total = geoPoints.size();

        double X = 0.0;
        double Y = 0.0;
        double Z = 0.0;

        for(int index = 0; index < total; index ++) {
            GeoPoint geoPoint = geoPoints.get(index);

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

        MapViewRender render = mapView.getMapViewRender();

        // 多边形
        render.drawPolygon(gl10, mMainActivity.getMainManager().getRenderOptionManager().getPlaneOption(), mGeoPoints);

        for (int i = 0; i < mGeoPoints.size(); i++) {
            GeoPoint geoPoint = mGeoPoints.get(i);
            Point point = mMainActivity.getMainManager().getMapManager().getMapView().getProjection().toPixels(geoPoint, null);

            // 拐点
            render.drawRound(gl10, mMainActivity.getMainManager().getRenderOptionManager().getCircleOption(), point,
                    mMainActivity.getMainManager().getRenderOptionManager().getCircleRadius());

            if(i == 0) { // 起点标注
                String strLab = "起点" + mMainActivity.getMainManager().getRenderOptionManager().getFillChars();
                render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPoint);
            } else if (i == (mGeoPoints.size() - 1)) { // 终点标注
                String strLab = "终点" + mMainActivity.getMainManager().getRenderOptionManager().getFillChars();
                render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPoint);
            }
        }

        // 面积与周长标注
        String strArea = getArea(mGeoPoints);
        String strGirth = getGirth(mGeoPoints);
        if(strArea != null && strGirth != null) {
            // 获取多边形中心点
            GeoPoint geoPointCenter = calculateCenter(mGeoPoints);

            String strLab = strArea + "，" + strGirth + mMainActivity.getMainManager().getRenderOptionManager().getFillChars();
            render.drawText(gl10, mMainActivity.getMainManager().getRenderOptionManager().getFontOption(), strLab, geoPointCenter);
        }
    }
}
