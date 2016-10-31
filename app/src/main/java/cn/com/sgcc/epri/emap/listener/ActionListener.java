package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.Button;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/27.
 */
public class ActionListener extends MainActivityContext implements View.OnClickListener {

    // 构造函数
    public ActionListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.zoon_in:
                onClickedZoomIn();
                break;
            case R.id.zoom_out:
                onClickedZoomOut();
                break;
            case R.id.location:
                onClickedLocation();
                break;
            default:
                break;
        }
    }

    // 放大
    private void onClickedZoomIn() {
        if(!mMainActivity.getMapManager().zoomIn()) {
            ((Button) mMainActivity.findViewById(R.id.zoon_in)).setClickable(false);
            ((Button) mMainActivity.findViewById(R.id.zoon_in)).setBackgroundResource(R.mipmap.zoom_in_max_bg);
        }

        ((Button) mMainActivity.findViewById(R.id.zoom_out)).setClickable(true);
        ((Button) mMainActivity.findViewById(R.id.zoom_out)).setBackgroundResource(R.mipmap.zoom_out_bg);
    }

    // 缩小
    private void onClickedZoomOut() {
        if(!mMainActivity.getMapManager().zoomOut()) {
            ((Button) mMainActivity.findViewById(R.id.zoom_out)).setClickable(false);
            ((Button) mMainActivity.findViewById(R.id.zoom_out)).setBackgroundResource(R.mipmap.zoom_out_min_bg);
        }

        ((Button) mMainActivity.findViewById(R.id.zoon_in)).setClickable(true);
        ((Button) mMainActivity.findViewById(R.id.zoon_in)).setBackgroundResource(R.mipmap.zoon_in_bg);
    }

    // 定位
    private void onClickedLocation() {
        mMainActivity.getMapManager().setCenter();
    }
}
