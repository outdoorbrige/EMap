package com.gh.emap.listenerA;

import android.view.View;
import android.widget.AdapterView;

import com.gh.emap.MainActivity;
import com.gh.emap.overlay.AreaGirthOverlay;
import com.gh.emap.overlay.DistanceAzimuthOverlay;

/**
 * Created by GuHeng on 2017/2/14.
 */

public class DrawListener implements AdapterView.OnItemClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public DrawListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case -1: // 点击的是headerView或者footerView
                break;
            case 0: // 测距与方位角
                onItemClickedDistanceAzimuth(parent, view, position, id);
                break;
            case 1: // 测面积与周长
                onItemClickedAreaGirth(parent, view, position, id);
                break;
            default:
                break;
        }

        if (-2 < position && position < parent.getCount()) {
            mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().setDrawSelectedIndex(position);
        }

        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().closeDrawMenu();
    }

    // 测距与方位角
    private void onItemClickedDistanceAzimuth(AdapterView<?> parent, View view, int position, long id) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomDrawDistanceAzimuthMenuLayout().show();

        // 添加覆盖物
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new DistanceAzimuthOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 测面积与周长
    private void onItemClickedAreaGirth(AdapterView<?> parent, View view, int position, long id) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomDrawAreaGirthMenuLayout().show();

        // 添加覆盖物
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new AreaGirthOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
    }
}
