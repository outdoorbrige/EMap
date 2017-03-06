package com.gh.emap.manager;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.renderoption.CircleOption;
import com.tianditu.android.maps.renderoption.FontOption;
import com.tianditu.android.maps.renderoption.LineOption;
import com.tianditu.android.maps.renderoption.PlaneOption;

/**
 * Created by GuHeng on 2017/2/27.
 */

public class RenderOptionManager {
    private MainActivity mMainActivity;

    private int mColor1ARGB = 0xFF000000;
    private int mColor2ARGB = 0x7F696969;
    private int mColor3ARGB = 0xFF5F9EA0;
    private int mCircleRadius = 12; // pixels
    private int mStrokeWidth = 5; // 宽度
    private boolean mIsDottedLine = false;
    private int mFontOffsetX = 0;
    private int mFontOffsetY = -30;

    // 注意：此处添加的填充字符，是为了解决drawText文本显示不全问题；
    // 并无实际意义，待天地图新版SDK修复此问题后，可删除此填充字符串
    private String mFillChars = "              #";

    private CircleOption mCircleOption;
    private LineOption mLineOption;
    public PlaneOption mPlaneOption;
    private FontOption mFontOption;

    public RenderOptionManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mCircleOption = new CircleOption();
        mCircleOption.setFillColor(mColor1ARGB);

        mLineOption = new LineOption();
        mLineOption.setStrokeWidth(mStrokeWidth);
        mLineOption.setStrokeColor(mColor3ARGB);
        mLineOption.setDottedLine(mIsDottedLine);

        mPlaneOption = new PlaneOption();
        mPlaneOption.setStrokeWidth(mStrokeWidth);
        mPlaneOption.setStrokeColor(mColor3ARGB);
        mPlaneOption.setDottedLine(mIsDottedLine);
        mPlaneOption.setFillColor(mColor2ARGB);

        mFontOption = new FontOption();
        mFontOption.setOffset(mFontOffsetX, mFontOffsetY);
    }

    public void unInit() {

    }

    public CircleOption getCircleOption() {
        return mCircleOption;
    }

    public int getCircleRadius() {
        return mCircleRadius;
    }

    public LineOption getLineOption() {
        return mLineOption;
    }

    public PlaneOption getPlaneOption() {
        return mPlaneOption;
    }

    public FontOption getFontOption() {
        return mFontOption;
    }

    public String getFillChars() {
        return mFillChars;
    }
}
