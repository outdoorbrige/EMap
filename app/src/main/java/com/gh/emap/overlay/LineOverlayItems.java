package com.gh.emap.overlay;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.overlay.PolylineOverlay;
import com.tianditu.android.maps.renderoption.LineOption;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/12.
 * 地物绘制-画线-覆盖物集合
 */

public class LineOverlayItems {
    private MainActivity mMainActivity;
    private ArrayList<LineObject> mLineObjects = new ArrayList<>();
    private ArrayList<PolylineOverlay> mPolylineOverlays = new ArrayList<>();

    public LineOverlayItems(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {

    }

    public PolylineOverlay LineObjectToPolylineOverlay(LineObject o) {
       if(o == null) {
            return null;
        }

        LineOption lineOption = new LineOption();
        lineOption.setStrokeWidth(o.getStrokeWidth());
        lineOption.setStrokeColor(o.getStrokeColor());
        lineOption.setDottedLine(o.isDottedLine());
        lineOption.setIntervals(o.getIntervals());

        PolylineOverlay polylineOverlay = new PolylineOverlay();
        polylineOverlay.setOption(lineOption);
        polylineOverlay.setPoints(o.getGeoPoints());

        return polylineOverlay;
    }

    public LineObject PolylineOverlayToLineObject(PolylineOverlay o) {
        if(o == null) {
            return null;
        }

        LineObject lineObject = new LineObject();
        lineObject.setStrokeWidth(o.getOption().getStrokeWidth());
        lineObject.setStrokeColor(o.getOption().getStrokeColor());
        lineObject.setDottedLine(o.getOption().isDottedLine());
        lineObject.setIntervals(o.getOption().getIntervals());
        lineObject.addGeoPoints(o.getPoints());

        return lineObject;
    }

    public ArrayList<PolylineOverlay> LineObjectsToPolylineOverlays(ArrayList<LineObject> objects) {
        if(objects == null) {
            return null;
        }

        ArrayList<PolylineOverlay> os = new ArrayList<>();
        for(int i = 0; i < objects.size(); i ++) {
            PolylineOverlay o = LineObjectToPolylineOverlay(objects.get(i));
            os.add(o);
        }

        return os;
    }

    public ArrayList<LineObject> PolylineOverlaysToLineObjects(ArrayList<PolylineOverlay> objects) {
        if(objects == null) {
            return null;
        }

        ArrayList<LineObject> os = new ArrayList<>();
        for(int i = 0; i < objects.size(); i ++) {
            LineObject o = PolylineOverlayToLineObject(objects.get(i));
            os.add(o);
        }

        return os;
    }

    public int size() {
        return mLineObjects.size();
    }

    public boolean isEmpty() {
        return mLineObjects.isEmpty();
    }

    public int indexOf(LineObject o) {
        return mLineObjects.indexOf(o);
    }

    public int indexOf(PolylineOverlay o) {
        return mPolylineOverlays.indexOf(o);
    }

    public int lastIndexOf(LineObject o) {
        return mLineObjects.lastIndexOf(o);
    }

    public int lastIndexOf(PolylineOverlay o) {
        return mPolylineOverlays.lastIndexOf(0);
    }

    public LineObject getLineObject(int index) {
        return mLineObjects.get(index);
    }

    public PolylineOverlay getPolylineOverlay(int index) {
        return mPolylineOverlays.get(index);
    }

    public LineObject set(int index, LineObject element) {
        mPolylineOverlays.set(index, LineObjectToPolylineOverlay(element));
        return mLineObjects.set(index, element);
    }

    public boolean add(LineObject e) {
        mPolylineOverlays.add(LineObjectToPolylineOverlay(e));
        return mLineObjects.add(e);
    }

    public void add(int index, LineObject element) {
        mPolylineOverlays.add(index, LineObjectToPolylineOverlay(element));
        mLineObjects.add(index, element);
    }

    public LineObject remove(int index) {
        mPolylineOverlays.remove(index);
        return mLineObjects.remove(index);
    }

    public boolean remove(LineObject o) {
        mPolylineOverlays.remove(LineObjectToPolylineOverlay(o));
        return mLineObjects.remove(o);
    }

    public void clear() {
        mPolylineOverlays.clear();
        mLineObjects.clear();
    }

    public boolean addAll(ArrayList<LineObject> c) {
        mPolylineOverlays.addAll(LineObjectsToPolylineOverlays(c));
        return mLineObjects.addAll(c);
    }

    public boolean addAll(int index, ArrayList<LineObject> c) {
        mPolylineOverlays.addAll(index, LineObjectsToPolylineOverlays(c));
        return mLineObjects.addAll(index, c);
    }


    protected boolean removeAll(ArrayList<LineObject> c) {
        mPolylineOverlays.removeAll(LineObjectsToPolylineOverlays(c));
        return mLineObjects.removeAll(c);
    }

    public ArrayList<LineObject> getLineObjects() {
        return mLineObjects;
    }
}
