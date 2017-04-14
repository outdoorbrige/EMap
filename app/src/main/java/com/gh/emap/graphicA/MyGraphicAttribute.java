package com.gh.emap.graphicA;

import java.io.Serializable;

/**
 * Created by GuHeng on 2017/4/14.
 */

public class MyGraphicAttribute implements Serializable {
    private String mName; // 名称
    private String mType; // 类型

    public MyGraphicAttribute() {

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
