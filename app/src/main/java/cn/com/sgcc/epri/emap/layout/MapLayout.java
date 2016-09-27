package cn.com.sgcc.epri.emap.layout;

import com.tianditu.android.maps.MapController;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.map.TMapView;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 天地图布局类
 */
public class MapLayout extends TransmitContext {
    private TMapView map_view; // 天地图地图控件
    private MapController map_controller; // 地图控制器

    // 构造函数
    public MapLayout(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        map_view = (TMapView) context.findViewById(R.id.emap_view);
        map_controller = map_view.getController();

        map_view.setLogoPos(TMapView.LOGO_RIGHT_BOTTOM); // 设置LOGO位置为右下角
    }

    // 获取地图控制器
    public MapController getMapController() {
        return map_controller;
    }
}
