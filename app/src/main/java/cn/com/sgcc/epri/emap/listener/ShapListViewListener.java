package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.AdapterView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.base.MainActivityContext;
import cn.com.sgcc.epri.emap.layout.ShapLayout;

/**
 * Created by GuHeng on 2016/11/2.
 */
public class ShapListViewListener extends MainActivityContext implements AdapterView.OnItemClickListener {
    private ShapLayout mShapLayout;

    public ShapListViewListener(MainActivity mainActivity, ShapLayout shapLayout) {
        super(mainActivity);
        this.mShapLayout = shapLayout;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // id == -1 点击的是headerView或者footerView
        if(-1 < id && id < parent.getCount()) {
        }

        mShapLayout.closePopupWindow();
    }
}
