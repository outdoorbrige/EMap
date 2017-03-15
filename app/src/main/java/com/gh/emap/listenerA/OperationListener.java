package com.gh.emap.listenerA;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.tianditu.android.maps.MapView;

/**
 * Created by GuHeng on 2016/11/9.
 */
public class OperationListener implements View.OnClickListener, AdapterView.OnItemClickListener {
    private MainActivity mMainActivity;

    public OperationListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.layer: // 地图切换按钮
                onClickedLayerButton(view);
                break;
            case R.id.zoon_in:
                onClickedZoomIn(view);
                break;
            case R.id.zoom_out:
                onClickedZoomOut(view);
                break;
            case R.id.location:
                onClickedLocation(view);
                break;
            default:
                break;
        }
    }

    // 地图切换按钮
    private void onClickedLayerButton(View view) {
        mMainActivity.getMainManager().getLayoutManager().getOperationLayout().showPopupWindow(view);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // id == -1 点击的是headerView或者footerView
        if(-1 < position && position < parent.getCount()) {
            mMainActivity.getMainManager().getLayoutManager().getOperationLayout().setCurrentSelectItemIndex(position);
            mMainActivity.getMainManager().getMapManager().getMapView().setMapType(MapView.TMapType.MAP_TYPE_IMG + position);
        }

        mMainActivity.getMainManager().getLayoutManager().getOperationLayout().closePopupWindow();
    }

    // 放大
    private void onClickedZoomIn(View view) {
        if(!mMainActivity.getMainManager().getMapManager().zoomIn()) {
            ((Button)mMainActivity.findViewById(R.id.zoon_in)).setClickable(false);
            ((Button)mMainActivity.findViewById(R.id.zoon_in)).setBackgroundResource(R.mipmap.zoom_in_max_bg);
        }

        ((Button)mMainActivity.findViewById(R.id.zoom_out)).setClickable(true);
        ((Button)mMainActivity.findViewById(R.id.zoom_out)).setBackgroundResource(R.mipmap.zoom_out_bg);
    }

    // 缩小
    private void onClickedZoomOut(View view) {
        if(!mMainActivity.getMainManager().getMapManager().zoomOut()) {
            ((Button)mMainActivity.findViewById(R.id.zoom_out)).setClickable(false);
            ((Button)mMainActivity.findViewById(R.id.zoom_out)).setBackgroundResource(R.mipmap.zoom_out_min_bg);
        }

        ((Button)mMainActivity.findViewById(R.id.zoon_in)).setClickable(true);
        ((Button)mMainActivity.findViewById(R.id.zoon_in)).setBackgroundResource(R.mipmap.zoon_in_bg);
    }

    // 定位
    private void onClickedLocation(View view) {
        mMainActivity.getMainManager().getMapManager().setCenter();
    }
}
