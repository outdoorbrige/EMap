package com.gh.emap.layoutA;

import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

import java.io.File;

/**
 * Created by GuHeng on 2017/3/1.
 */

public class BottomGroundRenderPlaneMenuLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private Button mCancel; // 取消
    private Button mUndo; // 撤销
    private Button mRedo; // 重绘
    private Button mAdd; // 添加
    private Button mSave; // 保存

    public BottomGroundRenderPlaneMenuLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.bottom_ground_render_plane_menu);

        mCancel = (Button) mMainActivity.findViewById(R.id.plane_menu_cancel);
        mUndo = (Button) mMainActivity.findViewById(R.id.plane_menu_undo);
        mRedo = (Button) mMainActivity.findViewById(R.id.plane_menu_redo);
        mAdd = (Button) mMainActivity.findViewById(R.id.plane_menu_add);
        mSave = (Button) mMainActivity.findViewById(R.id.plane_menu_save);

        mCancel.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getBottomGroundRenderPlaneMenuListener());
        mUndo.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getBottomGroundRenderPlaneMenuListener());
        mRedo.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getBottomGroundRenderPlaneMenuListener());
        mAdd.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getBottomGroundRenderPlaneMenuListener());
        mSave.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getBottomGroundRenderPlaneMenuListener());
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
        return ".kml";
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
