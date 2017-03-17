package com.gh.emap.layoutB;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class CityListLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private View mLayout; // 布局

    private TextView mCurrentCityView; // 当前城市
    private ListView mCurrentCityList; // 当前城市列表
    private List<String> mCurrentCityListItems; // 当前城市列表元素集合
    private ArrayAdapter<String> mCurrentCityArrayAdapter; // 当前城市列表适配器

    private TextView mHotCityView; // 热门城市
    private ListView mHotCityList; // 热门城市列表
    private List<String> mHotCityListItems; // 热门城市列表元素集合
    private ArrayAdapter<String> mHotCityArrayAdapter; // 热门城市列表适配器

    private TextView mOtherCityView; // 其他城市
    private ExpandableListView mOtherCityList; // 其他城市列表

    public CityListLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mLayout = mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list);
        mCurrentCityView = (TextView)mOfflineMapDownloadActivity.findViewById(R.id.city_list_current_city_view);
        mCurrentCityList = (ListView)mOfflineMapDownloadActivity.findViewById(R.id.city_list_current_city_list);
        mHotCityView = (TextView)mOfflineMapDownloadActivity.findViewById(R.id.city_list_hot_city_view);
        mHotCityList = (ListView)mOfflineMapDownloadActivity.findViewById(R.id.city_list_hot_city_list);
        mOtherCityView = (TextView)mOfflineMapDownloadActivity.findViewById(R.id.city_list_other_city_view);
        mOtherCityList = (ExpandableListView)mOfflineMapDownloadActivity.findViewById(R.id.city_list_other_city_list);

        mCurrentCityListItems = new ArrayList<>();
        mCurrentCityListItems.add("北京市"); // 默认初始当前城市为北京市
        mCurrentCityArrayAdapter = new ArrayAdapter<>(mOfflineMapDownloadActivity, android.R.layout.simple_list_item_1, mCurrentCityListItems);
        mCurrentCityList.setAdapter(mCurrentCityArrayAdapter);

        mHotCityListItems = new ArrayList<>();
        mHotCityArrayAdapter = new ArrayAdapter<>(mOfflineMapDownloadActivity, android.R.layout.simple_list_item_1, mHotCityListItems);
        mHotCityList.setAdapter(mHotCityArrayAdapter);

        updateCurrentCity();
        updateHotAndOtherCitys();
    }

    // 更新当前城市数据
    public void updateCurrentCity() {
        if (mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getMapManager().isTGeoAddressOk()) {
            mCurrentCityListItems.clear();
            mCurrentCityListItems.add(mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getMapManager().getTGeoAddress().getCity());
            mCurrentCityArrayAdapter.notifyDataSetChanged();
        } else {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().toastShowShort("定位失败！");
        }
    }

    // 更新热门城市和其他省市数据
    public void updateHotAndOtherCitys() {
        if(mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getMapManager().isMapAdminSetResultOk()) {
            mHotCityListItems.clear();
            ArrayList<TOfflineMapManager.MapAdminSet> maps = mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getMapManager().getMapAdminSetResult();
            for(int i = 0; i < maps.size(); i ++) {
                TOfflineMapManager.MapAdminSet mapAdminSet = maps.get(i);
                // TianDiTuSDK3.0.1此处有错误，Type值不正确；因此加入了Name值判断
                // if(mapAdminSet.getType() == TOfflineMapManager.MapAdminSet.MAP_SET_TYPE_HOTCITYS) { // 热门城市
                // }
                // else if(mapAdminSet.getType() == TOfflineMapManager.MapAdminSet.MAP_SET_TYPE_PROVINCE) { // 其他省市
                // }
                if(mapAdminSet.getName().equals("热门")) { // 热门城市
                    ArrayList<TOfflineMapManager.City> citys = mapAdminSet.getCitys();
                    for(int j = 0; j < citys.size(); j ++) {
                        TOfflineMapManager.City city = citys.get(j);
                        mHotCityListItems.add(city.getName());
                    }

                    mHotCityArrayAdapter.notifyDataSetChanged();
                    mHotCityView.setText("热门城市(" + String.valueOf(mHotCityListItems.size()) + ")");

                } else { // 其他省市

                }
            }
        }
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
