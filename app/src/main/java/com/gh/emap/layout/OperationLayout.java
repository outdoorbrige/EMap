package com.gh.emap.layout;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.listener.OperationListener;

/**
 * Created by GuHeng on 2016/11/9.
 * 地图操作布局
 */
public class OperationLayout {
    private Context mContext;
    private View mLayout; // 布局
    private Button mZoomInButton; // 放大按钮
    private Button mZoomOutButton; // 缩小按钮
    private Button mLocationButton; // 定位按钮

    public OperationLayout(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mLayout = ((MainActivity)this.mContext).findViewById(R.id.operation);
        this.mZoomInButton = (Button) ((MainActivity)this.mContext).findViewById(R.id.zoon_in);
        this.mZoomOutButton = (Button) ((MainActivity)this.mContext).findViewById(R.id.zoom_out);
        this.mLocationButton = (Button) ((MainActivity)this.mContext).findViewById(R.id.location);

        this.mZoomInButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getOperationListener());
        this.mZoomOutButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getOperationListener());
        this.mLocationButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getOperationListener());
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
