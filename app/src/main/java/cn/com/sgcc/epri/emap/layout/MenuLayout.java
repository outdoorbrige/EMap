package cn.com.sgcc.epri.emap.layout;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.MenuListener;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 菜单布局类
 */
public class MenuLayout extends TransmitContext {
    private LinearLayout layout; // 布局对象
    private Button favorite_btn; // 收藏按钮
    private Button clear_btn; // 删除按钮
    private Button set_btn; // 配置按钮
    private Button offline_btn; // 离线下载按钮
    private Button tool_btn; // 工具按钮
    private Button main_btn; // 菜单按钮

    private ArrayList<Button> list_buttons = new ArrayList<Button>(); // 展开和折叠菜单中控制的按钮集合

    // 构造函数
    public MenuLayout(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        layout = (LinearLayout)context.findViewById(R.id.emap_menu_layout);
        favorite_btn = (Button)context.findViewById(R.id.emap_setting_menu_favorite_btn);
        clear_btn = (Button)context.findViewById(R.id.emap_setting_menu_clear_btn);
        set_btn = (Button)context.findViewById(R.id.emap_setting_menu_set_btn);
        offline_btn = (Button)context.findViewById(R.id.emap_setting_menu_offline_btn);
        tool_btn = (Button)context.findViewById(R.id.emap_setting_menu_tool_btn);
        main_btn = (Button)context.findViewById(R.id.emap_setting_menu_main_btn);

        list_buttons.add(tool_btn);
        list_buttons.add(offline_btn);
        list_buttons.add(set_btn);
        list_buttons.add(clear_btn);
        list_buttons.add(favorite_btn);

        MenuListener listener = new MenuListener(context, list_buttons);

        favorite_btn.setOnClickListener(listener);
        clear_btn.setOnClickListener(listener);
        set_btn.setOnClickListener(listener);
        offline_btn.setOnClickListener(listener);
        tool_btn.setOnClickListener(listener);
        main_btn.setOnClickListener(listener);
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
