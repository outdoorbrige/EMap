package com.gh.emap.listener;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.file.RWLineFile;
import com.gh.emap.manager.LogManager;
import com.gh.emap.overlay.LineObject;
import com.gh.emap.overlay.LineOverlay;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.Overlay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuHeng on 2017/3/1.
 */

public class BottomGroundRenderLineMenuListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public BottomGroundRenderLineMenuListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.line_menu_cancel: // 取消
                onClickedLineCancel(view);
                break;
            case R.id.line_menu_save: // 保存
                onClickedLineSave(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedLineCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderLineMenuLayout().hide();

        ((TextView)mMainActivity.findViewById(R.id.line_type)).setText(
                mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().getOldLineType());

        // 删除覆盖物
        List<Overlay> overlays = mMainActivity.getMainManager().getMapManager().getMapView().getOverlays();
        overlays.remove(overlays.size() - 1);

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 保存
    private void onClickedLineSave(View view) {
        String lineName = ((EditText)mMainActivity.findViewById(R.id.line_name)).getText().toString();
        if(lineName.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请输入线-名称"));
            return;
        }

        // 检查线名是否存在
        if(LineNameExist(lineName)) {
            mMainActivity.getMainManager().getLogManager().show(String.format("线-名称已存在"));
            return;
        }

        String lineType = ((TextView)mMainActivity.findViewById(R.id.line_type)).getText().toString();
        if(lineType.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请选择线-类别"));
            return;
        }

        List<Overlay> overlays = mMainActivity.getMainManager().getMapManager().getMapView().getOverlays();
        LineOverlay currentLineOverlay = (LineOverlay)overlays.get(overlays.size() - 1);
        ArrayList<GeoPoint> points = currentLineOverlay.getPoints();
        overlays.remove(overlays.size() - 1);

        if(points == null || points.size() < 2) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请选择线的位置"));
            return;
        }

        String path = mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderLineMenuLayout().getGroundRenderLinePath();
        if(path == null || path.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, String.format("用户未登录!"));
            return;
        }

        // 保存线信息到文件

        LineObject lineObject = new LineObject();
        lineObject.setType(lineType);
        lineObject.setName(lineName);
        lineObject.addGeoPoints(points);

        RWLineFile.write(path + File.separator + lineObject.getName() +
                mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderLineMenuLayout().getGroundRenderLineFileSuffix(), lineObject);

        mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().add(lineObject);

        LineOverlay lineOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().getLineOverlay(
                mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().size() - 1);

        lineOverlay.setEditStatus(false);

        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(lineOverlay);
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new LineOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();

        // 设置默认线的名称
        EditText defaultLineName = (EditText)(mMainActivity.findViewById(R.id.line_name));
        defaultLineName.setText("线" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().size() + 1));
    }

    // 检查线-名称是否存在
    boolean LineNameExist(String lineName) {
        ArrayList<LineObject> items = mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().getLineObjects();
        if(items == null) {
            return false;
        }

        for(int i = 0; i < items.size(); i ++) {
            LineObject item = items.get(i);
            if(item == null) {
                continue;
            }

            if(item.getName().equals(lineName)) {
                return true;
            }
        }

        return false;
    }
}
