package com.gh.emap.layoutB;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.ViewB.MyScrollView;
import com.tianditu.android.maps.TGeoAddress;
import com.tianditu.android.maps.TOfflineMapManager;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class CityListLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private View mLayout; // 布局

    private MyScrollView mScrollView;
    private LinearLayout mScrollViewLinearLayout;

    private static final String[] mItemKeys = {"Item1", "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8", "Item9"};
    private static final int[] mItemResources = {
            R.id.offline_map_download_city_list_item_name,
            R.id.offline_map_download_city_list_item_image_status,
            R.id.offline_map_download_city_list_item_fill_gaps1,
            R.id.offline_map_download_city_list_item_image,
            R.id.offline_map_download_city_list_item_fill_gaps2,
            R.id.offline_map_download_city_list_item_vector_status,
            R.id.offline_map_download_city_list_item_fill_gaps3,
            R.id.offline_map_download_city_list_item_vector,
            R.id.offline_map_download_city_list_item_picture};

    public final long[] BYTE_FACTOR = {1, 1024, 1024 * 1024, 1024 * 1024 * 1024};
    public final String[] BYTE_UNIT = {"B", "KB", "MB", "GB"};

    public final int PROVINCE_CITY_TYPE_NONE = 0;
    public final int PROVINCE_CITY_TYPE_HOT_CITY = 1;
    public final int PROVINCE_CITY_TYPE_OTHER_PROVINCE = 2;

    public CityListLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mLayout = mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list);

        mScrollView = (MyScrollView)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list_scroll_view);

        // 开始
        // 解决ScrollView中ListView导致自动滚动问题
        mScrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS); // 只有当其子类控件不需要获取焦点时才获取焦点
        mScrollView.setFocusable(true);
        mScrollView.setFocusableInTouchMode(true);

        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.requestFocusFromTouch();
                return false;
            }
        });
        // 结束

        mScrollViewLinearLayout = (LinearLayout)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list_scroll_view_layout);

        updateCitiesList();

        mScrollViewLinearLayout.addView(mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListCurrentCityLayout().getLayout(),
                mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListCurrentCityLayout().getLayoutParams());

        mScrollViewLinearLayout.addView(mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListHotCityLayout().getLayout(),
                mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListHotCityLayout().getLayoutParams());


        mScrollViewLinearLayout.addView(mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().getLayout(),
                mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().getLayoutParams());
    }

    public static String[] getItemKeys() {
        return mItemKeys;
    }

    public static int[] getItemResources() {
        return mItemResources;
    }

    // 更新城市列表界面数据
    private boolean updateCitiesList() {
        // 检测网络是否联通
        if(!mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getNetworkManager().isNetworkAvailable()) {
            mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getLogManager().toastShowShort("无法访问网络！");
        }

        // 更新当前城市数据
        TGeoAddress tGeoAddress = mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getProvincesCitiesManager().getTGeoAddress();
        if(tGeoAddress != null) {
            TOfflineMapManager.City city = getCityFromCityName(tGeoAddress.getCity());
            if (city != null) {
                mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListCurrentCityLayout().setCurrentCity(city);
            }
        }

        ArrayList<TOfflineMapManager.MapAdminSet> provinces = new ArrayList<>();

        // 更新热门城市和其他省市数据
        ArrayList<TOfflineMapManager.MapAdminSet> mapAdminSets =
                mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getProvincesCitiesManager().getMapAdminSets();
        if(mapAdminSets != null) {
            for(int i = 0; i < mapAdminSets.size(); i ++) {
                TOfflineMapManager.MapAdminSet mapAdminSet = mapAdminSets.get(i);
                ArrayList<TOfflineMapManager.City> cities = mapAdminSet.getCitys();

                int provinceCityType = getProvinceCityType(mapAdminSet);
                if(provinceCityType == PROVINCE_CITY_TYPE_HOT_CITY) {
                    mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListHotCityLayout().setHotCities(cities);
                } else if(provinceCityType == PROVINCE_CITY_TYPE_OTHER_PROVINCE) {
                    provinces.add(mapAdminSet);
                }
            }

            mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().setOtherProvincesCities(provinces);
        }

        return true;
    }

    public LinearLayout getScrollViewLinearLayout() {
        return mScrollViewLinearLayout;
    }

    public String getFormatCount(int count) {
        return "(" + String.valueOf(count) + ")";
    }

    public String formatImageSize(long imageSize) {
        String imageSizeAndUnit[] = {"", ""};
        formatByteToSizeAndUnit(imageSize, imageSizeAndUnit);

        String strImageSize = "";
        if(!imageSizeAndUnit[0].isEmpty() && Double.valueOf(imageSizeAndUnit[0]).compareTo(Double.valueOf("0")) > 0) {
            strImageSize = "影像" + "(" + imageSizeAndUnit[0] + imageSizeAndUnit[1] + ")";
        }

        return strImageSize;
    }

    public String formatVectorSize(long vectorSize) {
        String vectorSizeAndUnit[] = {"", ""};
        formatByteToSizeAndUnit(vectorSize, vectorSizeAndUnit);

        String strVectorSize = "";
        if(!vectorSizeAndUnit[0].isEmpty() && Double.valueOf(vectorSizeAndUnit[0]).compareTo(Double.valueOf("0")) > 0) {
            strVectorSize = "矢量" + "(" + vectorSizeAndUnit[0] + vectorSizeAndUnit[1] + ")";
        }

        return strVectorSize;
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
            bytes = Double.valueOf("0");
        }

        return bytes;
    }

    public int getGroupSelectedId(String groupTitle) {
        ArrayList<TOfflineMapManager.MapAdminSet> mapAdminSets =
                mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getProvincesCitiesManager().getMapAdminSets();
        if(mapAdminSets == null) {
            return -1;
        }

        int id = -1;
        for(int i = 0; i < mapAdminSets.size(); i ++) {
            TOfflineMapManager.MapAdminSet mapAdminSet = mapAdminSets.get(i);
            int provinceCityType = getProvinceCityType(mapAdminSet);
            if(provinceCityType == PROVINCE_CITY_TYPE_OTHER_PROVINCE) {
                id = id + 1;

                if(mapAdminSet.getName().equals(groupTitle)) {
                    break;
                }
            }
        }

        return id;
    }

    // 根据城市名称获取当前城市详细数据
    public TOfflineMapManager.City getCityFromCityName(String cityName) {
        if(cityName == null || cityName.isEmpty()) {
            return null;
        }

        cityName = prepareFormatCityName(cityName);

        ArrayList<TOfflineMapManager.MapAdminSet> mapAdminSets =
                mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getProvincesCitiesManager().getMapAdminSets();
        if(mapAdminSets == null || mapAdminSets.isEmpty()) {
            return null;
        }

        for(int i = 0; i < mapAdminSets.size(); i ++) {
            TOfflineMapManager.MapAdminSet mapAdminSet = mapAdminSets.get(i);

            ArrayList<TOfflineMapManager.City> cities = mapAdminSet.getCitys();
            if(cities == null || cities.isEmpty()) {
                continue;
            }

            for(int j = 0; j < cities.size(); j ++) {
                TOfflineMapManager.City city = cities.get(j);

                if(city.getName().equals(cityName)) {
                    return city;
                }
            }
        }

        return null;
    }

    public String prepareFormatCityName(String cityName) {
        final String PROVINCE_SUFFIX = "省";
        final String CITY_SUFFIX = "市";

        if(cityName == null || cityName.isEmpty()) {
            return null;
        }

        String strCity = null;
        String[] provinceResults = cityName.split(PROVINCE_SUFFIX);
        if(provinceResults.length == 2) {
            strCity = provinceResults[1];
        } else {
            strCity = cityName;
        }

        String strResult = null;
        String[] cityResults = strCity.split(CITY_SUFFIX);
        if(cityResults.length > 0) {
            strResult = cityResults[0];
        }

        return strResult;
    }

    public int getProvinceCityType(TOfflineMapManager.MapAdminSet mapAdminSet) {
        if(mapAdminSet == null) {
            return PROVINCE_CITY_TYPE_NONE;
        }

        // TianDiTuSDK3.0.1此处有错误，Type值不正确；因此加入了Name值判断
        // if(mapAdminSet.getType() == TOfflineMapManager.MapAdminSet.MAP_SET_TYPE_HOTCITYS) { // 热门城市
        // }
        // else if(mapAdminSet.getType() == TOfflineMapManager.MapAdminSet.MAP_SET_TYPE_PROVINCE) { // 其他省市
        // }
        if(mapAdminSet.getName().equals(mOfflineMapDownloadActivity.getResources().getString(R.string.map_set_type_hot_city))) { // 热门城市
            return PROVINCE_CITY_TYPE_HOT_CITY;
        } else { // 其他省市
            return PROVINCE_CITY_TYPE_OTHER_PROVINCE;
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
