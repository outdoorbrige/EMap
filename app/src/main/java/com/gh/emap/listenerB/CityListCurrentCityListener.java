package com.gh.emap.listenerB;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.modelB.OneCityInfo;

/**
 * Created by GuHeng on 2017/3/31.
 */

public class CityListCurrentCityListener implements AdapterView.OnItemClickListener {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    public CityListCurrentCityListener(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // id == -1 点击的是headerView或者footerView
        if (-1 < position && position < parent.getCount()) {
            TextView title = (TextView) view.findViewById(R.id.offline_map_download_city_list_item_title);
            if (title == null) {
                return;
            }

            String titleName = title.getText().toString();
            OneCityInfo oneCityInfo = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getOneCityInfoFromName(titleName);
            mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getOfflineMapDownloadTypeSelectLayout().show(oneCityInfo);
        }
    }
}
