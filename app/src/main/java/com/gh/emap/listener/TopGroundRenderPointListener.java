package com.gh.emap.listener;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.file.RWPointFile;
import com.gh.emap.manager.LogManager;
import com.gh.emap.overlay.PointObject;
import com.gh.emap.overlay.PointOverlay;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.Overlay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuHeng on 2016/11/15.
 */
public class TopGroundRenderPointListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public TopGroundRenderPointListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.point_type: // 画点类型
                onClickedPointType(view);
                break;
            case R.id.point_name: // 画点名称
                onClickedPointName(view);
                break;
            case R.id.point_cancel: // 取消
                onClickedPointCancel(view);
                break;
            case R.id.point_save: // 保存
                onClickedPointSave(view);
                break;
            default:
                break;
        }
    }

    // 画点类型
    private void onClickedPointType(View view) {
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getOperationLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointLayout().show();

        ((TextView)mMainActivity.findViewById(R.id.point_type)).setText(
                (String)mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointLayout().getWheelViewTwo().getSelectionItem());
    }

    // 画点名称
    private void onClickedPointName(View view) {

    }

    // 取消
    private void onClickedPointCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointLayout().clear();
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getOperationLayout().show();

        // 删除覆盖物
        List<Overlay> overlays = mMainActivity.getMainManager().getMapManager().getMapView().getOverlays();
        overlays.remove(overlays.size() - 1);

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 保存
    private void onClickedPointSave(View view) {
        String pointName = ((EditText)mMainActivity.findViewById(R.id.point_name)).getText().toString();
        if(pointName.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请输入名称"));
            return;
        }

        // 检查点名是否存在
        if(PointNameExist(pointName)) {
            mMainActivity.getMainManager().getLogManager().show(String.format("点-名称已存在"));
            return;
        }

        String pointType = ((TextView)mMainActivity.findViewById(R.id.point_type)).getText().toString();
        if(pointType.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请选择类别"));
            return;
        }

        List<Overlay> overlays = mMainActivity.getMainManager().getMapManager().getMapView().getOverlays();
        PointOverlay currentPointOverlay = (PointOverlay)overlays.get(overlays.size() - 1);
        GeoPoint point = currentPointOverlay.getGeoPoint();
        overlays.remove(overlays.size() - 1);

        if(point == null) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请选择点的位置"));
            return;
        }

        String path = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().getGroundRenderPointPath();
        if(path == null || path.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, String.format("用户未登录!"));
            return;
        }

        // 保存点信息到文件

        PointObject pointObject = new PointObject();
        pointObject.setType(pointType);
        pointObject.setName(pointName);
        pointObject.setGeoPoint(point);

        RWPointFile.write(path + File.separator + pointObject.getName() +
                mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().getGroundRenderPointFileSuffix(), pointObject);

        mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().add(pointObject);

        PointOverlay pointOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().getPointOverlay(
                mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().size() - 1);

        pointOverlay.setEditStatus(false);

        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(pointOverlay);
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new PointOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();

        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointLayout().clear();
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getOperationLayout().show();

        // 设置默认点的名称
        EditText defaultPointName = (EditText)(mMainActivity.findViewById(R.id.point_name));
        defaultPointName.setText("点" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().size() + 1));
    }

    // 检查点-名称是否存在
    boolean PointNameExist(String pointName) {
        ArrayList<PointObject> items = mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().getPointObjects();
        if(items == null) {
            return false;
        }

        for(int i = 0; i < items.size(); i ++) {
            PointObject item = items.get(i);
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
