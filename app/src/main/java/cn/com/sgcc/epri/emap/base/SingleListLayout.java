package cn.com.sgcc.epri.emap.base;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.view.AdaptWidthListView;
import cn.com.sgcc.epri.emap.util.Log4jLevel;

/**
 * Created by GuHeng on 2016/11/1.
 * 单选ListView布局
 */
public class SingleListLayout extends BaseLayout {
    private PopupWindow mPopupWindow; // 弹出式菜单
    View popupView;
    AdaptWidthListView adaptWidthListView;
    ArrayAdapter<String> arrayAdapter;
    private String[] mListItems; // ListView菜单项
    private int mDefaultSelectItemIndex = -1; // 默认选中的Item索引
    private int mCurrentSelectItemIndex = -1; // 当前选中的Item索引
    private AdapterView.OnItemClickListener mListener;

    // 构造函数
    public SingleListLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 设置List元素
    public void setListItems(String[] listItems) {
        mListItems = listItems;
    }

    // 设置默认选中的Item索引
    public void setDefaultSelectItemIndex(int index) {
        mDefaultSelectItemIndex = index;
    }

    // 设置监听器
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
    }

    // 设置当前选中的Item索引
    public void setCurrentSelectItemIndex(int index) {
        mCurrentSelectItemIndex = index;
    }

    // 获取当前选中的Item索引
    public int getCurrentSelectItemIndex() {
        return mCurrentSelectItemIndex;
    }

    // 取消选中的Item
    public void cancelSelectedItem(int index) {
        if(adaptWidthListView != null) {
            adaptWidthListView.setItemChecked(mCurrentSelectItemIndex, false);
        }
    }

    // 显示弹出菜单
    public void showPopupWindow(View parentView) {
        if (mPopupWindow == null) {
            popupView = LayoutInflater.from(mMainActivity).inflate(R.layout.single_list_layout, null);
            adaptWidthListView = (AdaptWidthListView) popupView.findViewById(R.id.adapt_width_list);
            arrayAdapter = new ArrayAdapter<String>(mMainActivity, android.R.layout.simple_list_item_single_choice, mListItems);
            adaptWidthListView.setAdapter(arrayAdapter);
            adaptWidthListView.setItemsCanFocus(false);
            adaptWidthListView.setOnItemClickListener(mListener);

            if(mDefaultSelectItemIndex == -1) {
                if(0 <= mCurrentSelectItemIndex && mCurrentSelectItemIndex < mListItems.length) {
                    adaptWidthListView.setItemChecked(mCurrentSelectItemIndex, false);
                }
            } else if(0 <= mDefaultSelectItemIndex && mDefaultSelectItemIndex < mListItems.length) {
                adaptWidthListView.setItemChecked(mDefaultSelectItemIndex, true);
                setCurrentSelectItemIndex(mDefaultSelectItemIndex);
            } else {
                mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError,
                        String.format("传入的ListView Item Id[%d]无效！", mDefaultSelectItemIndex));
            }

            mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            mPopupWindow.setOutsideTouchable(true);
        } else {
            if(mDefaultSelectItemIndex == -1) {
                if(0 <= mCurrentSelectItemIndex && mCurrentSelectItemIndex < mListItems.length) {
                    cancelSelectedItem(mCurrentSelectItemIndex);
                }
            }
        }

        mPopupWindow.showAsDropDown(parentView);
    }

    // 关闭弹出菜单
    public void closePopupWindow() {
        mPopupWindow.dismiss();
    }
}
