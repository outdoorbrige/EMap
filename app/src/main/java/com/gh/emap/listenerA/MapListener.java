package com.gh.emap.listenerA;

import com.gh.emap.MainActivity;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.modelB.MyOfflineMapInfo;
import com.gh.emap.modelB.OneCityInfo;
import com.gh.emap.modelB.OneProvinceInfo;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.TErrorCode;
import com.tianditu.android.maps.TGeoAddress;
import com.tianditu.android.maps.TGeoDecode;
import com.tianditu.android.maps.TOfflineMapInfo;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/17.
 */

public class MapListener implements TGeoDecode.OnGeoResultListener, TOfflineMapManager.OnGetMapsResult {
    private MainActivity mMainActivity;

    private OneCityInfo mCurrentCity;
    private ArrayList<OneCityInfo> mHotCities;
    private ArrayList<OneProvinceInfo> mOtherProvincesCities;

    public final int PROVINCE_CITY_TYPE_NONE = 0;
    public final int PROVINCE_CITY_TYPE_HOT_CITY = 1;
    public final int PROVINCE_CITY_TYPE_OTHER_PROVINCE = 2;

    public MapListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public OneCityInfo getCurrentCity() {
        return mCurrentCity;
    }

    public ArrayList<OneCityInfo> getHotCities() {
        return mHotCities;
    }

    public ArrayList<OneProvinceInfo> getOtherProvincesCities() {
        return mOtherProvincesCities;
    }

    // 得到逆地理信息编码结果的回调函数
    @Override
    public void onGeoDecodeResult(TGeoAddress tGeoAddress, int errorCode) {
        mCurrentCity = getCurrentCity(tGeoAddress, errorCode);
    }

    // 获取所有地图信息的接口
    @Override
    public void onGetResult(ArrayList<TOfflineMapManager.MapAdminSet> mapAdminSets, int errorCode) {
        mHotCities = getHotCities(mapAdminSets, errorCode);
        mOtherProvincesCities = getOtherProvincesCities(mapAdminSets, errorCode);
    }

    private OneCityInfo getCurrentCity(TGeoAddress tGeoAddress, int errCode) {
        OneCityInfo oneCityInfo = new OneCityInfo();

        if(errCode == TErrorCode.OK) {
            oneCityInfo.setCityName(tGeoAddress.getCity());
            //mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo, "获取逆地理信息成功！");
        } else {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取逆地理信息失败！失败码：" + String.valueOf(errCode));
            return null;
        }

        return oneCityInfo;
    }

    private ArrayList<OneCityInfo> getHotCities(ArrayList<TOfflineMapManager.MapAdminSet> mapAdminSetArrayList, int errCode) {
        ArrayList<OneCityInfo> hotCities = new ArrayList<>();

        if(errCode == TErrorCode.OK) {
            for(int i = 0; i < mapAdminSetArrayList.size(); i ++) {
                TOfflineMapManager.MapAdminSet mapAdminSet = mapAdminSetArrayList.get(i);

                if(getProvinceCityType(mapAdminSet) != PROVINCE_CITY_TYPE_HOT_CITY) {
                    continue;
                }

                ArrayList<OneCityInfo> oneCityInfoArrayList = new ArrayList<>();

                ArrayList<TOfflineMapManager.City> cityList = mapAdminSet.getCitys();
                for(int j = 0; j < cityList.size(); j ++) {
                    TOfflineMapManager.City city = cityList.get(j);

                    OneCityInfo tmpOneCityInfo = new OneCityInfo();
                    tmpOneCityInfo.setCityName(city.getName());

                    ArrayList<TOfflineMapInfo> tOfflineMapInfos = city.getMaps();
                    for(int k = 0; k < tOfflineMapInfos.size(); k ++) {
                        TOfflineMapInfo tOfflineMapInfo = tOfflineMapInfos.get(k);

                        MyOfflineMapInfo myOfflineMapInfo = new MyOfflineMapInfo();
                        myOfflineMapInfo.setTOfflineMapInfo(tOfflineMapInfo);

                        int mapType = tOfflineMapInfo.getType();
                        switch (mapType) {
                            case MapView.TMapType.MAP_TYPE_IMG:
                                tmpOneCityInfo.setMyOfflineMapInfoImage(myOfflineMapInfo);
                                break;
                            case MapView.TMapType.MAP_TYPE_VEC:
                                tmpOneCityInfo.setMyOfflineMapInfoVector(myOfflineMapInfo);
                                break;
                            default:
                                break;
                        }
                    }

                    oneCityInfoArrayList.add(tmpOneCityInfo);
                }

                hotCities.addAll(oneCityInfoArrayList);
                break;
            }

            //mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo, "获取城市列表成功！");
        } else {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取城市列表失败！失败码：" + String.valueOf(errCode));
            return null;
        }

        return hotCities;
    }

