package cn.com.sgcc.epri.emap.manger;

import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.TOfflineMapManager;


import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.map.TMapView;
import cn.com.sgcc.epri.emap.map.TMyLocationOverlay;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.PhoneResources;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/28.
 */
public class MapManger extends MainActivityContext {
    private TMapView mTMapView; // 天地图地图控件
    private MapController mMapController; // 天地图控制器
    private TOfflineMapManager mTOfflineMapManager; // 离线地图管理类
    private TMyLocationOverlay mTMyLocationOverlay; // 位置覆盖物
    private int mMaxZoomLevel; // 当前地图支持的最大比例尺
    private int mMinZoomLevel; // 当前地图支持的最小比例尺
    private int mCurrentZoomLevel; // 当前地图的缩放级别

    public MapManger(MainActivity mainActivity) {
        super(mainActivity);
        mTMapView = (TMapView) mainActivity.findViewById(R.id.map_view);
        mMapController = this.mTMapView.getController();
        mTOfflineMapManager = new TOfflineMapManager(mainActivity, mTMapView);
        mTMyLocationOverlay = new TMyLocationOverlay(mainActivity, mTMapView);
        mTMyLocationOverlay.setGpsFollow(true); // 设置跟随状态
        mMaxZoomLevel = mTMapView.getMaxZoomLevel();
        mMinZoomLevel = mTMapView.getMinZoomLevel();
        mCurrentZoomLevel = mTMapView.getZoomLevel();

        mMainActivity.getLog4jManger().log(this.getClass(), Log4jLevel.mInfo,
                String.format("ZOOM:%d, MIN:%d, MAX:%d", mCurrentZoomLevel, mMinZoomLevel, mMaxZoomLevel));

        String cachePath = PhoneResources.getMapCachePath(mMainActivity); // 地图缓存路径
        String offlinePath = PhoneResources.getOfflineMapPath(mMainActivity); // 离线地图存储路径

        // 设置地图缓冲区路径
        mTMapView.setCachePath(cachePath);

        // 设置离线地图数据信息，用于地图显示加载
        // 离线地图设置之后会在程序显示时默认加载
        mTOfflineMapManager.setMapPath(offlinePath);
        mTOfflineMapManager.getMapList(); // 获取所有离线地图列表
        mTMapView.setOfflineMaps(mTOfflineMapManager.searchLocalMaps());

        // 设置LOGO位置为右下角
        mTMapView.setLogoPos(TMapView.LOGO_RIGHT_BOTTOM);

        mMainActivity.getLog4jManger().log(this.getClass(), Log4jLevel.mInfo,
                String.format("地图缓存路径" + cachePath + " " + "离线地图路径:" + offlinePath));
        mMainActivity.getLog4jManger().log(this.getClass(), Log4jLevel.mInfo,
                String.format("地图缓存路径" + mTMapView.getCachePath() + " " + "离线地图路径:" + mTOfflineMapManager.getMapPath()));
    }

    // 初始化天地图
    public void init() {
//        String cachePath = PhoneResources.getMapCachePath(mMainActivity); // 地图缓存路径
//        String offlinePath = PhoneResources.getOfflineMapPath(mMainActivity); // 离线地图存储路径
//
//        // 设置地图缓冲区路径
//        mTMapView.setCachePath(cachePath);
//
//        // 设置离线地图数据信息，用于地图显示加载
//        // 离线地图设置之后会在程序显示时默认加载
//        mTOfflineMapManager.setMapPath(offlinePath);
//        mTOfflineMapManager.getMapList(); // 获取所有离线地图列表
//        mTMapView.setOfflineMaps(mTOfflineMapManager.searchLocalMaps());
//
//        // 设置LOGO位置为右下角
//        mTMapView.setLogoPos(TMapView.LOGO_RIGHT_BOTTOM);
//
//        mMainActivity.getLog4jManger().log(this.getClass(), Log4jLevel.mInfo,
//                String.format("地图缓存路径" + cachePath + " " + "离线地图路径:" + offlinePath));
//        mMainActivity.getLog4jManger().log(this.getClass(), Log4jLevel.mInfo,
//                String.format("地图缓存路径" + mTMapView.getCachePath() + " " + "离线地图路径:" + mTOfflineMapManager.getMapPath()));
    }

    // 启用相关功能
    public void enableFeatures() {
        if(!mTMyLocationOverlay.isMyLocationEnabled()) {
            mTMyLocationOverlay.enableMyLocation(); // 启用我的位置
        }

        if(mTMyLocationOverlay.isCompassEnabled()) {
            mTMyLocationOverlay.disableCompass(); // 禁用指南针
        }

        if(mTMyLocationOverlay.isMyLocationEnabled()) {
            mTMapView.addOverlay(mTMyLocationOverlay);
        } else {
            mTMapView.removeOverlay(mTMyLocationOverlay);
        }
    }
    // 停用相关功能
    public void disableFeatures() {
        if(mTMyLocationOverlay.isMyLocationEnabled()) {
            mTMyLocationOverlay.disableMyLocation();
        }

        if(mTMyLocationOverlay.isCompassEnabled()) {
            mTMyLocationOverlay.disableCompass();
        }
    }

    // 获取天地图地图控件
    public TMapView getTMapView() {
        return mTMapView;
    }

    // 获取天地图控制器
    public MapController getMapController() {
        return mMapController;
    }

    // 获取我的位置
    public TMyLocationOverlay getTMyLocationOverlay() {
        return mTMyLocationOverlay;
    }

    // 设置中心点
    public void setCenter(GeoPoint geoPoint) {
        if(geoPoint == null) {
            geoPoint = mTMyLocationOverlay.getMyLocation();
        }
        mMapController.setCenter(geoPoint);
        mTMapView.setMapRotation(0.00f);
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
}
