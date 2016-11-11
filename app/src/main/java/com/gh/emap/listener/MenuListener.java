package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class MenuListener implements View.OnClickListener {
    public Context mContext;

    public MenuListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_main: // 主菜单按钮
                onClickedMenuMain(view);
                break;
            case R.id.menu_edit: // 编辑按钮
                onClickedMenuEdit(view);
                break;
            case R.id.menu_setting: // 设置按钮
                onClickedMenuSetting(view);
                break;
            case R.id.menu_download: // 下载按钮
                onClickedMenuDownload(view);
                break;
            default:
                break;
        }
    }

    // 主菜单按钮
    private void onClickedMenuMain(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().runMenuAnimation(view);
    }

    // 编辑按钮
    private void onClickedMenuEdit(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().runMenuAnimation(view);
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopNormalLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopEditLayout().show();
    }

    // 设置按钮
    private void onClickedMenuSetting(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().runMenuAnimation(view);
    }

    // 下载按钮
    private void onClickedMenuDownload(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().runMenuAnimation(view);
    }
}
