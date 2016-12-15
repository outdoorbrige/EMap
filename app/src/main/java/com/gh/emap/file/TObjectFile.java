package com.gh.emap.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by GuHeng on 2016/12/14.
 * 模版类，读写 对象 文件
 */

public class TObjectFile<T> {

    // 从文件中读取对象
    @SuppressWarnings("unchecked")
    public T read(String file) {
        File f = new File(file);
        if(f.exists()) { // 判断文件是否存在
            try {
                FileInputStream fileInputStream = new FileInputStream(f);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                T object = (T)objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();

                return object;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    // 从文件中读取对象集合
    @SuppressWarnings("unchecked")
    public List<T> readList(String file) {
        File f = new File(file);
        if(f.exists()) { // 判断文件是否存在
            try {
                FileInputStream fileInputStream = new FileInputStream(f);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                List<T> objects = (List<T>)objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();

                return objects;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    // 写对象到文件
    public boolean write(String file, T object) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(object);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // 写对象集合到文件
    public boolean writeList(String file, List<T> objects) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(objects);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
