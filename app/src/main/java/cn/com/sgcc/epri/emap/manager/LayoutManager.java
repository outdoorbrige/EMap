package cn.com.sgcc.epri.emap.manager;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.layout.EditLayout;
import cn.com.sgcc.epri.emap.layout.LayerLayout;
import cn.com.sgcc.epri.emap.layout.MapLayout;
import cn.com.sgcc.epri.emap.layout.MenuLayout;
import cn.com.sgcc.epri.emap.layout.SearchLayout;
import cn.com.sgcc.epri.emap.layout.ActionLayout;
import cn.com.sgcc.epri.emap.base.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 界面所有布局、控件的管理类
 */
public class LayoutManager extends MainActivityContext {
    private MapLayout mMapLayout; // 地图布局类
    private SearchLayout mSearchLayout; // 查找布局类
    private MenuLayout mMenuLayout; // 菜单布局类
    private EditLayout mEditLayout; // 编辑布局类
    private LayerLayout mLayerLayout; // 地图切换布局类
    private ActionLayout mActionLayout; // 地图操作布局类

    // 构造函数
    public LayoutManager(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mMapLayout = new MapLayout(mMainActivity);
        mMapLayout.init();

        mSearchLayout = new SearchLayout(mMainActivity);
        mSearchLayout.init();

        mMenuLayout = new MenuLayout(mMainActivity);
        mMenuLayout.init();

        mEditLayout = new EditLayout(mMainActivity);
        mEditLayout.init();

        mLayerLayout = new LayerLayout(mMainActivity);
        mLayerLayout.init();

        mActionLayout = new ActionLayout(mMainActivity);
        mActionLayout.init();
    }

    // 获取地图布局类
    public MapLayout getMapLayout() {
        return mMapLayout;
    }

    // 获取搜索布局
    public SearchLayout getSearchLayout() {
        return mSearchLayout;
    }

    // 获取菜单布局
    public MenuLayout getMenuLayout() {
        return mMenuLayout;
    }

    // 获取编辑菜单布局
    public EditLayout getEditLayout() {
        return mEditLayout;
    }

    // 获取地图切换布局
    public LayerLayout getLayerLayout() {
        return mLayerLayout;
    }

    // 获取地图操作布局
    ActionLayout getActionLayout() {
        return mActionLayout;
    }
}
