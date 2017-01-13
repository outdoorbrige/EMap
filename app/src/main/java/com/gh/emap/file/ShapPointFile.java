package com.gh.emap.file;

import android.os.Environment;

import com.gh.emap.MainActivity;
import com.gh.emap.manager.LogManager;
import com.gh.emap.model.ShapPoint;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by GuHeng on 2016/11/9.
 * 解析数据文件
 */
public class ShapPointFile {
    private MainActivity mMainActivity;
    private ShapPoint mShapPoint;

    public ShapPointFile(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mShapPoint = new ShapPoint();

        loadFile();
    }

    // 获取地物编辑-画点数据
    public ShapPoint getShapPoint() {
        return mShapPoint;
    }

    private String getFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString() + File.separator +
                    mMainActivity.getApplationName() + File.separator +
                    "Data" + File.separator +
                    "ShapPoint.dat";
        } else {
            return null;
        }
    }

    private void loadFile() {
        String shapPointFile = getFile();

        File file = new File(shapPointFile);
        if(file.exists()) { // 判断文件是否存在
            try {
                FileInputStream fileInputStream = new FileInputStream(file); // 获取输入流对象
                InputStream inputStream = new BufferedInputStream(fileInputStream);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    line.replaceAll("\\s*", ""); // 删除所有 空格、回车、换行符、制表符
                    String[] values = line.split(",");

                    if(values != null) {
                        mShapPoint.put(values[2], values[0] + values[1]);
                    }
                }

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                fileInputStream.close();

                mMainActivity.getMainManager().getLogManager().log(getClass(), LogManager.LogLevel.mInfo,
                        mShapPoint.toString());
            } catch (Exception e) {
                mMainActivity.getMainManager().getLogManager().log(getClass(), LogManager.LogLevel.mError,
                        e.getStackTrace().toString());
            }
        } else {
            mMainActivity.getMainManager().getLogManager().log(getClass(), LogManager.LogLevel.mError,
                    String.format("数据文件%s不存在", shapPointFile));
        }
    }
}
