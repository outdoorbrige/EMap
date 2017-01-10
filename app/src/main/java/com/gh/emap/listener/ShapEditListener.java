package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.file.RWPointFile;
import com.gh.emap.file.OperateFolder;
import com.gh.emap.overlay.LineOverlay;
import com.gh.emap.overlay.PointObject;
import com.gh.emap.overlay.PointOverlay;
import com.gh.emap.overlay.PointOverlayItem;
import com.gh.emap.overlay.PointOverlayItems;
import com.tianditu.android.maps.GeoPoint;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2016/11/15.
 */
public class ShapEditListener implements AdapterView.OnItemClickListener {
    private Context mContext;

    // 构造函数
    public ShapEditListener(Context context) {
        this.mContext = context;
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
            ((MainActivity) this.mContext).getMainManager().getLayoutManager().getTopEditLayout().setShapEditSelectedIndex(position);
        }

        ((MainActivity) this.mContext).getMainManager().getLayoutManager().getTopEditLayout().closeShapPopupWindow();
    }

    // 画点
    private void onItemClickedPoint(AdapterView<?> parent, View view, int position, long id) {
        ((MainActivity) this.mContext).getMainManager().getLayoutManager().getTopShapPointLayout().show();
        ((MainActivity) this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().clear();

        // 防止重复加载文件数据
        if(((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getPointOverlayItems().size() == 0) {
            // 加载文件数据
            ArrayList<File> files = new ArrayList<>();
            OperateFolder.TraverseFindFlies(((MainActivity) this.mContext).getMainManager().getLayoutManager().getTopShapPointLayout().getShapPointPath(), ".p", files);

            // 解析点文件
            ArrayList<PointObject> pointObjects = RWPointFile.read(files);
            if (pointObjects != null) {
                ((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getPointOverlayItems().clear();

                for (int i = 0; i < pointObjects.size(); i++) {
                    PointObject pointObject = pointObjects.get(i);

                    PointOverlayItem pointOverlayItem = new PointOverlayItem(pointObject.getLatitude(), pointObject.getLongitude(), pointObject.getTitle(), pointObject.getSnippet(), mContext);

                    pointOverlayItem.getPointObject().setIndex(i);
                    pointOverlayItem.getPointObject().setType(pointObject.getType());
                    pointOverlayItem.getPointObject().setName(pointObject.getName());

                    ((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getPointOverlayItems().put(pointOverlayItem);
                    ((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getPointOverlayItems().populate();
                }

                // 添加已保存的覆盖物
                ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().addOverlay(((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getPointOverlayItems());
                ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().postInvalidate();
            }
        }

        // 设置默认点的名称
        EditText defaultPointName = (EditText)(((MainActivity) this.mContext).findViewById(R.id.point_name));
        defaultPointName.setText("点" + String.valueOf(((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getPointOverlayItems().size() + 1));

        // 添加当前位置覆盖物
        PointOverlay overlay = ((MainActivity) this.mContext).getMainManager().getOverlayManager().getPointOverlay();
        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().addOverlay(overlay);

        GeoPoint geoPoint = ((MainActivity) this.mContext).getMainManager().getMyLocationManager().getGeoPoint();
        ((MainActivity) this.mContext).getMainManager().getOverlayManager().getPointOverlay().setGeoPoint(geoPoint);

        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 画线
    private void onItemClickedLine(AdapterView<?> parent, View view, int position, long id) {
        ((MainActivity) this.mContext).getMainManager().getLayoutManager().getTopShapLineLayout().show();
        ((MainActivity) this.mContext).getMainManager().getLayoutManager().getBottomShapLineLayout().clear();

        // 设置默认点的名称
        EditText defaultLineName = (EditText)(((MainActivity) this.mContext).findViewById(R.id.line_name));
        defaultLineName.setText("线" + String.valueOf(((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getLineOverlayItems().size() + 1));

        // 添加当前位置覆盖物
        LineOverlay overlay = ((MainActivity) this.mContext).getMainManager().getOverlayManager().getLineOverlay();
        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().addOverlay(overlay);

        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 画面
    private void onItemClickedCircle(AdapterView<?> parent, View view, int position, long id) {

    }
}
