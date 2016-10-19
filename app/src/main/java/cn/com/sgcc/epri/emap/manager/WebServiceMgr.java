package cn.com.sgcc.epri.emap.manager;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.TransmitContext;
import cn.com.sgcc.epri.emap.webservice.LoginWebService;
import cn.com.sgcc.epri.emap.webservice.RegisterWebService;

/**
 * Created by GuHeng on 2016/10/14.
 * 服务管理类
 */
public class WebServiceMgr extends TransmitContext {
    private RegisterWebService register_service; // 用户注册服务
    private LoginWebService login_service; // 用户登录服务

    // 构造函数
    public WebServiceMgr(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        register_service = new RegisterWebService(context);
        login_service = new LoginWebService(context);
    }

    // 注册服务
    public boolean RegisterService(UserInfo userinfo) {
        return register_service.Register(userinfo);
    }

    // 登录服务
    public boolean LoginService(UserInfo userinfo) {
        return login_service.Login(userinfo);
    }
}
