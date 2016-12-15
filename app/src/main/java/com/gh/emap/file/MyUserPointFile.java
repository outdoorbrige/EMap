package com.gh.emap.file;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gh.emap.model.MyUserOverlays;
import com.gh.emap.model.MyUserPoint;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2016/12/14.
 * 覆盖物 点 信息文件
 */

public class MyUserPointFile {
    private static TObjectFile<MyUserPoint> mTObjectFile = new TObjectFile<MyUserPoint>();

    public static MyUserPoint read(String file) {
        return mTObjectFile.read(file);
    }

    public static ArrayList<MyUserPoint> read(ArrayList<File> files) {
        if(files == null || files.size() == 0) {
            return null;
        }

        ArrayList<MyUserPoint> myUserPoints = new ArrayList<MyUserPoint>();

        for(int i = 0; i < files.size(); i ++) {
            MyUserPoint myUserPoint = read(files.get(i).getPath());
            if(myUserPoint != null) {
                myUserPoints.add(myUserPoint);
            }
        }

        return myUserPoints;
    }

    public static boolean write(String file, MyUserPoint myUserPoint) {
        return mTObjectFile.write(file, myUserPoint);
    }
}
