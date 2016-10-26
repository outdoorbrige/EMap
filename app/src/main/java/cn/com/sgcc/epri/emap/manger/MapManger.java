package cn.com.sgcc.epri.emap.manger;

import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.TOfflineMapManager;


import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.OfflineMapListener;
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
        mTOfflineMapManager = new TOfflineMapManager(new OfflineMapListener());
        mTMyLocationOverlay = new TMyLocationOverlay(mainActivity, mTMapView);
        mMaxZoomLevel = mTMapView.getMaxZoomLevel();
        mMinZoomLevel = mTMapView.getMinZoomLevel();
        mCurrentZoomLevel = mTMapView.getZoomLevel();

        mMainActivity.getLog4jManger().log(this.getClass(), Log4jLevel.mInfo,
                String.format("ZOOM:%d, MIN:%d, MAX:%d", mCurrentZoomLevel, mMinZoomLevel, mMaxZoomLevel));
    }

    // 初始化天地图
    public void init() {
        // 设置地图缓冲区路径
        mTMapView.setCachePath(PhoneResources.getMapCachePath(mMainActivity));

        // 设置离线地图数据信息，用于地图显示加载
        // 离线地图设置之后会在程序显示时默认加载
        mTOfflineMapManager.setMapPath(PhoneResources.getOfflineMapPath(mMainActivity));

        mTMapView.setOfflineMaps(mTOfflineMapManager.searchLocalMaps());

        // 设置LOGO位置为右下角
        mTMapView.setLogoPos(TMapView.LOGO_RIGHT_BOTTOM);
    }

    // 启用相关功能
    public void enableFeatures() {
        // 启用我的位置
        if(mTMyLocationOverlay.enableMyLocation()) // 启动成功
        {
            mTMapView.getOverlays().add(mTMyLocationOverlay);
            mTMapView.postInvalidate();
        } else { // 启动失败
            String message = "启用我的位置失败!";
            mMainActivity.getLog4jManger().show(message);
            mMainActivity.getLog4jManger().log(this.getClass(), Log4jLevel.mError, message);
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

    // 刷新地图
    public void invalidate() {
        /**
         * View绘制分三个步骤，顺序是：onMeasure，onLayout，onDraw。
         * 经代码亲测，log输出显示：
         *      调用invalidate方法只会执行onDraw方法；
         *      调用requestLayout方法只会执行onMeasure方法和onLayout方法，并不会执行onDraw方法。
         *所以当我们进行View更新时，若仅View的显示内容发生改变且新显示内容不影响View的大小、位置，则只需调用invalidate方法；
         * 若View宽高、位置发生改变且显示内容不变，只需调用requestLayout方法；
         * 若两者均发生改变，则需调用两者，按照View的绘制流程，推荐先调用requestLayout方法再调用invalidate方法。
         *
         * 注意：
         *      invalidate方法只能用于UI线程中，
         *      在非UI线程中，可直接使用postInvalidate方法，这样就省去使用handler的烦恼
         */
        mTMapView.requestLayout();
        mTMapView.postInvalidate();
    }

    // 设置地图中心点
    public void setCenter() {
        if(mTMyLocationOverlay.getMyLocation() != null) {
            mMapController.setCenter(mTMyLocationOverlay.getMyLocation());
            invalidate();
        } else {
            String message = "获取当前位置信息失败！";
            mMainActivity.getLog4jManger().show(message);
            mMainActivity.getLog4jManger().log(this.getClass(), Log4jLevel.mError, message);
        }
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
