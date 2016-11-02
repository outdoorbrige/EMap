package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.AdapterView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.layout.LayerLayout;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.base.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/9.
 */
public class LayerListViewListener extends MainActivityContext implements AdapterView.OnItemClickListener {

    // 构造函数
    public LayerListViewListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,  long id) {
        // id == -1 点击的是headerView或者footerView
        if(0 <= position && position < parent.getCount()) {
            mMainActivity.getLayoutManger().getLayerLayout().setCurrentSelectItemIndex(position);
            mMainActivity.getMapManager().getTMapView().setMapType(position + 1);
        }

        mMainActivity.getLayoutManger().getLayerLayout().closePopupWindow();
    }
}
