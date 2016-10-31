package cn.com.sgcc.epri.emap.manager;

import android.widget.Toast;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.MainActivityContext;
import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by GuHeng on 2016/10/8.
 * 日志管理类
 */
public class Log4jManager extends MainActivityContext {
    private Logger mLogger; // 日志对象

    // 构造函数
    public Log4jManager(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init(String fileName) {
        final LogConfigurator logConfigurator = new LogConfigurator();

        //设置文件名
        logConfigurator.setFileName(fileName);

        //设置root日志输出级别 默认为DEBUG
        logConfigurator.setRootLevel(Level.DEBUG);

        // 设置日志输出级别
        logConfigurator.setLevel("org.apache", Level.INFO);

        //设置 输出到日志文件的文字格式 默认 %d %-5p [%c{2}]-[%L] %m%n
        logConfigurator.setFilePattern("[%d{dd年MM月yyyy日HH时mm分ss秒}] [%p] %m%n");

        //设置输出到控制台的文字格式 默认%m%n
        logConfigurator.setLogCatPattern("%m%n");

        //设置总文件大小
        logConfigurator.setMaxFileSize(1024 * 1024 * 5);

        //设置最大产生的文件个数
        logConfigurator.setMaxBackupSize(1);

        //设置所有消息是否被立刻输出 默认为true,false 不输出
        logConfigurator.setImmediateFlush(true);

        //是否本地控制台打印输出 默认为true ，false不输出
        logConfigurator.setUseLogCatAppender(true);

        //设置是否启用文件附加,默认为true。false为覆盖文件
        logConfigurator.setUseFileAppender(true);

        //设置是否重置配置文件，默认为true
        logConfigurator.setResetConfiguration(true);

        //是否显示内部初始化日志,默认为false
        logConfigurator.setInternalDebugging(false);

        logConfigurator.configure();
    }

    // 写日志
    public void log(Class clazz, int level, String message) {
        mLogger = Logger.getLogger(clazz);

        String msg = "";

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        if(stackTraceElement != null) {
            msg = "[" + stackTraceElement.getFileName() + "]" +
                    "[" + stackTraceElement.getMethodName() + "]" +
                    "[" + stackTraceElement.getLineNumber() + "]";
        }
        msg += message;

        switch (level) {
            case Log4jLevel.mVerBose:
                break;
            case Log4jLevel.mDebug:
                mLogger.debug(msg);
                break;
            case Log4jLevel.mInfo:
                mLogger.info(msg);
                break;
            case Log4jLevel.mWarn:
                mLogger.warn(msg);
                break;
            case Log4jLevel.mError:
                mLogger.error(msg);
                break;
            case Log4jLevel.mAssert:
                break;
            default:
                break;
        }
    }

    // 显示消息
    public void show(String message) {
        Toast.makeText(mMainActivity, message, Toast.LENGTH_SHORT).show();
    }
}
