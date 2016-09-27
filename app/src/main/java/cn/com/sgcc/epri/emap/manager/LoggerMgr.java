package cn.com.sgcc.epri.emap.manager;

import android.util.Log;

import com.numob.david.util.CrashHandler;
import com.numob.david.util.CrashlyticsTree;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

import cn.com.sgcc.epri.emap.BuildConfig;
import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 * 日志管理类
 */
public class LoggerMgr extends TransmitContext {
    // 构造函数
    public LoggerMgr(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        CrashHandler.getInstance().init(context); // 捕获应用运行时异常

        Logger.initialize(new Settings()
                .isShowMethodLink(true)
                .isShowThreadInfo(false)
                .setMethodOffset(0)
                .setLogPriority(BuildConfig.DEBUG ? Log.VERBOSE : Log.ASSERT));

        if(!BuildConfig.DEBUG) {
            // for release
            Logger.plant(new CrashlyticsTree());
        }
    }
}
