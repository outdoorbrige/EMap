package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.wx.wheelview.widget.WheelView;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class TopEditListener implements View.OnClickListener {
    private Context mContext;

    // 构造函数
    public TopEditListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_shap: // 地物编辑
                onClickedMenuShap(view);
                break;
            case R.id.menu_line: // 线路编辑
                onClickedMenuLine(view);
                break;
            case R.id.menu_mapping: // 测绘
                onClickedMenuMapping(view);
                break;
            case R.id.menu_exit: // 退出编辑
                onClickedMenuExit(view);
                break;
            default:
                break;
        }
    }

    // 地物编辑
    private void onClickedMenuShap(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopEditLayout().showShapPopupWindow(view);
    }

    // 线路编辑
    private void onClickedMenuLine(View view) {
    }

    // 测绘
    private void onClickedMenuMapping(View view) {

    }

    // 退出编辑
    private void onClickedMenuExit(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopNormalLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopEditLayout().hide();
    }
}
