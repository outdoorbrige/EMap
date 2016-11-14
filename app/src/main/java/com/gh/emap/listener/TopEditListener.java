package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.wx.wheelview.widget.WheelView;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class TopEditListener implements View.OnClickListener, AdapterView.OnItemClickListener, WheelView.OnWheelItemSelectedListener<String> {
    private Context mContext;

    // 构造函数
    public TopEditListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_shap: // 地物编辑
                onClickedMenuShap(view);
                break;
            case R.id.menu_line: // 线路编辑
                onClickedMenuLine(view);
                break;
            case R.id.menu_mapping: // 测绘
                onClickedMenuMapping(view);
                break;
            case R.id.menu_exit: // 退出编辑
                onClickedMenuExit(view);
                break;
            case R.id.point_type: // 画点类型
                onClickedPointType(view);
                break;
            case R.id.point_name: // 画点名称
                onClickedPointName(view);
                break;
            case R.id.point_save: // 保存
                onClickedPointSave(view);
                break;
            default:
                break;
        }
    }

    // 地物编辑
    private void onClickedMenuShap(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopEditLayout().showShapPopupWindow(view);
    }

    // 线路编辑
    private void onClickedMenuLine(View view) {
    }

    // 测绘
    private void onClickedMenuMapping(View view) {

    }

    // 退出编辑
    private void onClickedMenuExit(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopNormalLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopEditLayout().hide();
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

    // 保存
    private void onClickedPointSave(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopShapPointLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().clear();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getOperationLayout().show();
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

//        if(0 <= position && position < parent.getCount()) {
//            mMainActivity.getLayoutManger().getEditLayout().getShapLayout().setCurrentSelectItemIndex(position);
//        }
//
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopEditLayout().closeShapPopupWindow();
    }

    // 画点
    private void onItemClickedPoint(AdapterView<?> parent, View view, int position, long id) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopShapPointLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().clear();
    }
    // 画线
    private void onItemClickedLine(AdapterView<?> parent, View view, int position, long id) {

    }

    // 画面
    private void onItemClickedCircle(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(int position, String item) {
        onItemSelectedWheel(position, item);
    }

    private void onItemSelectedWheel(int position, String item) {
        ((TextView)((MainActivity)this.mContext).findViewById(R.id.point_type)).setText(
                (String)((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().getWheelViewTwo().getSelectionItem());
    }
}
