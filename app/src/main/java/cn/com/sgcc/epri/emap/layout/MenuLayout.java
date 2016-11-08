package cn.com.sgcc.epri.emap.layout;

import android.widget.Button;

import java.util.ArrayList;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.MenuListener;
import cn.com.sgcc.epri.emap.base.BaseLayout;

/**
 * Created by GuHeng on 2016/9/27.
 * 菜单布局类
 */
public class MenuLayout extends BaseLayout {
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
        setLayout(mMainActivity.findViewById(R.id.menu_layout));
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

    // 布局显示
    public void show() {
        super.show();
    }

    // 布局隐藏
    public void hide() {
        super.hide();
        clear();
    }

    // 布局数据清理
    private void clear() {
    }
}
