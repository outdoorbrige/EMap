package com.gh.emap.layoutB;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.adapterB.CityListCurrentCityAdapter;
import com.tianditu.android.maps.TOfflineMapManager;

/**
 * Created by GuHeng on 2017/3/24.
 */

public class CityListCurrentCityLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    private LinearLayout mLayout;
    private LinearLayout.LayoutParams mLayoutParams;

    private TextView mCurrentType; // 当前城市
    private LinearLayout.LayoutParams mCurrentTypeLayoutParams;

    private CityListCurrentCityAdapter mCurrentCityListAdapter;

    private ListView mCurrentCityList;
    private LinearLayout.LayoutParams mCurrentCityListLayoutParams;

    public CityListCurrentCityLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mLayout = new LinearLayout(mOfflineMapDownloadActivity);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

        mLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int padding = (int)mOfflineMapDownloadActivity.getResources().getDimension(R.dimen.offline_map_activity_city_list_padding);

        mCurrentType = new TextView(mOfflineMapDownloadActivity);
        mCurrentType.setPadding(padding, padding, padding, padding);
        mCurrentType.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorLightGrey));

        mCurrentTypeLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mCurrentCityListAdapter = new CityListCurrentCityAdapter(mOfflineMapDownloadActivity);

        mCurrentCityList = new ListView(mOfflineMapDownloadActivity);
        mCurrentCityList.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));
        mCurrentCityList.setAdapter(mCurrentCityListAdapter);
        mCurrentCityList.setOnItemClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getCityListCurrentCityListener());

        mCurrentCityListLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mLayout.addView(mCurrentType, mCurrentTypeLayoutParams);
        mLayout.addView(mCurrentCityList, mCurrentCityListLayoutParams);
    }

    public LinearLayout getLayout() {
        return mLayout;
    }

    public LinearLayout.LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    public void setCurrentCity(TOfflineMapManager.City city) {
        if(city == null) {
            return;
        }

        mCurrentCityListAdapter.setCurrentCity(city);

        mCurrentType.setText("当前城市");
        setListViewHeightBasedOnChildren(mCurrentCityList);
        mCurrentCityListAdapter.notifyDataSetChanged();
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
