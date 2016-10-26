package cn.com.sgcc.epri.emap.layout;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.manger.MapManger;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 天地图布局类
 */
public class MapLayout extends MainActivityContext {
    private MapManger mMapManger; // 天地图控制类

    // 构造函数
    public MapLayout(MainActivity mainActivity, MapManger mapManger) {
        super(mainActivity);
        this.mMapManger = mapManger;
    }

    // 初始化
    public void init() {
    }
}
