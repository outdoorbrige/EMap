package cn.com.sgcc.epri.emap.listener;

import android.view.View;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 地图类型切换监听器
 */
public class LayerListener extends TransmitContext implements View.OnClickListener {

    // 构造函数
    public LayerListener(MainActivity context) {
        super(context);
    }

    @Override
    public void onClick(View view) {

    }
}
