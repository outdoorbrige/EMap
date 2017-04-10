package com.gh.emap;

import android.app.Application;

/**
 * Created by GuHeng on 2017/3/14.
 * 自己实现Application，实现数据共享
 *
 * --如想在整个应用中使用，在java中一般是使用静态变量，而在android中有个更优雅的方式是使用Application context。
 * --每个Activity 都是Context，其包含了其运行时的一些状态，android保证了其是single instance的。
 * --方法是创建一个属于你自己的android.app.Application的子类，然后在manifest中申明一下这个类，
 *   这是 android就为此建立一个全局可用的实例，你可以在其他任何地方使用Context.getApplicationContext()方法获取这个实例，进而获取其中的状态(变量)。
 */

public class MyApplication extends Application {
    private MainActivity mMainActivity = null;
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity = null;

    public void setMainActivity(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public MainActivity getMainActivity() {
        return mMainActivity;
    }

    public void setOfflineMapDownloadActivity(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public OfflineMapDownloadActivity getOfflineMapDownloadActivity() {
        return mOfflineMapDownloadActivity;
    }
}
