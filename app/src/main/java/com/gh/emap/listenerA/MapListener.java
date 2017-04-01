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

public class MapListener implements TOfflineMapManager.OnGetMapsResult, TGeoDecode.OnGeoResultListener {
    private MainActivity mMainActivity;

    public MapListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    // 获取所有地图信息的接口
    @Override
    public void onGetResult(ArrayList<TOfflineMapManager.MapAdminSet> mapAdminSetArrayList, int errCode) {
        ArrayList<OneCityInfo> hotCities = new ArrayList<>();
        ArrayList<OneProvinceInfo> otherCitiesProvinces = new ArrayList<>();

        if(errCode == TErrorCode.OK) {
            for(int i = 0; i < mapAdminSetArrayList.size(); i ++) {
                TOfflineMapManager.MapAdminSet mapAdminSet = mapAdminSetArrayList.get(i);

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

                // TianDiTuSDK3.0.1此处有错误，Type值不正确；因此加入了Name值判断
                // if(mapAdminSet.getType() == TOfflineMapManager.MapAdminSet.MAP_SET_TYPE_HOTCITYS) { // 热门城市
                // }
                // else if(mapAdminSet.getType() == TOfflineMapManager.MapAdminSet.MAP_SET_TYPE_PROVINCE) { // 其他省市
                // }
                if(mapAdminSet.getName().equals("热门")) { // 热门城市
                    hotCities.addAll(oneCityInfoArrayList);
                } else { // 其他省市
                    OneProvinceInfo oneProvinceInfo = new OneProvinceInfo();
                    oneProvinceInfo.setProvince(oneCityInfo);
                    oneProvinceInfo.setCities(oneCityInfoArrayList);

                    otherCitiesProvinces.add(oneProvinceInfo);
                }
            }

            //mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo, "获取城市列表成功！");
        } else {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取城市列表失败！失败码：" + String.valueOf(errCode));
            return;
        }

        String[] errorMsg1 = {""};
        mMainActivity.getMainManager().getFileManager().getHotCitiesFile().write(hotCities, errorMsg1);
        if(!errorMsg1[0].isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "缓存在线热门城市列表失败！" + errorMsg1[0]);
        }

        String[] errorMsg2 = {""};
        mMainActivity.getMainManager().getFileManager().getOtherProvincesCitiesFile().write(otherCitiesProvinces, errorMsg2);
        if(!errorMsg2[0].isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "缓存在线其他省市列表失败！" + errorMsg2[0]);
        }
    }

    // 得到逆地理信息编码结果的回调函数
    @Override
    public void onGeoDecodeResult(TGeoAddress tGeoAddress, int errCode) {
        OneCityInfo oneCityInfo = new OneCityInfo();

        if(errCode == TErrorCode.OK) {
            oneCityInfo.setCityName(tGeoAddress.getCity());
            //mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo, "获取逆地理信息成功！");
        } else {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取逆地理信息失败！失败码：" + String.valueOf(errCode));
            return;
        }

        String[] errorMsg = {""};
        mMainActivity.getMainManager().getFileManager().getCurrentPositionFile().write(oneCityInfo, errorMsg);
        if(!errorMsg[0].isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "缓存在线定位城市失败！" + errorMsg[0]);
        }
    }
}
