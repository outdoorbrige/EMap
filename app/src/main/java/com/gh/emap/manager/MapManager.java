package com.gh.emap.manager;

import android.content.Context;
import android.os.Environment;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;
import com.tianditu.android.maps.TOfflineMapManager;
import com.tianditu.maps.GeoPointEx;

import java.io.File;

/**
 * Created by GuHeng on 2016/11/9.
 * 地图管理类
 */
public class MapManager {
    private Context mContext;
    private MapView mMapView;
    private MapController mMapController;
    private TOfflineMapManager mTOfflineMapManager;
    private MyLocationOverlay mMyLocationOverlay;
    private GeoPoint mGeoPoint;
    private int mMaxZoomLevel; // 当前地图支持的最大比例尺
    private int mMinZoomLevel; // 当前地图支持的最小比例尺
    private int mCurrentZoomLevel; // 当前地图的缩放级别

    public MapManager(Context context) {
        this.mContext = context;
        this.mMapView = (MapView)((MainActivity)this.mContext).findViewById(R.id.map_view);
        this.mMapController = this.mMapView.getController();
        this.mTOfflineMapManager = new TOfflineMapManager(this.mContext, null);
        this.mMyLocationOverlay = new MyLocationOverlay(this.mContext, this.mMapView);
        this.mMaxZoomLevel = this.mMapView.getMaxZoomLevel();
        this.mMinZoomLevel = this.mMapView.getMinZoomLevel();
        this.mCurrentZoomLevel = this.mMapView.getZoomLevel();

        ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
                String.format("ZOOM:%d, MIN:%d, MAX:%d", mCurrentZoomLevel, mMinZoomLevel, mMaxZoomLevel));
    }

    public void init() {
//        String cachePath = getCachePath(); // 地图缓存路径
//        String offlinePath = getOfflinePath(); // 离线地图存储路径

        // 设置地图缓冲区路径
//        this.mMapView.setCachePath(cachePath);

        // 设置离线地图数据信息，用于地图显示加载
        // 离线地图设置之后会在程序显示时默认加载
//        this.mTOfflineMapManager.setMapPath(offlinePath);
//        this.mTOfflineMapManager.getMapList(); // 获取所有离线地图列表
//        this.mMapView.setOfflineMaps(this.mTOfflineMapManager.searchLocalMaps());

        enableTMyLocationOverlay(); // 启用我的位置

        // 设置LOGO位置为右下角
        this.mMapView.setLogoPos(MapView.LOGO_RIGHT_BOTTOM);

        // 设置在缩放动画过程中绘制overlay，默认为不绘制
        //this.mMapView.setDrawOverlayWhenZooming(true);

//        // 设置覆盖物监听器
//        this.mMapView.setOverlayListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getMyOverlayListener());

//        ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
//                String.format("地图缓存路径" + this.mMapView.getCachePath() + " " + "离线地图路径:" + this.mTOfflineMapManager.getMapPath()));
    }

