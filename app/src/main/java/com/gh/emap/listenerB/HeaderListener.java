package com.gh.emap.listenerB;

import android.view.View;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/3/16.
 */

public class HeaderListener implements View.OnClickListener {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    public HeaderListener(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.offline_map_download_back_button: // 返回
                onClickedBack(view);
                break;
            case R.id.offline_map_download_city_list_button: // 城市列表
                onClickedCityList(view);
                break;
            case R.id.offline_map_download_download_manager_button: // 下载管理
                onClickedDownloadManager(view);
                break;
            default:
                break;
        }
    }

    // 返回
    private void onClickedBack(View view) {
        mOfflineMapDownloadActivity.finish();
    }

    // 城市列表
    private void onClickedCityList(View view) {
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().show();
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getDownloadManagerLayout().hide();
    }

    // 下载管理
    private void onClickedDownloadManager(View view) {
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getDownloadManagerLayout().show();
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().hide();
    }
}
