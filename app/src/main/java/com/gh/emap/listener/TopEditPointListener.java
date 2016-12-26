package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.file.RWPointFile;
import com.gh.emap.manager.LogManager;
import com.gh.emap.overlay.PointOverlayItem;
import com.gh.emap.overlay.PointOverlayItems;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.maps.GeoPointEx;

import java.io.File;

/**
 * Created by GuHeng on 2016/11/15.
 */
public class TopEditPointListener implements View.OnClickListener {
    private Context mContext;

    // 构造函数
    public TopEditPointListener(Context context) {
        this.mContext = context;
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
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getOperationLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().show();

        ((TextView)((MainActivity)this.mContext).findViewById(R.id.point_type)).setText(
                (String)((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().getWheelViewTwo().getSelectionItem());
    }

    // 画点名称
    private void onClickedPointName(View view) {

    }

    // 取消
    private void onClickedPointCancel(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopShapPointLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().clear();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getOperationLayout().show();

//        // 删除画点覆盖物
//        PointOverlay pointOverlay = ((MainActivity) this.mContext).getMainManager().getOverlayManager().getPointOverlay();
//        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().removeOverlay(pointOverlay);
//        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 保存
    private void onClickedPointSave(View view) {
        String pointType = ((TextView)((MainActivity)this.mContext).findViewById(R.id.point_type)).getText().toString();
        if(pointType.contains("选择类别")) {
            ((MainActivity)this.mContext).getMainManager().getLogManager().show(String.format("请选择类别"));
            return;
        }

        String pointName = ((EditText)((MainActivity)this.mContext).findViewById(R.id.point_name)).getText().toString();
        if(pointName.isEmpty()) {
            ((MainActivity)this.mContext).getMainManager().getLogManager().show(String.format("请输入名称"));
            return;
        }

        GeoPoint point = GeoPointEx.Double2GeoPoint(116.3919236741D, 39.9057789520D); // ((MainActivity) this.mContext).getMainManager().getOverlayManager().getPointOverlay().getGeoPoint();

        String path = ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopShapPointLayout().getShapPointPath();
        if(path == null || path.isEmpty()) {
            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError, String.format("用户未登录!"));
            return;
        } else {
            // 保存点信息到文件

            PointOverlayItem overlay = new PointOverlayItem(point.getLatitudeE6(), point.getLongitudeE6(), "", "", mContext);
            overlay.getPointObject().setType(pointType);
            overlay.getPointObject().setName(pointName);

            PointOverlayItems overlayItems = ((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getPointOverlayItems();
            overlayItems.put(overlay);

            RWPointFile.write(path + File.separator + overlay.getPointObject().getName() + ".p", overlay.getPointObject());

            ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().addOverlay(overlayItems);
            ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().postInvalidate();
        }

        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopShapPointLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().clear();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getOperationLayout().show();
    }
}
