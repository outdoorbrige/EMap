package com.gh.emap.managerA;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;
import com.tianditu.android.maps.Overlay;
import com.tianditu.android.maps.TErrorCode;
import com.tianditu.android.maps.TGeoAddress;
import com.tianditu.android.maps.TGeoDecode;
import com.tianditu.android.maps.TOfflineMapManager;
import com.tianditu.maps.GeoPointEx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuHeng on 2016/11/9.
 * 地图管理类
 */
public class MapManager {
    private MainActivity mMainActivity;
    private MapView mMapView;
    private MapController mMapController;
    private TOfflineMapManager mTOfflineMapManager;

    private ArrayList<TOfflineMapManager.MapAdminSet> mMapAdminSetResult; // 离线地图列表
    private int mMapAdminSetResultCode = TErrorCode.ERROR; // 获取离线地图列表结果码

    private TGeoDecode mTGeoDecode; // 逆地理编码类，根据输入的点坐标，返回相应的地理信息内容
    private TGeoAddress mTGeoAddress; // 地理信息
    private int mTGeoAddressCode = TErrorCode.ERROR; // 获取地理信息结果码

    private MyLocationOverlay mMyLocationOverlay;
    private GeoPoint mGeoPoint;
    private int mMaxZoomLevel; // 当前地图支持的最大比例尺
    private int mMinZoomLevel; // 当前地图支持的最小比例尺
    private int mCurrentZoomLevel; // 当前地图的缩放级别

    public MapManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mMapView = (MapView)mMainActivity.findViewById(R.id.map_view);
        mMapController = mMapView.getController();
        mTOfflineMapManager = new TOfflineMapManager(mMainActivity, mMainActivity.getMainManager().getListenerManager().getMapListener());
        mTGeoDecode = new TGeoDecode(mMainActivity.getMainManager().getListenerManager().getMapListener());
        mMyLocationOverlay = new MyLocationOverlay(mMainActivity, mMapView);
        mMaxZoomLevel = mMapView.getMaxZoomLevel();
        mMinZoomLevel = mMapView.getMinZoomLevel();
        mCurrentZoomLevel = mMapView.getZoomLevel();

        getMapList();

        mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo,
                String.format("ZOOM:%d, MIN:%d, MAX:%d", mCurrentZoomLevel, mMinZoomLevel, mMaxZoomLevel));
    }

    public void init() {
//        String cachePath = getCachePath(); // 地图缓存路径
//        String offlinePath = getOfflinePath(); // 离线地图存储路径
//
//        // 设置地图缓冲区路径
//        mMapView.setCachePath(cachePath);
//
//        // 设置离线地图文件存放的根目录，如果目录不存在，则创建。
//        mTOfflineMapManager.setMapPath(offlinePath);
//        mTOfflineMapManager.getMapList(); // 获取所有离线地图列表

        enableTMyLocationOverlay(); // 启用我的位置

        // 设置LOGO位置为右下角
        mMapView.setLogoPos(MapView.LOGO_RIGHT_BOTTOM);

        // 设置默认地图类型
        mMapView.setMapType(MapView.TMapType.MAP_TYPE_VEC);

        // 设置在缩放动画过程中绘制overlay，默认为不绘制
        //mMapView.setDrawOverlayWhenZooming(true);

        mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo,
                String.format("离线地图路径：" + mTOfflineMapManager.getMapPath() + " " + "地图缓存路径：" + mMapView.getCachePath()));
    }

    public void unInit() {

    }

