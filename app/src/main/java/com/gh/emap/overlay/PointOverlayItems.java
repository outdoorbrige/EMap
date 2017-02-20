package com.gh.emap.overlay;

import com.gh.emap.MainActivity;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2016/12/22.
 * 地物编辑-画点-覆盖物集合
 */

public class PointOverlayItems {
    private MainActivity mMainActivity;
    private ArrayList<PointObject> mPointObjects = new ArrayList<>();
    private ArrayList<PointOverlay> mPointOverlays = new ArrayList<>();
    private PointOverlay mNewPointObject;

    // 构造方法
    public PointOverlayItems(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    // 初始化
    public void init() {

    }

    public PointOverlay createNewPointObject() {
        mNewPointObject = new PointOverlay(mMainActivity);
        return mNewPointObject;
    }

    public PointOverlay getNewPointObject() {
        return mNewPointObject;
    }

    public PointOverlay PointObjectToPointOverlay(PointObject o) {
        if(o == null) {
            return null;
        }

        PointOverlay pointOverlay = new PointOverlay(mMainActivity);
        pointOverlay.setGeoPoint(o.getGeoPoint());

        return pointOverlay;
    }

    public PointObject PointOverlayToPointObject(PointOverlay o) {
        if(o == null) {
            return null;
        }

        PointObject pointObject = new PointObject();
        pointObject.setGeoPoint(o.getGeoPoint());

        return pointObject;
    }

    public ArrayList<PointOverlay> PointObjectsToPointOverlays(ArrayList<PointObject> objects) {
        if(objects == null) {
            return null;
        }

        ArrayList<PointOverlay> os = new ArrayList<>();
        for(int i = 0; i < objects.size(); i ++) {
            PointOverlay o = PointObjectToPointOverlay(objects.get(i));
            os.add(o);
        }

        return os;
    }

    public ArrayList<PointObject> PointOverlaysToPointObjects(ArrayList<PointOverlay> objects) {
        if(objects == null) {
            return null;
        }

        ArrayList<PointObject> os = new ArrayList<>();
        for(int i = 0; i < objects.size(); i ++) {
            PointObject o = PointOverlayToPointObject(objects.get(i));
            os.add(o);
        }

        return os;
    }

    public int size() {
        return mPointObjects.size();
    }

    public boolean isEmpty() {
        return mPointObjects.isEmpty();
    }

    public int indexOf(PointObject o) {
        return mPointObjects.indexOf(o);
    }

    public int indexOf(PointOverlay o) {
        return mPointOverlays.indexOf(o);
    }

    public int lastIndexOf(PointObject o) {
        return mPointObjects.lastIndexOf(o);
    }

    public int lastIndexOf(PointOverlay o) {
        return mPointOverlays.lastIndexOf(o);
    }

    public PointObject getPointObject(int index) {
        return mPointObjects.get(index);
    }

    public PointOverlay getPointOverlay(int index) {
        return mPointOverlays.get(index);
    }

    public PointObject set(int index, PointObject element) {
        mPointOverlays.set(index, PointObjectToPointOverlay(element));
        return mPointObjects.set(index, element);
    }

    public boolean add(PointObject e) {
        mPointOverlays.add(PointObjectToPointOverlay(e));
        return mPointObjects.add(e);
    }

    public void add(int index, PointObject element) {
        mPointOverlays.add(index, PointObjectToPointOverlay(element));
        mPointObjects.add(index, element);
    }

    public PointObject remove(int index) {
        mPointOverlays.remove(index);
        return mPointObjects.remove(index);
    }

    public boolean remove(PointObject o) {
        mPointOverlays.remove(PointObjectToPointOverlay(o));
        return mPointObjects.remove(o);
    }

    public void clear() {
        mPointOverlays.clear();
        mPointObjects.clear();
    }

    public boolean addAll(ArrayList<PointObject> c) {
        mPointOverlays.addAll(PointObjectsToPointOverlays(c));
        return mPointObjects.addAll(c);
    }

    public boolean addAll(int index, ArrayList<PointObject> c) {
        mPointOverlays.addAll(index, PointObjectsToPointOverlays(c));
        return mPointObjects.addAll(index, c);
    }


    protected boolean removeAll(ArrayList<PointObject> c) {
        mPointOverlays.removeAll(PointObjectsToPointOverlays(c));
        return mPointObjects.removeAll(c);
    }

    public ArrayList<PointObject> getPointObjects() {
        return mPointObjects;
    }
}
