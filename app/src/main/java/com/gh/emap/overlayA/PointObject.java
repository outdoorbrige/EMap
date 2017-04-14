package com.gh.emap.overlayA;

import com.gh.emap.graphicA.MyCircleOption;
import com.gh.emap.graphicA.MyCoordinate;
import com.gh.emap.graphicA.MyGraphicAttribute;

import java.io.Serializable;

/**
 * Created by GuHeng on 2016/12/22.
 * 地物绘制-点-读写文件对象
 */

public class PointObject implements Serializable {
    private MyGraphicAttribute mMyGraphicAttribute = new MyGraphicAttribute();
    private MyCoordinate mMyCoordinate = new MyCoordinate();
    private MyCircleOption mMyCircleOption = new MyCircleOption();

    public PointObject() {
    }

    public MyGraphicAttribute getMyGraphicAttribute() {
        return mMyGraphicAttribute;
    }

    public void setMyGraphicAttribute(MyGraphicAttribute myGraphicAttribute) {
        mMyGraphicAttribute = myGraphicAttribute;
    }

    public MyCoordinate getMyCoordinate() {
        return mMyCoordinate;
    }

    public void setMyCoordinate(MyCoordinate myCoordinate) {
        mMyCoordinate = myCoordinate;
    }

    public MyCircleOption getMyCircleOption() {
        return mMyCircleOption;
    }

    public void setMyCircleOption(MyCircleOption myCircleOption) {
        mMyCircleOption = myCircleOption;
    }
}
