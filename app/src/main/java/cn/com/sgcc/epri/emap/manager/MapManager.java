package cn.com.sgcc.epri.emap.manager;

import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.TOfflineMapManager;
import com.tianditu.maps.GeoPointEx;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.map.TMapView;
import cn.com.sgcc.epri.emap.map.TMyLocationOverlay;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.PhoneResources;
import cn.com.sgcc.epri.emap.base.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/28.
 */
public class MapManager extends MainActivityContext {
    private TMapView mTMapView; // 天地图地图控件
    private MapController mMapController; // 天地图控制器
    private TOfflineMapManager mTOfflineMapManager; // 离线地图管理类
    private TMyLocationOverlay mTMyLocationOverlay; // 位置覆盖物
    private GeoPoint mCurrentGeoPoint; // 当前所在经纬度
    private int mMaxZoomLevel; // 当前地图支持的最大比例尺
    private int mMinZoomLevel; // 当前地图支持的最小比例尺
    private int mCurrentZoomLevel; // 当前地图的缩放级别

    public MapManager(MainActivity mainActivity) {
        super(mainActivity);
        mTMapView = (TMapView) mainActivity.findViewById(R.id.map_view);
        mMapController = this.mTMapView.getController();
        mTOfflineMapManager = new TOfflineMapManager(mainActivity, mTMapView);
        mTMyLocationOverlay = new TMyLocationOverlay(mainActivity, mTMapView);
        mMaxZoomLevel = mTMapView.getMaxZoomLevel();
        mMinZoomLevel = mTMapView.getMinZoomLevel();
        mCurrentZoomLevel = mTMapView.getZoomLevel();

        mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mInfo,
                String.format("ZOOM:%d, MIN:%d, MAX:%d", mCurrentZoomLevel, mMinZoomLevel, mMaxZoomLevel));
    }

    // 初始化天地图
    public void init() {
        String cachePath = PhoneResources.getMapCachePath(mMainActivity); // 地图缓存路径
        String offlinePath = PhoneResources.getOfflineMapPath(mMainActivity); // 离线地图存储路径

        // 设置地图缓冲区路径
        mTMapView.setCachePath(cachePath);

        // 设置离线地图数据信息，用于地图显示加载
        // 离线地图设置之后会在程序显示时默认加载
        mTOfflineMapManager.setMapPath(offlinePath);
        mTOfflineMapManager.getMapList(); // 获取所有离线地图列表
        mTMapView.setOfflineMaps(mTOfflineMapManager.searchLocalMaps());

        enableTMyLocationOverlay(); // 启用我的位置

        // 设置LOGO位置为右下角
        mTMapView.setLogoPos(TMapView.LOGO_RIGHT_BOTTOM);

        mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mInfo,
                String.format("地图缓存路径" + mTMapView.getCachePath() + " " + "离线地图路径:" + mTOfflineMapManager.getMapPath()));
    }

    // 启用我的位置
    public void enableTMyLocationOverlay () {
        mTMyLocationOverlay.enableMyLocation(); // 启用我的位置
        mTMyLocationOverlay.setGpsFollow(true); // 设置跟随状态
        mTMapView.addOverlay(mTMyLocationOverlay);
    }

    // 禁用我的位置
    public void disableTMyLocationOverlay() {
        mTMyLocationOverlay.disableMyLocation(); // 禁用我的位置
        mTMapView.removeOverlay(mTMyLocationOverlay);
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

    // 更新当前经纬度
    public void updateCurrentGeoPoint() {
        if(mCurrentGeoPoint == null) {
            mCurrentGeoPoint = mTMapView.getMapCenter();
        } else {
            mCurrentGeoPoint = mTMyLocationOverlay.getMyLocation();
        }

        if(mCurrentGeoPoint == null) {
            // 默认设置中心点为天安门
            mCurrentGeoPoint = GeoPointEx.Double2GeoPoint(116.3919236741D, 39.9057789520D);
        }

        if(mCurrentGeoPoint != null) {
            mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mInfo, "当前位置:" + mCurrentGeoPoint.toString());
        } else {
            mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError, "当前位置为空！");
        }
    }

    // 设置中心点
    public void setCenter() {
        updateCurrentGeoPoint();

        if(mCurrentGeoPoint != null) {
            mMapController.setCenter(mCurrentGeoPoint);
        }

        // 取消地图旋转，恢复正常状态
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
