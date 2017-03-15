package com.gh.emap.layoutA;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.view.MyListView;

/**
 * Created by GuHeng on 2016/11/9.
 * 地图操作布局
 */
public class OperationLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private Button mLayerButton; // 地图类型切换按钮
    private Button mZoomInButton; // 放大按钮
    private Button mZoomOutButton; // 缩小按钮
    private Button mLocationButton; // 定位按钮

    private String[] mListItems = {"影像图", "矢量图", "地形图"};
    private PopupWindow mPopupWindow; // 弹出式菜单
    private View mPopupLayout; // 弹出菜单布局
    private MyListView mMyListView;
    private ArrayAdapter<String> mArrayAdapter;
    private int mCurrentSelectItemIndex = -1; // 当前选中的Item索引

    public OperationLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.operation);

        mLayerButton = (Button)mMainActivity.findViewById(R.id.layer);
        mZoomInButton = (Button)mMainActivity.findViewById(R.id.zoon_in);
        mZoomOutButton = (Button)mMainActivity.findViewById(R.id.zoom_out);
        mLocationButton = (Button)mMainActivity.findViewById(R.id.location);

        mLayerButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getOperationListener());
        mZoomInButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getOperationListener());
        mZoomOutButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getOperationListener());
        mLocationButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getOperationListener());
    }

    // 显示弹出菜单
    public void showPopupWindow(View parentView) {
        if (mPopupWindow == null) {
            mPopupLayout = LayoutInflater.from(mMainActivity).inflate(R.layout.layer_popup_menu, null);;
            mMyListView = (MyListView) mPopupLayout.findViewById(R.id.layer_view);
            mArrayAdapter = new ArrayAdapter<>(mMainActivity, android.R.layout.simple_list_item_single_choice, mListItems);
            mMyListView.setAdapter(mArrayAdapter);
            mMyListView.setItemsCanFocus(false);
            mMyListView.setOnItemClickListener(mMainActivity.getMainManager().getListenerManager().getOperationListener());

            mMyListView.setItemChecked(1, true);

            mPopupWindow = new PopupWindow(mPopupLayout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            mPopupWindow.setOutsideTouchable(true);
        } else {

        }

        mPopupWindow.showAsDropDown(parentView);
    }

    // 关闭弹出菜单
    public void closePopupWindow() {
        mPopupWindow.dismiss();
    }

    // 设置当前选中的Item索引
    public void setCurrentSelectItemIndex(int index) {
        mCurrentSelectItemIndex = index;
    }

    // 获取当前选中的Item索引
    public int getCurrentSelectItemIndex() {
        return mCurrentSelectItemIndex;
    }

    // 显示布局
    public void show() {
        if(mLayout.getVisibility() == View.VISIBLE) {
            return;
        }

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
