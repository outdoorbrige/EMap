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
 * 地图编辑模式布局
 */
public class TopEditLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private TextView mShapEditView; // 地物编辑
    private TextView mLineEditView; // 线路编辑
    private TextView mMappingView; // 测绘
    private TextView mExitEditView; // 退出编辑

    private String[] mListItems = {"画点", "画线", "画面"};
    private PopupWindow mShapPopupWindow; // 弹出地物编辑式菜单
    private int mShapEditSelectedIndex; // 当前地物编辑选择的索引

    public TopEditLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.top_edit);
        mShapEditView = (TextView)mMainActivity.findViewById(R.id.menu_shap);
        mLineEditView = (TextView)mMainActivity.findViewById(R.id.menu_line);
        mMappingView = (TextView)mMainActivity.findViewById(R.id.menu_mapping);
        mExitEditView = (TextView)mMainActivity.findViewById(R.id.menu_exit);

        mShapEditView.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopEditListener());
        mLineEditView.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopEditListener());
        mMappingView.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopEditListener());
        mExitEditView.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getTopEditListener());
    }

    // 显示地物编辑弹出菜单
    public void showShapPopupWindow(View parentView) {
        if (mShapPopupWindow == null) {
            View popupLayout = LayoutInflater.from(mMainActivity).inflate(R.layout.shap_popup_menu, null);
            MyListView myListView = (MyListView) popupLayout.findViewById(R.id.shap_view);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mMainActivity, android.R.layout.simple_list_item_single_choice, mListItems);
            myListView.setAdapter(arrayAdapter);
            myListView.setItemsCanFocus(false);
            myListView.setOnItemClickListener(mMainActivity.getMainManager().getListenerManager().getShapEditListener());

            mShapPopupWindow = new PopupWindow(popupLayout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mShapPopupWindow.setBackgroundDrawable(new ColorDrawable());
            mShapPopupWindow.setOutsideTouchable(true);
        } else {

        }

        setShapEditSelectedIndex(-2);

        mShapPopupWindow.showAsDropDown(parentView);
    }

    // 关闭地物编辑弹出菜单
    public void closeShapPopupWindow() {
        mShapPopupWindow.dismiss();
    }

    // 设置当前地物编辑选择的索引
    public void setShapEditSelectedIndex(int index) {
        mShapEditSelectedIndex = index;
    }

    // 获取当前地物编辑选择的索引
    public int getShapEditSelectedIndex() {
        return mShapEditSelectedIndex;
    }

    // 获取地物编辑的工作目录
    public String getShapEditPath() {
        String path = null;

        String fatherPath = mMainActivity.getMainManager().getUserManager().getHomePath();
        if(fatherPath != null) {
            path = fatherPath + "MyShapEdit" + File.separator;

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
