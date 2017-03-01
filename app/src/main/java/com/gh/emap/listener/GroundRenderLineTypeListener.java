package com.gh.emap.listener;

import android.view.View;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.wx.wheelview.widget.WheelView;

/**
 * Created by GuHeng on 2017/1/9.
 */

public class GroundRenderLineTypeListener implements WheelView.OnWheelItemSelectedListener<String>, View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public GroundRenderLineTypeListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onItemSelected(int position, String item) {
        onItemSelectedWheel(position, item);
    }

    private void onItemSelectedWheel(int position, String item) {
//        ((TextView)mMainActivity.findViewById(R.id.line_type)).setText(
//                (String)mMainActivity.getMainManager().getLayoutManager().getGroundRenderLineTypeLayout().getWheelViewTwo().getSelectionItem());
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.line_type_cancel: // 取消
                onClickedCancelButton(view);
                break;
            case R.id.line_type_ok: // 确定
                onClickedOkButton(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedCancelButton(View view) {
        mMainActivity.getMainManager().getLayoutManager().getGroundRenderLineTypeLayout().hide();

        ((TextView)mMainActivity.findViewById(R.id.line_type)).setText(
                mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().getOldLineType());

        String oldLineType = mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().getOldLineType();
        int oneIndex = mMainActivity.getMainManager().getFileManager().getGroundRenderLineFile().getShapLine().getKeyIndex(oldLineType);
        int twoIndex = mMainActivity.getMainManager().getFileManager().getGroundRenderLineFile().getShapLine().getValueIndex(oldLineType);

        mMainActivity.getMainManager().getLayoutManager().getGroundRenderLineTypeLayout().reset(oneIndex, twoIndex);
    }

    // 确定
    private void onClickedOkButton(View view) {
        mMainActivity.getMainManager().getLayoutManager().getGroundRenderLineTypeLayout().hide();

        String lineType = (String)mMainActivity.getMainManager().getLayoutManager().getGroundRenderLineTypeLayout().getWheelViewTwo().getSelectionItem();

        ((TextView)mMainActivity.findViewById(R.id.line_type)).setText(lineType);
        mMainActivity.getMainManager().getLayoutManager().getTopGroundRenderLineLayout().setOldLineType(lineType);
    }
}
