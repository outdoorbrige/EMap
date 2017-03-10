package com.gh.emap.manager;

import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import com.gh.emap.MainActivity;
import com.gh.emap.file.OperateFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
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
        clearOldLogFiles(); // 清理过期日志文件
		
        try {
            mFileWriter = new FileWriter(getLogName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        mWriter = new BufferedWriter(mFileWriter, mBufferSize);
        mLock = new ReentrantLock();

        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        // 设置该LogManager为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
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

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        if (!handleException(exception) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, exception);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log(LogLevel.mError, e.getMessage());
            }

            toastCancel();
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
        if (exception == null) {
            return false;
        }

        // 显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                toastShowLong("很抱歉，程序出现异常，即将退出！");
                Looper.loop();
            }
        }.start();

        // 写异常日志
        String message = exception.getMessage();
        log(LogLevel.mError, message);

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
        String message = mMainActivity.getCurrentDateF2() + " " + getLevel(logLevel) + " " + " " + msg + "\n";
        return message;
    }

    protected String getLogPath() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String logPath = Environment.getExternalStorageDirectory().toString() + File.separator +
                    mMainActivity.getApplationName() + File.separator +
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

    // 清理24小时前的日志
    protected void clearOldLogFiles() {
        final int ONE_DAY_SECONDS = 86400; // 24h * 60m * 60s;

        String strNowDate = mMainActivity.getCurrentDateF1();
        long nNowDate = Long.valueOf(strNowDate);
        long nFlagDate = nNowDate - ONE_DAY_SECONDS;

        // 获取日志文件列表
        ArrayList<File> files = new ArrayList<>();
        OperateFolder.TraverseFindFlies(getLogPath(), getLogSuffix(), files);

        for(int i = 0; i < files.size(); i ++) {
            File file = files.get(i);
            String strLogName = file.getName();
            strLogName = strLogName.substring(0, strLogName.lastIndexOf(getLogSuffix()));
            long nLogName = Long.valueOf(strLogName);

            if(nLogName <= nFlagDate) {
                file.delete();
            }
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
