package com.gh.emap.manager;

import android.content.Context;
import android.os.Handler;

import com.gh.emap.model.UserInfo;
import com.gh.emap.webservice.UserLoginWebService;
import com.gh.emap.webservice.UserRegisterWebService;

/**
 * Created by GuHeng on 2016/10/14.
 * 服务管理类
 */
public class WebServiceManager {
    private Context mContext;
    private UserRegisterWebService mUserRegisterWebService; // 用户注册服务
    private UserLoginWebService mUserLoginWebService; // 用户登录服务

    // 构造函数
    public WebServiceManager(Context context) {
        this.mContext = context;
    }

    // 初始化
    public void init() {
        this.mUserRegisterWebService = new UserRegisterWebService(this.mContext);
        this.mUserRegisterWebService.init();

        this.mUserLoginWebService = new UserLoginWebService(this.mContext);
        this.mUserLoginWebService.init();
    }

    // 注册服务
    public void UserRegisterService(Handler handler, UserInfo userInfo) {
        this.mUserRegisterWebService.prepare(handler, userInfo);
        new Thread(this.mUserRegisterWebService).start();
    }

    // 登录服务
    public void UserLoginService(Handler handler, UserInfo userInfo) {
        this.mUserLoginWebService.prepare(handler, userInfo);
        new Thread(this.mUserLoginWebService).start();
    }
}
