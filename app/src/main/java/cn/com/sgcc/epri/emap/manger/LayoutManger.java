package cn.com.sgcc.epri.emap.manger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.layout.LayerLayout;
import cn.com.sgcc.epri.emap.layout.MapLayout;
import cn.com.sgcc.epri.emap.layout.MenuLayout;
import cn.com.sgcc.epri.emap.layout.SearchLayout;
import cn.com.sgcc.epri.emap.layout.ToolLayout;
import cn.com.sgcc.epri.emap.map.TMapView;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 界面所有布局、控件的管理类
 */
public class LayoutManger extends MainActivityContext {
    private MapManger mMapManger; // 地图管理类
    private MapLayout mMapLayout; // 地图管理类
    private SearchLayout mSearchLayout; // 查找管理类
    private MenuLayout mMenuLayout; // 菜单管理类
    private LayerLayout mLayerLayout; // 地图切换类
    private ToolLayout mToolLayout; // 工具管理类

    // 构造函数
    public LayoutManger(MainActivity context, MapManger mapManger) {
        super(context);
        this.mMapManger = mapManger;
    }

    // 初始化
    public void init() {
        mMapLayout = new MapLayout(context, mMapManger);
        mMapLayout.init();

        mSearchLayout = new SearchLayout(context);
        mSearchLayout.init();

        mMenuLayout = new MenuLayout(context);
        mMenuLayout.init();

        mLayerLayout = new LayerLayout(context);
        mLayerLayout.init();

        mToolLayout = new ToolLayout(context);
        mToolLayout.init();
    }

    // 获取天地图地图控件
    public TMapView getTMapView() {
        return mMapManger.getTMapView();
    }
}