//    private String getCachePath() {
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            return Environment.getExternalStorageDirectory().toString() + File.separator +
//                    mMainActivity.getApplationName() + File.separator +
//                    "MapCache" + File.separator;
//        } else {
//            return null;
//        }
//    }
//
//    private String getOfflinePath() {
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            return Environment.getExternalStorageDirectory().toString() + File.separator +
//                    mMainActivity.getApplationName() + File.separator +
//                    "OfflineMap" + File.separator;
//        } else {
//            return null;
//        }
//    }

    // 启用我的位置
    public void enableTMyLocationOverlay () {
        mMyLocationOverlay.enableMyLocation(); // 启用我的位置
        mMyLocationOverlay.setGpsFollow(true); // 设置跟随状态
        mMapView.addOverlay(mMyLocationOverlay);
    }

    // 禁用我的位置
    public void disableTMyLocationOverlay() {
        mMyLocationOverlay.disableMyLocation(); // 禁用我的位置
        mMapView.removeOverlay(mMyLocationOverlay);
    }

    // 获取天地图地图控件
    public MapView getMapView() {
        return mMapView;
    }

    // 获取天地图离线地图管理类
    public TOfflineMapManager getTOfflineMapManager() {
        return mTOfflineMapManager;
    }

    // 获得天地图支持的所有离线地图列表 此操作为异步，得到的结果通过构造接口传入的对象返回
    public void getMapList() {
        mMapAdminSetResult = null;
        mMapAdminSetResultCode = TErrorCode.ERROR;

        mTOfflineMapManager.getMapList();
    }

    // 获取城市列表
    public ArrayList<TOfflineMapManager.MapAdminSet> getMapAdminSetResult() {
        return mMapAdminSetResult;
    }

    // 设置城市列表
    public void setMapAdminSetResult(ArrayList<TOfflineMapManager.MapAdminSet> mapAdminSetResult) {
        mMapAdminSetResult = mapAdminSetResult;
    }

    // 获取城市列表结果码
    public int getMapAdminSetResultCode() {
        return mMapAdminSetResultCode;
    }

    // 设置获取城市列表结果码
    public void setMapAdminSetResultCode(int error) {
        mMapAdminSetResultCode = error;
    }

    // 判断获取城市列表是否成功
    public boolean isMapAdminSetResultOk() {
        if(mMapAdminSetResultCode != TErrorCode.OK) {
            return false;
        }

        return true;
    }

    // 搜索指定点的系相关地理信息，此方法为异步操作
    public void search(GeoPoint geoPoint) {
        mTGeoAddress = null;
        mTGeoAddressCode = TErrorCode.ERROR;

        mTGeoDecode.search(geoPoint);
    }

    // 获取地理信息
    public TGeoAddress getTGeoAddress() {
        return mTGeoAddress;
    }

    // 设置地理信息
    public void setTGeoAddress(TGeoAddress tGeoAddress) {
        mTGeoAddress = tGeoAddress;
    }

    // 获取地理信息状态码
    public int getTGeoAddressCode() {
        return mTGeoAddressCode;
    }

    // 设置获取地理信息状态码
    public void setTGeoAddressCode(int error) {
        mTGeoAddressCode = error;
    }

    // 判断获取地理信息是否成功
    public boolean isTGeoAddressOk() {
        if(mTGeoAddressCode != TErrorCode.OK) {
            return false;
        }

        return true;
    }

    // 获取天地图控制器
    public MapController getMapController() {
        return mMapController;
    }

    // 获取我的位置
    public MyLocationOverlay getMyLocationOverlay() {
        return mMyLocationOverlay;
    }

    // 更新当前经纬度
    public void updateCurrentGeoPoint() {
        if(mGeoPoint == null) {
            mGeoPoint = mMapView.getMapCenter();
        } else {
            mGeoPoint = mMyLocationOverlay.getMyLocation();
        }

        if(mGeoPoint == null) {
            // 默认北京市天安门经纬度
            mGeoPoint = GeoPointEx.Double2GeoPoint(116.3919236741D, 39.9057789520D);
        }

        search(mGeoPoint);

        showPositionInfo(mGeoPoint);
    }

    // 显示纬度、经度、高程信息
    public void showPositionInfo(GeoPoint geoPoint) {
        if(mGeoPoint == null && geoPoint == null) {
            return;
        }

        String latitude = "";
        String longitude = "";
        String elevation = "";

        if(mGeoPoint == null) {
            mGeoPoint = geoPoint;
        }

        latitude = String.valueOf(GeoPointEx.getdY(mGeoPoint));
        longitude = String.valueOf(GeoPointEx.getdX(mGeoPoint));

        mMainActivity.getMainManager().getLayoutManager().getBottomLocationInfoLayout().setLatitude(latitude);
        mMainActivity.getMainManager().getLayoutManager().getBottomLocationInfoLayout().setLongitude(longitude);
        mMainActivity.getMainManager().getLayoutManager().getBottomLocationInfoLayout().setElevation(elevation);
    }

    // 设置中心点
    public void setCenter() {
        updateCurrentGeoPoint();

        if(mGeoPoint != null) {
            mMapController.setCenter(mGeoPoint);
        }

        // 恢复初始缩放级别
        mMapController.setZoom(mCurrentZoomLevel);

        // 取消地图旋转，恢复正常状态
        mMapView.setMapRotation(0.00f);
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

    // 获取最后一个覆盖物
    public Overlay getLastOverlay() {
        List<Overlay> overlays = mMapView.getOverlays();
        if(overlays == null || overlays.isEmpty()) {
            return null;
        }

        Overlay overlay = overlays.get(overlays.size() - 1);
        return overlay;
    }
}
