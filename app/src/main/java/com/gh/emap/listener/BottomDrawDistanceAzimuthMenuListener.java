package com.gh.emap.listener;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.overlay.DistanceAzimuthOverlay;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.Overlay;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/2/21.
 */

public class BottomDrawDistanceAzimuthMenuListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public BottomDrawDistanceAzimuthMenuListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.distance_azimuth_menu_cancel: // 取消
                onClickedCancel(view);
                break;
            case R.id.distance_azimuth_menu_undo: // 撤销
                onClickedUndo(view);
                break;
            case R.id.distance_azimuth_menu_redo: // 重绘
                onClickedRedo(view);
                break;
            case R.id.distance_azimuth_menu_add: // 添加
                onClickedAdd(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getBottomDrawDistanceAzimuthMenuLayout().hide();

        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof DistanceAzimuthOverlay) {
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

        if(overlay instanceof DistanceAzimuthOverlay) {
            DistanceAzimuthOverlay distanceAzimuthOverlay = (DistanceAzimuthOverlay)overlay;
            ArrayList<GeoPoint> geoPoints = distanceAzimuthOverlay.getGeoPoints();
            if(geoPoints == null || geoPoints.isEmpty()) {
                return;
            }

            distanceAzimuthOverlay.removeLastGeoPoint();

            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }

    // 重绘
    private void onClickedRedo(View view) {
        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof DistanceAzimuthOverlay) {
            DistanceAzimuthOverlay distanceAzimuthOverlay = (DistanceAzimuthOverlay)overlay;
            ArrayList<GeoPoint> geoPoints = distanceAzimuthOverlay.getGeoPoints();
            if(geoPoints == null || geoPoints.isEmpty()) {
                return;
            }

            distanceAzimuthOverlay.clearGeoPoints();

            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }

    // 添加
    private void onClickedAdd(View view) {
        mMainActivity.getMainManager().getLayoutManager().getDrawDistanceAzimuthAddGeoPointLayout().show();
    }
}
