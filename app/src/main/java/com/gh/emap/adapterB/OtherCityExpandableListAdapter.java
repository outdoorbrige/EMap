package com.gh.emap.adapterB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by GuHeng on 2017/3/20.
 */

public class OtherCityExpandableListAdapter extends BaseExpandableListAdapter {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    public OtherCityExpandableListAdapter(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        super();
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    @Override
    public int getGroupCount() {
        return mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getOtherProvincesCities().size();
    }

    @Override
    public int getChildrenCount(int var1) {
        return mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getOtherProvincesCities().get(getGroup(var1)).size();
    }

    @Override
    public Object getGroup(int var1) {
        Map<String, List<String>> otherCityMap = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getOtherProvincesCities();

        Iterator<Map.Entry<String, List<String>>> iterator = otherCityMap.entrySet().iterator();
        Map.Entry<String, List<String>> entry = null;
        for (int i = 0; i < otherCityMap.size(); i++) {
            entry = iterator.next();

            if (i == var1) {
                return entry.getKey();
            }
        }

        return null;
    }

    @Override
    public Object getChild(int var1, int var2) {
        String group = (String)getGroup(var1);
        if(group == null || group.isEmpty()) {
            return null;
        }

        String child = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getOtherProvincesCities().get(group).get(var2);

        return child;
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
        if (var3 == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mOfflineMapDownloadActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            var3 = layoutInflater.inflate(R.layout.other_provinces_cities_province, null);
        }

        var3.setTag(R.layout.other_provinces_cities_province, var1);
        var3.setTag(R.layout.other_provinces_cities_city, -1);

        String group = (String)getGroup(var1);
        if(group == null || group.isEmpty()) {
            return var3;
        }

        group = group + mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getFormatCount(getChildrenCount(var1));

        ListView listView = (ListView)var3.findViewById(R.id.other_provinces_cities_province_name);
        List<String> items = new ArrayList<>();
        items.add(group);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mOfflineMapDownloadActivity, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getOtherCityExpandableListGroupItemListener());

        return var3;
    }

    @Override
    public View getChildView(int var1, int var2, boolean var3, View var4, ViewGroup var5) {
        if (var4 == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mOfflineMapDownloadActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            var4 = layoutInflater.inflate(R.layout.other_provinces_cities_city, null);
        }

        var4.setTag(R.layout.other_provinces_cities_province, var1);
        var4.setTag(R.layout.other_provinces_cities_city, var2);

        String child = (String)getChild(var1, var2);
        if(child == null || child.isEmpty()) {
            return var4;
        }

        ListView listView = (ListView)var4.findViewById(R.id.other_provinces_cities_city_name);
        List<String> items = new ArrayList<>();
        items.add(child);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mOfflineMapDownloadActivity, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        return var4;
    }

    @Override
    public boolean isChildSelectable(int var1, int var2) {
        return true;
    }
}