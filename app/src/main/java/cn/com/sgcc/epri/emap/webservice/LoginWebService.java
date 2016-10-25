package cn.com.sgcc.epri.emap.webservice;

import android.os.Handler;
import android.os.Message;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.MessageWhat;

/**
 * Created by GuHeng on 2016/10/14.
 * 用户登录WEBSERVICE
 */
public class LoginWebService extends BaseUserWebService implements Runnable {
    private Handler mHandler;
    private UserInfo mUserInfo;

    // 构造函数
    public LoginWebService(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        super.init();
    }

    // 预处理
    public void prepare(Handler handler, UserInfo userInfo) {
        super.prepare(MessageWhat.MSG_LOGIN, userInfo);
        this.mHandler = handler;
        this.mUserInfo = userInfo;
    }

    @Override
    public void run() {
        super.call(mUserInfo);
        Message message = new Message();
        message.what = MessageWhat.MSG_LOGIN;
        message.obj = mUserInfo;
        mHandler.sendMessage(message);
    }
}
