package cn.com.sgcc.epri.emap.util;

import android.content.Context;

/**
 * Created by GuHeng on 2016/9/28.
 * 设备无关像素(dp/dip)与像素(px)相互转换
 */
public class DisplayMetrics {

    // 获取屏幕的宽度(px)
    public static int getWidthPx(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    // 获取屏幕的高度(px)
    public static int getHeightPx(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    // 设备无关像素(dp/dip)转换为像素(px)
    public static int Dp2Px(Context context, float dpValue) {
        return (int)(Math.round(dpValue * context.getResources().getDisplayMetrics().density));
    }

    // 像素(px)转换为设备无关像素(dp/dip)
    public static int Px2Dp(Context context, float pxValue) {
        return (int)(Math.round(pxValue / context.getResources().getDisplayMetrics().density));
    }

    // 获取屏幕的宽度(dp)
    public static int getWidthDp(Context context) {
        return Px2Dp(context, getWidthPx(context));
    }

    // 获取屏幕的高度(dp)
    public static int getHeightDp(Context context) {
        return Px2Dp(context, getHeightPx(context));
    }
}
