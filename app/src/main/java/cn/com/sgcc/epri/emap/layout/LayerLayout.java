package cn.com.sgcc.epri.emap.layout;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;

import org.apache.log4j.Logger;

import java.util.List;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.LayerListViewListener;
import cn.com.sgcc.epri.emap.listener.LayerListener;
import cn.com.sgcc.epri.emap.util.ListViewAdaptWidth;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 地图类型切换布局类
 */
public class LayerLayout extends TransmitContext {
    LayoutInflater layout_inflater;
    private View layout; // 布局
    private Button layer_btn; // 地图类型切换按钮
    private PopupWindow popup_window; // 弹出式菜单
    private String[] list_items = {"影像图", "矢量图", "地形图"};
    private int selected_item_id; // 被选择的菜单项id

    // 构造函数
    public LayerLayout(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        layout_inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = context.findViewById(R.id.emap_layer_layout);
        layer_btn = (Button) context.findViewById(R.id.emap_meun_layer_btn);
        layer_btn.setOnClickListener(new LayerListener(context, this));
        selected_item_id = 1; // 默认选中第二项(矢量图)

        Logger.getLogger(this.getClass()).info(String.format("%s,%s,%s", list_items[0], list_items[1], list_items[2]));
    }

    // 显示弹出菜单
    public void showPopupWindow(View view) {
        if (popup_window == null) {
            View popup_view = LayoutInflater.from(context).inflate(R.layout.emap_menu_layer_popup_window, null);
            ListViewAdaptWidth list_view = (ListViewAdaptWidth) popup_view.findViewById(R.id.emap_menu_layer_popup_list_view);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_single_choice, list_items);
            list_view.setAdapter(adapter);
            list_view.setItemsCanFocus(false);
            list_view.setOnItemClickListener(new LayerListViewListener(context, this));
            list_view.setItemChecked(getSelectedItemId(), true);

            popup_window = new PopupWindow(popup_view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popup_window.setBackgroundDrawable(new ColorDrawable());
            popup_window.setOutsideTouchable(true);
        }

        popup_window.showAsDropDown(view);
    }

    // 关闭弹出菜单
    public void closePopupWindow() {
        popup_window.dismiss();
    }

    // 设置选中的菜单项id
    public void setSelectedItemId(int id) {
        selected_item_id = id;
    }

    // 获取选中的菜单项id
    public int getSelectedItemId() {
        return selected_item_id;
    }

    // 显示布局
    public void show() {
        layout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置
        layout.setVisibility(View.GONE);
    }
}
