package com.gh.emap.listenerA;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/1/9.
 */

public class TopGroundRenderLineListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public TopGroundRenderLineListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.line_name: // 画线名称
                onClickedLineName(view);
                break;
            case R.id.line_type: // 画线类型
                onClickedLineType(view);
                break;
            default:
                break;
        }
    }

    // 画线名称
    private void onClickedLineName(View view) {

    }

    // 画线类型
    private void onClickedLineType(View view) {
        String oldLineType = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().getOldLineType();
        int oneIndex = mMainActivity.getMainManager().getFileManager().getGroundRenderLineFile().getShapLine().getKeyIndex(oldLineType);
        int twoIndex = mMainActivity.getMainManager().getFileManager().getGroundRenderLineFile().getShapLine().getValueIndex(oldLineType);

        mMainActivity.getMainManager().getLayoutManager().getGroundRenderLineTypeLayout().show(oneIndex, twoIndex);
    }
}
