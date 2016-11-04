package cn.com.sgcc.epri.emap.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.base.MainActivityContext;
import cn.com.sgcc.epri.emap.model.ShapPointInfo;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.PhoneResources;

/**
 * Created by GuHeng on 2016/11/3.
 * 解析地物编辑-画点文件
 */
public class ShapPointFile extends MainActivityContext {
    private ShapPointInfo mShapPointInfo; // 地物编辑-画点数据

    // 构造函数
    public ShapPointFile(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mShapPointInfo = new ShapPointInfo();

        String shapPointFile = PhoneResources.getShapPointFile(mMainActivity);

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
                        //mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mInfo, String.format("[%s][%s][%s]", values[0], values[1], values[2]));
                        mShapPointInfo.add(values[2], values[1], values[0]);
                    }
                }

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                fileInputStream.close();

                //mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mInfo, mShapPointInfo.toString());
            } catch (Exception e) {
                mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError, e.toString());
            }
        } else {
            mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError, String.format("数据文件%s不存在", shapPointFile));
        }
    }

    // 获取地物编辑-画点数据
    public ShapPointInfo getShapPointInfo() {
        return mShapPointInfo;
    }
}
