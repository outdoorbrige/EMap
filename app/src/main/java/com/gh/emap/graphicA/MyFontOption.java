package com.gh.emap.graphicA;

import com.tianditu.android.maps.renderoption.FontOption;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/4/13.
 */

public class MyFontOption implements Serializable {
    private int mFontSize = 16;
    private int mFillColor = 0;
    private int mFontColor = -16777216;
    private int mStrokeColor = 0;
    private int mStrokeWidth = 0;
    private int mAlignX = 0;
    private int mAlignY = 0;
    private int mOffsetX = 0;
    private int mOffsetY = -30;
    private float mRotate = 0.0F;

    public MyFontOption() {

    }

    public void setFontSize(int size) {
        this.mFontSize = size;
    }

    public int getFontSize() {
        return this.mFontSize;
    }

    public void setFillColor(int color) {
        this.mFillColor = color;
    }

    public int getFillColor() {
        return this.mFillColor;
    }

    public void setFontColor(int color) {
        this.mFontColor = color;
    }

    public int getFontColor() {
        return this.mFontColor;
    }

    public void setStrokeColor(int color) {
        this.mStrokeColor = color;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public void setStrokeWidth(int width) {
        this.mStrokeWidth = width;
    }

    public int getStrokeWidth() {
        return this.mStrokeWidth;
    }

    public void setAlign(int alignX, int alignY) {
        this.mAlignX = alignX;
        this.mAlignY = alignY;
    }

    public int getAlignX() {
        return this.mAlignX;
    }

    public int getAlignY() {
        return this.mAlignY;
    }

    public void setOffset(int x, int y) {
        this.mOffsetX = x;
        this.mOffsetY = y;
    }

    public int getOffsetX() {
        return this.mOffsetX;
    }

    public int getOffsetY() {
        return this.mOffsetY;
    }

    public void setTextRotate(float rotate) {
        this.mRotate = rotate;
    }

    public float getTextRotate() {
        return this.mRotate;
    }

    public void setFontOption(FontOption fontOption) {
        this.mFontSize = fontOption.getFontSize();
        this.mFillColor = fontOption.getFillColor();
        this.mFontColor = fontOption.getFontColor();
        this.mStrokeColor = fontOption.getStrokeColor();
        this.mStrokeWidth = fontOption.getStrokeWidth();
        this.mAlignX = fontOption.getAlignX();
        this.mAlignY = fontOption.getAlignY();
        this.mOffsetX = fontOption.getOffsetX();
        this.mOffsetY = fontOption.getOffsetY();
        this.mRotate = fontOption.getTextRotate();
    }

    public FontOption getFontOption() {
        FontOption fontOption = new FontOption();

        fontOption.setFontSize(mFontSize);
        fontOption.setFillColor(mFillColor);
        fontOption.setFontColor(mFontColor);
        fontOption.setStrokeColor(mStrokeColor);
        fontOption.setStrokeWidth(mStrokeWidth);
        fontOption.setAlign(mAlignX, mAlignY);
        fontOption.setOffset(mOffsetX, mOffsetY);
        fontOption.setTextRotate(mRotate);

        return fontOption;
    }
}
