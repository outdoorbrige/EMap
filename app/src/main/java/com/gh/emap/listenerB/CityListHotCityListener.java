package com.gh.emap.listenerB;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.tianditu.android.maps.TOfflineMapManager;

/**
 * Created by GuHeng on 2017/3/31.
 */

public class CityListHotCityListener implements AdapterView.OnItemClickListener {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    public CityListHotCityListener(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // id == -1 点击的是headerView或者footerView
        if (-1 < position && position < parent.getCount()) {
            TextView nameView = (TextView) view.findViewById(R.id.offline_map_download_city_list_item_name);
            if (nameView == null) {
                return;
            }

            String name = nameView.getText().toString();
            TOfflineMapManager.City city = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getCityFromCityName(name);
            mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getOfflineMapDownloadTypeSelectLayout().show(city);
        }
    }
}
