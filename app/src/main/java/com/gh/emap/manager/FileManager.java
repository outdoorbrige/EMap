package com.gh.emap.manager;

import android.content.Context;

import com.gh.emap.file.EMapFile;
import com.gh.emap.file.ShapPointFile;

/**
 * Created by GuHeng on 2016/11/9.
 * 文件管理类
 */
public class FileManager {
    private Context mContext;
    private EMapFile mEMapFile;
    private ShapPointFile mShapPointFile;

    public FileManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mEMapFile = new EMapFile(this.mContext);
        this.mEMapFile.init();

        this.mShapPointFile = new ShapPointFile(this.mContext);
        this.mShapPointFile.init();
    }

    public EMapFile getEMapFile() {
        return this.mEMapFile;
    }

    public ShapPointFile getShapPointFile() {
        return this.mShapPointFile;
    }
}
