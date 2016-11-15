package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.gh.emap.MainActivity;

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

        // 添加画点覆盖物
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
