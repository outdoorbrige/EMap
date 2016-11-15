package com.gh.emap.listener;

import android.content.Context;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.wx.wheelview.widget.WheelView;

/**
 * Created by GuHeng on 2016/11/15.
 */
public class BottomEditPointListener implements WheelView.OnWheelItemSelectedListener<String> {
    private Context mContext;

    // 构造函数
    public BottomEditPointListener(Context context) {
        this.mContext = context;
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
