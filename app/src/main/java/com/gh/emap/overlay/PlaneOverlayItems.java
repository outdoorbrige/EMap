package com.gh.emap.overlay;

import com.gh.emap.MainActivity;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/17.
 */

public class PlaneOverlayItems {
    private MainActivity mMainActivity;
    private ArrayList<PlaneObject> mPlaneObjects = new ArrayList<>();
    private ArrayList<PlaneOverlay> mPlaneOverlays = new ArrayList<>();

    public PlaneOverlayItems(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {

    }

    public PlaneOverlay PlaneObjectToPlaneOverlay(PlaneObject o) {
        if(o == null) {
            return null;
        }

        PlaneOverlay planeOverlay = new PlaneOverlay(mMainActivity);
        planeOverlay.setPoints(o.getGeoPoints());

        return planeOverlay;
    }

    public PlaneObject PlaneOverlayToPlaneObject(PlaneOverlay o) {
        if(o == null) {
            return null;
        }

        PlaneObject planeObject = new PlaneObject();
        planeObject.addGeoPoints(o.getPoints());

        return planeObject;
    }

    public ArrayList<PlaneOverlay> PlaneObjectsToPlaneOverlays(ArrayList<PlaneObject> objects) {
        if(objects == null) {
            return null;
        }

        ArrayList<PlaneOverlay> os = new ArrayList<>();
        for(int i = 0; i < objects.size(); i ++) {
            PlaneOverlay o = PlaneObjectToPlaneOverlay(objects.get(i));
            os.add(o);
        }

        return os;
    }

    public ArrayList<PlaneObject> PlaneOverlaysToPlaneObjects(ArrayList<PlaneOverlay> objects) {
        if(objects == null) {
            return null;
        }

        ArrayList<PlaneObject> os = new ArrayList<>();
        for(int i = 0; i < objects.size(); i ++) {
            PlaneObject o = PlaneOverlayToPlaneObject(objects.get(i));
            os.add(o);
        }

        return os;
    }

    public int size() {
        return mPlaneObjects.size();
    }

    public boolean isEmpty() {
        return mPlaneObjects.isEmpty();
    }

    public int indexOf(PlaneObject o) {
        return mPlaneObjects.indexOf(o);
    }

    public int indexOf(PlaneOverlay o) {
        return mPlaneOverlays.indexOf(o);
    }

    public int lastIndexOf(PlaneObject o) {
        return mPlaneObjects.lastIndexOf(o);
    }

    public int lastIndexOf(PlaneOverlay o) {
        return mPlaneOverlays.lastIndexOf(o);
    }

    public PlaneObject getPlaneObject(int index) {
        return mPlaneObjects.get(index);
    }

    public PlaneOverlay getPlaneOverlay(int index) {
        return mPlaneOverlays.get(index);
    }

    public PlaneObject set(int index, PlaneObject element) {
        mPlaneOverlays.set(index, PlaneObjectToPlaneOverlay(element));
        return mPlaneObjects.set(index, element);
    }

    public boolean add(PlaneObject e) {
        mPlaneOverlays.add(PlaneObjectToPlaneOverlay(e));
        return mPlaneObjects.add(e);
    }

    public void add(int index, PlaneObject element) {
        mPlaneOverlays.add(index, PlaneObjectToPlaneOverlay(element));
        mPlaneObjects.add(index, element);
    }

    public PlaneObject remove(int index) {
        mPlaneOverlays.remove(index);
        return mPlaneObjects.remove(index);
    }

    public boolean remove(PlaneObject o) {
        mPlaneOverlays.remove(PlaneObjectToPlaneOverlay(o));
        return mPlaneObjects.remove(o);
    }

    public void clear() {
        mPlaneOverlays.clear();
        mPlaneObjects.clear();
    }

    public boolean addAll(ArrayList<PlaneObject> c) {
        mPlaneOverlays.addAll(PlaneObjectsToPlaneOverlays(c));
        return mPlaneObjects.addAll(c);
    }

    public boolean addAll(int index, ArrayList<PlaneObject> c) {
        mPlaneOverlays.addAll(index, PlaneObjectsToPlaneOverlays(c));
        return mPlaneObjects.addAll(index, c);
    }


    protected boolean removeAll(ArrayList<PlaneObject> c) {
        mPlaneOverlays.removeAll(PlaneObjectsToPlaneOverlays(c));
        return mPlaneObjects.removeAll(c);
    }

    public ArrayList<PlaneObject> getPlaneObjects() {
        return mPlaneObjects;
    }
}
