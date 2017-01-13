package com.gh.emap.listener;

import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.wx.wheelview.widget.WheelView;

/**
 * Created by GuHeng on 2017/1/9.
 */

public class BottomEditLineListener implements WheelView.OnWheelItemSelectedListener<String> {
    private MainActivity mMainActivity;

    // 构造函数
    public BottomEditLineListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onItemSelected(int position, String item) {
        onItemSelectedWheel(position, item);
    }

    private void onItemSelectedWheel(int position, String item) {
        ((TextView)mMainActivity.findViewById(R.id.line_type)).setText(
                (String)mMainActivity.getMainManager().getLayoutManager().getBottomShapLineLayout().getWheelViewTwo().getSelectionItem());
    }
}
