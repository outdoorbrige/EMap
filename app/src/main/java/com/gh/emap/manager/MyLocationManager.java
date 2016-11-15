package com.gh.emap.manager;

import android.Manifest;
import android.content.Context;
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
    private Context mContext;
    private LocationManager mLocationManager;
    private String mProvider;
    private GeoPoint mGeoPoint;

    public MyLocationManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        // 获取定位服务
        this.mLocationManager = (LocationManager) this.mContext.getSystemService(Context.LOCATION_SERVICE);

        // 获取当前可用的位置控制器
        List<String> providerList = this.mLocationManager.getProviders(true);

        ((MainActivity) this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
                String.format("位置控制器列表：%s", providerList.toString()));

        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            this.mProvider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            this.mProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            ((MainActivity) this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                    String.format("位置控制器无效！"));
            return;
        }

        if (ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location location = this.mLocationManager.getLastKnownLocation(this.mProvider);
        if (location != null) {
            GeoPoint geoPoint = GeoPointEx.Double2GeoPoint(location.getLongitude(), location.getLatitude());
            ((MainActivity) this.mContext).getMainManager().getMapManager().showPositionInfo(geoPoint);
            ((MainActivity) this.mContext).getMainManager().getMyLocationManager().setGeoPoint(geoPoint);
        }

        //绑定定位事件，监听位置是否改变
        //第一个参数为控制器类型第二个参数为监听位置变化的时间间隔（单位：毫秒）
        //第三个参数为位置变化的间隔（单位：米）第四个参数为位置监听器
        this.mLocationManager.requestLocationUpdates(this.mProvider, 1000, 1, ((MainActivity) this.mContext).getMainManager().getListenerManager().getMyLocationListener());
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.mGeoPoint = geoPoint;
    }

    public GeoPoint getGeoPoint() {
        return this.mGeoPoint;
    }

    public void removeUpdates() {
        if (ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (this.mLocationManager != null) {
            this.mLocationManager.removeUpdates(((MainActivity) this.mContext).getMainManager().getListenerManager().getMyLocationListener());
        }
    }
}
