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
import com.gh.emap.overlay.PointOverlayItem;
import com.tianditu.android.maps.GeoPoint;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2016/11/15.
 */
public class TopEditPointListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public TopEditPointListener(MainActivity mainActivity) {
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
        mMainActivity.getMainManager().getLayoutManager().getBottomShapPointLayout().show();

        ((TextView)mMainActivity.findViewById(R.id.point_type)).setText(
                (String)mMainActivity.getMainManager().getLayoutManager().getBottomShapPointLayout().getWheelViewTwo().getSelectionItem());
    }

    // 画点名称
    private void onClickedPointName(View view) {

    }

    // 取消
    private void onClickedPointCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopShapPointLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomShapPointLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomShapPointLayout().clear();
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getOperationLayout().show();

        // 删除画点覆盖物
        PointOverlay pointOverlay = mMainActivity.getMainManager().getOverlayManager().getPointOverlay();
        mMainActivity.getMainManager().getMapManager().getMapView().removeOverlay(pointOverlay);
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

        GeoPoint point = mMainActivity.getMainManager().getOverlayManager().getPointOverlay().getGeoPoint();
        if(point == null) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请选择点的位置"));
            return;
        }

        String path = mMainActivity.getMainManager().getLayoutManager().getTopShapPointLayout().getShapPointPath();
        if(path == null || path.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(getClass(), LogManager.LogLevel.mError, String.format("用户未登录!"));
            return;
        } else {
            // 保存点信息到文件

            PointOverlayItem overlay = new PointOverlayItem(mMainActivity, "", "", point.getLatitudeE6(), point.getLongitudeE6());
            overlay.getPointObject().setType(pointType);
            overlay.getPointObject().setName(pointName);

            mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().put(overlay);
            mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().populate();

            RWPointFile.write(path + File.separator + overlay.getPointObject().getName() + ".p", overlay.getPointObject());

            mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(
                    mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems());
            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }

        mMainActivity.getMainManager().getLayoutManager().getTopShapPointLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getBottomShapPointLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomShapPointLayout().clear();
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getOperationLayout().show();

        // 设置默认点的名称
        EditText defaultPointName = (EditText)(mMainActivity.findViewById(R.id.point_name));
        defaultPointName.setText("点" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().size() + 1));
    }

    // 检查点-名称是否存在
    boolean PointNameExist(String pointName) {
        ArrayList<PointOverlayItem> items = mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().getItems();
        if(items == null) {
            return false;
        }

        for(int i = 0; i < items.size(); i ++) {
            PointOverlayItem item = items.get(i);
            if(item == null) {
                continue;
            }

            PointObject pointObject = item.getPointObject();
            if(pointObject == null) {
                continue;
            }

            if(pointObject.getName().equals(pointName)) {
                return true;
            }
        }

        return false;
    }
}
