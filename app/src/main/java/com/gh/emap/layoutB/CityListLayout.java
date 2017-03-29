package com.gh.emap.layoutB;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.modelB.OneCityInfo;
import com.gh.emap.modelB.OneProvinceInfo;

import java.util.ArrayList;
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

    private static String[] mItemKeys = {"Item1", "Item2", "Item3"};

    public CityListLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mLayout = mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list);

        mScrollView = (ScrollView)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list_scroll_view);
        mScrollViewLinearLayout = (LinearLayout)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list_scroll_view_layout);

        if(updateCurrentCity()) {
            mScrollViewLinearLayout.addView(mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListCurrentCityLayout().getLayout(),
                    mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListCurrentCityLayout().getLayoutParams());
        } else {

        }

        if(updateHotCities()) {
            mScrollViewLinearLayout.addView(mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListHotCityLayout().getLayout(),
                    mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListHotCityLayout().getLayoutParams());
        } else {

        }

        if(updateOtherCities()) {
            mScrollViewLinearLayout.addView(mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().getLayout(),
                    mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().getLayoutParams());
        } else {

        }
    }

    public static String[] getItemKeys() {
        return mItemKeys;
    }

    // 更新当前城市数据
    private boolean updateCurrentCity() {
        String[] errorMsg = {""};
        mOfflineMapDownloadActivity.getMainManager().getFileManager().getCurrentCityFile().read(errorMsg);
        if(!errorMsg[0].isEmpty()) {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取当前城市数据失败！" + errorMsg[0]);
            return false;
        }

        OneCityInfo oneCityInfo = mOfflineMapDownloadActivity.getMainManager().getFileManager().getCurrentCityFile().getCurrentCity();
        if(oneCityInfo == null) {
            oneCityInfo = new OneCityInfo();
            oneCityInfo.setCityName(mOfflineMapDownloadActivity.getResources().getString(R.string.default_location_city));
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().toastShowShort("定位失败！");
        }

        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListCurrentCityLayout().setCurrentCity(oneCityInfo);

        return true;
    }

    // 更新热门城市
    private boolean updateHotCities() {
        String[] errorMsg = {""};
        mOfflineMapDownloadActivity.getMainManager().getFileManager().getHotCitiesFile().read(errorMsg);
        if(!errorMsg[0].isEmpty()) {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取热门城市列表数据失败！" + errorMsg[0]);
            return false;
        }

        ArrayList<OneCityInfo> hotCities = mOfflineMapDownloadActivity.getMainManager().getFileManager().getHotCitiesFile().getHotCities();
        if(hotCities == null || hotCities.size() == 0) {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().toastShowShort("获取热门城市数据失败！");
            return false;
        }

        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListHotCityLayout().setHotCities(hotCities);

        return true;
    }

    // 更新其他省市
    private boolean updateOtherCities() {
        String[] errorMsg = {""};
        mOfflineMapDownloadActivity.getMainManager().getFileManager().getOtherProvincesCitiesFile().read(errorMsg);
        if(!errorMsg[0].isEmpty()) {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取其他省市列表数据失败！" + errorMsg[0]);
            return false;
        }

        ArrayList<OneProvinceInfo> otherProvincesCities = mOfflineMapDownloadActivity.getMainManager().getFileManager().getOtherProvincesCitiesFile().getOtherProvincesCities();
        if(otherProvincesCities == null || otherProvincesCities.size() == 0) {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().toastShowShort("获取其他省市数据失败！");
            return false;
        }

        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().setOtherProvincesCities(otherProvincesCities);

        return true;
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

    public int getGroupSelectedId(String groupTitle) {
        ArrayList<OneProvinceInfo> otherProvincesCities = mOfflineMapDownloadActivity.getMainManager().getFileManager().getOtherProvincesCitiesFile().getOtherProvincesCities();
        if(otherProvincesCities == null || otherProvincesCities.size() == 0) {
            return -1;
        }

        for(int i = 0; i < otherProvincesCities.size(); i ++) {
            if(groupTitle.contains(otherProvincesCities.get(i).getProvince().getCityName())) {
                return i;
            }
        }

        return -1;
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
