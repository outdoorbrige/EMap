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
    public static boolean isSDCard() {
        return (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED));
    }

    // 获取手机SD卡根路径
    public static String getSDCardRoot() {
        String path = null;
        if(isSDCard()) {
            path = Environment.getExternalStorageDirectory().toString() + File.separator;
        } else {
            path = null;
        }

        return path;
    }

    // 获取APP名称
    public static String getAppName(MainActivity mainActivity) {
        return mainActivity.getResources().getString(R.string.app_name);
    }

    // 创建路径
    public static void mkdir(String path) {
        // 判断路径是否存在，不存在，就创建
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdir();
        }
    }

    // 获取项目工作根路径
    public static String getWorkPath(MainActivity mainActivity) {
        String path = null;

        if(isSDCard()) {
            path = getSDCardRoot() + getAppName(mainActivity) + File.separator;
            mkdir(path);
        } else {
            path = null;
        }

        return path;
    }

    // 获取当前时间字符串yyyy-MM-dd_HH:mm:ss
    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        return simpleDateFormat.format(date);
    }

    // 获取配置文件路径
    public static String getConfigFile(MainActivity mainActivity) {
        String file;

        if(isSDCard()) {
            file = getWorkPath(mainActivity) + getAppName(mainActivity) + ".config";
        } else {
            file = null;
        }

        return file;
    }

    // 获取日志文件名
    public static String getLogFile(MainActivity mainActivity) {
        String file;

        if(isSDCard()) {
            file = getWorkPath(mainActivity) + "log" + File.separator + getCurrentDate() + ".log";
        } else {
            file = null;
        }

        return file;
    }

    // 获取地图缓冲区路径
    public static String getMapCachePath(MainActivity mainActivity) {
        String path;

        if(isSDCard()) {
            path = getWorkPath(mainActivity) + "files" + File.separator + "map" + File.separator;
            mkdir(path);
        } else {
            path = null;
        }

        return path;
    }

    // 获取离线地图路径
    public static String getOfflineMapPath(MainActivity mainActivity) {
        String path;

        if(isSDCard()) {
            path = getWorkPath(mainActivity) + "offlinemap" + File.separator;
            mkdir(path);
        } else {
            path = null;
        }

        return path;
    }
}
