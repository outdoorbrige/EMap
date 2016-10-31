package cn.com.sgcc.epri.emap.layout;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.MenuListener;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 菜单布局类
 */
public class MenuLayout extends MainActivityContext {
    private View mLayout; // 布局
    private Button mEditButton; // 编辑按钮
    private Button mSetButton; // 配置按钮
    private Button mDownloadButton; // 下载按钮
    private Button mMenuButton; // 菜单按钮
    private ArrayList<Button> mArrayListButtons = new ArrayList<Button>(); // 展开和折叠菜单中控制的按钮集合

    // 构造函数
    public MenuLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mLayout = mMainActivity.findViewById(R.id.main_menu);
        mEditButton = (Button) mMainActivity.findViewById(R.id.menu_edit);
        mSetButton = (Button) mMainActivity.findViewById(R.id.menu_setting);
        mDownloadButton = (Button) mMainActivity.findViewById(R.id.menu_download);
        mMenuButton = (Button) mMainActivity.findViewById(R.id.menu_main);

        // 注意：
        // 按钮排列数序是从下向上排列的
        // 底部的按钮在List的队首，顶部的按钮在List的队尾
        mArrayListButtons.add(mDownloadButton);
        mArrayListButtons.add(mSetButton);
        mArrayListButtons.add(mEditButton);

        MenuListener listener = new MenuListener(mMainActivity, mArrayListButtons);

        mEditButton.setOnClickListener(listener);
        mSetButton.setOnClickListener(listener);
        mDownloadButton.setOnClickListener(listener);
        mMenuButton.setOnClickListener(listener);
    }

    // 显示布局
    public void show() {
        mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置
        mLayout.setVisibility(View.GONE);
    }
}
