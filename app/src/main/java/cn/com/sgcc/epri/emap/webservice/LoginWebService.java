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

    public boolean Login(UserInfo userinfo) {
        boolean bSuccessed = false;
        String eMsg = "";

        return bSuccessed;
    }
}
