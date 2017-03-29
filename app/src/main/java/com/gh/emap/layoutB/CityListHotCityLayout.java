package com.gh.emap.layoutB;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.modelB.OneCityInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2017/3/24.
 */

public class CityListHotCityLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    private LinearLayout mLayout;
    private LinearLayout.LayoutParams mLayoutParams;

    private TextView mHotType; // 热门城市
    private LinearLayout.LayoutParams mHotTypeLayoutParams;

    private ArrayList<HashMap<String, Object>> mHotCitiesListItems;
    private SimpleAdapter mHotCitiesListAdapter;

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

        int padding = (int)(8 * mOfflineMapDownloadActivity.getResources().getDisplayMetrics().density); // 8dp 转换为 px

        mHotType = new TextView(mOfflineMapDownloadActivity);
        mHotType.setPadding(padding, padding, padding, padding);
        mHotType.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorLightGrey));

        mHotTypeLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mHotCitiesListItems = new ArrayList<>();
        mHotCitiesListAdapter = new SimpleAdapter(mOfflineMapDownloadActivity, mHotCitiesListItems, R.layout.offline_map_download_city_list_item,
                CityListLayout.getItemKeys(),
                new int[]{R.id.offline_map_download_city_list_item_title, R.id.offline_map_download_city_list_item_text, R.id.offline_map_download_city_list_item_image_view});

        mHotCitiesList = new ListView(mOfflineMapDownloadActivity);
        mHotCitiesList.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));
        mHotCitiesList.setAdapter(mHotCitiesListAdapter);

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

    public void setHotCities(ArrayList<OneCityInfo> hotCities) {
        if(hotCities == null) {
            return;
        }

        mHotCitiesListItems.clear();

        for(int i = 0; i < hotCities.size(); i ++) {

                HashMap<String, Object> map = new HashMap<>();
                map.put(CityListLayout.getItemKeys()[0], hotCities.get(i).getCityName());
                map.put(CityListLayout.getItemKeys()[1], "矢量图(0M)，影像图(0M)");
                map.put(CityListLayout.getItemKeys()[2], R.mipmap.offline_map_no_download);

                mHotCitiesListItems.add(map);
        }

        setHotType("热门城市" + mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getFormatCount(hotCities.size()));
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
