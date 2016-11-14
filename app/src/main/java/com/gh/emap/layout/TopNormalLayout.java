package com.gh.emap.layout;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.listener.TopNormalListener;

/**
 * Created by GuHeng on 2016/11/9.
 * 地图非编辑模式布局
 */
public class TopNormalLayout {
    private Context mContext;
    private View mLayout; // 布局
    private Button mLoginButton; // 登录按钮
    private Button mSearchButton; // 查找按钮

    public TopNormalLayout(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mLayout = ((MainActivity)this.mContext).findViewById(R.id.top_normal);
        this.mLoginButton = (Button) ((MainActivity)this.mContext).findViewById(R.id.user_login_button);
        this.mSearchButton = (Button) ((MainActivity)this.mContext).findViewById(R.id.search_button);

        this.mLoginButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopNormalListener());
        this.mSearchButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopNormalListener());
    }

    // 显示布局
    public void show() {
        this.mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置
        this.mLayout.setVisibility(View.GONE);
    }
}
