package cn.com.sgcc.epri.emap.layout;

import android.view.View;
import android.widget.Button;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.ActionListener;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 地图操作布局类
 */
public class ActionLayout extends MainActivityContext {
    private View mLayout; // 布局
    private Button mZoomInButton; // 放大按钮
    private Button mZoomOutButton; // 缩小按钮
    private Button mLocateButton; // 定位按钮

    // 构造函数
    public ActionLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mLayout = mMainActivity.findViewById(R.id.action);
        mZoomInButton = (Button) mMainActivity.findViewById(R.id.zoon_in);
        mZoomOutButton = (Button) mMainActivity.findViewById(R.id.zoom_out);
        mLocateButton = (Button) mMainActivity.findViewById(R.id.location);

        ActionListener listener = new ActionListener(mMainActivity);

        mZoomInButton.setOnClickListener(listener);
        mZoomOutButton.setOnClickListener(listener);
        mLocateButton.setOnClickListener(listener);
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
