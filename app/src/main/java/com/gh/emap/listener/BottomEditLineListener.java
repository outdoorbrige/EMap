package com.gh.emap.listener;

import android.content.Context;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.wx.wheelview.widget.WheelView;

/**
 * Created by GuHeng on 2017/1/9.
 */

public class BottomEditLineListener implements WheelView.OnWheelItemSelectedListener<String> {
    private Context mContext;

    // 构造函数
    public BottomEditLineListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onItemSelected(int position, String item) {
        onItemSelectedWheel(position, item);
    }

    private void onItemSelectedWheel(int position, String item) {
        ((TextView)((MainActivity)this.mContext).findViewById(R.id.point_type)).setText(
                (String)((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapLineLayout().getWheelViewTwo().getSelectionItem());
    }
}
