package com.gh.emap.adapterB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.layoutB.CityListLayout;
import com.gh.emap.modelB.OneCityInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2017/4/1.
 */

public class CityListCurrentCityAdapter extends BaseAdapter {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private OneCityInfo mCurrentCity;

    public CityListCurrentCityAdapter(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        super();
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void setCurrentCity(OneCityInfo currentCity) {
        mCurrentCity = currentCity;
    }

    public int getCount() {
        if(mCurrentCity == null) {
            return 0;
        }

        return 1;
    }

    public Object getItem(int var1) {
        if(mCurrentCity == null) {
            return null;
        }

        return mCurrentCity;
    }

    public long getItemId(int var1) {
        return var1;
    }

    public View getView(int var1, View var2, ViewGroup var3) {
        if (var2 == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mOfflineMapDownloadActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            var2 = layoutInflater.inflate(R.layout.city_list_current_city_city, null);
        }

        var2.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

        OneCityInfo oneCityInfo = mCurrentCity;
        if(oneCityInfo == null) {
            return var2;
        }

        ArrayList<HashMap<String, Object>> listViewItems = new ArrayList<>();

        int index = 0;
        HashMap<String, Object> map = new HashMap<>();
        map.put(CityListLayout.getItemKeys()[index ++], oneCityInfo.getCityName());

        if(oneCityInfo.getMyOfflineMapInfoImage().getSize() > 0) {
            map.put(CityListLayout.getItemKeys()[index++], R.mipmap.offline_map_state_undownload);
            map.put(CityListLayout.getItemKeys()[index++], " ");
            map.put(CityListLayout.getItemKeys()[index++], mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().formatImageSize(oneCityInfo.getMyOfflineMapInfoImage().getSize()));
            map.put(CityListLayout.getItemKeys()[index++], "      ");
        } else {
            index ++;
            index ++;
            index ++;
            index ++;
        }

        if(oneCityInfo.getMyOfflineMapInfoVector().getSize() > 0) {
            map.put(CityListLayout.getItemKeys()[index++], R.mipmap.offline_map_state_undownload);
            map.put(CityListLayout.getItemKeys()[index++], " ");
            map.put(CityListLayout.getItemKeys()[index++], mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().formatVectorSize(oneCityInfo.getMyOfflineMapInfoVector().getSize()));
        } else {
            index ++;
            index ++;
            index ++;
        }

        map.put(CityListLayout.getItemKeys()[index ++], R.mipmap.offline_map_no_download);

        listViewItems.add(map);

        SimpleAdapter simpleAdapter = new SimpleAdapter(mOfflineMapDownloadActivity, listViewItems, R.layout.offline_map_download_city_list_item,
                CityListLayout.getItemKeys(), CityListLayout.getItemResources());

        ListView listView = (ListView)var2.findViewById(R.id.city_list_current_city_city_list);
        listView.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getCityListCurrentCityListener());

        return var2;
    }
}
