package com.gh.emap.listenerA;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.TGeoAddress;
import com.tianditu.android.maps.TGeoDecode;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/17.
 */

public class MapListener implements TGeoDecode.OnGeoResultListener, TOfflineMapManager.OnGetMapsResult, TOfflineMapManager.OnDownLoadResult {
    private MainActivity mMainActivity;

    public MapListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    // 得到逆地理信息编码结果的回调函数
    @Override
    public void onGeoDecodeResult(TGeoAddress tGeoAddress, int errorCode) {
        mMainActivity.getMainManager().getProvincesCitiesManager().setTGeoAddress(tGeoAddress, errorCode);
    }

    // 获取所有地图信息的接口
    @Override
    public void onGetResult(ArrayList<TOfflineMapManager.MapAdminSet> mapAdminSets, int errorCode) {
        mMainActivity.getMainManager().getProvincesCitiesManager().setMapAdminSets(mapAdminSets, errorCode);
    }

    // 下载开始
    @Override
    public void onDownLoadStart(String var1, int var2, int var3) {

    }

    // 下载停止
    @Override
    public void onDownLoadStop(String var1, int var2, int var3) {

    }

    // 下载数据
    @Override
    public void onDownLoadData(String var1, int var2, int var3) {

    }

    // 下载完成
    @Override
    public void onDownLoadOver(String var1, int var2, int var3) {

    }

    // 下载删除
    @Override
    public void onDownLoadDelete(String var1, int var2, int var3) {

    }
}
