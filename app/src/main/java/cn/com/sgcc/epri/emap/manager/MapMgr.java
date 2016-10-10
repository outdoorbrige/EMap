package cn.com.sgcc.epri.emap.manager;

import android.os.Handler;

import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.TOfflineMapManager;

import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.OfflineMapListener;
import cn.com.sgcc.epri.emap.map.TMapView;
import cn.com.sgcc.epri.emap.map.TMyLocationOverlay;
import cn.com.sgcc.epri.emap.util.PhoneResources;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/28.
 */
public class MapMgr extends TransmitContext {
    private Logger logger; // 日志对象
    private TMapView map_view; // 天地图地图控件
    private MapController controller; // 天地图控制器
    private TOfflineMapManager offline_map_mgr; // 离线地图管理类
    private TMyLocationOverlay location_overlay; // 位置覆盖物
    private int max_zoom_level; // 当前地图支持的最大比例尺
    private int min_zoom_level; // 当前地图支持的最小比例尺
    private int now_zoom_level; // 当前地图的缩放级别

    public MapMgr(MainActivity context) {
        super(context);
        logger = Logger.getLogger(this.getClass());
        map_view = (TMapView) context.findViewById(R.id.emap_view);
        controller = this.map_view.getController();
        offline_map_mgr = new TOfflineMapManager(new OfflineMapListener());
        location_overlay = new TMyLocationOverlay(context, map_view);
        max_zoom_level = map_view.getMaxZoomLevel();
        min_zoom_level = map_view.getMinZoomLevel();
        now_zoom_level = map_view.getZoomLevel();

        logger.info(String.format("ZOOM:%d, MIN:%d, MAX:%d", now_zoom_level, min_zoom_level, max_zoom_level));
    }

    // 初始化天地图
    public void init() {
        // 设置地图缓冲区路径
        map_view.setCachePath(PhoneResources.getMapCachePath(context));

        // 设置离线地图数据信息，用于地图显示加载
        // 离线地图设置之后会在程序显示时默认加载
        offline_map_mgr.setMapPath(PhoneResources.getOfflineMapPath(context));

        map_view.setOfflineMaps(offline_map_mgr.searchLocalMaps());
    }

    // 启用相关功能
    public void enableFeatures() {
        // 启用我的位置
        if(location_overlay.enableMyLocation()) // 启动成功
        {
            map_view.getOverlays().add(location_overlay);
            map_view.postInvalidate();
        } else { // 启动失败
            logger.error(String.format("启动我的位置失败!"));
        }
    }
    // 停用相关功能
    public void disableFeatures() {
        if(location_overlay.isMyLocationEnabled()) {
            location_overlay.disableMyLocation();
        }

        if(location_overlay.isCompassEnabled()) {
            location_overlay.disableCompass();
        }
    }

    // 获取天地图地图控件
    public TMapView getTMapView() {
        return map_view;
    }

    // 获取天地图控制器
    public MapController getController() {
        return controller;
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
        map_view.requestLayout();
        map_view.postInvalidate();
    }

    // 设置地图中心点
    public void setCenter() {
        if(location_overlay.getMyLocation() != null) {
            controller.setCenter(location_overlay.getMyLocation());
            invalidate();
        } else {
            logger.error(String.format("获取当前位置信息失败！"));
        }
    }

    // 放大地图
    public boolean setZoomin() {
        boolean zoomin = true;

        ++ now_zoom_level;
        if(now_zoom_level == max_zoom_level) {
            zoomin = false;
        } else {
            zoomin = true;
        }
        controller.zoomIn();

        return zoomin;
    }

    // 缩小地图
    public boolean setZoomout() {
        boolean zoomout = true;

        --now_zoom_level;
        if(now_zoom_level == min_zoom_level) {
            zoomout = false;
        } else {
            zoomout = true;
        }
        controller.zoomOut();

        return zoomout;
    }
}