//    private String getCachePath() {
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            return Environment.getExternalStorageDirectory().toString() + File.separator +
//                    ((MainActivity)this.mContext).getApplationName() + File.separator +
//                    "MapCache" + File.separator;
//        } else {
//            return null;
//        }
//    }
//
//    private String getOfflinePath() {
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            return Environment.getExternalStorageDirectory().toString() + File.separator +
//                    ((MainActivity)this.mContext).getApplationName() + File.separator +
//                    "OfflineMap" + File.separator;
//        } else {
//            return null;
//        }
//    }

    // 启用我的位置
    public void enableTMyLocationOverlay () {
        this.mMyLocationOverlay.enableMyLocation(); // 启用我的位置
        this.mMyLocationOverlay.setGpsFollow(true); // 设置跟随状态
        this.mMapView.addOverlay(this.mMyLocationOverlay);
    }

    // 禁用我的位置
    public void disableTMyLocationOverlay() {
        this.mMyLocationOverlay.disableMyLocation(); // 禁用我的位置
        this.mMapView.removeOverlay(this.mMyLocationOverlay);
    }

    // 获取天地图地图控件
    public MapView getMapView() {
        return this.mMapView;
    }

    // 获取天地图控制器
    public MapController getMapController() {
        return mMapController;
    }

    // 获取我的位置
    public MyLocationOverlay getMyLocationOverlay() {
        return this.mMyLocationOverlay;
    }

    // 更新当前经纬度
    public void updateCurrentGeoPoint() {
        if(this.mGeoPoint == null) {
            this.mGeoPoint = this.mMapView.getMapCenter();
        } else {
            this.mGeoPoint = this.mMyLocationOverlay.getMyLocation();
        }

        if(this.mGeoPoint == null) {
            // 默认天安门
            this.mGeoPoint = GeoPointEx.Double2GeoPoint(116.3919236741D, 39.9057789520D);
        }

        showPositionInfo(this.mGeoPoint);
    }

    // 显示纬度、经度、高程信息
    public void showPositionInfo(GeoPoint geoPoint) {

        String latitude = "";
        String longitude = "";
        String elevation = "";

        if(this.mGeoPoint != null) {
            String[] strings = toSexagesimalString(this.mGeoPoint.getLatitudeE6(), this.mGeoPoint.getLongitudeE6());
            latitude = strings[0];
            longitude = strings[1];
        }

        ((TextView)((MainActivity)this.mContext).findViewById(R.id.latitude)).setText(latitude);
        ((TextView)((MainActivity)this.mContext).findViewById(R.id.longitude)).setText(longitude);
        ((TextView)((MainActivity)this.mContext).findViewById(R.id.elevation)).setText(elevation);
    }

    // 设置中心点
    public void setCenter() {
        updateCurrentGeoPoint();

        if(this.mGeoPoint != null) {
            mMapController.setCenter(this.mGeoPoint);
        }

        // 恢复初始缩放级别
        this.mMapController.setZoom(this.mCurrentZoomLevel);

        // 取消地图旋转，恢复正常状态
        this.mMapView.setMapRotation(0.00f);
    }

    // 放大地图
    public boolean zoomIn() {
        boolean zoomIn = true;

        ++mCurrentZoomLevel;
        if(mCurrentZoomLevel == mMaxZoomLevel) {
            zoomIn = false;
        } else {
            zoomIn = true;
        }
        mMapController.zoomIn();

        return zoomIn;
    }

    // 缩小地图
    public boolean zoomOut() {
        boolean zoomOut = true;

        --mCurrentZoomLevel;
        if(mCurrentZoomLevel == mMinZoomLevel) {
            zoomOut = false;
        } else {
            zoomOut = true;
        }
        mMapController.zoomOut();

        return zoomOut;
    }

    /*
    1.怎么把经纬度十进制单位转换成标准的度分秒单位计算公式是，
    十进制的经度，纬度数的整数部分就是度数(°)，小数部分乘以60得到的数取整数部分就是分数(′)，再用该数的小数部分乘以60就是秒数(″)。
    如一个经度的十进制为:117.121806，那么:
    第一步：度数(°)117°，
    第二步：分数(′)7′(0.121806×60=7.308360189199448,取整数部分为7),
    第三步:秒数(″)18.501611351966858″(0.30836018919944763×60=18.501611351966858)，即度分秒为117°7′18.501611351966858″。

    2.怎么把经纬度度分秒单位转换成十进制单位
    将度分秒转换为十进制则刚好相反，将秒数(″)除以60，得到的数就是分数(′)的小数部分，将该小数加上分数(′)整数部
    分就是整个分数(′)，再将该分数(′)除以60，得到的小数就是度数(°)的小数部分，在加上度数的整数部分就是经纬度的十进制形式。
    例如，将一个纬度为37°25′19.222″的六十进制转换为十进制的步骤为：
    第一步(对应上面的第三步):19.222/60=0.3203666666666667,0.3203666666666667为分数(′)的小数部分，
    第二步(对应上面的第二步):25+0.3203666666666667=25.3203666666666667，25.3203666666666667分数(′)
    第三步(对应上面的第一步):25.3203666666666667/60=0.4220061111111111，0.4220061111111111为度数(°)的小数部分
    37°25′19.222″转换的最终结果为37+0.4220061111111111=37.4220061111111111
     */

    // 把经纬度十进制单位转换成标准的度分秒单位
    public static String[] toSexagesimalString(int latitude, int longitude) {
        String prefix1 = "";
        String prefix2 = "";
        String[] strings = new String[2];

        if(latitude > 0) { // 北纬
            prefix1 = "北纬N";
        } else { // 南纬
            prefix1 = "南纬S";
        }
        strings[0] = prefix1 + toSexagesimalString(latitude);

        if(longitude > 0) { // 东经
            prefix2 = "东经E";
        } else { // 西经
            prefix2 = "西经W";
        }
        strings[1] = prefix2 + toSexagesimalString(longitude);

        return strings;
    }

    // 把经纬度十进制单位转换成标准的度分秒单位
    public static String toSexagesimalString(int degree) {
        final double factor1 = 1000000.0D;
        final int factor2 = 60;

        degree = Math.abs(degree);

        double hDegree = degree / factor1;
        int h = (int)hDegree; // 度数

        double mDegree = (hDegree - h) * factor2;
        int m = (int)mDegree; // 分数

        double sDegree = (mDegree - m) * factor2;
        int s = (int)sDegree; // 秒数

        return String.format("%d°%d′%d″", h, m, s);
    }
}
