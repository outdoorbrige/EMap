package com.gh.emap.layout;

import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2016/11/9.
 * 地图操作布局
 */
public class OperationLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private Button mZoomInButton; // 放大按钮
    private Button mZoomOutButton; // 缩小按钮
    private Button mLocationButton; // 定位按钮

    public OperationLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.operation);
        mZoomInButton = (Button)mMainActivity.findViewById(R.id.zoon_in);
        mZoomOutButton = (Button)mMainActivity.findViewById(R.id.zoom_out);
        mLocationButton = (Button)mMainActivity.findViewById(R.id.location);

        mZoomInButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getOperationListener());
        mZoomOutButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getOperationListener());
        mLocationButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getOperationListener());
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
