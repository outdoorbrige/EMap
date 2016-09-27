package com.numob.david.util;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

/**
 * Created by GuHeng on 2016/9/26.
 */

public class CrashlyticsTree extends Timber.Tree {

    @Override
    protected void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            // 只分发异常
            return;
        }

        if (t == null && message != null) {
            Crashlytics.logException(new Exception(message));
        } else if (t != null && message != null) {
            Crashlytics.logException(new Exception(message, t));
        } else if (t != null) {
            Crashlytics.logException(t);
        }
    }
}

