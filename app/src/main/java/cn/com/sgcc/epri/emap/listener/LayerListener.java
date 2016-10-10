package cn.com.sgcc.epri.emap.listener;

import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.layout.LayerLayout;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 地图类型切换监听器
 */
public class LayerListener extends TransmitContext implements View.OnClickListener {
    private LayerLayout layer_layout;

    // 构造函数
    public LayerListener(MainActivity context, LayerLayout layer_layout) {
        super(context);
        this.layer_layout = layer_layout;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.emap_meun_layer_btn:
                layer_layout.showPopupWindow(view);
                break;
            default:
                break;
        }
    }
}
