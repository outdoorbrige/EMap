package cn.com.sgcc.epri.emap.webservice;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/14.
 * 用户登录WEBSERVICE
 */
public class LoginWebService extends TransmitContext {

    // 构造函数
    public LoginWebService(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {

    }

    public UserInfo Login(UserInfo userinfo) {
        UserInfo return_userinfo = new UserInfo();

        return return_userinfo;
    }
}
