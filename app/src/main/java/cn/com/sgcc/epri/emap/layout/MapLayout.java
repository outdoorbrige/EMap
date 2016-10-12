package cn.com.sgcc.epri.emap.layout;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.manager.MapMgr;
import cn.com.sgcc.epri.emap.map.TMapView;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 天地图布局类
 */
public class MapLayout extends TransmitContext {
    private MapMgr map_mgr; // 天地图控制类

    // 构造函数
    public MapLayout(MainActivity context, MapMgr map_mgr) {
        super(context);
        this.map_mgr = map_mgr;
    }

    // 初始化
    public void init() {
    }
}
