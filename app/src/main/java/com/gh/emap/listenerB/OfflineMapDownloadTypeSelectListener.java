package com.gh.emap.listenerB;

import android.view.View;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/3/31.
 */

public class OfflineMapDownloadTypeSelectListener implements View.OnClickListener {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    public OfflineMapDownloadTypeSelectListener(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.offline_map_download_select_all: // 下载全部
                onClickedAll(view);
                break;
            case R.id.offline_map_download_select_image: // 下载影像
                onClickedImage(view);
                break;
            case R.id.offline_map_download_select_vector: // 下载矢量
                onClickedVector(view);
                break;
            case R.id.offline_map_download_select_cancel: // 取消
                onClickedCancel(view);
                break;
            default:
                break;
        }
    }

    private void onClickedAll(View view) {

    }

    private void onClickedImage(View view) {

    }

    private void onClickedVector(View view) {

    }

    private void onClickedCancel(View view) {
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getOfflineMapDownloadTypeSelectLayout().hide();
    }
}
