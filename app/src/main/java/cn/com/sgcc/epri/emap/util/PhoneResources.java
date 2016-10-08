package cn.com.sgcc.epri.emap.util;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;

/**
 * Created by GuHeng on 2016/9/30.
 * 判断手机资源是否可用(SD卡...等资源)
 */
public class PhoneResources {

    // 判断手机SD卡资源是否可用
    public static boolean sdCardExist() {
        return (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED));
    }

    // 获取手机SD卡根路径
    public static String getSDPath() {
        String path = null;
        if(sdCardExist()) {
            path = Environment.getExternalStorageDirectory().toString() + File.separator;
        } else {
            path = null;
        }

        return path;
    }

    // 获取APP名称
    public static String getAppName(MainActivity context) {
        return context.getResources().getString(R.string.app_name);
    }

    // 创建路径
    public static void mkDir(String path) {
        // 判断路径是否存在，不存在，就创建
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdir();
        }
    }

    // 获取项目工作根路径
    public static String getWorkPath(MainActivity context) {
        String path = null;

        if(sdCardExist()) {
            path = getSDPath() + File.separator + getAppName(context) + File.separator;
            mkDir(path);
        } else {
            path = null;
        }

        return path;
    }

    // 获取当前时间字符串yyyy-MM-dd_HH:mm:ss
    public static String getNowTimeString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date nowTime = new Date(System.currentTimeMillis());

        return format.format(nowTime);
    }

    // 获取日志文件名
    public static String getLogFile(MainActivity context) {
        String file;

        if(sdCardExist()) {
            file = getWorkPath(context) + "log" + File.separator + getNowTimeString() + ".log";
        } else {
            file = null;
        }

        return file;
    }

    // 获取地图缓冲区路径
    public static String getMapCachePath(MainActivity context) {
        String path;

        if(sdCardExist()) {
            path = getWorkPath(context) + "files" + File.separator + "map" + File.separator;
            mkDir(path);
        } else {
            path = null;
        }

        return path;
    }

    // 获取离线地图路径
    public static String getOfflineMapPath(MainActivity context) {
        String path;

        if(sdCardExist()) {
            path = getWorkPath(context) + "offlinemap" + File.separator;
            mkDir(path);
        } else {
            path = null;
        }

        return path;
    }
}