    private ArrayList<OneProvinceInfo> getOtherProvincesCities(ArrayList<TOfflineMapManager.MapAdminSet> mapAdminSetArrayList, int errCode) {
        ArrayList<OneProvinceInfo> otherCitiesProvinces = new ArrayList<>();

        if(errCode == TErrorCode.OK) {
            for(int i = 0; i < mapAdminSetArrayList.size(); i ++) {
                TOfflineMapManager.MapAdminSet mapAdminSet = mapAdminSetArrayList.get(i);

                if(getProvinceCityType(mapAdminSet) != PROVINCE_CITY_TYPE_OTHER_PROVINCE) {
                    continue;
                }

                int imageTotalSize = 0;
                int vectorTotalSize = 0;

                OneCityInfo oneCityInfo = new OneCityInfo();
                oneCityInfo.setCityName(mapAdminSet.getName());

                ArrayList<OneCityInfo> oneCityInfoArrayList = new ArrayList<>();

                ArrayList<TOfflineMapManager.City> cityList = mapAdminSet.getCitys();
                for(int j = 0; j < cityList.size(); j ++) {
                    TOfflineMapManager.City city = cityList.get(j);

                    OneCityInfo tmpOneCityInfo = new OneCityInfo();
                    tmpOneCityInfo.setCityName(city.getName());

                    ArrayList<TOfflineMapInfo> tOfflineMapInfos = city.getMaps();
                    for(int k = 0; k < tOfflineMapInfos.size(); k ++) {
                        TOfflineMapInfo tOfflineMapInfo = tOfflineMapInfos.get(k);

                        MyOfflineMapInfo myOfflineMapInfo = new MyOfflineMapInfo();
                        myOfflineMapInfo.setTOfflineMapInfo(tOfflineMapInfo);

                        int mapType = tOfflineMapInfo.getType();
                        int mapSize = tOfflineMapInfo.getSize();

                        switch (mapType) {
                            case MapView.TMapType.MAP_TYPE_IMG:
                                tmpOneCityInfo.setMyOfflineMapInfoImage(myOfflineMapInfo);
                                imageTotalSize = imageTotalSize + mapSize;
                                break;
                            case MapView.TMapType.MAP_TYPE_VEC:
                                tmpOneCityInfo.setMyOfflineMapInfoVector(myOfflineMapInfo);
                                vectorTotalSize = vectorTotalSize + mapSize;
                                break;
                            default:
                                break;
                        }
                    }

                    oneCityInfoArrayList.add(tmpOneCityInfo);
                }

                oneCityInfo.getMyOfflineMapInfoImage().setSize(imageTotalSize);
                oneCityInfo.getMyOfflineMapInfoVector().setSize(vectorTotalSize);

                OneProvinceInfo oneProvinceInfo = new OneProvinceInfo();
                oneProvinceInfo.setProvince(oneCityInfo);
                oneProvinceInfo.setCities(oneCityInfoArrayList);

                otherCitiesProvinces.add(oneProvinceInfo);
            }

            //mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo, "获取城市列表成功！");
        } else {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取城市列表失败！失败码：" + String.valueOf(errCode));
            return null;
        }

        return otherCitiesProvinces;
    }

    private int getProvinceCityType(TOfflineMapManager.MapAdminSet mapAdminSet) {
        if(mapAdminSet == null) {
            return PROVINCE_CITY_TYPE_NONE;
        }

        // TianDiTuSDK3.0.1此处有错误，Type值不正确；因此加入了Name值判断
        // if(mapAdminSet.getType() == TOfflineMapManager.MapAdminSet.MAP_SET_TYPE_HOTCITYS) { // 热门城市
        // }
        // else if(mapAdminSet.getType() == TOfflineMapManager.MapAdminSet.MAP_SET_TYPE_PROVINCE) { // 其他省市
        // }
        if(mapAdminSet.getName().equals("热门")) { // 热门城市
            return PROVINCE_CITY_TYPE_HOT_CITY;
        } else { // 其他省市
            return PROVINCE_CITY_TYPE_OTHER_PROVINCE;
        }
    }
}
