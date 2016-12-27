package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.model.UserInfo;

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

        // 此处代码只是为了测试
        UserInfo userInfo = ((MainActivity)this.mContext).getMainManager().getUserManager().getUserInfo();
        if(userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setUserName("GuHeng");
            userInfo.setSuccess(true);

            ((MainActivity)this.mContext).getMainManager().getUserManager().setUserInfo(userInfo);
        } // 此处代码只是为了测试

        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().runMenuAnimation(view);
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopNormalLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopEditLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().hide();
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
