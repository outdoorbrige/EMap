package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.AdapterView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.base.MainActivityContext;

/**
 * Created by GuHeng on 2016/11/2.
 */
public class ShapListViewListener extends MainActivityContext implements AdapterView.OnItemClickListener {

    // 构造函数
    public ShapListViewListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case -1: // 点击的是headerView或者footerView
                break;
            case 0: // 画点
                mMainActivity.getLayoutManger().getEditLayout().getDrawPointLayout().show();
                break;
            case 1: // 画线
                break;
            case 2: // 画面
                break;
            default:
                break;
        }

        if(0 <= position && position < parent.getCount()) {
            mMainActivity.getLayoutManger().getEditLayout().getShapLayout().setCurrentSelectItemIndex(position);
        }

        mMainActivity.getLayoutManger().getEditLayout().getShapLayout().closePopupWindow();
    }
}
