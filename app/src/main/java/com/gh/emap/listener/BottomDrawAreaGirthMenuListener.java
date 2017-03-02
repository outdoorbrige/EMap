package com.gh.emap.listener;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.tianditu.android.maps.Overlay;

import java.util.List;

/**
 * Created by GuHeng on 2017/2/21.
 */

public class BottomDrawAreaGirthMenuListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public BottomDrawAreaGirthMenuListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.area_girth_menu_cancel: // 取消
                onClickedCancel(view);
                break;
            case R.id.area_girth_menu_undo: // 撤销
                onClickedUndo(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getBottomDrawAreaGirthMenuLayout().hide();

        // 删除覆盖物
        List<Overlay> overlays = mMainActivity.getMainManager().getMapManager().getMapView().getOverlays();
        overlays.remove(overlays.size() - 1);

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 撤销
    private void onClickedUndo(View view) {

    }
}
