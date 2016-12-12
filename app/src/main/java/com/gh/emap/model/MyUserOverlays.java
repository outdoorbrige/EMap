package com.gh.emap.model;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by GuHeng on 2016/11/18.
 * 用户覆盖物类
 */

public class MyUserOverlays {
    private Context mContext;
    private MyShapEdit mMyShapEdit;

    public MyUserOverlays(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mMyShapEdit = new MyShapEdit();
    }

    // 地物编辑类
    public class MyShapEdit {
        public HashMap<String, MyPoint> mMyPoints;

        public MyShapEdit() {
            this.mMyPoints = new HashMap<>();
        }

        public HashMap<String, MyPoint> getMyPoints() {
            return this.mMyPoints;
        }

        // 点
        public class MyPoint {
            private String mName; // 名称
            private String mType; // 类型
            private int mLatitude; // 纬度
            private int mLongitude; // 经度

            public MyPoint() {

            }

            public MyPoint(String name, String type, int latitude, int longitude) {
                this.mName = name;
                this.mType = type;
                this.mLatitude = latitude;
                this.mLongitude = longitude;
            }

            public String getName() {
                return mName;
            }

            public void setName(String name) {
                this.mName = name;
            }

            public String getType() {
                return mType;
            }

            public void setType(String type) {
                this.mType = type;
            }

            public int getLatitude() {
                return mLatitude;
            }

            public void setLatitude(int latitude) {
                this.mLatitude = latitude;
            }

            public int getLongitude() {
                return mLongitude;
            }

            public void setLongitude(int longitude) {
                this.mLongitude = longitude;
            }

            @Override
            public String toString() {
                return "MyPoint{" +
                        "mName='" + mName + '\'' +
                        ", mType='" + mType + '\'' +
                        ", mLatitude=" + mLatitude +
                        ", mLongitude=" + mLongitude +
                        '}';
            }
        }

        // 线
        public class MyLine {

        }

        // 面
        public class MySurface {

        }
    }

    // 线路编辑
    public class MyLineEdit {

    }
}
