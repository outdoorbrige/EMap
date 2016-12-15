package com.gh.emap.manager;

import android.content.Context;

import com.gh.emap.MainActivity;
import com.gh.emap.file.EMapFile;
import com.gh.emap.file.ShapPointFile;
import com.gh.emap.file.TObjectFile;
import com.gh.emap.model.MyUserPoint;

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
            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
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
            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                    e.getStackTrace().toString());
        }

        return object;
    }
}
