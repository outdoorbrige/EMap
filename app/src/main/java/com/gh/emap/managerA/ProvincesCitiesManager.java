package com.gh.emap.managerA;

import android.content.Context;
import android.content.SharedPreferences;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.Utility.MyReflectionMechanism;
import com.tianditu.android.maps.TErrorCode;
import com.tianditu.android.maps.TGeoAddress;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/4/5.
 */

public class ProvincesCitiesManager {
    private MainActivity mMainActivity;

    private TGeoAddress mTGeoAddress;
    private ArrayList<TOfflineMapManager.MapAdminSet> mMapAdminSets;

    public final String LAST_LOCATION_CITY = "last_location_city";

    public ProvincesCitiesManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {

    }

    public void unInit() {
        saveLastLocationCity();
    }

    public TGeoAddress getTGeoAddress() {
        if(mTGeoAddress == null) {
            mTGeoAddress = createTGeoAddress();
        }

        return mTGeoAddress;
    }

    public void setTGeoAddress(TGeoAddress tGeoAddress, int errorCode) {
        if(errorCode == TErrorCode.OK) {
            mTGeoAddress = tGeoAddress;
        }
    }

    public ArrayList<TOfflineMapManager.MapAdminSet> getMapAdminSets() {
        return mMapAdminSets;
    }

    public void setMapAdminSets(ArrayList<TOfflineMapManager.MapAdminSet> mapAdminSets, int errorCode) {
        if(errorCode == TErrorCode.OK) {
            mMapAdminSets = mapAdminSets;
        }
    }

    private TGeoAddress createTGeoAddress() {
        try {
            String lastLocationCity = null;

            SharedPreferences sharedPreferences = mMainActivity.getSharedPreferences(mMainActivity.getResources().getString(R.string.application_name), Context.MODE_PRIVATE);
            lastLocationCity = sharedPreferences.getString(LAST_LOCATION_CITY, mMainActivity.getResources().getString(R.string.default_location_city));

            mMainActivity.getResources().getString(R.string.default_location_city);

            // 创建无参构造函数
            // 使用相对路径，同一个包中可以不用带包路径
            TGeoAddress tGeoAddress = (TGeoAddress)MyReflectionMechanism.newInstance("com.tianditu.android.maps.TGeoAddress", new Object[] {});
            MyReflectionMechanism.invokeMethod(tGeoAddress, "setCity", new Object[] {lastLocationCity});

            return tGeoAddress;
        } catch (Exception exception) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "createTGeoAddress错误：" + exception.toString());
        }

        return null;
    }

    private void saveLastLocationCity() {
        if(mTGeoAddress == null || mTGeoAddress.getCity() == null || mTGeoAddress.getCity().isEmpty()) {
            return;
        }

        SharedPreferences.Editor editor = mMainActivity.getSharedPreferences(mMainActivity.getResources().getString(R.string.application_name), Context.MODE_PRIVATE).edit();
        editor.putString(LAST_LOCATION_CITY, mTGeoAddress.getCity());
    }
}
