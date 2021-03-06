package com.gh.emap.managerA;

import com.gh.emap.MainActivity;
import com.gh.emap.fileA.EMapFile;
import com.gh.emap.fileA.GroundRenderLineFile;
import com.gh.emap.fileA.GroundRenderPointFile;

/**
 * Created by GuHeng on 2016/11/9.
 * 文件管理类
 */
public class FileManager {
    private MainActivity mMainActivity;
    private EMapFile mEMapFile;
    private GroundRenderPointFile mGroundRenderPointFile;
    private GroundRenderLineFile mGroundRenderLineFile;

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
}
