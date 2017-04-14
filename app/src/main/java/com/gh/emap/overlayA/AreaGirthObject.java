package com.gh.emap.overlayA;

import com.gh.emap.graphicA.MyCircleOption;
import com.gh.emap.graphicA.MyFontOption;
import com.gh.emap.graphicA.MyPlaneOption;
import com.tianditu.android.maps.renderoption.CircleOption;
import com.tianditu.android.maps.renderoption.FontOption;
import com.tianditu.android.maps.renderoption.PlaneOption;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/4/13.
 */

public class AreaGirthObject implements Serializable {
    private MyCircleOption mMyCircleOption = new MyCircleOption();
    private MyPlaneOption mMyPlaneOption = new MyPlaneOption();
    private MyFontOption mMyFontOption = new MyFontOption();

    public AreaGirthObject() {

    }

    public void setMyCircleOption(MyCircleOption myCircleOption) {
        mMyCircleOption = myCircleOption;
    }

    public MyCircleOption getMyCircleOption() {
        return mMyCircleOption;
    }

    public void setMyPlaneOption(MyPlaneOption myPlaneOption) {
        mMyPlaneOption = myPlaneOption;
    }

    public MyPlaneOption getMyPlaneOption() {
        return mMyPlaneOption;
    }

    public void setMyFontOption(MyFontOption myFontOption) {
        mMyFontOption = myFontOption;
    }

    public MyFontOption getMyFontOption() {
        return mMyFontOption;
    }
}
