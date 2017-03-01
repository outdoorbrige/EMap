package com.gh.emap.listener;

import android.view.View;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.wx.wheelview.widget.WheelView;

/**
 * Created by GuHeng on 2016/11/15.
 */
public class GroundRenderPointTypeListener implements WheelView.OnWheelItemSelectedListener<String>, View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public GroundRenderPointTypeListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onItemSelected(int position, String item) {
        onItemSelectedWheel(position, item);
    }

    private void onItemSelectedWheel(int position, String item) {
//        ((TextView)mMainActivity.findViewById(R.id.point_type)).setText(
//                (String)mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointTypeLayout().getWheelViewTwo().getSelectionItem());
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.point_type_cancel: // 取消
                onClickedCancelButton(view);
                break;
            case R.id.point_type_ok: // 确定
                onClickedOkButton(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedCancelButton(View view) {
        mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointTypeLayout().hide();

        ((TextView)mMainActivity.findViewById(R.id.point_type)).setText(
                mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().getOldPointType());

        String oldPointType = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().getOldPointType();
        int oneIndex = mMainActivity.getMainManager().getFileManager().getGroundRenderPointFile().getShapPoint().getKeyIndex(oldPointType);
        int twoIndex = mMainActivity.getMainManager().getFileManager().getGroundRenderPointFile().getShapPoint().getValueIndex(oldPointType);

        mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointTypeLayout().reset(oneIndex, twoIndex);
    }

    // 确定
    private void onClickedOkButton(View view) {
        mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointTypeLayout().hide();

        String pointType = (String)mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointTypeLayout().getWheelViewTwo().getSelectionItem();

        ((TextView)mMainActivity.findViewById(R.id.point_type)).setText(pointType);
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderPointLayout().setOldPointType(pointType);
    }
}
