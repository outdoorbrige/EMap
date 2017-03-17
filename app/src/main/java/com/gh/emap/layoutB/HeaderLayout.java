package com.gh.emap.layoutB;

import android.view.View;
import android.widget.Button;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/3/16.
 */

public class HeaderLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private View mLayout; // 布局
    private Button mBack; // 返回
    private Button mCityList; // 城市列表
    private Button mDownloadManager; // 下载管理

    public HeaderLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mLayout = mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_header);
        mBack = (Button)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_back_button);
        mCityList = (Button)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_city_list_button);
        mDownloadManager = (Button)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_download_manager_button);

        mBack.setOnClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getHeaderListener());
        mCityList.setOnClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getHeaderListener());
        mDownloadManager.setOnClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getHeaderListener());
    }

    public void unInit() {

    }

    // 显示布局
    public void show() {
        mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置

        if(mLayout.getVisibility() == View.GONE) {
            return;
        }

        mLayout.setVisibility(View.GONE);
    }

    // 判断布局的隐藏性
    public int getVisibility() {
        // View.VISIBLE    可见
        // View.INVISIBLE    不可见但是占用布局空间
        // View.GONE    不可见也不占用布局空搜索间
        return mLayout.getVisibility();
    }
}
