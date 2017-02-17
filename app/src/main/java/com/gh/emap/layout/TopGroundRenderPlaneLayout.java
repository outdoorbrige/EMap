package com.gh.emap.layout;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

import java.io.File;

/**
 * Created by GuHeng on 2017/1/17.
 * 地物绘制-画面 顶部布局
 */

public class TopGroundRenderPlaneLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private TextView mPlaneType; // 画面的类型
    private EditText mPlaneName; // 画面的名称
    private Button mPlaneCancel; // 取消
    private Button mPlaneSave; // 保存

    public TopGroundRenderPlaneLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.top_ground_render_plane);
        mPlaneType = (TextView)mMainActivity.findViewById(R.id.plane_type);
        mPlaneName = (EditText)mMainActivity.findViewById(R.id.plane_name);
        mPlaneCancel = (Button)mMainActivity.findViewById(R.id.plane_cancel);
        mPlaneSave = (Button)mMainActivity.findViewById(R.id.plane_save);

        mPlaneType.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderPlaneListener());
        mPlaneName.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderPlaneListener());
        mPlaneCancel.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderPlaneListener());
        mPlaneSave.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderPlaneListener());
    }

    // 获取地物绘制-画点工作目录
    public String getGroundRenderPlanePath() {
        String path = null;

        String fatherPath = mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().getGroundRenderPath();
        if(fatherPath != null) {
            path = fatherPath + "Planes" + File.separator;

            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdirs();
            }
        }

        return path;
    }

    // 获取地物绘制-画点工作目录
    public String getGroundRenderPlaneFileSuffix() {
        return ".plane";
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
        mPlaneType.setText("");
        mPlaneName.setText("");
    }
}
