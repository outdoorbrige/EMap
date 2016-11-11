package com.gh.emap.manager;

/**
 * Created by GuHeng on 2016/11/10.
 */

import android.content.Context;

import com.gh.emap.model.UserInfo;

/**
 * Created by GuHeng on 2016/10/25.
 * 用户信息管理类
 */
public class UserManager {
    private Context mContext;
    private UserInfo mUserInfo; // 用户信息

    // 构造函数
    public UserManager (Context context) {
        this.mContext = context;
    }

    // 初始化
    public void init() {

    }

    // 设置用户信息
    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
    }

    // 获取用户信息
    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    // 是否在线
    public boolean isOnLine() {
        return mUserInfo == null ? false : mUserInfo.isSuccess();
    }

    public class UserType { // 用户类型
        public static final String mNormalType = "普通用户";
        public static final String mAdminType = "管理员";
    }
}