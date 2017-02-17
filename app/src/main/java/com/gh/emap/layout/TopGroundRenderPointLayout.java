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
 * 地物绘制-画点 顶部布局
 */
public class TopGroundRenderPointLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private TextView mPointType; // 画点的类型
    private EditText mPointName; // 画点的名称
    private Button mPointCancel; // 取消
    private Button mPointSave; // 保存

    public TopGroundRenderPointLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.top_ground_render_point);
        mPointType = (TextView)mMainActivity.findViewById(R.id.point_type);
        mPointName = (EditText)mMainActivity.findViewById(R.id.point_name);
        mPointCancel = (Button)mMainActivity.findViewById(R.id.point_cancel);
        mPointSave = (Button)mMainActivity.findViewById(R.id.point_save);

        mPointType.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderPointListener());
        mPointName.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderPointListener());
        mPointCancel.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderPointListener());
        mPointSave.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderPointListener());
    }

    // 获取地物绘制-画点工作目录
    public String getGroundRenderPointPath() {
        String path = null;

        String fatherPath = mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().getGroundRenderPath();
        if(fatherPath != null) {
            path = fatherPath + "Points" + File.separator;

            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdirs();
            }
        }

        return path;
    }

    // 获取地物绘制-画点文件后缀名
    public String getGroundRenderPointFileSuffix() {
        return ".point";
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

    // 清理上次数据
    private void clear() {
        mPointType.setText("");
        mPointName.setText("");
    }
}
