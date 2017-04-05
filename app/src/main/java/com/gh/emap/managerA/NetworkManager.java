package com.gh.emap.managerA;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gh.emap.MainActivity;

/**
 * Created by GuHeng on 2017/4/5.
 * 网络状态监测
 */

public class NetworkManager extends BroadcastReceiver {
    private MainActivity mMainActivity;
    private BroadcastReceiver mBroadcastReceiver;
    private boolean mNetworkAvailable;

    public NetworkManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mBroadcastReceiver = this;
        mNetworkAvailable = false;

        registerReceiver();
    }

    public void unInit() {
        unregisterReceiver();
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mMainActivity.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        if (mBroadcastReceiver != null) {
            mMainActivity.unregisterReceiver(mBroadcastReceiver);
        }
    }

    public void setNetworkAvailable(boolean networkAvailable) {
        mNetworkAvailable = networkAvailable;
    }

    public boolean isNetworkAvailable() {
        return mNetworkAvailable;
    }

    public void onReceive(Context var1, Intent var2) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mMainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            mNetworkAvailable = false;
            return;
        }

        NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mobileNetwork.isConnected() && !wifiNetwork.isConnected()) {
            mNetworkAvailable = false;
        } else {
            mNetworkAvailable = true;
            mMainActivity.getMainManager().getMapManager().getMapList();
        }
    }
}