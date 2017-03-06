package com.gh.emap.manager;

import android.os.Environment;
import android.widget.Toast;

import com.gh.emap.MainActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by GuHeng on 2016/11/9.
 * 日志管理类
 */
public class LogManager {
    private MainActivity mMainActivity;
    private FileWriter mFileWriter;
    private Writer mWriter;
    private int mBufferSize = 2048;
    private Lock mLock;

    public LogManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        try {
            mFileWriter = new FileWriter(getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        mWriter = new BufferedWriter(mFileWriter, mBufferSize);
        mLock = new ReentrantLock();
    }

    public void unInit() {
        try {
            mWriter.flush();
            mWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    // 显示消息
    public void show(String message) {
        Toast.makeText(mMainActivity, message, Toast.LENGTH_SHORT).show();
    }

    // 写日志
    public void log(LogLevel logLevel, String msg) {
        String message = formatMessage(logLevel, msg);

        try {
            mLock.lock();
            mWriter.write(message);
            mLock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
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
        String stackTrace = "";
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        if(stackTraceElement != null) {
            stackTrace = "[" + stackTraceElement.getFileName() + "]" + "[" + stackTraceElement.getMethodName() + "]" + "[" + stackTraceElement.getLineNumber() + "]";
        }

        String message = mMainActivity.getCurrentDate() + " " + getLevel(logLevel) + " " + stackTrace + " " + msg + "\n";

        return message;
    }

    protected String getFileName() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String fileLog = Environment.getExternalStorageDirectory().toString() + File.separator +
                    mMainActivity.getApplationName() + File.separator +
                    mMainActivity.getApplationName() + ".txt";

            File file = new File(fileLog);
            if(file.exists()) {
                file.delete();
            }

            return fileLog;
        } else {
            return null;
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
