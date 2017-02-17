package com.gh.emap.layout;

import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2016/11/9.
 * 地图非绘制模式布局
 */
public class TopNormalLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private Button mLoginButton; // 登录按钮
    private Button mSearchButton; // 查找按钮

    public TopNormalLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.top_normal);
        mLoginButton = (Button)mMainActivity.findViewById(R.id.user_login_button);
        mSearchButton = (Button)mMainActivity.findViewById(R.id.search_button);

        mLoginButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopNormalListener());
        mSearchButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopNormalListener());
    }

    // 显示布局
    public void show() {
        if(mLayout.getVisibility() == View.VISIBLE) {
            return;
        }

        mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置

        if(mLayout.getVisibility() == View.GONE) {
            return;
        }

        mLayout.setVisibility(View.GONE);
    }

    // 判断布局的隐藏性
    public int getVisibility() {
        // View.VISIBLE    可见
        // View.INVISIBLE    不可见但是占用布局空间
        // View.GONE    不可见也不占用布局空搜索间
        return mLayout.getVisibility();
    }
}
