package cn.com.sgcc.epri.emap.base;

import android.view.View;

import cn.com.sgcc.epri.emap.MainActivity;

/**
 * Created by GuHeng on 2016/11/1.
 * 布局基类
 */
public class BaseLayout extends MainActivityContext {
    private View mLayout; // 布局

    // 构造函数
    protected BaseLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 设置布局
    protected void setLayout(View layout) {
        this.mLayout = layout;
    }

    // 获取布局
    protected View getLayout() {
        return mLayout;
    }

    // 显示布局
    protected void show() {
        mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    protected void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置
        mLayout.setVisibility(View.GONE);
    }
}
