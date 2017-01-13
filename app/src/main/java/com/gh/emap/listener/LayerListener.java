package com.gh.emap.listener;

import android.view.View;
import android.widget.AdapterView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class LayerListener implements View.OnClickListener, AdapterView.OnItemClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public LayerListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.layer_button: // 地图切换按钮
                onClickedLayerButton(view);
                break;
            default:
                break;
        }
    }

    // 地图切换按钮
    private void onClickedLayerButton(View view) {
        mMainActivity.getMainManager().getLayoutManager().getLayerLayout().showPopupWindow(view);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // id == -1 点击的是headerView或者footerView
        if(-1 < position && position < parent.getCount()) {
            mMainActivity.getMainManager().getLayoutManager().getLayerLayout().setCurrentSelectItemIndex(position);
            mMainActivity.getMainManager().getMapManager().getMapView().setMapType(position + 1);
        }

        mMainActivity.getMainManager().getLayoutManager().getLayerLayout().closePopupWindow();
    }
}
