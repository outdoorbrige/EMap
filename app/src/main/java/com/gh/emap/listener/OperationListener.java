package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2016/11/9.
 */
public class OperationListener implements View.OnClickListener {
    private Context mContext;

    public OperationListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.zoon_in:
                onClickedZoomIn(view);
                break;
            case R.id.zoom_out:
                onClickedZoomOut(view);
                break;
            case R.id.location:
                onClickedLocation(view);
                break;
            default:
                break;
        }
    }

    // 放大
    private void onClickedZoomIn(View view) {
        if(!((MainActivity)this.mContext).getMainManager().getMapManager().zoomIn()) {
            ((Button) ((MainActivity)this.mContext).findViewById(R.id.zoon_in)).setClickable(false);
            ((Button) ((MainActivity)this.mContext).findViewById(R.id.zoon_in)).setBackgroundResource(R.mipmap.zoom_in_max_bg);
        }

        ((Button) ((MainActivity)this.mContext).findViewById(R.id.zoom_out)).setClickable(true);
        ((Button) ((MainActivity)this.mContext).findViewById(R.id.zoom_out)).setBackgroundResource(R.mipmap.zoom_out_bg);
    }

    // 缩小
    private void onClickedZoomOut(View view) {
        if(!((MainActivity)this.mContext).getMainManager().getMapManager().zoomOut()) {
            ((Button) ((MainActivity)this.mContext).findViewById(R.id.zoom_out)).setClickable(false);
            ((Button) ((MainActivity)this.mContext).findViewById(R.id.zoom_out)).setBackgroundResource(R.mipmap.zoom_out_min_bg);
        }

        ((Button) ((MainActivity)this.mContext).findViewById(R.id.zoon_in)).setClickable(true);
        ((Button) ((MainActivity)this.mContext).findViewById(R.id.zoon_in)).setBackgroundResource(R.mipmap.zoon_in_bg);
    }

    // 定位
    private void onClickedLocation(View view) {
        ((MainActivity)this.mContext).getMainManager().getMapManager().setCenter();
    }
}
