package com.gh.emap.listenerA;

import android.view.View;
import android.widget.AdapterView;

import com.gh.emap.MainActivity;
import com.gh.emap.fileA.RWLineFile;
import com.gh.emap.fileA.RWPlaneFile;
import com.gh.emap.fileA.RWPointFile;
import com.gh.emap.fileA.OperateFolder;
import com.gh.emap.overlayA.LineObject;
import com.gh.emap.overlayA.LineOverlay;
import com.gh.emap.overlayA.PlaneObject;
import com.gh.emap.overlayA.PlaneOverlay;
import com.gh.emap.overlayA.PointObject;
import com.gh.emap.overlayA.PointOverlay;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2016/11/15.
 */
public class GroundRenderListener implements AdapterView.OnItemClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public GroundRenderListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case -1: // 点击的是headerView或者footerView
                break;
            case 0: // 画点
                onItemClickedPoint(parent, view, position, id);
                break;
            case 1: // 画线
                onItemClickedLine(parent, view, position, id);
                break;
            case 2: // 画面
                onItemClickedCircle(parent, view, position, id);
                break;
            default:
                break;
        }

        if(-2 < position && position < parent.getCount()) {
            mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().setGroundRenderSelectedIndex(position);
        }

        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().closeGroundRenderMenu();
    }

    // 画点
    private void onItemClickedPoint(AdapterView<?> parent, View view, int position, long id) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointMenuLayout().show();

        // 防止重复加载文件数据
        if(mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlays().size() == 0) {
            // 加载文件数据
            ArrayList<File> files = new ArrayList<>();
            OperateFolder.TraverseFindFlies(mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointMenuLayout().getGroundRenderPointPath(),
                    mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointMenuLayout().getGroundRenderPointFileSuffix(), files);

            // 解析点文件
            String[] errorMsg = {""};
            ArrayList<PointObject> pointObjects = RWPointFile.read(files, errorMsg);
            if (pointObjects != null) {
                mMainActivity.getMainManager().getMyUserOverlaysManager().addPointObjects(pointObjects);

                for (int i = 0; i < mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlays().size(); i ++) {
                    PointOverlay pointOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlays().get(i);
                    pointOverlay.setEditStatus(false);

                    // 添加已保存的覆盖物
                    mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(pointOverlay);
                }

                mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
            }
        }

        // 添加覆盖物
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new PointOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();

        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().setPointName(
                "点" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlays().size() + 1));
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().setPointType("");
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().setOldPointType("");
    }

    // 画线
    private void onItemClickedLine(AdapterView<?> parent, View view, int position, long id) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderLineMenuLayout().show();

        // 防止重复加载文件数据
        if(mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlays().size() == 0) {
            // 加载文件数据
            ArrayList<File> files = new ArrayList<>();
            OperateFolder.TraverseFindFlies(mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderLineMenuLayout().getGroundRenderLinePath(),
                    mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderLineMenuLayout().getGroundRenderLineFileSuffix(), files);

            // 解析线文件
            String[] errorMsg = {""};
            ArrayList<LineObject> lineObjects = RWLineFile.read(files, errorMsg);
            if (lineObjects != null) {
                mMainActivity.getMainManager().getMyUserOverlaysManager().addLineObjects(lineObjects);

                for (int i = 0; i < mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlays().size(); i ++) {
                    LineOverlay lineOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlays().get(i);
                    lineOverlay.setEditStatus(false);

                    // 添加已保存的覆盖物
                    mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(lineOverlay);
                }

                mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
            }
        }

        // 添加覆盖物
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new LineOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();

        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().setLineName(
                "线" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlays().size() + 1));
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().setLineType("");
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().setOldLineType("");
    }

    // 画面
    private void onItemClickedCircle(AdapterView<?> parent, View view, int position, long id) {
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneMenuLayout().show();

        // 防止重复加载文件数据
        if(mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlays().size() == 0) {
            // 加载文件数据
            ArrayList<File> files = new ArrayList<>();
            OperateFolder.TraverseFindFlies(mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneMenuLayout().getGroundRenderPlanePath(),
                    mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneMenuLayout().getGroundRenderPlaneFileSuffix(), files);

            // 解析面文件
            String[] errorMsg = {""};
            ArrayList<PlaneObject> planeObjects = RWPlaneFile.read(files, errorMsg);
            if (planeObjects != null) {
                mMainActivity.getMainManager().getMyUserOverlaysManager().addPlaneObjects(planeObjects);

                for (int i = 0; i < mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlays().size(); i ++) {
                    PlaneOverlay planeOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlays().get(i);
                    planeOverlay.setEditStatus(false);

                    // 添加已保存的覆盖物
                    mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(planeOverlay);
                }

                mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
            }
        }

        // 添加覆盖物
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new PlaneOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();

        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().setPlaneName(
                "面" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlays().size() + 1));
    }
}
