package com.gh.emap.modelB;

import com.tianditu.android.maps.MapView;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/3/27.
 * 离线地图一个城市信息类
 */

public class OneCityInfo implements Serializable {
    private String mCityName; // 当前城市名称
    MyOfflineMapInfo[] mMyOfflineMapInfos = {new MyOfflineMapInfo(), new MyOfflineMapInfo(), new MyOfflineMapInfo()};

    public OneCityInfo() {

    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        this.mCityName = cityName;
    }

    public MyOfflineMapInfo getMyOfflineMapInfoImage() {
        return mMyOfflineMapInfos[MapView.TMapType.MAP_TYPE_IMG];
    }

    public void setMyOfflineMapInfoImage(MyOfflineMapInfo myOfflineMapInfoImage) {
        mMyOfflineMapInfos[MapView.TMapType.MAP_TYPE_IMG] = myOfflineMapInfoImage;
    }

    public MyOfflineMapInfo getMyOfflineMapInfoVector() {
        return mMyOfflineMapInfos[MapView.TMapType.MAP_TYPE_VEC];
    }

    public void setMyOfflineMapInfoVector(MyOfflineMapInfo myOfflineMapInfoVector) {
        mMyOfflineMapInfos[MapView.TMapType.MAP_TYPE_VEC] = myOfflineMapInfoVector;
    }
}
