package com.gh.emap.layout;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.listener.TopEditListener;
import com.gh.emap.view.MyListView;

/**
 * Created by GuHeng on 2016/11/9.
 */
public class TopEditLayout {
    private Context mContext;
    private View mLayout; // 布局
    private TextView mShapEditView; // 地物编辑
    private TextView mLineEditView; // 线路编辑
    private TextView mMappingView; // 测绘
    private TextView mExitEditView; // 退出编辑

    private String[] mListItems = {"画点", "画线", "画面"};
    private PopupWindow mShapPopupWindow; // 弹出地物编辑式菜单

    public TopEditLayout(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mLayout = ((MainActivity)this.mContext).findViewById(R.id.top_edit);
        this.mShapEditView = (TextView)((MainActivity)this.mContext).findViewById(R.id.menu_shap);
        this.mLineEditView = (TextView)((MainActivity)this.mContext).findViewById(R.id.menu_line);
        this.mMappingView = (TextView)((MainActivity)this.mContext).findViewById(R.id.menu_mapping);
        this.mExitEditView = (TextView)((MainActivity)this.mContext).findViewById(R.id.menu_exit);

        this.mShapEditView.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditListener());
        this.mLineEditView.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditListener());
        this.mMappingView.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditListener());
        this.mExitEditView.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditListener());
    }

    // 显示地物编辑弹出菜单
    public void showShapPopupWindow(View parentView) {
        if (this.mShapPopupWindow == null) {
            View popupLayout = LayoutInflater.from(this.mContext).inflate(R.layout.shap_popup_menu, null);;
            MyListView myListView = (MyListView) popupLayout.findViewById(R.id.shap_view);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.mContext, android.R.layout.simple_list_item_single_choice, this.mListItems);
            myListView.setAdapter(arrayAdapter);
            myListView.setItemsCanFocus(false);
            myListView.setOnItemClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditListener());

            this.mShapPopupWindow = new PopupWindow(popupLayout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            this.mShapPopupWindow.setBackgroundDrawable(new ColorDrawable());
            this.mShapPopupWindow.setOutsideTouchable(true);
        } else {

        }

        this.mShapPopupWindow.showAsDropDown(parentView);
    }

    // 关闭地物编辑弹出菜单
    public void closeShapPopupWindow() {
        this.mShapPopupWindow.dismiss();
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
