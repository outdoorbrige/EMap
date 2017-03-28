package com.gh.emap.managerA;

import com.gh.emap.MainActivity;
import com.gh.emap.fileA.EMapFile;
import com.gh.emap.fileA.GroundRenderLineFile;
import com.gh.emap.fileA.GroundRenderPointFile;
import com.gh.emap.fileB.CurrentCityFile;
import com.gh.emap.fileB.HotCitiesFile;
import com.gh.emap.fileB.OtherProvincesCitiesFile;

/**
 * Created by GuHeng on 2016/11/9.
 * 文件管理类
 */
public class FileManager {
    private MainActivity mMainActivity;
    private EMapFile mEMapFile;
    private GroundRenderPointFile mGroundRenderPointFile;
    private GroundRenderLineFile mGroundRenderLineFile;
    private CurrentCityFile mCurrentCityFile;
    private HotCitiesFile mHotCitiesFile;
    private OtherProvincesCitiesFile mOtherProvincesCitiesFile;

    public FileManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mEMapFile = new EMapFile(mMainActivity);
        mEMapFile.init();

        mGroundRenderPointFile = new GroundRenderPointFile(mMainActivity);
        mGroundRenderPointFile.init();

        mGroundRenderLineFile = new GroundRenderLineFile(mMainActivity);
        mGroundRenderLineFile.init();

        mCurrentCityFile = new CurrentCityFile(mMainActivity);
        mCurrentCityFile.init();

        mHotCitiesFile = new HotCitiesFile(mMainActivity);
        mHotCitiesFile.init();

        mOtherProvincesCitiesFile = new OtherProvincesCitiesFile(mMainActivity);
        mOtherProvincesCitiesFile.init();
    }

    public void unInit() {

    }

    public EMapFile getEMapFile() {
        return mEMapFile;
    }

    public GroundRenderPointFile getGroundRenderPointFile() {
        return mGroundRenderPointFile;
    }

    public GroundRenderLineFile getGroundRenderLineFile() {
        return mGroundRenderLineFile;
    }

    public CurrentCityFile getCurrentPositionFile() {
        return mCurrentCityFile;
    }

    public HotCitiesFile getHotCitiesFile() {
        return mHotCitiesFile;
    }

    public OtherProvincesCitiesFile getOtherProvincesCitiesFile() {
        return mOtherProvincesCitiesFile;
    }
}
