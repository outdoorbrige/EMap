package com.gh.emap.manager;

import android.os.Environment;

import com.gh.emap.MainActivity;
import com.gh.emap.model.UserInfo;

import java.io.File;

/**
 * Created by GuHeng on 2016/10/25.
 * 用户信息管理类
 */
public class UserManager {
    private MainActivity mMainActivity;
    private UserInfo mUserInfo; // 用户信息

    // 构造函数
    public UserManager (MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    // 初始化
    public void init() {

    }

    public void unInit() {

    }

    // 设置用户信息
    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }

    // 获取用户信息
    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    // 是否在线
    public boolean isOnLine() {
        return mUserInfo == null ? false : mUserInfo.isSuccess();
    }

    // 获取用户目录
    public String getHomePath() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }

        if(mUserInfo == null || !mUserInfo.isSuccess()) {
            return null;
        }

        String homePath = Environment.getExternalStorageDirectory().toString() + File.separator +
                mMainActivity.getApplationName() + File.separator +
                "Users" + File.separator +
                mUserInfo.getUserName() + File.separator;

        // 如果目录不存在，就创建
        File homeDir = new File(homePath);
        if(!homeDir.exists()) {
            homeDir.mkdirs();
        }

        return homePath;
    }

    public class UserType { // 用户类型
        public static final String mAdminType = "管理员";
        public static final String mNormalType = "普通用户";
    }
}