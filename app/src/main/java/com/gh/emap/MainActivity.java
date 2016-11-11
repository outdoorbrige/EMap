package com.gh.emap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.gh.emap.manager.LogManager;
import com.gh.emap.manager.MainManager;

public class MainActivity extends AppCompatActivity {
    private MainManager mMainManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mMainManager = new MainManager(this);
        this.mMainManager.init();

        this.mMainManager.getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
                String.format("像素(长*宽):%d * %d, 像素密度:%f, 设备独立像素(长*宽):%d * %d",
                        this.getResources().getDisplayMetrics().widthPixels,
                        this.getResources().getDisplayMetrics().heightPixels,
                        this.getResources().getDisplayMetrics().density,
                        (int)(Math.round(this.getResources().getDisplayMetrics().widthPixels / this.getResources().getDisplayMetrics().density)),
                        (int)(Math.round(this.getResources().getDisplayMetrics().heightPixels / this.getResources().getDisplayMetrics().density))));
    }

    public MainManager getMainManager() {
        return this.mMainManager;
    }

    // 当一个Activity变为显示时被调用
    @Override
    protected void onStart() {
        this.mMainManager.getMapManager().enableTMyLocationOverlay();
        this.mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onStart();
    }

    // 重新启动Activity时调用，总是在onStart方法以后执行
    @Override
    protected void onRestart() {
        this.mMainManager.getMapManager().enableTMyLocationOverlay();
        this.mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onRestart();
    }

    // 暂停Activity时被调用
    @Override
    protected void onPause() {
        this.mMainManager.getMapManager().disableTMyLocationOverlay();
        super.onPause();
    }

    // 当Activity由暂停变为活动状态时调用
    @Override
    protected void onResume() {
        this.mMainManager.getMapManager().enableTMyLocationOverlay();
        this.mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onResume();
    }

    // 停止Activity时被调用
    @Override
    protected void onStop() {
        this.mMainManager.getMapManager().disableTMyLocationOverlay();
        super.onStop();
    }

    // 销毁Activity时被调用
    @Override
    protected void onDestroy() {
        this.mMainManager.getMapManager().disableTMyLocationOverlay();
        this.mMainManager.unInit();
        super.onDestroy();
    }
}
