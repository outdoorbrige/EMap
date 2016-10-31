package cn.com.sgcc.epri.emap.manager;

import android.os.Handler;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.MainActivityContext;
import cn.com.sgcc.epri.emap.webservice.LoginWebService;
import cn.com.sgcc.epri.emap.webservice.RegisterWebService;

/**
 * Created by GuHeng on 2016/10/14.
 * 服务管理类
 */
public class WebServiceManager extends MainActivityContext {
    private RegisterWebService mRegisterWebService; // 用户注册服务
    private LoginWebService mLoginWebService; // 用户登录服务

    // 构造函数
    public WebServiceManager(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mRegisterWebService = new RegisterWebService(mMainActivity);
        mRegisterWebService.init();

        mLoginWebService = new LoginWebService(mMainActivity);
        mLoginWebService.init();
    }

    // 注册服务
    public void RegisterService(Handler handler, UserInfo userInfo) {
        mRegisterWebService.prepare(handler, userInfo);
        new Thread(mRegisterWebService).start();
    }

    // 登录服务
    public void LoginService(Handler handler, UserInfo userInfo) {
        mLoginWebService.prepare(handler, userInfo);
        new Thread(mLoginWebService).start();
    }
}
