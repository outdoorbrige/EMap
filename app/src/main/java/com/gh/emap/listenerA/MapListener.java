package com.gh.emap.listenerA;

import com.gh.emap.MainActivity;
import com.gh.emap.managerA.LogManager;
import com.tianditu.android.maps.TErrorCode;
import com.tianditu.android.maps.TGeoAddress;
import com.tianditu.android.maps.TGeoDecode;
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
    public void onGetResult(ArrayList<TOfflineMapManager.MapAdminSet> maps, int error) {
        mMainActivity.getMainManager().getMapManager().setMapAdminSetResultCode(error);
        if(mMainActivity.getMainManager().getMapManager().isMapAdminSetResultOk()) {
            mMainActivity.getMainManager().getMapManager().setMapAdminSetResult(maps);
            //mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo, "获取城市列表成功！");
        } else {
            mMainActivity.getMainManager().getMapManager().setMapAdminSetResult(null);
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取城市列表失败！失败码：" + String.valueOf(error));
        }
    }

    // 得到逆地理信息编码结果的回调函数
    @Override
    public void onGeoDecodeResult(TGeoAddress addr, int errCode) {
        mMainActivity.getMainManager().getMapManager().setTGeoAddressCode(errCode);
        if(mMainActivity.getMainManager().getMapManager().isTGeoAddressOk()) {
            mMainActivity.getMainManager().getMapManager().setTGeoAddress(addr);
            //mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo, "获取逆地理信息成功！");
        } else {
            mMainActivity.getMainManager().getMapManager().setTGeoAddress(null);
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "获取逆地理信息失败！失败码：" + String.valueOf(errCode));
        }
    }
}
