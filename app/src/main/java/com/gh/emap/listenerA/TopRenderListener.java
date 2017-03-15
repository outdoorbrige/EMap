package com.gh.emap.listenerA;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class TopRenderListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public TopRenderListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_ground_render: // 地物绘制
                onClickedGroundRender(view);
                break;
            case R.id.menu_line_render: // 线路绘制
                onClickedLineRender(view);
                break;
            case R.id.menu_draw: // 测绘
                onClickedDraw(view);
                break;
            case R.id.menu_exit_render: // 退出绘制
                onClickedExitRender(view);
                break;
            default:
                break;
        }
    }

    // 地物绘制
    private void onClickedGroundRender(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().popupGroundRenderMenu(view);
    }

    // 线路绘制
    private void onClickedLineRender(View view) {
    }

    // 测绘
    private void onClickedDraw(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().popupDrawMenu(view);
    }

    // 退出绘制
    private void onClickedExitRender(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopNormalLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().hide();
    }
}
