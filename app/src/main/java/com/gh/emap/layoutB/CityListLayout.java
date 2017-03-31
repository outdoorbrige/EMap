package com.gh.emap.layoutB;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.modelB.OneCityInfo;
import com.gh.emap.modelB.OneProvinceInfo;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class CityListLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private View mLayout; // 布局

    private ScrollView mScrollView;
    private LinearLayout mScrollViewLinearLayout;

    private static String[] mItemKeys = {"Item1", "Item2", "Item3"};

    public final long[] BYTE_FACTOR = {1, 1024, 1024 * 1024, 1024 * 1024 * 1024};
    public final String[] BYTE_UNIT = {"B", "KB", "MB", "GB"};

    public CityListLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mLayout = mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list);

        mScrollView = (ScrollView)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list_scroll_view);
        mScrollViewLinearLayout = (LinearLayout)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list_scroll_view_layout);

        if(loadProvincesAndCites()) {

        } else {

        }

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

    private boolean loadProvincesAndCites() {
        boolean bReturn = true;

        String[] errorMsg1 = {""};
        mOfflineMapDownloadActivity.getMainManager().getFileManager().getCurrentCityFile().read(errorMsg1);
        if(!errorMsg1[0].isEmpty()) {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取当前城市数据失败！" + errorMsg1[0]);
            bReturn = false;
        }

        String[] errorMsg2 = {""};
        mOfflineMapDownloadActivity.getMainManager().getFileManager().getHotCitiesFile().read(errorMsg2);
        if(!errorMsg2[0].isEmpty()) {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取热门城市列表数据失败！" + errorMsg2[0]);
            bReturn = false;
        }

        String[] errorMsg3 = {""};
        mOfflineMapDownloadActivity.getMainManager().getFileManager().getOtherProvincesCitiesFile().read(errorMsg3);
        if(!errorMsg3[0].isEmpty()) {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取其他省市列表数据失败！" + errorMsg3[0]);
            bReturn = false;
        }

        return bReturn;
    }

    // 更新当前城市数据
    private boolean updateCurrentCity() {
        OneCityInfo oneCityInfo = mOfflineMapDownloadActivity.getMainManager().getFileManager().getCurrentCityFile().getCurrentCity();
        if(oneCityInfo == null) {
            oneCityInfo = new OneCityInfo();
            oneCityInfo.setCityName(mOfflineMapDownloadActivity.getResources().getString(R.string.default_location_city));
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().toastShowShort("定位失败！");
        }

        oneCityInfo = getCurrentCityMapSize(oneCityInfo);

        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListCurrentCityLayout().setCurrentCity(oneCityInfo);

        return true;
    }

    // 更新热门城市
    private boolean updateHotCities() {
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
        ArrayList<OneProvinceInfo> otherProvincesCities = mOfflineMapDownloadActivity.getMainManager().getFileManager().getOtherProvincesCitiesFile().getOtherProvincesCities();
        if(otherProvincesCities == null || otherProvincesCities.size() == 0) {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().toastShowShort("获取其他省市数据失败！");
            return false;
        }

        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().setOtherProvincesCities(otherProvincesCities);

        return true;
    }

    // 获取当前城市影像和矢量地图的大小
    // 获取原因：逆地址解析的城市信息不包含地图类型和大小，需要手动查找相关信息
    private OneCityInfo getCurrentCityMapSize(OneCityInfo oneCityInfo) {
        String cityName = oneCityInfo.getCityName();
        int provinceEndIndex = cityName.indexOf("省");
        int cityEndIndex = cityName.indexOf("市");
        int cityStartIndex = -1;

        String province = "";
        String city = "";

        if(provinceEndIndex > 0) {
            province = cityName.substring(0, provinceEndIndex);
            cityStartIndex = provinceEndIndex + 1;
        } else {
            cityStartIndex = 0;
        }

        if(cityEndIndex > cityStartIndex) {
            city = cityName.substring(cityStartIndex, cityEndIndex);
        } else {
            city = cityName;
        }

        ArrayList<OneCityInfo> hotCities = mOfflineMapDownloadActivity.getMainManager().getFileManager().getHotCitiesFile().getHotCities();
        if(hotCities != null) {
            for(int i = 0; i < hotCities.size(); i ++) {
                OneCityInfo hotCity = hotCities.get(i);
                if(hotCity.getCityName().equals(city)) {
                    return hotCity;
                }
            }
        }

        ArrayList<OneProvinceInfo> otherProvincesCities = mOfflineMapDownloadActivity.getMainManager().getFileManager().getOtherProvincesCitiesFile().getOtherProvincesCities();
        if(otherProvincesCities == null) {
            for(int i = 0; i < otherProvincesCities.size(); i ++) {
                OneProvinceInfo provinceInfo = otherProvincesCities.get(i);
                for(int j = 0; j < provinceInfo.getCities().size(); j ++) {
                    OneCityInfo provinceCity = provinceInfo.getCities().get(j);
                    if(provinceCity.getCityName().equals(city)) {
                        return provinceCity;
                    }
                }
            }
        }

        return oneCityInfo;
    }

    public OneCityInfo getOneCityInfoFromName(String strName) {
        if(strName == null || strName.isEmpty()) {
            return null;
        }

        ArrayList<OneCityInfo> hotCities = mOfflineMapDownloadActivity.getMainManager().getFileManager().getHotCitiesFile().getHotCities();
        if(hotCities != null) {
            for(int i = 0; i < hotCities.size(); i ++) {
                OneCityInfo hotCity = hotCities.get(i);
                if(hotCity.getCityName().equals(strName)) {
                    return hotCity;
                }
            }
        }

        ArrayList<OneProvinceInfo> otherProvincesCities = mOfflineMapDownloadActivity.getMainManager().getFileManager().getOtherProvincesCitiesFile().getOtherProvincesCities();
        if(otherProvincesCities != null) {
            for(int i = 0; i < otherProvincesCities.size(); i ++) {
                OneProvinceInfo provinceInfo = otherProvincesCities.get(i);
                for(int j = 0; j < provinceInfo.getCities().size(); j ++) {
                    OneCityInfo provinceCity = provinceInfo.getCities().get(j);
                    if(provinceCity.getCityName().equals(strName)) {
                        return provinceCity;
                    }
                }
            }
        }

        return null;
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

    public String formatImageAndVectorSizeToText(long imageSize, long vectorSize) {
        String imageSizeAndUnit[] = {"", ""};
        formatByteToSizeAndUnit(imageSize, imageSizeAndUnit);

        String strImageSize = "";
        if(!imageSizeAndUnit[0].isEmpty() && Double.valueOf(imageSizeAndUnit[0]).compareTo(Double.valueOf("0")) > 0) {
            strImageSize = "影像" + "(" + imageSizeAndUnit[0] + imageSizeAndUnit[1] + ")";
        }

        String vectorSizeAndUnit[] = {"", ""};
        formatByteToSizeAndUnit(vectorSize, vectorSizeAndUnit);

        String strVectorSize = "";
        if(!vectorSizeAndUnit[0].isEmpty() && Double.valueOf(vectorSizeAndUnit[0]).compareTo(Double.valueOf("0")) > 0) {
            strVectorSize = "矢量" + "(" + vectorSizeAndUnit[0] + vectorSizeAndUnit[1] + ")";
        }

        String strImageVectorSize = "";

        if(strImageSize.isEmpty()) {
            strImageVectorSize = strVectorSize;
        } else {
            if(strVectorSize.isEmpty()) {
                strImageVectorSize = strImageSize;
            } else {
                strImageVectorSize = strImageSize + " " + strVectorSize;
            }
        }

        return strImageVectorSize;
    }

    public boolean formatByteToSizeAndUnit(long bytes, String[] sizeAndUnit) {
        double kb = (double) bytes / BYTE_FACTOR[1];
        double mb = (double) bytes / BYTE_FACTOR[2];
        double gb = (double) bytes / BYTE_FACTOR[3];

        if(Math.ceil(gb) > 1) {
            BigDecimal bigDecimal = new BigDecimal(gb);
            sizeAndUnit[0] = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
            sizeAndUnit[1] = BYTE_UNIT[3];
        }else if(Math.ceil(mb) > 1) {
            BigDecimal bigDecimal = new BigDecimal(mb);
            sizeAndUnit[0] = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
            sizeAndUnit[1] = BYTE_UNIT[2];
        }else if(Math.ceil(kb) > 1) {
            BigDecimal bigDecimal = new BigDecimal(kb);
            sizeAndUnit[0] = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
            sizeAndUnit[1] = BYTE_UNIT[1];
        } else {
            sizeAndUnit[0] = String.valueOf(bytes);
            sizeAndUnit[1] = BYTE_UNIT[0];
        }

        return true;
    }

    public Double getBytesFromSizeAndUnit(String[] sizeUnit) {
        Double bytes = null;

        if(sizeUnit[1].equals(BYTE_UNIT[0])) {
            bytes = Double.valueOf(sizeUnit[0]);
        } else if(sizeUnit[1].equals(BYTE_UNIT[1])) {
            bytes = Double.valueOf(sizeUnit[0]) * BYTE_FACTOR[1];
        } else if(sizeUnit[1].equals(BYTE_UNIT[2])) {
            bytes = Double.valueOf(sizeUnit[0]) * BYTE_FACTOR[2];
        } else if(sizeUnit[1].equals(BYTE_UNIT[3])) {
            bytes = Double.valueOf(sizeUnit[0]) * BYTE_FACTOR[3];
        } else {
            bytes = Double.valueOf(0);
        }

        return bytes;
    }

    public int getGroupSelectedId(String groupTitle) {
        ArrayList<OneProvinceInfo> otherProvincesCities = mOfflineMapDownloadActivity.getMainManager().getFileManager().getOtherProvincesCitiesFile().getOtherProvincesCities();
        if(otherProvincesCities == null || otherProvincesCities.size() == 0) {
            return -1;
        }

        for(int i = 0; i < otherProvincesCities.size(); i ++) {
            if(groupTitle.equals(otherProvincesCities.get(i).getProvince().getCityName())) {
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
