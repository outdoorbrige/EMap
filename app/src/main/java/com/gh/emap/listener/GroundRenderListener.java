package com.gh.emap.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.file.RWLineFile;
import com.gh.emap.file.RWPlaneFile;
import com.gh.emap.file.RWPointFile;
import com.gh.emap.file.OperateFolder;
import com.gh.emap.overlay.LineObject;
import com.gh.emap.overlay.LineOverlay;
import com.gh.emap.overlay.PlaneObject;
import com.gh.emap.overlay.PlaneOverlay;
import com.gh.emap.overlay.PointObject;
import com.gh.emap.overlay.PointOverlay;

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
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPointLayout().clear();

        // 防止重复加载文件数据
        if(mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().size() == 0) {
            // 加载文件数据
            ArrayList<File> files = new ArrayList<>();
            OperateFolder.TraverseFindFlies(mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().getGroundRenderPointPath(),
                    mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().getGroundRenderPointFileSuffix(), files);

            // 解析点文件
            ArrayList<PointObject> pointObjects = RWPointFile.read(files);
            if (pointObjects != null) {
                mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().clear();
                mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().addAll(pointObjects);

                for (int i = 0; i < mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().size(); i ++) {
                    PointOverlay pointOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().getPointOverlay(i);
                    pointOverlay.setEditStatus(false);

                    // 添加已保存的覆盖物
                    mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(pointOverlay);
                }

                mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
            }
        }

        // 设置默认点的名称
        EditText defaultPointName = (EditText)(mMainActivity.findViewById(R.id.point_name));
        defaultPointName.setText("点" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().size() + 1));

        // 添加覆盖物
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new PointOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 画线
    private void onItemClickedLine(AdapterView<?> parent, View view, int position, long id) {
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderLineLayout().clear();

        // 防止重复加载文件数据
        if(mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().size() == 0) {
            // 加载文件数据
            ArrayList<File> files = new ArrayList<>();
            OperateFolder.TraverseFindFlies(mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().getGroundRenderLinePath(),
                    mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().getGroundRenderLineFileSuffix(), files);

            // 解析线文件
            ArrayList<LineObject> lineObjects = RWLineFile.read(files);
            if (lineObjects != null) {
                mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().clear();
                mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().addAll(lineObjects);

                for (int i = 0; i < mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().size(); i ++) {
                    LineOverlay lineOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().getLineOverlay(i);
                    lineOverlay.setEditStatus(false);

                    // 添加已保存的覆盖物
                    mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(lineOverlay);
                }

                mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
            }
        }

        // 设置默认线的名称
        EditText defaultLineName = (EditText)(mMainActivity.findViewById(R.id.line_name));
        defaultLineName.setText("线" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().size() + 1));

        // 添加覆盖物
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new LineOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 画面
    private void onItemClickedCircle(AdapterView<?> parent, View view, int position, long id) {
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().show();
//        mMainActivity.getMainManager().getLayoutManager().getBottomGroundRenderPlaneLayout().clear();

        // 防止重复加载文件数据
        if(mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().size() == 0) {
            // 加载文件数据
            ArrayList<File> files = new ArrayList<>();
            OperateFolder.TraverseFindFlies(mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().getGroundRenderPlanePath(),
                    mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPlaneLayout().getGroundRenderPlaneFileSuffix(), files);

            // 解析面文件
            ArrayList<PlaneObject> planeObjects = RWPlaneFile.read(files);
            if (planeObjects != null) {
                mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().clear();
                mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().addAll(planeObjects);

                for (int i = 0; i < mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().size(); i ++) {
                    PlaneOverlay planeOverlay = mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().getPlaneOverlay(i);
                    planeOverlay.setEditStatus(false);

                    // 添加已保存的覆盖物
                    mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(planeOverlay);
                }

                mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
            }
        }

        // 设置默认面的名称
        EditText defaultPlaneName = (EditText)(mMainActivity.findViewById(R.id.plane_name));
        defaultPlaneName.setText("面" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().size() + 1));

        // 添加覆盖物
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(new PlaneOverlay(mMainActivity));

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
    }
}
