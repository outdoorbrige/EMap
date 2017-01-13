package com.gh.emap.listener;

import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.wx.wheelview.widget.WheelView;

/**
 * Created by GuHeng on 2016/11/15.
 */
public class BottomEditPointListener implements WheelView.OnWheelItemSelectedListener<String> {
    private MainActivity mMainActivity;

    // 构造函数
    public BottomEditPointListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onItemSelected(int position, String item) {
        onItemSelectedWheel(position, item);
    }

    private void onItemSelectedWheel(int position, String item) {
        ((TextView)mMainActivity.findViewById(R.id.point_type)).setText(
                (String)mMainActivity.getMainManager().getLayoutManager().getBottomShapPointLayout().getWheelViewTwo().getSelectionItem());
    }
}
