package com.gh.emap.fileB;

import android.os.Environment;

import com.gh.emap.MainActivity;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.managerA.MapManager;
import com.gh.emap.modelB.OneCityInfo;
import com.gh.emap.modelB.OneProvinceInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/4/5.
 */

public class CacheProvincesCitiesFiles {
    private MainActivity mMainActivity;

    private OneCityInfo mCurrentCity;
    private ArrayList<OneCityInfo> mHotCities;
    private ArrayList<OneProvinceInfo> mOtherProvincesCities;

    public CacheProvincesCitiesFiles(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mCurrentCity = readCurrentCityFile();
        mHotCities = readHotCitiesFile();
        mOtherProvincesCities = readOtherProvincesCitiesFile();
    }

    public OneCityInfo getCurrentCity() {
        return mCurrentCity;
    }

    public ArrayList<OneCityInfo> getHotCities() {
        return mHotCities;
    }

    public ArrayList<OneProvinceInfo> getOtherProvincesCities() {
        return mOtherProvincesCities;
    }

    private String getCurrentCityFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return MapManager.getCachePath(mMainActivity) + File.separator +
                    "CurrentCity.config";
        } else {
            return null;
        }
    }

    private String getHotCitiesFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return MapManager.getCachePath(mMainActivity) + File.separator +
                    "HotCities.config";
        } else {
            return null;
        }
    }

    private String getOtherProvincesCitiesFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return MapManager.getCachePath(mMainActivity) + File.separator +
                    "OtherProvincesCities.config";
        } else {
            return null;
        }
    }

    public void writeCurrentCityFile(OneCityInfo currentCity) {
        if(currentCity == null) {
            return;
        }

        String[] errorMsg = {""};
        RWCurrentCityFile.write(getCurrentCityFile(), currentCity, errorMsg);
        if(!errorMsg[0].isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "缓存在线定位城市失败！" + errorMsg[0]);
        }
    }

    private OneCityInfo readCurrentCityFile() {
        String [] errorMsg = {""};
        OneCityInfo oneCityInfo = RWCurrentCityFile.read(getCurrentCityFile(), errorMsg);
        if(!errorMsg[0].isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "读取定位城市文件失败！" + errorMsg[0]);
            return null;
        }

        return oneCityInfo;
    }

    public void writeHotCitiesFile(ArrayList<OneCityInfo> hotCities) {
        if(hotCities == null || hotCities.isEmpty()) {
            return;
        }

        String[] errorMsg = {""};
        RWHotCitiesFile.write(getHotCitiesFile(), hotCities, errorMsg);
        if(!errorMsg[0].isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "缓存在线热门城市列表失败！" + errorMsg[0]);
        }
    }

    private ArrayList<OneCityInfo> readHotCitiesFile() {
        String[] errorMsg = {""};
        ArrayList<OneCityInfo> hotCities = RWHotCitiesFile.read(getHotCitiesFile(), errorMsg);
        if(!errorMsg[0].isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "读取热门城市列表文件失败！" + errorMsg[0]);
            return null;
        }

        return hotCities;
    }

    public void writeOtherProvincesCitiesFile(ArrayList<OneProvinceInfo> otherCitiesProvinces) {
        if(otherCitiesProvinces == null || otherCitiesProvinces.isEmpty()) {
            return;
        }

        String[] errorMsg = {""};
        RWOtherProvincesCitiesFile.write(getOtherProvincesCitiesFile(), otherCitiesProvinces, errorMsg);
        if(!errorMsg[0].isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "缓存在线其他省市列表失败！" + errorMsg[0]);
        }
    }

    private ArrayList<OneProvinceInfo> readOtherProvincesCitiesFile() {
        String[] errorMsg = {""};
        ArrayList<OneProvinceInfo> otherProvinceCities = RWOtherProvincesCitiesFile.read(getOtherProvincesCitiesFile(), errorMsg);
        if(!errorMsg[0].isEmpty()) {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError, "读取其他省市列表文件失败！" + errorMsg[0]);
            return null;
        }

        return otherProvinceCities;
    }
}
