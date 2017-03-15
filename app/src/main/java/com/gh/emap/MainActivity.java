package com.gh.emap;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gh.emap.managerA.LogManager;
import com.gh.emap.managerA.MainManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private MainManager mMainManager;
    private MyApplication mMyApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mMainManager = new MainManager(this);
        mMainManager.init();

        mMyApplication = (MyApplication)getApplication();
        mMyApplication.setMainActivity(this);

        mMainManager.getLogManager().log(LogManager.LogLevel.mInfo,
                String.format("屏幕 宽:%d, 高:%d; 密度 density:%f, densityDpi:%d; 精确密度 xdpi:%f, ydpi:%f; 物理 宽:%d, 高%d;",
                        getResources().getDisplayMetrics().widthPixels,
                        getResources().getDisplayMetrics().heightPixels,
                        getResources().getDisplayMetrics().density,
                        getResources().getDisplayMetrics().densityDpi,
                        getResources().getDisplayMetrics().xdpi,
                        getResources().getDisplayMetrics().ydpi,
                        (int)(Math.round(getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().density)),
                        (int)(Math.round(getResources().getDisplayMetrics().heightPixels / getResources().getDisplayMetrics().density))));

        addShortcut(getApplationName()); // 添加桌面快捷方式
    }

    public MainManager getMainManager() {
        return mMainManager;
    }

    // 当一个Activity变为显示时被调用
    @Override
    protected void onStart() {
        mMainManager.getMapManager().enableTMyLocationOverlay();
        mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onStart();
    }

    // 重新启动Activity时调用，总是在onStart方法以后执行
    @Override
    protected void onRestart() {
        mMainManager.getMapManager().enableTMyLocationOverlay();
        mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onRestart();
    }

    // 暂停Activity时被调用
    @Override
    protected void onPause() {
        mMainManager.getMapManager().disableTMyLocationOverlay();
        super.onPause();
    }

    // 当Activity由暂停变为活动状态时调用
    @Override
    protected void onResume() {
        mMainManager.getMapManager().enableTMyLocationOverlay();
        mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onResume();
    }

    // 停止Activity时被调用
    @Override
    protected void onStop() {
        mMainManager.getMapManager().disableTMyLocationOverlay();
        super.onStop();
    }

    // 销毁Activity时被调用
    @Override
    protected void onDestroy() {
        mMainManager.getMapManager().disableTMyLocationOverlay();
        mMainManager.unInit();
        super.onDestroy();
    }

    // 启动离线地图下载Activity
    public void startOfflineMapDownloadActivity() {
        Intent intent = new Intent(MainActivity.this, OfflineMapDownloadActivity.class);
        startActivity(intent);
    }

    // 获取应用程序名称
    public String getApplationName() {
        return "EMap";
    }

    // 添加快捷方式
    private void addShortcut(String name) {
        // 如果存在，就取消添加
        if(hasInstallShortcut(name)) {
            return;
        }

        final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

        Intent addShortcutIntent = new Intent(ACTION_ADD_SHORTCUT);

        // 不允许重复创建
        addShortcutIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
        // 屏幕上没有空间时会提示
        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式

        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 图标
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(MainActivity.this,
                        R.mipmap.ic_launcher));

        // 设置关联程序
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
        launcherIntent.setClass(MainActivity.this, MainActivity.class);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        addShortcutIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        sendBroadcast(addShortcutIntent);
    }

    // 移除快捷方式
    // 在小米系统上不管用，在三星上可以移除
    private void removeShortcut(String name) {
        final String ACTION_REMOVE_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT";

        // remove shortcut的方法在小米系统上不管用，在三星上可以移除
        Intent intent = new Intent(ACTION_REMOVE_SHORTCUT);

        // 名字
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 设置关联程序
        Intent launcherIntent = new Intent(MainActivity.this,
                MainActivity.class).setAction(Intent.ACTION_MAIN);

        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        sendBroadcast(intent);
    }

    // 查询快捷方式
    // 查询快捷方式是否存在的方法是从网上其他资料那里查来的，但是测试查询的时候失败了，两个手机(小米、三星)都查不到。
    // 先留着代码以后看看是什么原因吧
    private boolean hasInstallShortcut(String name) {

        boolean hasInstall = false;

        final String AUTHORITY = "com.android.launcher2.settings";
        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/favorites?notify=true");

        // 这里总是failed to find provider info
        // com.android.launcher2.settings和com.android.launcher.settings都不行
        Cursor cursor = this.getContentResolver().query(CONTENT_URI,
                new String[] { "title", "iconResource" }, "title=?",
                new String[] { name }, null);

        if (cursor != null && cursor.getCount() > 0) {
            hasInstall = true;
        }

        if(cursor != null) {
            cursor.close();
        }

        return hasInstall;
    }

    // 获取当前日期(年月日时分秒)字符串(不带分隔符)
    public String getCurrentDateF1() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());

        return simpleDateFormat.format(date);
    }

    // 获取当前日期(年月日时分秒)字符串(带分隔符)
    public String getCurrentDateF2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        return simpleDateFormat.format(date);
    }
}
