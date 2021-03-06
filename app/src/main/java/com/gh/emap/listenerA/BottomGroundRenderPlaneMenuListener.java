package com.gh.emap.listenerA;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.fileA.RWPlaneFile;
import com.gh.emap.graphicA.MyCoordinate;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.overlayA.PlaneObject;
import com.gh.emap.overlayA.PlaneOverlay;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.Overlay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuHeng on 2017/3/1.
 */

public class BottomGroundRenderPlaneMenuListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public BottomGroundRenderPlaneMenuListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plane_menu_cancel: // 取消
                onClickedCancel(view);
                break;
            case R.id.plane_menu_undo: // 撤销
                onClickedUndo(view);
                break;
            case R.id.plane_menu_redo: // 重绘
                onClickedRedo(view);
                break;
            case R.id.plane_menu_add: // 添加
                onClickedAdd(view);
                break;
            case R.id.plane_menu_save: // 保存
                onClickedSave(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneMenuLayout().hide();

        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof PlaneOverlay) {
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

        if(overlay instanceof PlaneOverlay) {
            PlaneOverlay planeOverlay = (PlaneOverlay)overlay;
            ArrayList<MyCoordinate> myCoordinates = planeOverlay.getPlaneObject().getMyCoordinates();
            if(myCoordinates == null || myCoordinates.isEmpty()) {
                return;
            }

            myCoordinates.remove(myCoordinates.size() - 1);

            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }

    // 重绘
    private void onClickedRedo(View view) {
        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof PlaneOverlay) {
            PlaneOverlay planeOverlay = (PlaneOverlay)overlay;
            ArrayList<MyCoordinate> myCoordinates = planeOverlay.getPlaneObject().getMyCoordinates();
            if(myCoordinates == null || myCoordinates.isEmpty()) {
                return;
            }

            myCoordinates.clear();

            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }

    // 添加
    private void onClickedAdd(View view) {
        mMainActivity.getMainManager().getLayoutManager().getGroundRenderPlaneAddGeoPointLayout().show();
    }

    // 保存
    private void onClickedSave(View view) {
        String planeName = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().getPlaneName();
        if (planeName.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("请输入面-名称"));
            return;
        }

        // 检查面名是否存在
        if (PlaneNameExist(planeName)) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("面-名称已存在"));
            return;
        }

        List<Overlay> overlays = mMainActivity.getMainManager().getMapManager().getMapView().getOverlays();
        PlaneOverlay currentPlaneOverlay = (PlaneOverlay)overlays.get(overlays.size() - 1);
        ArrayList<MyCoordinate> myCoordinates = currentPlaneOverlay.getPlaneObject().getMyCoordinates();

        if (myCoordinates == null || myCoordinates.size() < 3) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("请选择面的位置"));
            return;
        }

        String path = mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneMenuLayout().getGroundRenderPlanePath();
        if (path == null || path.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, String.format("用户未登录!"));
            return;
        }

        // 保存面信息到文件

        PlaneObject planeObject = new PlaneObject();
        planeObject.getMyGraphicAttribute().setName(planeName);
        planeObject.setMyCoordinates(myCoordinates);

        String[] errorMsg = {""};
        RWPlaneFile.write(path +  planeObject.getMyGraphicAttribute().getName() +
                mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneMenuLayout().getGroundRenderPlaneFileSuffix(), planeObject, errorMsg);

        mMainActivity.getMainManager().getMyUserOverlaysManager().addPlaneObject(planeObject);

        PlaneOverlay planeOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlays().get(
                mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlays().size() - 1);

        planeOverlay.setEditStatus(false);

        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(planeOverlay);
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new PlaneOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();

        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().setPlaneName(
                "面" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlays().size() + 1));
    }

    // 检查面-名称是否存在
    boolean PlaneNameExist(String planeName) {
        for (int i = 0; i < mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlays().size(); i++) {
            PlaneObject item = mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlays().get(i).getPlaneObject();
            if (item == null) {
                continue;
            }

            if (item.getMyGraphicAttribute().getName().equals(planeName)) {
                return true;
            }
        }

        return false;
    }
}
