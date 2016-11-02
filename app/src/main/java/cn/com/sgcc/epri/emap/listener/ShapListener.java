package cn.com.sgcc.epri.emap.listener;

import android.view.View;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.base.MainActivityContext;
import cn.com.sgcc.epri.emap.layout.ShapLayout;

/**
 * Created by GuHeng on 2016/11/2.
 */
public class ShapListener extends MainActivityContext implements View.OnClickListener {
    private ShapLayout mShapLayout;

    // 构造函数
    public ShapListener(MainActivity mainActivity, ShapLayout shapLayout) {
        super(mainActivity);
        mShapLayout = shapLayout;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.menu_shap:
                mShapLayout.showPopupWindow(view);
                break;
            default:
                break;
        }
    }
}
