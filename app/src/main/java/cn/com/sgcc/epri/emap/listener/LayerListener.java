package cn.com.sgcc.epri.emap.listener;

import android.view.View;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.base.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 地图类型切换监听器
 */
public class LayerListener extends MainActivityContext implements View.OnClickListener {

    // 构造函数
    public LayerListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.layer_button:
                mMainActivity.getLayoutManger().getLayerLayout().showPopupWindow(view);
                break;
            default:
                break;
        }
    }
}
