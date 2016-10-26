package cn.com.sgcc.epri.emap.layout;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.LayerListViewListener;
import cn.com.sgcc.epri.emap.listener.LayerListener;
import cn.com.sgcc.epri.emap.util.ListViewAdaptWidth;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 地图类型切换布局类
 */
public class LayerLayout extends MainActivityContext {
    LayoutInflater mLayoutInflater;
    private View mLayout; // 布局
    private Button mLayerButton; // 地图类型切换按钮
    private PopupWindow mPopupWindow; // 弹出式菜单
    private String[] mListItems = {"影像图", "矢量图", "地形图"};
    private int mSelectedItemId; // 被选择的菜单项id

    // 构造函数
    public LayerLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mLayoutInflater = (LayoutInflater) mMainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = mMainActivity.findViewById(R.id.layer);
        mLayerButton = (Button) mMainActivity.findViewById(R.id.layer_button);
        mLayerButton.setOnClickListener(new LayerListener(mMainActivity, this));
        mSelectedItemId = 1; // 默认选中第二项(矢量图)

        mMainActivity.getLog4jManger().log(this.getClass(), Log4jLevel.mInfo, String.format("%s,%s,%s", mListItems[0], mListItems[1], mListItems[2]));
    }

    // 显示弹出菜单
    public void showPopupWindow(View view) {
        if (mPopupWindow == null) {
            View popupView = LayoutInflater.from(mMainActivity).inflate(R.layout.layer_menu, null);
            ListViewAdaptWidth listViewAdaptWidth = (ListViewAdaptWidth) popupView.findViewById(R.id.layer_menu_list);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mMainActivity, android.R.layout.simple_list_item_single_choice, mListItems);
            listViewAdaptWidth.setAdapter(arrayAdapter);
            listViewAdaptWidth.setItemsCanFocus(false);
            listViewAdaptWidth.setOnItemClickListener(new LayerListViewListener(mMainActivity, this));
            listViewAdaptWidth.setItemChecked(getSelectedItemId(), true);

            mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            mPopupWindow.setOutsideTouchable(true);
        }

        mPopupWindow.showAsDropDown(view);
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
