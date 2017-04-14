package com.gh.emap.graphicA;

import com.tianditu.android.maps.renderoption.CircleOption;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/4/13.
 */

public class MyCircleOption implements Serializable {
    private int mCircleRadius = 12; // pixels

    private int mFillColor = 0xFF000000;
    private int mStrokeWidth =  0;
    private int mStrokeColor = -1442840576;
    private boolean mDottedLine = false;
    private int[] mIntervals = new int[]{13, 10, 13, 10};

    public MyCircleOption() {

    }

    public int getCircleRadius() {
        return mCircleRadius;
    }

    public void setCircleRadius(int circleRadius) {
        this.mCircleRadius = circleRadius;
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

    public CircleOption getCircleOption() {
        CircleOption circleOption = new CircleOption();

        circleOption.setStrokeWidth(mStrokeWidth);
        circleOption.setStrokeColor(mStrokeColor);
        circleOption.setDottedLine(mDottedLine);
        circleOption.setIntervals(mIntervals);
        circleOption.setFillColor(mFillColor);

        return circleOption;
    }

    public void setCircleOption(CircleOption circleOption) {
        mStrokeWidth = circleOption.getStrokeWidth();
        mStrokeColor = circleOption.getStrokeColor();
        mDottedLine = circleOption.isDottedLine();
        mIntervals = circleOption.getIntervals();
        mFillColor = circleOption.getFillColor();
    }
}
