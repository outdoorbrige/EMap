package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.Button;

import com.tianditu.android.maps.MapController;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 */
public class ToolListener extends TransmitContext implements View.OnClickListener {
    private MapController controller; // 地图控制器

    // 构造函数
    public ToolListener(MainActivity context, MapController controller) {
        super(context);
        this.controller = controller;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.emap_tool_bar_zoomin_btn) {
            onClickZoomin();
        } else if(view.getId() == R.id.emap_tool_bar_zoomout_btn) {
            onClickZoomout();
        } else if(view.getId() == R.id.emap_tool_bar_locate_btn) {

        } else {
        }
    }

    private void onClickZoomin() {
        if(!controller.zoomIn()) {
            ((Button)context.findViewById(R.id.emap_tool_bar_zoomin_btn)).setClickable(false);
            ((Button)context.findViewById(R.id.emap_tool_bar_zoomin_btn)).setBackgroundResource(R.mipmap.btn_zoomin_gray);
        }

        ((Button)context.findViewById(R.id.emap_tool_bar_zoomout_btn)).setClickable(true);
        ((Button)context.findViewById(R.id.emap_tool_bar_zoomout_btn)).setBackgroundResource(R.mipmap.btn_zoomout);
    }

    private void onClickZoomout() {
        if(!controller.zoomOut()) {
            ((Button)context.findViewById(R.id.emap_tool_bar_zoomout_btn)).setClickable(false);
            ((Button)context.findViewById(R.id.emap_tool_bar_zoomout_btn)).setBackgroundResource(R.mipmap.btn_zoomout_gray);
        }

        ((Button)context.findViewById(R.id.emap_tool_bar_zoomin_btn)).setClickable(true);
        ((Button)context.findViewById(R.id.emap_tool_bar_zoomin_btn)).setBackgroundResource(R.mipmap.btn_zoomin);
    }
}
