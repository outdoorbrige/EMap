package com.gh.emap.listener;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/2/21.
 */

public class TopDrawAreaGirthListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public TopDrawAreaGirthListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.area_girth_cancel: // 取消
                onClickedCancel(view);
                break;
            case R.id.area_girth_undo: // 撤销
                onClickedUndo(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopDrawAreaGirthLayout().hide();
    }

    // 撤销
    private void onClickedUndo(View view) {

    }
}
