package com.gh.emap.managerA;

import com.gh.emap.MainActivity;

/**
 * Created by GuHeng on 2017/2/27.
 */

public class RenderOptionManager {
    private MainActivity mMainActivity;

    // 注意：此处添加的填充字符，是为了解决drawText文本显示不全问题；
    // 并无实际意义，待天地图新版SDK修复此问题后，可删除此填充字符串
    private String mFillChars = "              #";


    public RenderOptionManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {

    }

    public void unInit() {

    }

    public String getFillChars() {
        return mFillChars;
    }
}
