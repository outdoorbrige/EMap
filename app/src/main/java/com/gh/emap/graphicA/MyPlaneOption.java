package com.gh.emap.graphicA;

import com.tianditu.android.maps.renderoption.PlaneOption;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/4/13.
 */

public class MyPlaneOption implements Serializable {
    private int mFillColor = 0x7F696969;
    private int mStrokeWidth = 5;
    private int mStrokeColor = 0xFF5F9EA0;
    private boolean mDottedLine = false;
    private int[] mIntervals = new int[]{13, 10, 13, 10};

    public MyPlaneOption() {
    }

    public void setFillColor(int color) {
        this.mFillColor = color;
    }

    public int getFillColor() {
        return this.mFillColor;
    }

    public void setStrokeWidth(int width) {
        if(width > 0) {
            this.mStrokeWidth = width;
        }

    }

    public int getStrokeWidth() {
        return this.mStrokeWidth;
    }

    public void setStrokeColor(int color) {
        this.mStrokeColor = color;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public void setDottedLine(boolean b) {
        this.mDottedLine = b;
    }

    public boolean isDottedLine() {
        return this.mDottedLine;
    }

    public void setIntervals(int[] intervals) {
        if(intervals != null) {
            if(intervals.length >= 2 && intervals.length % 2 == 0) {
                this.mIntervals = intervals;
            }
        }
    }

    public int[] getIntervals() {
        return this.mIntervals;
    }

    public void setPlaneOption(PlaneOption planeOption) {
        mFillColor = planeOption.getFillColor();
        mStrokeWidth = planeOption.getStrokeWidth();
        mStrokeColor = planeOption.getStrokeColor();
        mDottedLine = planeOption.isDottedLine();
        mIntervals = planeOption.getIntervals();
    }

    public PlaneOption getPlaneOption() {
        PlaneOption planeOption = new PlaneOption();

        planeOption.setFillColor(mFillColor);
        planeOption.setStrokeWidth(mStrokeWidth);
        planeOption.setStrokeColor(mStrokeColor);
        planeOption.setDottedLine(mDottedLine);
        planeOption.setIntervals(mIntervals);

        return planeOption;
    }
}
