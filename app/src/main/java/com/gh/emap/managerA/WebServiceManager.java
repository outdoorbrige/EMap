package com.gh.emap.managerA;

import android.os.Handler;

import com.gh.emap.MainActivity;
import com.gh.emap.model.UserInfo;
import com.gh.emap.webservice.UserLoginWebService;
import com.gh.emap.webservice.UserLogoutWebService;
import com.gh.emap.webservice.UserRegisterWebService;

/**
 * Created by GuHeng on 2016/10/14.
 * Web服务管理类
 */
public class WebServiceManager {
    private MainActivity mMainActivity;
    private UserRegisterWebService mUserRegisterWebService; // 用户注册服务
    private UserLoginWebService mUserLoginWebService; // 用户登录服务
    private UserLogoutWebService mUserLogoutWebService; // 用户注销服务

    // 构造函数
    public WebServiceManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    // 初始化
    public void init() {
        mUserRegisterWebService = new UserRegisterWebService(mMainActivity);
        mUserRegisterWebService.init();

        mUserLoginWebService = new UserLoginWebService(mMainActivity);
        mUserLoginWebService.init();

        mUserLogoutWebService = new UserLogoutWebService(mMainActivity);
        mUserLogoutWebService.init();
    }

    public void unInit() {

    }

    // 注册服务
    public void userRegisterWebService(Handler handler, UserInfo userInfo) {
        mUserRegisterWebService.prepare(handler, userInfo);
        new Thread(mUserRegisterWebService).start();
    }

    // 登录服务
    public void userLoginWebService(Handler handler, UserInfo userInfo) {
        mUserLoginWebService.prepare(handler, userInfo);
        new Thread(mUserLoginWebService).start();
    }

    // 注销服务
    public void userLogoutWebService(Handler handler, UserInfo userInfo) {
        mUserLogoutWebService.prepare(handler, userInfo);
        new Thread(mUserLogoutWebService).start();
    }

    // Web Service 消息类型
    public class WebServiceMsgType {
        public static final int WS_MSG_MIN = 1; // 消息最小编号

        public static final int WS_MSG_REGISTER = WS_MSG_MIN + 1; // 用户注册消息
        public static final int WS_MSG_LOGIN = WS_MSG_REGISTER + 1; // 用户登录消息
        public static final int WS_MSG_LOGOUT = WS_MSG_LOGIN + 1; // 用户注销消息

        public static final int WS_MSG_MAX = WS_MSG_LOGOUT + 1; // 消息最大编号
    }
}
