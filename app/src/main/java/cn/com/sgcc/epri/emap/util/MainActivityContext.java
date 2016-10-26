package cn.com.sgcc.epri.emap.util;

import cn.com.sgcc.epri.emap.MainActivity;

/**
 * Created by GuHeng on 2016/9/27.
 * 传递上下文句柄类
 */
public class MainActivityContext {
    protected MainActivity mMainActivity; // 上下文句柄

    // 构造函数
    protected MainActivityContext(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }
}
