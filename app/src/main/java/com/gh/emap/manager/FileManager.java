package com.gh.emap.manager;

import com.gh.emap.MainActivity;
import com.gh.emap.file.EMapFile;
import com.gh.emap.file.ShapLineFile;
import com.gh.emap.file.ShapPointFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by GuHeng on 2016/11/9.
 * 文件管理类
 */
public class FileManager {
    private MainActivity mMainActivity;
    private EMapFile mEMapFile;
    private ShapPointFile mShapPointFile;
    private ShapLineFile mShapLineFile;

    public FileManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mEMapFile = new EMapFile(mMainActivity);
        mEMapFile.init();

        mShapPointFile = new ShapPointFile(mMainActivity);
        mShapPointFile.init();

        mShapLineFile = new ShapLineFile(mMainActivity);
        mShapLineFile.init();
    }

    public EMapFile getEMapFile() {
        return mEMapFile;
    }

    public ShapPointFile getShapPointFile() {
        return mShapPointFile;
    }

    public ShapLineFile getShapLineFile() {
        return mShapLineFile;
    }

    // 写对象到文件中
    public void writeObjectToFile(String fileName, Object object) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            byteArrayOutputStream.close();

            byte[] bytes = byteArrayOutputStream.toByteArray();

            File file = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            mMainActivity.getMainManager().getLogManager().log(getClass(), LogManager.LogLevel.mError,
                    e.getStackTrace().toString());
        }
    }

    // 读文件中的对象
    public Object readObjectFromFile(String fileName) {
        Object object = null;

        try {
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            mMainActivity.getMainManager().getLogManager().log(getClass(), LogManager.LogLevel.mError,
                    e.getStackTrace().toString());
        }

        return object;
    }
}
