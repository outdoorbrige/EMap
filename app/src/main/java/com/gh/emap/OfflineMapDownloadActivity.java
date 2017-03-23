package com.gh.emap;

import android.app.Activity;
import android.os.Bundle;

import com.gh.emap.managerB.MainManager;

/**
 * Created by GuHeng on 2017/3/13.
 */

public class OfflineMapDownloadActivity extends Activity {
    private MyApplication mMyApplication;
    private MainManager mMainManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_map_download_activity);

        mMyApplication = (MyApplication)getApplication();
        mMyApplication.setOfflineMapDownloadActivity(this);

        mMainManager = new MainManager(this);
        mMainManager.init();
    }

    // 当一个Activity变为显示时被调用
    @Override
    protected void onStart() {
        super.onStart();
    }

    // 重新启动Activity时调用，总是在onStart方法以后执行
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    // 暂停Activity时被调用
    @Override
    protected void onPause() {
        super.onPause();
    }

    // 当Activity由暂停变为活动状态时调用
    @Override
    protected void onResume() {
        super.onResume();
    }

    // 停止Activity时被调用
    @Override
    protected void onStop() {
        super.onStop();
    }

    // 销毁Activity时被调用
    @Override
    protected void onDestroy() {
        mMainManager.unInit();
        super.onDestroy();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    public MyApplication getMyApplication() {
        return mMyApplication;
    }

    public MainManager getMainManager() {
        return mMainManager;
    }
}
