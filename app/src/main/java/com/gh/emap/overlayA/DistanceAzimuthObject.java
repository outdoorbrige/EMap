package com.gh.emap.overlayA;

import com.gh.emap.graphicA.MyCircleOption;
import com.gh.emap.graphicA.MyFontOption;
import com.gh.emap.graphicA.MyLineOption;
import com.tianditu.android.maps.renderoption.CircleOption;
import com.tianditu.android.maps.renderoption.FontOption;
import com.tianditu.android.maps.renderoption.LineOption;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/4/13.
 */

public class DistanceAzimuthObject implements Serializable {
    private MyCircleOption mMyCircleOption = new MyCircleOption();
    private MyLineOption mMyLineOption = new MyLineOption();
    private MyFontOption mMyFontOption = new MyFontOption();

    public DistanceAzimuthObject() {

    }

    public void setMyCircleOption(MyCircleOption myCircleOption) {
        mMyCircleOption = myCircleOption;
    }

    public MyCircleOption getMyCircleOption() {
        return mMyCircleOption;
    }

    public void setMyLineOption(MyLineOption myLineOption) {
        mMyLineOption = myLineOption;
    }

    public MyLineOption getMyLineOption() {
        return mMyLineOption;
    }

    public void setMyFontOption(MyFontOption myFontOption) {
        mMyFontOption = myFontOption;
    }

    public MyFontOption getMyFontOption() {
        return mMyFontOption;
    }
}
