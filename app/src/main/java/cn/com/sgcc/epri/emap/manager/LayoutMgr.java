package cn.com.sgcc.epri.emap.manager;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.layout.LayerLayout;
import cn.com.sgcc.epri.emap.layout.MapLayout;
import cn.com.sgcc.epri.emap.layout.MenuLayout;
import cn.com.sgcc.epri.emap.layout.SearchLayout;
import cn.com.sgcc.epri.emap.layout.ToolLayout;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 界面所有布局、控件的管理类
 */
public class LayoutMgr extends TransmitContext {
    private MapLayout map; // 地图管理类
    private SearchLayout search; // 查找管理类
    private MenuLayout menu; // 菜单管理类
    private LayerLayout layer; // 地图切换类
    private ToolLayout tool; // 工具管理类

    // 构造函数
    public LayoutMgr(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        map = new MapLayout(context);
        map.init();

        search = new SearchLayout(context);
        search.init();

        menu = new MenuLayout(context);
        menu.init();

        layer = new LayerLayout(context);
        layer.init();

        tool = new ToolLayout(context, map.getMapController());
        tool.init();
    }
}
