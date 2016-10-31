package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.AdapterView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.layout.LayerLayout;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/9.
 */
public class LayerListViewListener extends MainActivityContext implements AdapterView.OnItemClickListener {
    private LayerLayout mLayerLayout;

    // 构造函数
    public LayerListViewListener(MainActivity mainActivity, LayerLayout layerLayout) {
        super(mainActivity);
        this.mLayerLayout = layerLayout;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,  long id) {
        mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mDebug, String.format("position:%d, id:%d", position, id));

        // id == -1 点击的是headerView或者footerView
        if(-1 < id && id < parent.getCount()) {
            mLayerLayout.setSelectedItemId((int)id);
            mMainActivity.getMapManager().getTMapView().setMapType((int)id + 1);
        }

        mLayerLayout.closePopupWindow();
    }
}
