package com.gh.emap.layout;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

import java.io.File;

/**
 * Created by GuHeng on 2017/1/9.
 * 地物绘制-画线 顶部布局
 */

public class TopGroundRenderLineLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private TextView mLineType; // 画线的类型
    private EditText mLineName; // 画线的名称
    private Button mLineCancel; // 取消
    private Button mLineSave; // 保存

    public TopGroundRenderLineLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.top_ground_render_line);
        mLineType = (TextView)mMainActivity.findViewById(R.id.line_type);
        mLineName = (EditText)mMainActivity.findViewById(R.id.line_name);
        mLineCancel = (Button)mMainActivity.findViewById(R.id.line_cancel);
        mLineSave = (Button)mMainActivity.findViewById(R.id.line_save);

        mLineType.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderLineListener());
        mLineName.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderLineListener());
        mLineCancel.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderLineListener());
        mLineSave.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderLineListener());
    }

    // 获取地物绘制-画点工作目录
    public String getGroundRenderLinePath() {
        String path = null;

        String fatherPath = mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().getGroundRenderPath();
        if(fatherPath != null) {
            path = fatherPath + "Lines" + File.separator;

            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdirs();
            }
        }

        return path;
    }

    // 获取地物绘制-画点工作目录
    public String getGroundRenderLineFileSuffix() {
        return ".line";
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
        mLineType.setText("");
        mLineName.setText("");
    }
}
