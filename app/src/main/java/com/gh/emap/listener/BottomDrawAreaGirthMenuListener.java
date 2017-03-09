package com.gh.emap.listener;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.overlay.AreaGirthOverlay;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.Overlay;

import java.util.ArrayList;

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
            case R.id.area_girth_menu_redo: // 重绘
                onClickedRedo(view);
                break;
            case R.id.area_girth_menu_add: // 添加
                onClickedAdd(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getBottomDrawAreaGirthMenuLayout().hide();

        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof AreaGirthOverlay) {
            mMainActivity.getMainManager().getMapManager().getMapView().removeOverlay(overlay);
            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }

    // 撤销
    private void onClickedUndo(View view) {
        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof AreaGirthOverlay) {
            AreaGirthOverlay areaGirthOverlay = (AreaGirthOverlay)overlay;
            ArrayList<GeoPoint> geoPoints = areaGirthOverlay.getGeoPoints();
            if(geoPoints == null || geoPoints.isEmpty()) {
                return;
            }

            geoPoints.remove(geoPoints.size() - 1);

            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }

    // 重绘
    private void onClickedRedo(View view) {
        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof AreaGirthOverlay) {
            AreaGirthOverlay areaGirthOverlay = (AreaGirthOverlay)overlay;
            ArrayList<GeoPoint> geoPoints = areaGirthOverlay.getGeoPoints();
            if(geoPoints == null || geoPoints.isEmpty()) {
                return;
            }

            geoPoints.clear();

            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }

    // 添加
    private void onClickedAdd(View view) {
        mMainActivity.getMainManager().getLayoutManager().getDrawAreaGirthAddGeoPointLayout().show();
    }
}
