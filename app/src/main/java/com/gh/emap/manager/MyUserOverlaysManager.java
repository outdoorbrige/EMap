package com.gh.emap.manager;

import android.content.Context;

import com.gh.emap.model.MyUserOverlays;

/**
 * Created by GuHeng on 2016/12/15.
 * 用户覆盖物管理类
 */

public class MyUserOverlaysManager {
    private Context mContext;
    private MyUserOverlays mMyUserOverlays;

    public MyUserOverlaysManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mMyUserOverlays = new MyUserOverlays(this.mContext);
        this.mMyUserOverlays.init();
    }

    public MyUserOverlays getMyUserOverlays() {
        return this.mMyUserOverlays;
    }
}
