package cn.com.sgcc.epri.emap.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.list.AdaptWidthListView;

/**
 * Created by GuHeng on 2016/11/1.
 * 单选ListView布局
 */
public class SingleListLayout extends BaseLayout {
    LayoutInflater mLayoutInflater;
    private PopupWindow mPopupWindow; // 弹出式菜单
    private String[] mListItems; // ListView菜单项
    private int mSelectedItemId; // 被选择的菜单项id
    private AdapterView.OnItemClickListener mListener;

    // 构造函数
    public SingleListLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init(String[] listItems, int selectedItemId, AdapterView.OnItemClickListener listener) {
        mLayoutInflater = (LayoutInflater) mMainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListItems = listItems;
        mSelectedItemId = selectedItemId;
        mListener = listener;
    }

    // 显示弹出菜单
    public void showPopupWindow(View parentView) {
        if (mPopupWindow == null) {
            View popupView = LayoutInflater.from(mMainActivity).inflate(R.layout.single_list, null);
            AdaptWidthListView adaptWidthListView = (AdaptWidthListView) popupView.findViewById(R.id.adapt_width_list);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mMainActivity, android.R.layout.simple_list_item_single_choice, mListItems);
            adaptWidthListView.setAdapter(arrayAdapter);
            adaptWidthListView.setItemsCanFocus(false);
            adaptWidthListView.setOnItemClickListener(mListener);
            adaptWidthListView.setItemChecked(getSelectedItemId(), true);

            mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            mPopupWindow.setOutsideTouchable(true);
        }

        mPopupWindow.showAsDropDown(parentView);
    }

    // 关闭弹出菜单
    public void closePopupWindow() {
        mPopupWindow.dismiss();
    }

    // 设置选中的菜单项id
    public void setSelectedItemId(int id) {
        mSelectedItemId = id;
    }

    // 获取选中的菜单项id
    public int getSelectedItemId() {
        return mSelectedItemId;
    }
}
