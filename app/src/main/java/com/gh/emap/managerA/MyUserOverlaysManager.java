package com.gh.emap.managerA;

import com.gh.emap.MainActivity;

import com.gh.emap.overlayA.LineObject;
import com.gh.emap.overlayA.LineOverlay;
import com.gh.emap.overlayA.PlaneObject;
import com.gh.emap.overlayA.PlaneOverlay;
import com.gh.emap.overlayA.PointObject;
import com.gh.emap.overlayA.PointOverlay;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2016/12/15.
 * 用户覆盖物管理类
 */

public class MyUserOverlaysManager {
    private MainActivity mMainActivity;
    private ArrayList<PointOverlay> mPointOverlays = new ArrayList<>();
    private ArrayList<LineOverlay> mLineOverlays = new ArrayList<>();
    private ArrayList<PlaneOverlay> mPlaneOverlays = new ArrayList<>();

    public MyUserOverlaysManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
    }

    public void unInit() {

    }

    public ArrayList<PointOverlay> getPointOverlays() {
        return mPointOverlays;
    }

    public boolean addPointObject(PointObject pointObject) {
        PointOverlay pointOverlay = new PointOverlay(mMainActivity);
        pointOverlay.setPointObject(pointObject);

        return mPointOverlays.add(pointOverlay);
    }

    public boolean addPointObjects(ArrayList<PointObject> pointObjects) {
        boolean bAdd = true;
        if(pointObjects != null) {
            for(int i = 0; i < pointObjects.size(); i ++) {
                bAdd = bAdd && addPointObject(pointObjects.get(i));
            }
        }

        return bAdd;
    }

    public ArrayList<LineOverlay> getLineOverlays() {
        return mLineOverlays;
    }

    public boolean addLineObject(LineObject lineObject) {
        LineOverlay lineOverlay = new LineOverlay(mMainActivity);
        lineOverlay.setLineObject(lineObject);

        return mLineOverlays.add(lineOverlay);
    }

    public boolean addLineObjects(ArrayList<LineObject> lineObjects) {
        boolean bAdd = true;
        if(lineObjects != null) {
            for(int i = 0; i < lineObjects.size(); i ++) {
                bAdd = bAdd && addLineObject(lineObjects.get(i));
            }
        }

        return bAdd;
    }

    public ArrayList<PlaneOverlay> getPlaneOverlays() {
        return mPlaneOverlays;
    }

    public boolean addPlaneObject(PlaneObject planeObject) {
        PlaneOverlay planeOverlay = new PlaneOverlay(mMainActivity);
        planeOverlay.setPlaneObject(planeObject);

        return mPlaneOverlays.add(planeOverlay);
    }

    public boolean addPlaneObjects(ArrayList<PlaneObject> planeObjects) {
        boolean bAdd = true;
        if(planeObjects != null) {
            for(int i = 0; i < planeObjects.size(); i ++) {
                bAdd = bAdd && addPlaneObject(planeObjects.get(i));
            }
        }

        return bAdd;
    }
}
