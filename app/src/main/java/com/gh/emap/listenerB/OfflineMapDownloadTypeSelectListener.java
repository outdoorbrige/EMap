package com.gh.emap.listenerB;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.TOfflineMapManager;

/**
 * Created by GuHeng on 2017/3/31.
 */

public class OfflineMapDownloadTypeSelectListener implements View.OnClickListener {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    private final int MAP_TYPE_ALL = 0;

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

    // 下载全部
    private void onClickedAll(View view) {
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getOfflineMapDownloadTypeSelectLayout().hide();

        boolean bReturn = startDownload(MAP_TYPE_ALL);
    }

    // 下载影像
    private void onClickedImage(View view) {
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getOfflineMapDownloadTypeSelectLayout().hide();

        boolean bReturn = startDownload(MapView.TMapType.MAP_TYPE_IMG);
    }

    // 下载矢量
    private void onClickedVector(View view) {
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getOfflineMapDownloadTypeSelectLayout().hide();

        boolean bReturn = startDownload(MapView.TMapType.MAP_TYPE_VEC);
    }

    // 取消
    private void onClickedCancel(View view) {
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getOfflineMapDownloadTypeSelectLayout().hide();
    }

    private TOfflineMapManager getTOfflineMapManager() {
        MainActivity mainActivity = mOfflineMapDownloadActivity.getMyApplication().getMainActivity();
        if(mainActivity == null) {
            return null;
        }

        TOfflineMapManager tOfflineMapManager = mainActivity.getMainManager().getMapManager().getTOfflineMapManager();
        return tOfflineMapManager;
    }

    private boolean startDownload(int mapType) {
        TOfflineMapManager tOfflineMapManager = getTOfflineMapManager();
        if(tOfflineMapManager == null) {
            return false;
        }

        String city = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getOfflineMapDownloadTypeSelectLayout().getTitle();
        if(city == null || city.isEmpty()) {
            return false;
        }

        boolean bReturn = true;

        switch (mapType) {
            case MAP_TYPE_ALL:
                bReturn = bReturn && tOfflineMapManager.startDownload(city, MapView.TMapType.MAP_TYPE_IMG);
                bReturn = bReturn && tOfflineMapManager.startDownload(city, MapView.TMapType.MAP_TYPE_VEC);
                break;
            case MapView.TMapType.MAP_TYPE_IMG:
                bReturn = bReturn && tOfflineMapManager.startDownload(city, MapView.TMapType.MAP_TYPE_IMG);
                break;
            case MapView.TMapType.MAP_TYPE_VEC:
                bReturn = bReturn && tOfflineMapManager.startDownload(city, MapView.TMapType.MAP_TYPE_VEC);
                break;
            default:
                bReturn = bReturn && false;
                break;
        }

        return bReturn;
    }
}
