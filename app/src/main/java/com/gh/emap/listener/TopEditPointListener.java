package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

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

    // 保存
    private void onClickedPointSave(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopShapPointLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapPointLayout().clear();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getOperationLayout().show();

        // 删除画点覆盖物
        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().removeOverlay(
                ((MainActivity) this.mContext).getMainManager().getOverlayManager().getPointOverlay());
        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().postInvalidate();
    }
}
