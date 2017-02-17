package com.gh.emap.listener;

import android.view.View;
import android.widget.AdapterView;

import com.gh.emap.MainActivity;

/**
 * Created by GuHeng on 2017/2/14.
 */

public class DrawListener implements AdapterView.OnItemClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public DrawListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case -1: // 点击的是headerView或者footerView
                break;
            case 0: // 测距
                onItemClickedDistance(parent, view, position, id);
                break;
            case 1: // 测面积
                onItemClickedArea(parent, view, position, id);
                break;
            default:
                break;
        }

        if (-2 < position && position < parent.getCount()) {
            mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().setDrawSelectedIndex(position);
        }

        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().closeDrawMenu();
    }

    // 测距
    private void onItemClickedDistance(AdapterView<?> parent, View view, int position, long id) {

    }

    // 测面积
    private void onItemClickedArea(AdapterView<?> parent, View view, int position, long id) {

    }
}
