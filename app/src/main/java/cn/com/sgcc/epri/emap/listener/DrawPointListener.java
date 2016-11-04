package cn.com.sgcc.epri.emap.listener;

import android.view.View;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.base.MainActivityContext;

/**
 * Created by GuHeng on 2016/11/2.
 */
public class DrawPointListener extends MainActivityContext implements View.OnClickListener {

    // 构造函数
    public DrawPointListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.point_save:
                onClickedShapSave();
                break;
            default:
                break;
        }
    }

    private void onClickedShapSave() {
        mMainActivity.getLayoutManger().getEditLayout().getDrawPointLayout().hide();
    }
}
