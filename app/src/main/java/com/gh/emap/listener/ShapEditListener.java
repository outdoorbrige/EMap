package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.gh.emap.MainActivity;
import com.gh.emap.file.MyUserPointFile;
import com.gh.emap.file.OperateFolder;
import com.gh.emap.model.MyUserPoint;
import com.gh.emap.overlay.PointOverlay;
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

        // 加载文件数据
        ArrayList<File> files = new ArrayList<>();
        OperateFolder.TraverseFindFlies(((MainActivity) this.mContext).getMainManager().getLayoutManager().getTopShapPointLayout().getShapPointPath(), ".p", files);

        // 解析点文件
        ArrayList<MyUserPoint> myUserPoints = MyUserPointFile.read(files);

        if(myUserPoints != null && myUserPoints.size() > 0) {
            ((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getMyUserOverlays().putMyUserPoint(myUserPoints);
        }

        // 添加已保存的覆盖物
        for(int i = 0; i < myUserPoints.size(); i ++) {
            MyUserPoint myUserPoint = myUserPoints.get(i);
            PointOverlay pointOverlay = new PointOverlay(this.mContext);
            pointOverlay.setGeoPoint(new GeoPoint(myUserPoint.getLatitude(), myUserPoint.getLongitude()));
            ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().addOverlay(pointOverlay);
        }

        // 添加当前位置覆盖物
        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().addOverlay(
                ((MainActivity) this.mContext).getMainManager().getOverlayManager().getPointOverlay());

        ((MainActivity) this.mContext).getMainManager().getOverlayManager().getPointOverlay().setGeoPoint(
                ((MainActivity) this.mContext).getMainManager().getMyLocationManager().getGeoPoint());

        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 画线
    private void onItemClickedLine(AdapterView<?> parent, View view, int position, long id) {

    }

    // 画面
    private void onItemClickedCircle(AdapterView<?> parent, View view, int position, long id) {

    }
}
