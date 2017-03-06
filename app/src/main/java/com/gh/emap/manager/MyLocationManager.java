package com.gh.emap.manager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.maps.GeoPointEx;

import java.util.List;

/**
 * Created by GuHeng on 2016/11/14.
 * 我的位置管理
 */
public class MyLocationManager {
    private MainActivity mMainActivity;
    private LocationManager mLocationManager;
    private String mProvider;
    private GeoPoint mGeoPoint;

    public MyLocationManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        // 获取定位服务
        mLocationManager = (LocationManager)mMainActivity.getSystemService(mMainActivity.LOCATION_SERVICE);

        // 获取当前可用的位置控制器
        List<String> providerList = mLocationManager.getProviders(true);

        mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo,
                String.format("位置控制器列表：%s", providerList.toString()));

        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            mProvider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            mProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError,
                    String.format("位置控制器无效！"));
            return;
        }

        if (ActivityCompat.checkSelfPermission(mMainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mMainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = mLocationManager.getLastKnownLocation(mProvider);
        if (location != null) {
            GeoPoint geoPoint = GeoPointEx.Double2GeoPoint(location.getLongitude(), location.getLatitude());
            mMainActivity.getMainManager().getMapManager().showPositionInfo(geoPoint);
            mMainActivity.getMainManager().getMyLocationManager().setGeoPoint(geoPoint);
        }

        //绑定定位事件，监听位置是否改变
        //第一个参数为控制器类型第二个参数为监听位置变化的时间间隔（单位：毫秒）
        //第三个参数为位置变化的间隔（单位：米）第四个参数为位置监听器
        mLocationManager.requestLocationUpdates(mProvider, 1000, 1, mMainActivity.getMainManager().getListenerManager().getMyLocationListener());
    }

    public void unInit() {
        if (ActivityCompat.checkSelfPermission(mMainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mMainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (mLocationManager != null) {
            mLocationManager.removeUpdates(mMainActivity.getMainManager().getListenerManager().getMyLocationListener());
        }
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        mGeoPoint = geoPoint;
    }

    public GeoPoint getGeoPoint() {
        return mGeoPoint;
    }
}
