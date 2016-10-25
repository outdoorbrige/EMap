package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.AdapterView;

import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.layout.LayerLayout;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/9.
 */
public class LayerListViewListener extends MainActivityContext implements AdapterView.OnItemClickListener {
    private LayerLayout mLayerLayout;

    // 构造函数
    public LayerListViewListener(MainActivity context, LayerLayout layerLayout) {
        super(context);
        this.mLayerLayout = layerLayout;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,  long id) {
        Logger.getLogger(this.getClass()).info(String.format("position:%d, id:%d", position, id));

        // id == -1 点击的是headerView或者footerView
        if(-1 < id && id < parent.getCount()) {
            mLayerLayout.setSelectedItemId((int)id);
            context.getMapManger().getTMapView().setMapType((int)id + 1);
            context.getMapManger().invalidate();
        }

        mLayerLayout.closePopupWindow();
    }
}
