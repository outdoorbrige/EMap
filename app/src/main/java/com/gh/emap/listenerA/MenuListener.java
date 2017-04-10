package com.gh.emap.listenerA;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.modelA.UserInfo;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class MenuListener implements View.OnClickListener {
    public MainActivity mMainActivity;

    public MenuListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_main: // 主菜单按钮
                onClickedMenuMain(view);
                break;
            case R.id.menu_render: // 绘制按钮
                onClickedMenuRender(view);
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
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().runMenuAnimation(view);
    }

    // 绘制按钮
    private void onClickedMenuRender(View view) {

        // 此处代码只是为了测试
        UserInfo userInfo = mMainActivity.getMainManager().getUserManager().getUserInfo();
        if(userInfo == null) {
            userInfo = mMainActivity.getMainManager().getUserManager().getGuestUserInfo();
            mMainActivity.getMainManager().getUserManager().setUserInfo(userInfo);
        }

        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().runMenuAnimation(view);
        mMainActivity.getMainManager().getLayoutManager().getTopNormalLayout().hide();
        mMainActivity.getMainManager().getLayoutManager().getTopRenderLayout().show();
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().hide();
    }

    // 设置按钮
    private void onClickedMenuSetting(View view) {
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().runMenuAnimation(view);
    }

    // 下载按钮
    private void onClickedMenuDownload(View view) {
        mMainActivity.getMainManager().getLayoutManager().getMenuLayout().runMenuAnimation(view);

        // 启动离线地图下载Activity
        mMainActivity.startOfflineMapDownloadActivity();
    }
}
