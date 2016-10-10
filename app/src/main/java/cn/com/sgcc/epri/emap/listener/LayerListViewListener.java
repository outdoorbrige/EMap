package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import com.tianditu.android.maps.MapView;

import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.layout.LayerLayout;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/9.
 */
public class LayerListViewListener extends TransmitContext implements AdapterView.OnItemClickListener {
    private LayerLayout layer_layout;

    // 构造函数
    public LayerListViewListener(MainActivity context, LayerLayout layer_layout) {
        super(context);
        this.layer_layout = layer_layout;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,  long id) {
        Logger.getLogger(this.getClass()).info(String.format("position:%d, id:%d", position, id));

        // id == -1 点击的是headerView或者footerView
        if(-1 < id && id < parent.getCount()) {
            layer_layout.setSelectedItemId((int)id);
            context.getMapMgr().getTMapView().setMapType((int)id + 1);
            context.getMapMgr().invalidate();
        }

        layer_layout.closePopupWindow();
    }
}
