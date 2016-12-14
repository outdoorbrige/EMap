package com.gh.emap.file;

import android.content.Context;

import com.gh.emap.model.MyUserOverlays;
import com.gh.emap.model.MyUserPoint;

/**
 * Created by GuHeng on 2016/12/14.
 * 覆盖物 点 信息文件
 */

public class MyUserPointFile {
    private Context mContext;

    public MyUserPointFile(Context context) {
        this.mContext = context;
    }

    public boolean read(String file, MyUserPoint myUserPoint) {
        TObjectFile<MyUserPoint> tObjectFile = new TObjectFile<MyUserPoint>();
        return tObjectFile.read(file, myUserPoint);
    }

    public boolean write(String file, MyUserPoint myUserPoint) {
        TObjectFile<MyUserPoint> tObjectFile = new TObjectFile<MyUserPoint>();
        return tObjectFile.write(file, myUserPoint);
    }
}
