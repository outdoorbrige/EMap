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
import com.gh.emap.overlay.PointOverlayItem;
import com.tianditu.android.maps.GeoPoint;

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

                for (int i = 0; i < pointObjects.size(); i++) {
                    PointObject pointObject = pointObjects.get(i);

                    PointOverlayItem pointOverlayItem = new PointOverlayItem(mMainActivity, pointObject.getTitle(), pointObject.getSnippet(), pointObject.getLatitude(), pointObject.getLongitude());

                    pointOverlayItem.getPointObject().setIndex(i);
                    pointOverlayItem.getPointObject().setType(pointObject.getType());
                    pointOverlayItem.getPointObject().setName(pointObject.getName());

                    mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().put(pointOverlayItem);
                    mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().populate();
                }

                // 添加已保存的覆盖物
                mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems());
                mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
            }
        }

        // 设置默认点的名称
        EditText defaultPointName = (EditText)(mMainActivity.findViewById(R.id.point_name));
        defaultPointName.setText("点" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPointOverlayItems().size() + 1));

        // 添加当前位置覆盖物
        PointOverlay overlay = mMainActivity.getMainManager().getOverlayManager().getPointOverlay();
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(overlay);

        GeoPoint geoPoint = mMainActivity.getMainManager().getMyLocationManager().getGeoPoint();
        mMainActivity.getMainManager().getOverlayManager().getPointOverlay().setGeoPoint(geoPoint);

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
                    // 添加已保存的覆盖物
                    mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(
                            mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().getLineOverlay(i));
                }

                mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
            }
        }

        // 设置默认线的名称
        EditText defaultLineName = (EditText)(mMainActivity.findViewById(R.id.line_name));
        defaultLineName.setText("线" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getLineOverlayItems().size() + 1));

        // 添加当前位置覆盖物
        LineOverlay overlay = mMainActivity.getMainManager().getOverlayManager().getLineOverlay();
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(overlay);

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
                    // 添加已保存的覆盖物
                    mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(
                            mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().getPlaneOverlay(i));
                }

                mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
            }
        }

        // 设置默认面的名称
        EditText defaultPlaneName = (EditText)(mMainActivity.findViewById(R.id.plane_name));
        defaultPlaneName.setText("面" + String.valueOf(mMainActivity.getMainManager().getMyUserOverlaysManager().getPlaneOverlayItems().size() + 1));

        // 添加当前位置覆盖物
        PlaneOverlay overlay = mMainActivity.getMainManager().getOverlayManager().getPlaneOverlay();
        mMainActivity.getMainManager().getMapManager().getMapView().addOverlay(overlay);

        mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
    }
}
