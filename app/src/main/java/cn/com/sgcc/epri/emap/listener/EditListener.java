package cn.com.sgcc.epri.emap.listener;

import android.view.View;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.base.BaseAnimation;

/**
 * Created by GuHeng on 2016/10/31.
 */
public class EditListener extends BaseAnimation implements View.OnClickListener {

    // 构造函数
    public EditListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_shap:
                mMainActivity.getLayoutManger().getEditLayout().getShapLayout().showPopupWindow(view);
                break;
            case R.id.menu_line:
                break;
            case R.id.menu_mapping:
                break;
            case R.id.menu_exit:
                mMainActivity.getLayoutManger().getSearchLayout().show();
                mMainActivity.getLayoutManger().getMenuLayout().show();
                mMainActivity.getLayoutManger().getEditLayout().hide();
                break;
            default:
                break;
        }
    }
}
