package com.gh.emap.overlay;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.renderoption.LineOption;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/12.
 * 地物绘制-画线-覆盖物集合
 */

public class LineOverlayItems {
    private MainActivity mMainActivity;
    private ArrayList<LineObject> mLineObjects = new ArrayList<>();
    private ArrayList<LineOverlay> mLineOverlays = new ArrayList<>();

    public LineOverlayItems(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {

    }

    public LineOverlay LineObjectToLineOverlay(LineObject o) {
       if(o == null) {
            return null;
        }

        LineOption lineOption = new LineOption();
        lineOption.setStrokeWidth(o.getStrokeWidth());
        lineOption.setStrokeColor(o.getStrokeColor());
        lineOption.setDottedLine(o.isDottedLine());
        lineOption.setIntervals(o.getIntervals());

        LineOverlay lineOverlay = new LineOverlay(mMainActivity);
        lineOverlay.setOption(lineOption);
        lineOverlay.setPoints(o.getGeoPoints());

        return lineOverlay;
    }

    public LineObject LineOverlayToLineObject(LineOverlay o) {
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

    public ArrayList<LineOverlay> LineObjectsToLineOverlays(ArrayList<LineObject> objects) {
        if(objects == null) {
            return null;
        }

        ArrayList<LineOverlay> os = new ArrayList<>();
        for(int i = 0; i < objects.size(); i ++) {
            LineOverlay o = LineObjectToLineOverlay(objects.get(i));
            os.add(o);
        }

        return os;
    }

    public ArrayList<LineObject> LineOverlaysToLineObjects(ArrayList<LineOverlay> objects) {
        if(objects == null) {
            return null;
        }

        ArrayList<LineObject> os = new ArrayList<>();
        for(int i = 0; i < objects.size(); i ++) {
            LineObject o = LineOverlayToLineObject(objects.get(i));
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

    public int indexOf(LineOverlay o) {
        return mLineOverlays.indexOf(o);
    }

    public int lastIndexOf(LineObject o) {
        return mLineObjects.lastIndexOf(o);
    }

    public int lastIndexOf(LineOverlay o) {
        return mLineOverlays.lastIndexOf(0);
    }

    public LineObject getLineObject(int index) {
        return mLineObjects.get(index);
    }

    public LineOverlay getLineOverlay(int index) {
        return mLineOverlays.get(index);
    }

    public LineObject set(int index, LineObject element) {
        mLineOverlays.set(index, LineObjectToLineOverlay(element));
        return mLineObjects.set(index, element);
    }

    public boolean add(LineObject e) {
        mLineOverlays.add(LineObjectToLineOverlay(e));
        return mLineObjects.add(e);
    }

    public void add(int index, LineObject element) {
        mLineOverlays.add(index, LineObjectToLineOverlay(element));
        mLineObjects.add(index, element);
    }

    public LineObject remove(int index) {
        mLineOverlays.remove(index);
        return mLineObjects.remove(index);
    }

    public boolean remove(LineObject o) {
        mLineOverlays.remove(LineObjectToLineOverlay(o));
        return mLineObjects.remove(o);
    }

    public void clear() {
        mLineOverlays.clear();
        mLineObjects.clear();
    }

    public boolean addAll(ArrayList<LineObject> c) {
        mLineOverlays.addAll(LineObjectsToLineOverlays(c));
        return mLineObjects.addAll(c);
    }

    public boolean addAll(int index, ArrayList<LineObject> c) {
        mLineOverlays.addAll(index, LineObjectsToLineOverlays(c));
        return mLineObjects.addAll(index, c);
    }


    protected boolean removeAll(ArrayList<LineObject> c) {
        mLineOverlays.removeAll(LineObjectsToLineOverlays(c));
        return mLineObjects.removeAll(c);
    }

    public ArrayList<LineObject> getLineObjects() {
        return mLineObjects;
    }
}
