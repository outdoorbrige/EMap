package com.gh.emap.overlay;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.overlay.PolygonOverlay;
import com.tianditu.android.maps.renderoption.PlaneOption;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/17.
 */

public class PlaneOverlayItems {
    private MainActivity mMainActivity;
    private ArrayList<PlaneObject> mPlaneObjects = new ArrayList<>();
    private ArrayList<PolygonOverlay> mPolygonOverlays = new ArrayList<>();

    public PlaneOverlayItems(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {

    }

    public PolygonOverlay PlaneObjectToPolygonOverlay(PlaneObject o) {
        if(o == null) {
            return null;
        }

        PlaneOption planeOption = new PlaneOption();
        planeOption.setStrokeWidth(o.getStrokeWidth());
        planeOption.setStrokeColor(o.getStrokeColor());
        planeOption.setDottedLine(o.isDottedLine());
        planeOption.setIntervals(o.getIntervals());
        planeOption.setFillColor(o.getFillColor());

        PolygonOverlay polygonOverlay = new PolygonOverlay();
        polygonOverlay.setOption(planeOption);
        polygonOverlay.setPoints(o.getGeoPoints());

        return polygonOverlay;
    }

    public PlaneObject PolygonOverlayToPlaneObject(PolygonOverlay o) {
        if(o == null) {
            return null;
        }

        PlaneObject planeObject = new PlaneObject();
        planeObject.setStrokeWidth(o.getOption().getStrokeWidth());
        planeObject.setStrokeColor(o.getOption().getStrokeColor());
        planeObject.setDottedLine(o.getOption().isDottedLine());
        planeObject.setIntervals(o.getOption().getIntervals());
        planeObject.setFillColor(o.getOption().getFillColor());
        planeObject.addGeoPoints(o.getPoints());

        return planeObject;
    }

    public ArrayList<PolygonOverlay> PlaneObjectsToPolygonOverlays(ArrayList<PlaneObject> objects) {
        if(objects == null) {
            return null;
        }

        ArrayList<PolygonOverlay> os = new ArrayList<>();
        for(int i = 0; i < objects.size(); i ++) {
            PolygonOverlay o = PlaneObjectToPolygonOverlay(objects.get(i));
            os.add(o);
        }

        return os;
    }

    public ArrayList<PlaneObject> PolygonOverlaysToPlaneObjects(ArrayList<PolygonOverlay> objects) {
        if(objects == null) {
            return null;
        }

        ArrayList<PlaneObject> os = new ArrayList<>();
        for(int i = 0; i < objects.size(); i ++) {
            PlaneObject o = PolygonOverlayToPlaneObject(objects.get(i));
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

    public int indexOf(PolygonOverlay o) {
        return mPolygonOverlays.indexOf(o);
    }

    public int lastIndexOf(PlaneObject o) {
        return mPlaneObjects.lastIndexOf(o);
    }

    public int lastIndexOf(PolygonOverlay o) {
        return mPolygonOverlays.lastIndexOf(0);
    }

    public PlaneObject getPlaneObject(int index) {
        return mPlaneObjects.get(index);
    }

    public PolygonOverlay getPolygonOverlay(int index) {
        return mPolygonOverlays.get(index);
    }

    public PlaneObject set(int index, PlaneObject element) {
        mPolygonOverlays.set(index, PlaneObjectToPolygonOverlay(element));
        return mPlaneObjects.set(index, element);
    }

    public boolean add(PlaneObject e) {
        mPolygonOverlays.add(PlaneObjectToPolygonOverlay(e));
        return mPlaneObjects.add(e);
    }

    public void add(int index, PlaneObject element) {
        mPolygonOverlays.add(index, PlaneObjectToPolygonOverlay(element));
        mPlaneObjects.add(index, element);
    }

    public PlaneObject remove(int index) {
        mPolygonOverlays.remove(index);
        return mPlaneObjects.remove(index);
    }

    public boolean remove(PlaneObject o) {
        mPolygonOverlays.remove(PlaneObjectToPolygonOverlay(o));
        return mPlaneObjects.remove(o);
    }

    public void clear() {
        mPolygonOverlays.clear();
        mPlaneObjects.clear();
    }

    public boolean addAll(ArrayList<PlaneObject> c) {
        mPolygonOverlays.addAll(PlaneObjectsToPolygonOverlays(c));
        return mPlaneObjects.addAll(c);
    }

    public boolean addAll(int index, ArrayList<PlaneObject> c) {
        mPolygonOverlays.addAll(index, PlaneObjectsToPolygonOverlays(c));
        return mPlaneObjects.addAll(index, c);
    }


    protected boolean removeAll(ArrayList<PlaneObject> c) {
        mPolygonOverlays.removeAll(PlaneObjectsToPolygonOverlays(c));
        return mPlaneObjects.removeAll(c);
    }

    public ArrayList<PlaneObject> getPlaneObjects() {
        return mPlaneObjects;
    }
}
