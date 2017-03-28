package com.gh.emap.listenerA;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.fileA.RWPointFile;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.overlayA.PointObject;
import com.gh.emap.overlayA.PointOverlay;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.Overlay;

import java.io.File;
import java.util.List;

/**
 * Created by GuHeng on 2017/3/1.
 */

public class BottomGroundRenderPointMenuListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public BottomGroundRenderPointMenuListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.point_menu_cancel: // 取消
                onClickedPointCancel(view);
                break;
            case R.id.point_menu_add: // 添加
                onClickedAdd(view);
                break;
            case R.id.point_menu_save: // 保存
                onClickedPointSave(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedPointCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointMenuLayout().hide();

        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().setPointType(
                mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().getOldPointType());

        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof PointOverlay) {
            mMainActivity.getMainManager().getMapManager().getMapView().removeOverlay(overlay);
            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }

    // 添加
    private void onClickedAdd(View view) {
        mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointAddGeoPointLayout().show();
    }

    // 保存
    private void onClickedPointSave(View view) {
        String pointName = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().getPointName();
        if(pointName.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("请输入名称"));
            return;
        }

        // 检查点名是否存在
        if(PointNameExist(pointName)) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("点-名称已存在"));
            return;
        }

        String pointType = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().getPointType();
        if(pointType.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("请选择类别"));
            return;
        }

        List<Overlay> overlays = mMainActivity.getMainManager().getMapManager().getMapView().getOverlays();
        PointOverlay currentPointOverlay = (PointOverlay)overlays.get(overlays.size() - 1);
        GeoPoint point = currentPointOverlay.getPointObject().getGeoPoint();

        if(point == null) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("请选择点的位置"));
            return;
        }

        String path = mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointMenuLayout().getGroundRenderPointPath();
        if(path == null || path.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, String.format("用户未登录!"));
            return;
        }

        // 保存点信息到文件

        PointObject pointObject = new PointObject();
        pointObject.setName(pointName);
        pointObject.setType(pointType);
        pointObject.setGeoPoint(point);

        String[] errorMsg = {""};
        RWPointFile.write(path + File.separator + pointObject.getName() +
                mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointMenuLayout().getGroundRenderPointFileSuffix(), pointObject, errorMsg);

        mMainActivity.getMainManager().getMyUserOverlaysManager().addPointObject(pointObject);

        PointOverlay pointOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlays().get(
                mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlays().size() - 1);

        pointOverlay.setEditStatus(false);

        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(pointOverlay);
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new PointOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();

        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().setPointName(
                "点" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlays().size() + 1));
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().setPointType("");
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().setOldPointType("");
    }

    // 检查点-名称是否存在
    boolean PointNameExist(String pointName) {
        for(int i = 0; i < mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlays().size(); i ++) {
            PointObject item = mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlays().get(i).getPointObject();
            if(item == null) {
                continue;
            }

            if(item.getName().equals(pointName)) {
                return true;
            }
        }

        return false;
    }
}
