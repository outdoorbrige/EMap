package com.gh.emap.listener;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.file.RWPlaneFile;
import com.gh.emap.manager.LogManager;
import com.gh.emap.overlay.PlaneObject;
import com.gh.emap.overlay.PlaneOverlay;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.overlay.PolygonOverlay;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/17.
 */

public class TopGroundRenderPlaneListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public TopGroundRenderPlaneListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plane_type: // 画面类型
                onClickedPlaneType(view);
                break;
            case R.id.plane_name: // 画面名称
                onClickedPlaneName(view);
                break;
            case R.id.plane_cancel: // 取消
                onClickedPlaneCancel(view);
                break;
            case R.id.plane_save: // 保存
                onClickedPlaneSave(view);
                break;
            default:
                break;
        }
    }

    // 画面类型
    private void onClickedPlaneType(View view) {
//        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().hide();
//        mMainActivity.getMainManager().getLayoutManager().getOperationLayout().hide();
//        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneLayout().show();
//
//        ((TextView)mMainActivity.findViewById(R.id.plane_type)).setText(
//                (String)mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneLayout().getWheelViewTwo().getSelectionItem());
    }

    // 画面名称
    private void onClickedPlaneName(View view) {

    }

    // 取消
    private void onClickedPlaneCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().hide();
//        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneLayout().hide();
//        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneLayout().clear();
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getOperationLayout().show();

        // 删除画面覆盖物
        PlaneOverlay planeOverlay = mMainActivity.getMainManager().getOverlayManager().getPlaneOverlay();
        planeOverlay.getPoints().clear();
        mMainActivity.getMainManager().getMapManager().getMapView().removeOverlay(planeOverlay);
        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 保存
    private void onClickedPlaneSave(View view) {
        String planeName = ((EditText) mMainActivity.findViewById(R.id.plane_name)).getText().toString();
        if (planeName.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请输入面-名称"));
            return;
        }

        // 检查面名是否存在
        if (PlaneNameExist(planeName)) {
            mMainActivity.getMainManager().getLogManager().show(String.format("面-名称已存在"));
            return;
        }

        String planeType = ((TextView) mMainActivity.findViewById(R.id.plane_type)).getText().toString();
//        if(planeType.isEmpty()) {
//            mMainActivity.getMainManager().getLogManager().show(String.format("请选择面-类型"));
//            return;
//        }

        ArrayList<GeoPoint> points = mMainActivity.getMainManager().getOverlayManager().getPlaneOverlay().getPoints();
        if (points == null || points.size() < 3) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请选择面的位置"));
            return;
        }

        String path = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().getGroundRenderPlanePath();
        if (path == null || path.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, String.format("用户未登录!"));
            return;
        }

        // 保存面信息到文件

        PlaneObject planeObject = new PlaneObject();
        planeObject.setType(planeType);
        planeObject.setName(planeName);
        planeObject.addGeoPoints(points);

        RWPlaneFile.write(path + File.separator + planeObject.getName() +
                mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().getGroundRenderPlaneFileSuffix(), planeObject);

        mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().add(planeObject);

        PlaneOverlay planeOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().getPlaneOverlay(
                mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().size() - 1);

        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(planeOverlay);

        mMainActivity.getMainManager().getOverlayManager().getPlaneOverlay().getPoints().clear();
        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();

        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().show();
//        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneLayout().hide();
//        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneLayout().clear();
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getOperationLayout().show();

        // 设置默认面的名称
        EditText defaultPlaneName = (EditText) (mMainActivity.findViewById(R.id.plane_name));
        defaultPlaneName.setText("面" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().size() + 1));
    }

    // 检查面-名称是否存在
    boolean PlaneNameExist(String planeName) {
        ArrayList<PlaneObject> items = mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().getPlaneObjects();
        if (items == null) {
            return false;
        }

        for (int i = 0; i < items.size(); i++) {
            PlaneObject item = items.get(i);
            if (item == null) {
                continue;
            }

            if (item.getName().equals(planeName)) {
                return true;
            }
        }

        return false;
    }
}
