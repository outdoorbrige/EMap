package com.gh.emap.layout;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.listener.LayerListener;
import com.gh.emap.view.MyListView;

/**
 * Created by GuHeng on 2016/11/9.
 * 地图类型切换布局
 */
public class LayerLayout {
    private Context mContext;
    private View mLayout; // 布局
    private Button mLayerButton; // 地图类型切换按钮
    private String[] mListItems = {"影像图", "矢量图", "地形图"};
    private PopupWindow mPopupWindow; // 弹出式菜单
    private View mPopupLayout; // 弹出菜单布局
    private MyListView mMyListView;
    private ArrayAdapter<String> mArrayAdapter;
    private int mCurrentSelectItemIndex = -1; // 当前选中的Item索引

    public LayerLayout(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mLayout = ((MainActivity)this.mContext).findViewById(R.id.layer);
        this.mLayerButton = (Button)((MainActivity)this.mContext).findViewById(R.id.layer_button);

        this.mLayerButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getLayerListener());
    }

    // 显示弹出菜单
    public void showPopupWindow(View parentView) {
        if (this.mPopupWindow == null) {
            this.mPopupLayout = LayoutInflater.from(this.mContext).inflate(R.layout.layer_popup_menu, null);;
            this.mMyListView = (MyListView) this.mPopupLayout.findViewById(R.id.layer_view);
            this.mArrayAdapter = new ArrayAdapter<>(this.mContext, android.R.layout.simple_list_item_single_choice, this.mListItems);
            this.mMyListView.setAdapter(this.mArrayAdapter);
            this.mMyListView.setItemsCanFocus(false);
            this.mMyListView.setOnItemClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getLayerListener());

            this.mMyListView.setItemChecked(1, true);

            this.mPopupWindow = new PopupWindow(this.mPopupLayout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            this.mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            this.mPopupWindow.setOutsideTouchable(true);
        } else {

        }

        this.mPopupWindow.showAsDropDown(parentView);
    }

    // 关闭弹出菜单
    public void closePopupWindow() {
        this.mPopupWindow.dismiss();
    }

    // 设置当前选中的Item索引
    public void setCurrentSelectItemIndex(int index) {
        this.mCurrentSelectItemIndex = index;
    }

    // 获取当前选中的Item索引
    public int getCurrentSelectItemIndex() {
        return this.mCurrentSelectItemIndex;
    }

    // 显示布局
    public void show() {
        this.mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置
        this.mLayout.setVisibility(View.GONE);
    }
}
