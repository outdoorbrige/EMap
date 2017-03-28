package com.gh.emap.adapterB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.modelB.OneCityInfo;
import com.gh.emap.modelB.OneProvinceInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2017/3/20.
 */

public class OtherCityExpandableListAdapter extends BaseExpandableListAdapter {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    private ArrayList<OneProvinceInfo> mOtherProvincesCities;

    public OtherCityExpandableListAdapter(OfflineMapDownloadActivity offlineMapDownloadActivity) {
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
        if(mOtherProvincesCities == null || mOtherProvincesCities.size() == 0) {
            return 0;
        }

        return mOtherProvincesCities.size();
    }

    @Override
    public int getChildrenCount(int var1) {
        if(mOtherProvincesCities == null || mOtherProvincesCities.size() == 0) {
            return 0;
        }

        return mOtherProvincesCities.get(var1).getCities().size();
    }

    @Override
    public Object getGroup(int var1) {
        if(mOtherProvincesCities == null || mOtherProvincesCities.size() == 0) {
            return null;
        }

        return mOtherProvincesCities.get(var1);
    }

    @Override
    public Object getChild(int var1, int var2) {
        if(mOtherProvincesCities == null || mOtherProvincesCities.size() == 0) {
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
            var3 = layoutInflater.inflate(R.layout.other_provinces_cities_province, null);
        }

        ((LinearLayout)var3).setOrientation(LinearLayout.VERTICAL);
        var3.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

        var3.setTag(R.layout.other_provinces_cities_province, var1);
        var3.setTag(R.layout.other_provinces_cities_city, -1);

        OneProvinceInfo oneProvinceInfo = mOtherProvincesCities.get(var1);
        if(oneProvinceInfo == null) {
            return var3;
        }

        ArrayList<HashMap<String, Object>> listViewItems = new ArrayList<>();

        HashMap<String, Object> map = new HashMap<>();
        map.put("ItemTitle", oneProvinceInfo.getProvince().getCityName());
        map.put("ItemText", "矢量图(0M)，影像图(0M)");
        map.put("ItemDownload", R.mipmap.offline_map_no_download);

        listViewItems.add(map);

        SimpleAdapter simpleAdapter = new SimpleAdapter(mOfflineMapDownloadActivity, listViewItems, R.layout.offline_map_download_city_list_item,
                new String[]{"ItemTitle", "ItemText", "ItemDownload"},
                new int[]{R.id.offline_map_download_city_list_item_title, R.id.offline_map_download_city_list_item_text, R.id.offline_map_download_city_list_item_download});

        ListView listView = (ListView)var3.findViewById(R.id.other_provinces_cities_province_list);
        listView.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getOtherCityExpandableListGroupItemListener());

        return var3;
    }

    @Override
    public View getChildView(int var1, int var2, boolean var3, View var4, ViewGroup var5) {
        if (var4 == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mOfflineMapDownloadActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            var4 = layoutInflater.inflate(R.layout.other_provinces_cities_city, null);
        }

        ((LinearLayout)var4).setOrientation(LinearLayout.VERTICAL);
        var4.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

        var4.setTag(R.layout.other_provinces_cities_province, var1);
        var4.setTag(R.layout.other_provinces_cities_city, var2);

        OneProvinceInfo oneProvinceInfo = mOtherProvincesCities.get(var1);
        if(oneProvinceInfo == null) {
            return var4;
        }

        OneCityInfo oneCityInfo = oneProvinceInfo.getCities().get(var2);
        if(oneCityInfo == null) {
            return var4;
        }

        ArrayList<HashMap<String, Object>> listViewItems = new ArrayList<>();

        HashMap<String, Object> map = new HashMap<>();
        map.put("ItemTitle", oneCityInfo.getCityName());
        map.put("ItemText", "矢量图(0M)，影像图(0M)");
        map.put("ItemDownload", R.mipmap.offline_map_no_download);

        listViewItems.add(map);

        SimpleAdapter simpleAdapter = new SimpleAdapter(mOfflineMapDownloadActivity, listViewItems, R.layout.offline_map_download_city_list_item,
                new String[]{"ItemTitle", "ItemText", "ItemDownload"},
                new int[]{R.id.offline_map_download_city_list_item_title, R.id.offline_map_download_city_list_item_text, R.id.offline_map_download_city_list_item_download});

        ListView listView = (ListView)var4.findViewById(R.id.other_provinces_cities_city_list);
        listView.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));
        listView.setAdapter(simpleAdapter);
        //listView.setOnItemClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getOtherCityExpandableListGroupItemListener());

        return var4;
    }

    @Override
    public boolean isChildSelectable(int var1, int var2) {
        return true;
    }
}