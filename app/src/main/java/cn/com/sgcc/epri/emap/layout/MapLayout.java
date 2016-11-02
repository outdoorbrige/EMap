package cn.com.sgcc.epri.emap.layout;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.manager.MapManager;
import cn.com.sgcc.epri.emap.base.BaseLayout;

/**
 * Created by GuHeng on 2016/9/27.
 * 天地图布局类
 */
public class MapLayout extends BaseLayout {
    private MapManager mMapManger; // 天地图控制类

    // 构造函数
    public MapLayout(MainActivity mainActivity, MapManager mapManger) {
        super(mainActivity);
        this.mMapManger = mapManger;
    }

    // 初始化
    public void init() {
        setLayout(mMainActivity.findViewById(R.id.map_view));
    }
}
