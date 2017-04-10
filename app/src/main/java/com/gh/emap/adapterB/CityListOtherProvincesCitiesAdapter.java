package com.gh.emap.adapterB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
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
 * Created by GuHeng on 2017/3/20.
 */

public class CityListOtherProvincesCitiesAdapter extends BaseExpandableListAdapter {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private ArrayList<TOfflineMapManager.MapAdminSet> mProvinces;

    public CityListOtherProvincesCitiesAdapter(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        super();
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void setOtherProvincesCities(ArrayList<TOfflineMapManager.MapAdminSet> provinces) {
        mProvinces = provinces;

        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().setOtherProvincesCitiesType("其他省市" +
                mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getFormatCount(getGroupCount()));
    }

    @Override
    public int getGroupCount() {
        if(mProvinces == null) {
            return 0;
        }

        return mProvinces.size();
    }

    @Override
    public int getChildrenCount(int var1) {
        if(mProvinces == null || mProvinces.size() <= var1 || mProvinces.get(var1).getCitys() == null) {
            return 0;
        }

        return mProvinces.get(var1).getCitys().size();
    }

    @Override
    public Object getGroup(int var1) {
        if(mProvinces == null || mProvinces.size() <= var1) {
            return null;
        }

        return mProvinces.get(var1);
    }

    @Override
    public Object getChild(int var1, int var2) {
        if(mProvinces == null || mProvinces.size() <= var1 ||
                mProvinces.get(var1).getCitys() == null || mProvinces.get(var1).getCitys().size() <= var2) {
            return null;
        }

        return mProvinces.get(var1).getCitys().get(var2);
    }

    @Override
    public long getGroupId(int var1) {
        return var1;
    }

    @Override
    public long getChildId(int var1, int var2) {
        return var2;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int var1, boolean var2, View var3, ViewGroup var4) {
        if(var3 == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mOfflineMapDownloadActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            var3 = layoutInflater.inflate(R.layout.city_list_other_provinces_cities_province, null);
        }

        ((LinearLayout)var3).setOrientation(LinearLayout.VERTICAL);
        var3.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

        var3.setTag(R.layout.city_list_other_provinces_cities_province, var1);
        var3.setTag(R.layout.city_list_other_provinces_cities_city, -1);

        if(mProvinces == null || mProvinces.isEmpty()) {
            return var3;
        }

        TOfflineMapManager.MapAdminSet province = mProvinces.get(var1);
        if(province == null) {
            return var3;
        }

        ArrayList<TOfflineMapManager.City> cities = province.getCitys();
        if(cities == null) {
            return var3;
        }

        long imageSize = 0;
        long vectorSize = 0;

        for(int i = 0; i < cities.size(); i ++) {
            TOfflineMapManager.City city = cities.get(i);
            if(city == null) {
                continue;
            }

            ArrayList<TOfflineMapInfo> tOfflineMapInfos = city.getMaps();
            if(tOfflineMapInfos == null) {
                continue;
            }

            for(int j = 0; j < tOfflineMapInfos.size(); j ++) {
                TOfflineMapInfo tOfflineMapInfo = tOfflineMapInfos.get(j);
                switch (tOfflineMapInfo.getType()) {
                    case MapView.TMapType.MAP_TYPE_IMG:
                        imageSize = imageSize + tOfflineMapInfo.getSize();
                        break;
                    case MapView.TMapType.MAP_TYPE_VEC:
                        vectorSize = vectorSize + tOfflineMapInfo.getSize();
                        break;
                    default:
                        break;
                }
            }
        }

        ArrayList<HashMap<String, Object>> listViewItems = new ArrayList<>();

        int index = 0;
        HashMap<String, Object> map = new HashMap<>();
        map.put(CityListLayout.getItemKeys()[index ++], province.getName());

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

        map.put(CityListLayout.getItemKeys()[index], R.mipmap.offline_map_collapse_group);

        ExpandableListView otherProvincesCitiesList = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().getOtherProvincesCitiesList();
        if(otherProvincesCitiesList != null) {
            int groupSelectedId = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getGroupSelectedId(province.getName());
            if(groupSelectedId >= 0) {
                boolean isGroupExpanded = otherProvincesCitiesList.isGroupExpanded(groupSelectedId);
                if(isGroupExpanded) {
                    map.remove(CityListLayout.getItemKeys()[index]);
                    map.put(CityListLayout.getItemKeys()[index], R.mipmap.offline_map_expend_group); // 展开图片
                }
            }
        }

        listViewItems.add(map);

        SimpleAdapter simpleAdapter = new SimpleAdapter(mOfflineMapDownloadActivity, listViewItems, R.layout.offline_map_download_city_list_item,
                CityListLayout.getItemKeys(), CityListLayout.getItemResources());

        ListView listView = (ListView)var3.findViewById(R.id.city_list_other_provinces_cities_province_list);
        listView.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getOtherCityExpandableListGroupItemListener());

        return var3;
    }

    @Override
    public View getChildView(int var1, int var2, boolean var3, View var4, ViewGroup var5) {
        if (var4 == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mOfflineMapDownloadActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            var4 = layoutInflater.inflate(R.layout.city_list_other_provinces_cities_city, null);
        }

        ((LinearLayout)var4).setOrientation(LinearLayout.VERTICAL);
        var4.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

        var4.setTag(R.layout.city_list_other_provinces_cities_province, var1);
        var4.setTag(R.layout.city_list_other_provinces_cities_city, var2);

        if(mProvinces == null || mProvinces.isEmpty()) {
            return var4;
        }

        TOfflineMapManager.MapAdminSet province = mProvinces.get(var1);
        if(province == null) {
            return var4;
        }

        ArrayList<TOfflineMapManager.City> cities = province.getCitys();
        if(cities == null) {
            return var4;
        }

        TOfflineMapManager.City city = cities.get(var2);
        if(city == null) {
            return var4;
        }

        ArrayList<TOfflineMapInfo> tOfflineMapInfos = city.getMaps();
        if(tOfflineMapInfos == null) {
            return var4;
        }

        long imageSize = 0;
        long vectorSize = 0;

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

        map.put(CityListLayout.getItemKeys()[index], R.mipmap.offline_map_no_download);

        listViewItems.add(map);

        SimpleAdapter simpleAdapter = new SimpleAdapter(mOfflineMapDownloadActivity, listViewItems, R.layout.offline_map_download_city_list_item,
                CityListLayout.getItemKeys(), CityListLayout.getItemResources());

        ListView listView = (ListView)var4.findViewById(R.id.city_list_other_provinces_cities_city_list);
        listView.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorSmallGrey));
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getOtherCityExpandableListChildItemListener());

        return var4;
    }

    @Override
    public boolean isChildSelectable(int var1, int var2) {
        return true;
    }
}