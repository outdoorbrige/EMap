package com.gh.emap.file;

import android.content.Context;
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
 */
public class ShapPointFile {
    private Context mContext;
    private ShapPoint mShapPoint;

    public ShapPointFile(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mShapPoint = new ShapPoint();

        String shapPointFile = getFile()
                ;

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
//                        ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
//                                String.format("[%s][%s][%s]", values[0], values[1], values[2]));
                        this.mShapPoint.put(values[2], values[0] + values[1]);
                    }
                }

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                fileInputStream.close();

//                ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
//                        this.mShapPoint.toString());
            } catch (Exception e) {
                ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                        e.toString());
            }
        } else {
            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                    String.format("数据文件%s不存在", shapPointFile));
        }
    }

    // 获取地物编辑-画点数据
    public ShapPoint getShapPoint() {
        return this.mShapPoint;
    }

    private String getFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString() + File.separator
                    + "EMap" + File.separator
                    + "Data" + File.separator
                    + "ShapPoint.dat";
        } else {
            return null;
        }
    }
}
