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
import com.gh.emap.modelB.OneCityInfo;
import com.gh.emap.modelB.OneProvinceInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2017/3/20.
 */

public class CityListOtherProvincesCitiesAdapter extends BaseExpandableListAdapter {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private ArrayList<OneProvinceInfo> mOtherProvincesCities;

    public CityListOtherProvincesCitiesAdapter(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        super();
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void setOtherProvincesCities(ArrayList<OneProvinceInfo> otherProvincesCities) {
        mOtherProvincesCities = otherProvincesCities;

        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().setOtherProvincesCitiesType("其他省市" +
                mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getFormatCount(getGroupCount()));
    }

    @Override
    public int getGroupCount() {
        if(mOtherProvincesCities == null) {
            return 0;
        }

        return mOtherProvincesCities.size();
    }

    @Override
    public int getChildrenCount(int var1) {
        if(mOtherProvincesCities == null || mOtherProvincesCities.size() <= var1 || mOtherProvincesCities.get(var1).getCities() == null) {
            return 0;
        }

        return mOtherProvincesCities.get(var1).getCities().size();
    }

    @Override
    public Object getGroup(int var1) {
        if(mOtherProvincesCities == null || mOtherProvincesCities.size() <= var1) {
            return null;
        }

        return mOtherProvincesCities.get(var1);
    }

    @Override
    public Object getChild(int var1, int var2) {
        if(mOtherProvincesCities == null || mOtherProvincesCities.size() <= var1 || mOtherProvincesCities.get(var1).getCities().size() <= var2) {
            return null;
        }

        return mOtherProvincesCities.get(var1).getCities().get(var2);
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

        OneProvinceInfo oneProvinceInfo = mOtherProvincesCities.get(var1);
        if(oneProvinceInfo == null) {
            return var3;
        }

        OneCityInfo oneCityInfo = oneProvinceInfo.getProvince();

        ArrayList<HashMap<String, Object>> listViewItems = new ArrayList<>();

        int index = 0;
        HashMap<String, Object> map = new HashMap<>();
        map.put(CityListLayout.getItemKeys()[index ++], oneCityInfo.getCityName());

        if(oneCityInfo.getMyOfflineMapInfoImage().getSize() > 0) {
            map.put(CityListLayout.getItemKeys()[index++], R.mipmap.offline_mapnavi_state_undownload);
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
            map.put(CityListLayout.getItemKeys()[index++], R.mipmap.offline_mapnavi_state_undownload);
            map.put(CityListLayout.getItemKeys()[index++], " ");
            map.put(CityListLayout.getItemKeys()[index++], mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().formatVectorSize(oneCityInfo.getMyOfflineMapInfoVector().getSize()));
        } else {
            index ++;
            index ++;
            index ++;
        }

        map.put(CityListLayout.getItemKeys()[index ++], R.mipmap.offline_map_collapse_group);

        ExpandableListView otherProvincesCitiesList = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().getOtherProvincesCitiesList();
        if(otherProvincesCitiesList != null) {
            int groupSelectedId = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getGroupSelectedId(oneProvinceInfo.getProvince().getCityName());
            if(groupSelectedId >= 0) {
                boolean isGroupExpanded = otherProvincesCitiesList.isGroupExpanded(groupSelectedId);
                if(isGroupExpanded) {
                    map.remove(CityListLayout.getItemKeys()[-- index]);
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

        OneProvinceInfo oneProvinceInfo = mOtherProvincesCities.get(var1);
        if(oneProvinceInfo == null) {
            return var4;
        }

        OneCityInfo oneCityInfo = oneProvinceInfo.getCities().get(var2);
        if(oneCityInfo == null) {
            return var4;
        }

        ArrayList<HashMap<String, Object>> listViewItems = new ArrayList<>();

        int index = 0;
        HashMap<String, Object> map = new HashMap<>();
        map.put(CityListLayout.getItemKeys()[index ++], oneCityInfo.getCityName());

        if(oneCityInfo.getMyOfflineMapInfoImage().getSize() > 0) {
            map.put(CityListLayout.getItemKeys()[index++], R.mipmap.offline_mapnavi_state_undownload);
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
            map.put(CityListLayout.getItemKeys()[index++], R.mipmap.offline_mapnavi_state_undownload);
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