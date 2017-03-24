package com.gh.emap.layoutB;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.adapterB.OtherCityExpandableListAdapter;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class CityListLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private View mLayout; // 布局

    private ScrollView mScrollView;
    private LinearLayout mScrollViewLinearLayout;

    private ArrayList<String> mCityTypes;

    private TextView mCurrentType; // 当前城市
    private TextView mCurrentCityName; // 当前城市列表

    private TextView mHotType; // 热门城市
    private List<String> mHotCities; // 热门城市列表元素集合

    private TextView mOtherType; // 其他城市
    private ExpandableListView mOtherCityExpandableList; // 其他城市列表
    private Map<String, List<String>> mOtherProvincesCities; // 其他省市数据集合
    private OtherCityExpandableListAdapter mOtherCityExpandableListAdapter;

    public CityListLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mLayout = mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list);

        mScrollView = (ScrollView)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list_scroll_view);
        mScrollViewLinearLayout = (LinearLayout)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list_scroll_view_layout);

        mCityTypes = new ArrayList<>();
        mCityTypes.add("当前城市");
        mCityTypes.add("热门城市");
        mCityTypes.add("其他省市");

        mCurrentType = new TextView(mOfflineMapDownloadActivity);
        mCurrentType.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorLightGrey));

        mCurrentCityName = new TextView(mOfflineMapDownloadActivity);
        mCurrentCityName.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

        mHotType = new TextView(mOfflineMapDownloadActivity);
        mHotType.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorLightGrey));

        mHotCities = new ArrayList<>();

        mOtherType = new TextView(mOfflineMapDownloadActivity);
        mOtherType.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorLightGrey));

        mOtherCityExpandableList = new ExpandableListView(mOfflineMapDownloadActivity);
        mOtherCityExpandableList.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));
        mOtherCityExpandableListAdapter = new OtherCityExpandableListAdapter(mOfflineMapDownloadActivity);
        mOtherProvincesCities = new HashMap<>();
        mOtherCityExpandableList.setAdapter(mOtherCityExpandableListAdapter);
        mOtherCityExpandableList.setGroupIndicator(null); // 隐藏系统自带的箭头图标

        updateCityList();
    }

    // 更新城市列表
    private void updateCityList() {
        updateCurrentCity();
        updateHotCitiesAndOtherProvincesCities();
    }

    // 更新当前城市数据
    private void updateCurrentCity() {
        mCurrentType.setText(mCityTypes.get(0));
        mScrollViewLinearLayout.addView(mCurrentType);

        String locationCity = null;
        if (mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getMapManager().isTGeoAddressOk()) {
            locationCity = mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getMapManager().getTGeoAddress().getCity();
        } else {
            locationCity = mOfflineMapDownloadActivity.getResources().getString(R.string.default_location_city);

            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().toastShowShort("定位失败！");
        }

        mCurrentCityName.setText(locationCity);
        mScrollViewLinearLayout.addView(mCurrentCityName);
    }

    // 更新热门城市和其他省市数据
    private void updateHotCitiesAndOtherProvincesCities() {
        if(!mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getMapManager().isMapAdminSetResultOk()) {
            return;
        }

        mHotCities.clear();

        mOtherProvincesCities.clear();

        ArrayList<TOfflineMapManager.MapAdminSet> maps = mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getMapManager().getMapAdminSetResult();
        for(int i = 0; i < maps.size(); i ++) {
            TOfflineMapManager.MapAdminSet mapAdminSet = maps.get(i);
            ArrayList<TOfflineMapManager.City> cityList = mapAdminSet.getCitys();

            List<String> cities = new ArrayList<>();
            for(int j = 0; j < cityList.size(); j ++) {
                TOfflineMapManager.City city = cityList.get(j);
                cities.add(city.getName());
            }

            // TianDiTuSDK3.0.1此处有错误，Type值不正确；因此加入了Name值判断
            // if(mapAdminSet.getType() == TOfflineMapManager.MapAdminSet.MAP_SET_TYPE_HOTCITYS) { // 热门城市
            // }
            // else if(mapAdminSet.getType() == TOfflineMapManager.MapAdminSet.MAP_SET_TYPE_PROVINCE) { // 其他省市
            // }
            if(mapAdminSet.getName().equals("热门")) { // 热门城市
                mHotCities.addAll(cities);
            } else { // 其他省市
                if(mOtherProvincesCities.containsKey(mapAdminSet.getName())) {
                    mOtherProvincesCities.values().add(cities);
                } else {
                    mOtherProvincesCities.put(mapAdminSet.getName(), cities);
                }
            }
        }

        // 热门城市

        mHotType.setText(mCityTypes.get(1) + getFormatCount(mHotCities.size()));

        mScrollViewLinearLayout.addView(mHotType);

        for(int i = 0; i < mHotCities.size(); i ++) {
            TextView textView = new TextView(mOfflineMapDownloadActivity);
            textView.setText(mHotCities.get(i));
            textView.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

            mScrollViewLinearLayout.addView(textView);
        }

        // 其他省市

        mOtherType.setText(mCityTypes.get(2) + getFormatCount(mOtherProvincesCities.size()));

        mScrollViewLinearLayout.addView(mOtherType);
        mScrollViewLinearLayout.addView(mOtherCityExpandableList);

        setExpandableListViewHeightBasedOnChildren(mOtherCityExpandableList, -1);

        mOtherCityExpandableListAdapter.notifyDataSetChanged();
    }

    public void setScrollViewToTop(ScrollView scrollView) {
        scrollView.smoothScrollTo(0, 0); // 移动滚动条到顶部
    }

    public LinearLayout getScrollViewLinearLayout() {
        return mScrollViewLinearLayout;
    }

    public String getFormatCount(int count) {
        return "(" + String.valueOf(count) + ")";
    }

    public ExpandableListView getOtherCityExpandableList() {
        return mOtherCityExpandableList;
    }

    public Map<String, List<String>> getOtherProvincesCities() {
        return mOtherProvincesCities;
    }

    public int getOtherProvincesCitiesKeyIndex(String key) {
        Iterator<Map.Entry<String, List<String>>> iterator = mOtherProvincesCities.entrySet().iterator();
        Map.Entry<String, List<String>> entry = null;
        for (int i = 0; i < mOtherProvincesCities.size(); i++) {
            entry = iterator.next();

            if (key.contains(entry.getKey())) {
                return i;
            }
        }

        return -1;
    }

    // ScrollView中嵌套ListView
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter == null) {
            return;
        }

        // 统计所有子项的总高度
        int totalHeight = 0;
        for(int i = 0; i < listAdapter.getCount(); i ++) {
            View listItem = listAdapter.getView(i, null, listView);

            // 计算子项View的高度，注意ListView所在的要是LinearLayout布局
            listItem.measure(0, 0);

            totalHeight = totalHeight + listItem.getMeasuredHeight();
        }

        // 统计所有子项间分隔符占用的总高度
        int totalDividerHeight = listView.getDividerHeight() * (listAdapter.getCount() - 1);

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        if(layoutParams == null) {
            layoutParams = new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        layoutParams.height = totalHeight + totalDividerHeight;

        listView.setLayoutParams(layoutParams);
    }

    // ScrollView中嵌套ExpandableListView
    public void setExpandableListViewHeightBasedOnChildren(ExpandableListView expandableListView, int groupPos) {
        ExpandableListAdapter expandableListAdapter = expandableListView.getExpandableListAdapter();
        if(expandableListAdapter == null) {
            return;
        }

        // 统计所有子项的总高度
        int totalHeight = 0;
        for(int i = 0; i < expandableListAdapter.getGroupCount(); i ++) {
            View listGroupItem = expandableListAdapter.getGroupView(i, true, null, expandableListView);

            // 计算子项View的高度，注意ExpandableListView所在的要是LinearLayout布局
            listGroupItem.measure(0, 0);

            totalHeight = totalHeight + listGroupItem.getMeasuredHeight();
        }

        if(groupPos > -1) {
            for(int j = 0; j < expandableListAdapter.getChildrenCount(groupPos); j ++) {
                View listChildItem = expandableListAdapter.getChildView(groupPos, j, false, null, expandableListView);

                // 计算子项View的高度，注意ExpandableListView所在的要是LinearLayout布局
                listChildItem.measure(0, 0);

                totalHeight = totalHeight + listChildItem.getMeasuredHeight();
            }
        }

        // 统计所有子项间分隔符占用的总高度
        int totalDividerHeight = expandableListView.getDividerHeight() * (expandableListAdapter.getGroupCount() - 1);

        ViewGroup.LayoutParams layoutParams = expandableListView.getLayoutParams();
        if(layoutParams == null) {
            layoutParams = new ExpandableListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        layoutParams.height = totalHeight + totalDividerHeight;

        expandableListView.setLayoutParams(layoutParams);
    }

    // 显示布局
    public void show() {
        mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置

        if(mLayout.getVisibility() == View.GONE) {
            return;
        }

        mLayout.setVisibility(View.GONE);
    }

    // 判断布局的隐藏性
    public int getVisibility() {
        // View.VISIBLE    可见
        // View.INVISIBLE    不可见但是占用布局空间
        // View.GONE    不可见也不占用布局空搜索间
        return mLayout.getVisibility();
    }
}
