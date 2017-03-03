package com.gh.emap.layout;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/1/9.
 * 地物绘制-画线 顶部布局
 */

public class TopGroundRenderLineLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private TextView mLineType; // 画线的类型
    private EditText mLineName; // 画线的名称
    private String mOldLineType; // 上次保存的画线类型

    public TopGroundRenderLineLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.top_ground_render_line);
        mLineType = (TextView)mMainActivity.findViewById(R.id.line_type);
        mLineName = (EditText)mMainActivity.findViewById(R.id.line_name);

        mLineType.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderLineListener());
        mLineName.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopGroundRenderLineListener());
    }

    public void setLineName(String lineName) {
        mLineName.setText(lineName);
    }

    public String getLineName() {
        return mLineName.getText().toString();
    }

    public void setLineType(String lineType) {
        mLineType.setText(lineType);
    }

    public String getLineType() {
        return mLineType.getText().toString();
    }

    public void setOldLineType(String oldLineType) {
        mOldLineType = oldLineType;
    }

    public String getOldLineType() {
        return mOldLineType;
    }

    // 清理上次数据
    private void clear() {
        mLineType.setText("");
        mLineName.setText("");
        mOldLineType = "";
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
}
