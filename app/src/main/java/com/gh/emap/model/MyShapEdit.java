package com.gh.emap.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2016/12/14.
 * 地物编辑
 */

public class MyShapEdit {
    private HashMap<String, MyUserPoint> mMyUserPoints;

    public MyShapEdit() {
        this.mMyUserPoints = new HashMap<>();
    }

    public HashMap<String, MyUserPoint> getMyUserPoints() {
        return this.mMyUserPoints;
    }

    public void put(MyUserPoint myUserPoint) {
        mMyUserPoints.put(myUserPoint.getName(), myUserPoint);
    }

    public void put(ArrayList<MyUserPoint> myUserPoints) {
        for(MyUserPoint myUserPoint : myUserPoints) {
            put(myUserPoint);
        }
    }

    public MyUserPoint get(String key) {
        return mMyUserPoints.get(key);
    }

    @Override
    public String toString() {
        return "MyShapEdit{" +
                "mMyUserPoints=" + mMyUserPoints +
                '}';
    }
}
