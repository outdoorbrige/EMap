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
    private Button mFavoriteButton; // 收藏按钮
    private Button mClearButton; // 删除按钮
    private Button mSetButton; // 配置按钮
    private Button mOfflineButton; // 离线下载按钮
    private Button mToolButton; // 工具按钮
    private Button mMainButton; // 菜单按钮
    private ArrayList<Button> mArrayListButtons = new ArrayList<Button>(); // 展开和折叠菜单中控制的按钮集合

    // 构造函数
    public MenuLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mLayout = mMainActivity.findViewById(R.id.setting_menu);
        mFavoriteButton = (Button) mMainActivity.findViewById(R.id.favorite);
        mClearButton = (Button) mMainActivity.findViewById(R.id.clear);
        mSetButton = (Button) mMainActivity.findViewById(R.id.setting);
        mOfflineButton = (Button) mMainActivity.findViewById(R.id.download);
        mToolButton = (Button) mMainActivity.findViewById(R.id.tool);
        mMainButton = (Button) mMainActivity.findViewById(R.id.menu);

        mArrayListButtons.add(mToolButton);
        mArrayListButtons.add(mOfflineButton);
        mArrayListButtons.add(mSetButton);
        mArrayListButtons.add(mClearButton);
        mArrayListButtons.add(mFavoriteButton);

        MenuListener listener = new MenuListener(mMainActivity, mArrayListButtons);

        mFavoriteButton.setOnClickListener(listener);
        mClearButton.setOnClickListener(listener);
        mSetButton.setOnClickListener(listener);
        mOfflineButton.setOnClickListener(listener);
        mToolButton.setOnClickListener(listener);
        mMainButton.setOnClickListener(listener);
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
