package com.gh.emap.listener;

import android.content.Context;
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
    private Context mContext;

    public MyLocationListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        ((MainActivity)this.mContext).getMainManager().getMapManager().showPositionInfo(GeoPointEx.Double2GeoPoint(location.getLongitude(), location.getLatitude()));
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
