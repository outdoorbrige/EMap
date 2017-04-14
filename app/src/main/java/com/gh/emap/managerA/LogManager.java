package com.gh.emap.managerA;

import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.utility.OperateFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by GuHeng on 2016/11/9.
 * 日志管理类
 *
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 * 需要在Application中注册，为了要在程序启动器就监控整个程序。
 */
public class LogManager implements Thread.UncaughtExceptionHandler {
    private MainActivity mMainActivity;
    private Toast mToast;
    private FileWriter mFileWriter;
    private Writer mWriter;
    private int mBufferSize = 2048;
    private Lock mLock;

    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public LogManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        try {
            mFileWriter = new FileWriter(getLogName());
        } catch (Exception exception) {
            try {
                throw exception;
            }catch (Exception exception1) {
                exception1.printStackTrace();
            }
        } finally {

        }

        mWriter = new BufferedWriter(mFileWriter, mBufferSize);
        mLock = new ReentrantLock();

        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        // 设置该LogManager为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);

        clearOldLogFiles(); // 清理过期日志文件
    }

    public void unInit() {
        try {
            mWriter.flush();
            mWriter.close();

            if(mToast != null) {
                mToast.cancel();
            }
        } catch (Exception exception) {
            try {
                throw exception;
            }catch (Exception exception1) {
                exception1.printStackTrace();
            }
        } finally {
            // 恢复系统默认的UncaughtException处理器
            Thread.setDefaultUncaughtExceptionHandler(mDefaultHandler);
        }
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        if (exception == null) {
            return;
        }

        // 写异常日志
        String message = Log.getStackTraceString(exception);
        log(LogLevel.mError, message);

        if (!handleException(exception) && mDefaultHandler != null) {
            // 如果用户没有处理，则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, exception);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log(LogLevel.mError, e.getMessage());
            }

            unInit();
            mMainActivity.finish();
            System.exit(0);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     * @param exception
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable exception) {
        // 显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                toastShowLong("很抱歉，程序出现异常，即将退出！");
                Looper.loop();
            }
        }.start();

        return true;
    }

    // 显示短消息
    public void toastShowShort(String message) {
        if(mToast == null) {
            mToast = Toast.makeText(mMainActivity, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }

    // 显示长消息
    public void toastShowLong(String message) {
        if(mToast == null) {
            mToast = Toast.makeText(mMainActivity, message, Toast.LENGTH_LONG);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_LONG);
        }

        mToast.show();
    }

    // 关闭消息显示
    public void toastCancel() {
        if(mToast != null) {
            mToast.cancel();
        }
    }

    // 写日志
    public void log(LogLevel logLevel, String msg) {
        String message = formatMessage(logLevel, msg);

        try {
            mLock.lock();
            mWriter.write(message);
            mWriter.flush();
            mLock.unlock();
        } catch (Exception exception) {
            try {
                throw exception;
            }catch (Exception exception1) {
                exception1.printStackTrace();
            }
        } finally {

        }
    }

    protected String getLevel(LogLevel logLevel) {
        if(logLevel == LogLevel.mVerBose) {
            return "VERBOSE";
        } else if(logLevel == LogLevel.mDebug) {
            return "DEBUG";
        } else if(logLevel == LogLevel.mInfo) {
            return "INFO";
        } else if(logLevel == LogLevel.mWarn) {
            return "WARN";
        } else if(logLevel == LogLevel.mError) {
            return "ERROR";
        } else if(logLevel == LogLevel.mAssert) {
            return "ASSERT";
        } else {
            return "UNKNOWN";
        }
    }

    protected  String formatMessage(LogLevel logLevel, String msg) {
        String message = mMainActivity.getCurrentDateF2() + " " + getLevel(logLevel) + " " + " " + msg + "\n";
        return message;
    }

    protected String getLogPath() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String logPath = Environment.getExternalStorageDirectory().toString() + File.separator +
                    mMainActivity.getResources().getString(R.string.home_name) + File.separator +
                    "Logs" + File.separator;

            File file = new File(logPath);
            if(!file.exists()) {
                file.mkdirs();
            }

            return logPath;
        } else {
            return null;
        }
    }

    private String getLogSuffix() {
        return ".txt";
    }

    protected String getLogName() {
        String fileName = null;

        String fatherPath = getLogPath();
        if(fatherPath != null) {
            String strCurrentDate = mMainActivity.getCurrentDateF1();
            if(strCurrentDate != null) {
                fileName = fatherPath + strCurrentDate + getLogSuffix();
                return fileName;
            }
        }

        return null;
    }

    // 清理一周前的日志
    protected void clearOldLogFiles() {
        final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        final long DAY_FACTOR = 1000 * 60 * 60 * 24;
        final long HOUR_FACTOR = 1000 * 60 * 60;
        final long MINUTE_FACTOR = 1000 * 60;

        final long DAY_THRESHOLD = 7;
        final long HOUR_THRESHOLD = 0;
        final long MINUTE_THRESHOLD = 0;

        try {
            String strNowDate = mMainActivity.getCurrentDateF1();
            Date dateNow = dateFormat.parse(strNowDate);

            // 获取日志文件列表
            ArrayList<File> files = new ArrayList<>();
            OperateFolder.TraverseFindFlies(getLogPath(), getLogSuffix(), files);

            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                String strLogName = file.getName();
                String strDateOld = strLogName.substring(0, strLogName.lastIndexOf(getLogSuffix()));
                Date dateOld = dateFormat.parse(strDateOld);

                long dateDifference = dateNow.getTime() - dateOld.getTime();
                long daysDifference = dateDifference / DAY_FACTOR;
                long hoursDifference = (dateDifference - daysDifference * DAY_FACTOR) / HOUR_FACTOR;
                long minutesDifference = (dateDifference - daysDifference * DAY_FACTOR - hoursDifference * HOUR_FACTOR) / MINUTE_FACTOR;

                if (dateDifference > DAY_THRESHOLD && hoursDifference > HOUR_THRESHOLD && minutesDifference > MINUTE_THRESHOLD) {
                    log(LogLevel.mInfo, String.format("删除过期日志文件(%l天%l小时%l分钟前)：", DAY_THRESHOLD, HOUR_THRESHOLD, MINUTE_THRESHOLD) + file.getAbsolutePath());
                    file.delete();
                }
            }
        } catch (Exception exception) {
            log(LogLevel.mError, "删除过期日志文件错误：" + exception.getMessage());
        }
    }

    // 日志等级
    public enum LogLevel {
        mVerBose,
        mDebug,
        mInfo,
        mWarn,
        mError,
        mAssert
    }
}
