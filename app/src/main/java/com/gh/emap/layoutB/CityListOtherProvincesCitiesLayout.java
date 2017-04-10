package com.gh.emap.layoutB;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.adapterB.CityListOtherProvincesCitiesAdapter;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/24.
 */

public class CityListOtherProvincesCitiesLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    private LinearLayout mLayout;
    private LinearLayout.LayoutParams mLayoutParams;

    private TextView mOtherProvincesCitiesType; // 其他城市
    private LinearLayout.LayoutParams mOtherProvincesCitiesTypeLayoutParams;

    private CityListOtherProvincesCitiesAdapter mOtherProvincesCitiesListAdapter;

    private ExpandableListView mOtherProvincesCitiesList; // 其他城市列表
    private LinearLayout.LayoutParams mOtherProvincesCitiesListLayoutParams;

    public CityListOtherProvincesCitiesLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mLayout = new LinearLayout(mOfflineMapDownloadActivity);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));

        mLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int padding = (int)mOfflineMapDownloadActivity.getResources().getDimension(R.dimen.offline_map_activity_city_list_padding);

        mOtherProvincesCitiesType = new TextView(mOfflineMapDownloadActivity);
        mOtherProvincesCitiesType.setPadding(padding, padding, padding, padding);
        mOtherProvincesCitiesType.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorLightGrey));

        mOtherProvincesCitiesTypeLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mLayout.addView(mOtherProvincesCitiesType, mOtherProvincesCitiesTypeLayoutParams);

        mOtherProvincesCitiesListAdapter = new CityListOtherProvincesCitiesAdapter(mOfflineMapDownloadActivity);

        mOtherProvincesCitiesList = new ExpandableListView(mOfflineMapDownloadActivity);
        mOtherProvincesCitiesList.setBackgroundColor(mOfflineMapDownloadActivity.getResources().getColor(R.color.colorWhite));
        mOtherProvincesCitiesList.setAdapter(mOtherProvincesCitiesListAdapter);
        mOtherProvincesCitiesList.setGroupIndicator(null); // 隐藏系统自带的箭头图标

        mOtherProvincesCitiesListLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mLayout.addView(mOtherProvincesCitiesList, mOtherProvincesCitiesListLayoutParams);
    }

    public LinearLayout getLayout() {
        return mLayout;
    }

    public LinearLayout.LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    public void setOtherProvincesCitiesType(String otherProvincesCitiesType) {
        mOtherProvincesCitiesType.setText(otherProvincesCitiesType);
    }

    public ExpandableListView getOtherProvincesCitiesList() {
        return mOtherProvincesCitiesList;
    }

    public void setOtherProvincesCities(ArrayList<TOfflineMapManager.MapAdminSet> provinces) {
        if(provinces == null || provinces.isEmpty()) {
            return;
        }

        mOtherProvincesCitiesListAdapter.setOtherProvincesCities(provinces);

        setOtherProvincesCitiesType("其他省市" + mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getFormatCount(provinces.size()));
        setExpandableListViewHeightBasedOnChildren(mOtherProvincesCitiesList, -1);
        mOtherProvincesCitiesListAdapter.notifyDataSetChanged();
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
