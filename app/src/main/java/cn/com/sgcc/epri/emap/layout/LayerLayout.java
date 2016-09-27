package cn.com.sgcc.epri.emap.layout;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.LayerListener;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 地图类型切换布局类
 */
public class LayerLayout extends TransmitContext {
    private LinearLayout layout; // 布局
    private Button layer_btn; // 地图类型切换按钮

    // 构造函数
    public LayerLayout(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        layout = (LinearLayout) context.findViewById(R.id.emap_layer_layout);
        layer_btn = (Button) context.findViewById(R.id.emap_meun_layer_btn);

        LayerListener listener = new LayerListener(context);

        layer_btn.setOnClickListener(listener);
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
