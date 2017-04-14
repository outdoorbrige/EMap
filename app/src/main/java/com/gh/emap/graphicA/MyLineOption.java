package com.gh.emap.graphicA;

import com.tianditu.android.maps.renderoption.LineOption;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/4/13.
 */

public class MyLineOption implements Serializable {
    private int mStrokeWidth = 5;
    private int mStrokeColor = 0xFF5F9EA0;
    private boolean mDottedLine = false;
    private int[] mIntervals = new int[]{13, 10, 13, 10};

    public MyLineOption() {
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

    public void setLineOption(LineOption lineOption) {
        mStrokeWidth = lineOption.getStrokeWidth();
        mStrokeColor = lineOption.getStrokeColor();
        mDottedLine = lineOption.isDottedLine();
        mIntervals = lineOption.getIntervals();
    }

    public LineOption getLineOption() {
        LineOption lineOption = new LineOption();

        lineOption.setStrokeWidth(mStrokeWidth);
        lineOption.setStrokeColor(mStrokeColor);
        lineOption.setDottedLine(mDottedLine);
        lineOption.setIntervals(mIntervals);

        return lineOption;
    }
}
