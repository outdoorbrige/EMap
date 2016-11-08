package cn.com.sgcc.epri.emap.layout;

import android.widget.Button;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.ActionListener;
import cn.com.sgcc.epri.emap.base.BaseLayout;

/**
 * Created by GuHeng on 2016/9/27.
 * 地图操作布局类
 */
public class ActionLayout extends BaseLayout {

    private Button mZoomInButton; // 放大按钮
    private Button mZoomOutButton; // 缩小按钮
    private Button mLocateButton; // 定位按钮

    // 构造函数
    public ActionLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        setLayout(mMainActivity.findViewById(R.id.action_layout));
        mZoomInButton = (Button) mMainActivity.findViewById(R.id.zoon_in);
        mZoomOutButton = (Button) mMainActivity.findViewById(R.id.zoom_out);
        mLocateButton = (Button) mMainActivity.findViewById(R.id.location);

        ActionListener listener = new ActionListener(mMainActivity);

        mZoomInButton.setOnClickListener(listener);
        mZoomOutButton.setOnClickListener(listener);
        mLocateButton.setOnClickListener(listener);
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
