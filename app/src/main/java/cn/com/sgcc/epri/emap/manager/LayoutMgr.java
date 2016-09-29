package cn.com.sgcc.epri.emap.manager;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.layout.LayerLayout;
import cn.com.sgcc.epri.emap.layout.MapLayout;
import cn.com.sgcc.epri.emap.layout.MenuLayout;
import cn.com.sgcc.epri.emap.layout.SearchLayout;
import cn.com.sgcc.epri.emap.layout.ToolLayout;
import cn.com.sgcc.epri.emap.map.TMapView;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 界面所有布局、控件的管理类
 */
public class LayoutMgr extends TransmitContext {
    private MapMgr map_mgr; // 地图管理类
    private MapLayout map_layout; // 地图管理类
    private SearchLayout search_layout; // 查找管理类
    private MenuLayout menu_layout; // 菜单管理类
    private LayerLayout layer_layout; // 地图切换类
    private ToolLayout tool_layout; // 工具管理类

    // 构造函数
    public LayoutMgr(MainActivity context, MapMgr map_mgr) {
        super(context);
        this.map_mgr = map_mgr;
    }

    // 初始化
    public void init() {
        map_layout = new MapLayout(context, map_mgr);
        map_layout.init();

        search_layout = new SearchLayout(context);
        search_layout.init();

        menu_layout = new MenuLayout(context);
        menu_layout.init();

        layer_layout = new LayerLayout(context);
        layer_layout.init();

        tool_layout = new ToolLayout(context);
        tool_layout.init();
    }

    // 获取天地图地图控件
    public TMapView getTMapView() {
        return map_mgr.getTMapView();
    }
}
