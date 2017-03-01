package com.gh.emap.listener;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/1/17.
 */

public class TopGroundRenderPlaneListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public TopGroundRenderPlaneListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plane_name: // 画面名称
                onClickedPlaneName(view);
                break;
            default:
                break;
        }
    }

    // 画面名称
    private void onClickedPlaneName(View view) {

    }
}
