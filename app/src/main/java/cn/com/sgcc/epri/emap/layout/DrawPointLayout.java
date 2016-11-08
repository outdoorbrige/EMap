package cn.com.sgcc.epri.emap.layout;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.base.MainActivityContext;

/**
 * Created by GuHeng on 2016/11/7.
 * 地物编辑-画点布局
 */
public class DrawPointLayout extends MainActivityContext {
    private DrawPointTopLayout mDrawPointTopLayout; // 顶部布局
    private DrawPointBottomLayout mDrawPointBottomLayout; // 底部布局

    // 构造函数
    public DrawPointLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mDrawPointTopLayout = new DrawPointTopLayout(mMainActivity);
        mDrawPointTopLayout.init();

        mDrawPointBottomLayout = new DrawPointBottomLayout(mMainActivity);
        mDrawPointBottomLayout.init();
    }

    // 布局显示
    public void show() {
        mDrawPointTopLayout.show();
        mDrawPointBottomLayout.show();
    }

    // 布局隐藏
    public void hide() {
        mDrawPointTopLayout.hide();
        mDrawPointBottomLayout.hide();
        clear();
    }

    // 布局数据清理
    private void clear() {

    }

    // 获取顶部布局
    public DrawPointTopLayout getDrawPointTopLayout() {
        return mDrawPointTopLayout;
    }

    // 获取底部布局
    public DrawPointBottomLayout getDrawPointBottomLayout() {
        return mDrawPointBottomLayout;
    }
}
