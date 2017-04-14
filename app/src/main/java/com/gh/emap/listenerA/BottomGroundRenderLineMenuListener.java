package com.gh.emap.listenerA;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.fileA.RWLineFile;
import com.gh.emap.graphicA.MyCoordinate;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.overlayA.LineObject;
import com.gh.emap.overlayA.LineOverlay;
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
            case R.id.line_menu_undo: // 撤销
                onClickedUndo(view);
                break;
            case R.id.line_menu_redo: // 重绘
                onClickedRedo(view);
                break;
            case R.id.line_menu_add: // 添加
                onClickedAdd(view);
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
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderLineMenuLayout().hide();

        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().setLineType(
                mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().getOldLineType());

        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof LineOverlay) {
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

        if(overlay instanceof LineOverlay) {
            LineOverlay lineOverlay = (LineOverlay)overlay;
            ArrayList<MyCoordinate> myCoordinates = lineOverlay.getLineObject().getMyCoordinates();
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

        if(overlay instanceof LineOverlay) {
            LineOverlay lineOverlay = (LineOverlay)overlay;
            ArrayList<MyCoordinate> myCoordinates = lineOverlay.getLineObject().getMyCoordinates();
            if(myCoordinates == null || myCoordinates.isEmpty()) {
                return;
            }

            myCoordinates.clear();

            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }

    // 添加
    private void onClickedAdd(View view) {
        mMainActivity.getMainManager().getLayoutManager().getGroundRenderLineAddGeoPointLayout().show();
    }

    // 保存
    private void onClickedLineSave(View view) {
        String lineName = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().getLineName();
        if(lineName.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("请输入线-名称"));
            return;
        }

        // 检查线名是否存在
        if(LineNameExist(lineName)) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("线-名称已存在"));
            return;
        }

        String lineType = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().getLineType();
        if(lineType.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("请选择线-类别"));
            return;
        }

        List<Overlay> overlays = mMainActivity.getMainManager().getMapManager().getMapView().getOverlays();
        LineOverlay currentLineOverlay = (LineOverlay)overlays.get(overlays.size() - 1);
        ArrayList<MyCoordinate> myCoordinates = currentLineOverlay.getLineObject().getMyCoordinates();

        if(myCoordinates == null || myCoordinates.size() < 2) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("请选择线的位置"));
            return;
        }

        String path = mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderLineMenuLayout().getGroundRenderLinePath();
        if(path == null || path.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, String.format("用户未登录!"));
            return;
        }

        // 保存线信息到文件

        LineObject lineObject = new LineObject();
        lineObject.getMyGraphicAttribute().setName(lineName);
        lineObject.getMyGraphicAttribute().setType(lineType);
        lineObject.setMyCoordinates(myCoordinates);

        String[] errorMsg = {""};
        RWLineFile.write(path + lineObject.getMyGraphicAttribute().getName() +
                mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderLineMenuLayout().getGroundRenderLineFileSuffix(), lineObject, errorMsg);

        mMainActivity.getMainManager().getMyUserOverlaysManager().addLineObject(lineObject);

        LineOverlay lineOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlays().get(
                mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlays().size() - 1);

        lineOverlay.setEditStatus(false);

        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(lineOverlay);
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new LineOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();

        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().setLineName(
                "线" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlays().size() + 1));
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().setLineType("");
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().setOldLineType("");
    }

    // 检查线-名称是否存在
    boolean LineNameExist(String lineName) {
        for(int i = 0; i < mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlays().size(); i ++) {
            LineObject item = mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlays().get(i).getLineObject();
            if(item == null) {
                continue;
            }

            if(item.getMyGraphicAttribute().getName().equals(lineName)) {
                return true;
            }
        }

        return false;
    }
}
