package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.Button;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 */
public class ToolListener extends TransmitContext implements View.OnClickListener {

    // 构造函数
    public ToolListener(MainActivity context) {
        super(context);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.emap_tool_bar_zoomin_btn) {
            onClickZoomin();
        } else if(view.getId() == R.id.emap_tool_bar_zoomout_btn) {
            onClickZoomout();
        } else if(view.getId() == R.id.emap_tool_bar_locate_btn) {
            onClickLocate();
        } else {
        }
    }

    // 放大
    private void onClickZoomin() {
        if(!context.getMapMgr().setZoomin()) {
            ((Button) context.findViewById(R.id.emap_tool_bar_zoomin_btn)).setClickable(false);
            ((Button) context.findViewById(R.id.emap_tool_bar_zoomin_btn)).setBackgroundResource(R.mipmap.btn_zoomin_gray);
        }

        ((Button) context.findViewById(R.id.emap_tool_bar_zoomout_btn)).setClickable(true);
        ((Button) context.findViewById(R.id.emap_tool_bar_zoomout_btn)).setBackgroundResource(R.mipmap.btn_zoomout);
    }

    // 缩小
    private void onClickZoomout() {
        if(!context.getMapMgr().setZoomout()) {
            ((Button) context.findViewById(R.id.emap_tool_bar_zoomout_btn)).setClickable(false);
            ((Button) context.findViewById(R.id.emap_tool_bar_zoomout_btn)).setBackgroundResource(R.mipmap.btn_zoomout_gray);
        }

        ((Button)context.findViewById(R.id.emap_tool_bar_zoomin_btn)).setClickable(true);
        ((Button)context.findViewById(R.id.emap_tool_bar_zoomin_btn)).setBackgroundResource(R.mipmap.btn_zoomin);
    }

    // 定位
    private void onClickLocate() {
        context.getMapMgr().setCenter();
    }
}