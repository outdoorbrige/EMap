package com.gh.emap.listener;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.maps.GeoPointEx;

/**
 * Created by GuHeng on 2016/11/14.
 */
public class MyLocationListener implements LocationListener {
    private MainActivity mMainActivity;

    public MyLocationListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onLocationChanged(Location location) {
        GeoPoint geoPoint = GeoPointEx.Double2GeoPoint(location.getLongitude(), location.getLatitude());
        mMainActivity.getMainManager().getMapManager().showPositionInfo(geoPoint);
        mMainActivity.getMainManager().getMyLocationManager().setGeoPoint(geoPoint);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
