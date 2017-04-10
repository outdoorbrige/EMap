package com.gh.emap.layoutB;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.adapterB.CityListHotCityAdapter;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/24.
 */

public class CityListHotCityLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    private LinearLayout mLayout;
    private LinearLayout.LayoutParams mLayoutParams;

    private TextView mHotType; // 热门城市
    private LinearLayout.LayoutParams mHotTypeLayoutParams;

    private CityListHotCityAdapter mHotCitiesListAdapter;

    private ListView mHotCitiesList;
    private LinearLayout.LayoutParams mHotCitiesListLayoutParams;

    public CityListHotCityLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mLayout = new LinearLayout(mOfflineMapDownloadActivity);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

        mLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int padding = (int)mOfflineMapDownloadActivity.getResources().getDimension(R.dimen.offline_map_activity_city_list_padding);

        mHotType = new TextView(mOfflineMapDownloadActivity);
        mHotType.setPadding(padding, padding, padding, padding);
        mHotType.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorLightGrey));

        mHotTypeLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mHotCitiesListAdapter = new CityListHotCityAdapter(mOfflineMapDownloadActivity);

        mHotCitiesList = new ListView(mOfflineMapDownloadActivity);
        mHotCitiesList.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));
        mHotCitiesList.setAdapter(mHotCitiesListAdapter);
        mHotCitiesList.setOnItemClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getCityListHotCityListener());

        mHotCitiesListLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mLayout.addView(mHotType, mHotTypeLayoutParams);
        mLayout.addView(mHotCitiesList, mHotCitiesListLayoutParams);
    }

    public LinearLayout getLayout() {
        return mLayout;
    }

    public LinearLayout.LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    public void setHotType(String hotType) {
        mHotType.setText(hotType);
    }

    public void setHotCities(ArrayList<TOfflineMapManager.City> cities) {
        if(cities == null || cities.isEmpty()) {
            return;
        }

        mHotCitiesListAdapter.setHotCities(cities);

        setHotType("热门城市" + mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getFormatCount(cities.size()));
        setListViewHeightBasedOnChildren(mHotCitiesList);
        mHotCitiesListAdapter.notifyDataSetChanged();
    }

    // ScrollView中嵌套ListView
    private void setListViewHeightBasedOnChildren(ListView listView) {
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
