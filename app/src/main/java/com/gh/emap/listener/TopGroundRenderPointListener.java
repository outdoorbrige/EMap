package com.gh.emap.listener;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2016/11/15.
 */
public class TopGroundRenderPointListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public TopGroundRenderPointListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.point_name: // 画点名称
                onClickedPointName(view);
                break;
            case R.id.point_type: // 画点类型
                onClickedPointType(view);
                break;
            default:
                break;
        }
    }

    // 画点名称
    private void onClickedPointName(View view) {

    }

    // 画点类型
    private void onClickedPointType(View view) {
        String oldPointType = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().getOldPointType();
        int oneIndex = mMainActivity.getMainManager().getFileManager().getGroundRenderPointFile().getShapPoint().getKeyIndex(oldPointType);
        int twoIndex = mMainActivity.getMainManager().getFileManager().getGroundRenderPointFile().getShapPoint().getValueIndex(oldPointType);

        mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointTypeLayout().show(oneIndex, twoIndex);
    }

}
