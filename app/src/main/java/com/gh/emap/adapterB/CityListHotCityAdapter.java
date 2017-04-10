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
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.TOfflineMapInfo;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2017/4/1.
 */

public class CityListHotCityAdapter extends BaseAdapter {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private ArrayList<TOfflineMapManager.City> mCities;

    public CityListHotCityAdapter(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        super();
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void setHotCities(ArrayList<TOfflineMapManager.City> cities) {
        mCities = cities;
    }

    public int getCount() {
        if(mCities == null) {
            return 0;
        }

        return mCities.size();
    }

    public Object getItem(int var1) {
        if(mCities == null || mCities.size() <= var1) {
            return null;
        }

        return mCities.get(var1);
    }

    public long getItemId(int var1) {
        return var1;
    }

    public View getView(int var1, View var2, ViewGroup var3) {
        if (var2 == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mOfflineMapDownloadActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            var2 = layoutInflater.inflate(R.layout.city_list_hot_cities_city, null);
        }

        var2.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

        if(mCities == null || mCities.isEmpty()) {
            return var2;
        }

        TOfflineMapManager.City city = mCities.get(var1);
        if(city == null) {
            return var2;
        }

        ArrayList<TOfflineMapInfo> tOfflineMapInfos = city.getMaps();
        if(tOfflineMapInfos == null) {
            return var2;
        }

        int imageSize = 0;
        int vectorSize = 0;

        for(int i = 0; i < tOfflineMapInfos.size(); i ++) {
            TOfflineMapInfo tOfflineMapInfo = tOfflineMapInfos.get(i);
            switch (tOfflineMapInfo.getType()) {
                case MapView.TMapType.MAP_TYPE_IMG:
                    imageSize = tOfflineMapInfo.getSize();
                    break;
                case MapView.TMapType.MAP_TYPE_VEC:
                    vectorSize = tOfflineMapInfo.getSize();
                    break;
                default:
                    break;
            }
        }

        ArrayList<HashMap<String, Object>> listViewItems = new ArrayList<>();

        int index = 0;
        HashMap<String, Object> map = new HashMap<>();
        map.put(CityListLayout.getItemKeys()[index ++], city.getName());

        if(imageSize > 0) {
            map.put(CityListLayout.getItemKeys()[index++], R.mipmap.offline_map_state_undownload);
            map.put(CityListLayout.getItemKeys()[index++], " ");
            map.put(CityListLayout.getItemKeys()[index++], mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().formatImageSize(imageSize));
            map.put(CityListLayout.getItemKeys()[index++], "      ");
        } else {
            index ++;
            index ++;
            index ++;
            index ++;
        }

        if(vectorSize > 0) {
            map.put(CityListLayout.getItemKeys()[index++], R.mipmap.offline_map_state_undownload);
            map.put(CityListLayout.getItemKeys()[index++], " ");
            map.put(CityListLayout.getItemKeys()[index++], mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().formatVectorSize(vectorSize));
        } else {
            index ++;
            index ++;
            index ++;
        }

        map.put(CityListLayout.getItemKeys()[index ++], R.mipmap.offline_map_no_download);

        listViewItems.add(map);

        SimpleAdapter simpleAdapter = new SimpleAdapter(mOfflineMapDownloadActivity, listViewItems, R.layout.offline_map_download_city_list_item,
                CityListLayout.getItemKeys(), CityListLayout.getItemResources());

        ListView listView = (ListView)var2.findViewById(R.id.city_list_hot_cities_city_list);
        listView.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getCityListHotCityListener());

        return var2;
    }
}
