package com.gh.emap.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2016/11/18.
 * 用户覆盖物类
 */

public class MyUserOverlays {
    private Context mContext;
    private MyShapEdit mMyShapEdit;

    public MyUserOverlays(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mMyShapEdit = new MyShapEdit();
    }

    public void putMyUserPoint(MyUserPoint myUserPoint) {
        mMyShapEdit.put(myUserPoint);
    }

    public void putMyUserPoint(ArrayList<MyUserPoint> myUserPoints) {
        mMyShapEdit.put(myUserPoints);
    }

    public MyUserPoint getMyUserPoint(String key) {
        return mMyShapEdit.get(key);
    }
}
