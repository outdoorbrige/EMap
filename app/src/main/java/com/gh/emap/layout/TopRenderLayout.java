package com.gh.emap.layout;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.view.MyListView;

import java.io.File;

/**
 * Created by GuHeng on 2016/11/9.
 * 地图绘制模式布局
 */
public class TopRenderLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private TextView mGroundRenderView; // 地物绘制
    private TextView mLineRenderView; // 线路绘制
    private TextView mDrawView; // 测绘
    private TextView mExitRenderView; // 退出绘制

    private String[] mListItems = {"画点", "画线", "画面"};
    private PopupWindow mGroundRenderPopupWindow; // 弹出地物绘制式菜单
    private int mGroundRenderSelectedIndex; // 当前地物绘制选择的索引

    public TopRenderLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.top_edit);
        mGroundRenderView = (TextView)mMainActivity.findViewById(R.id.menu_ground_render);
        mLineRenderView = (TextView)mMainActivity.findViewById(R.id.menu_line_render);
        mDrawView = (TextView)mMainActivity.findViewById(R.id.menu_draw);
        mExitRenderView = (TextView)mMainActivity.findViewById(R.id.menu_exit_render);

        mGroundRenderView.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopRenderListener());
        mLineRenderView.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopRenderListener());
        mDrawView.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopRenderListener());
        mExitRenderView.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopRenderListener());
    }

    // 显示地物绘制弹出菜单
    public void showShapPopupWindow(View parentView) {
        if (mGroundRenderPopupWindow == null) {
            View popupLayout = LayoutInflater.from(mMainActivity).inflate(R.layout.ground_render_popup_menu, null);
            MyListView myListView = (MyListView) popupLayout.findViewById(R.id.ground_render_view);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mMainActivity, android.R.layout.simple_list_item_single_choice, mListItems);
            myListView.setAdapter(arrayAdapter);
            myListView.setItemsCanFocus(false);
            myListView.setOnItemClickListener(mMainActivity.getMainManager().getListenerManager().getGroundRenderEditListener());

            mGroundRenderPopupWindow = new PopupWindow(popupLayout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mGroundRenderPopupWindow.setBackgroundDrawable(new ColorDrawable());
            mGroundRenderPopupWindow.setOutsideTouchable(true);
        } else {

        }

        setGroundRenderSelectedIndex(-2);

        mGroundRenderPopupWindow.showAsDropDown(parentView);
    }

    // 关闭地物绘制弹出菜单
    public void closeGroundRenderPopupWindow() {
        mGroundRenderPopupWindow.dismiss();
    }

    // 设置当前地物绘制选择的索引
    public void setGroundRenderSelectedIndex(int index) {
        mGroundRenderSelectedIndex = index;
    }

    // 获取当前地物绘制选择的索引
    public int getGroundRenderSelectedIndex() {
        return mGroundRenderSelectedIndex;
    }

    // 获取地物绘制的工作目录
    public String getGroundRenderPath() {
        String path = null;

        String fatherPath = mMainActivity.getMainManager().getUserManager().getHomePath();
        if(fatherPath != null) {
            path = fatherPath + "GroundRender" + File.separator;

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
