package com.gh.emap.layout;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

import java.io.File;

/**
 * Created by GuHeng on 2016/11/10.
 * 地物编辑-画点 顶部布局
 */
public class TopShapPointLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private TextView mPointType; // 画点的类型
    private EditText mPointName; // 画点的名称
    private Button mPointCancel; // 取消
    private Button mPointSave; // 保存

    public TopShapPointLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.top_shap_point);
        mPointType = (TextView)mMainActivity.findViewById(R.id.point_type);
        mPointName = (EditText)mMainActivity.findViewById(R.id.point_name);
        mPointCancel = (Button)mMainActivity.findViewById(R.id.point_cancel);
        mPointSave = (Button)mMainActivity.findViewById(R.id.point_save);

        mPointType.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopEditPointListener());
        mPointName.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopEditPointListener());
        mPointCancel.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopEditPointListener());
        mPointSave.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopEditPointListener());
    }

    // 获取地物编辑-画点工作目录
    public String getShapPointPath() {
        String path = null;

        String fatherPath = mMainActivity.getMainManager().getLayoutManager().getTopEditLayout().getShapEditPath();
        if(fatherPath != null) {
            path = fatherPath + "MyPoint" + File.separator;

            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdirs();
            }
        }

        return path;
    }

    // 显示布局
    public void show() {
        mLayout.setVisibility(View.VISIBLE);
        clear();
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置
        mLayout.setVisibility(View.GONE);
    }

    // 清理上次数据
    private void clear() {
        mPointType.setText("");
        mPointName.setText("");
    }
}
