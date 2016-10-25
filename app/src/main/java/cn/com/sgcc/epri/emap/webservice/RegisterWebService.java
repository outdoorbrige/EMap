package cn.com.sgcc.epri.emap.webservice;

import android.os.Handler;
import android.os.Message;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.MessageWhat;

/**
 * Created by GuHeng on 2016/10/14.
 * 用户注册WEBSERVICE
 */
public class RegisterWebService extends BaseUserWebService implements Runnable {
    private Handler mHandler;
    private UserInfo userInfo;

    // 构造函数
    public RegisterWebService(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        super.init();
    }

    // 预处理
    public void prepare(Handler handler, UserInfo userInfo) {
        super.prepare(MessageWhat.MSG_REGISTER, userInfo);
        this.mHandler = handler;
        this.userInfo = userInfo;
    }

    @Override
    public void run() {
        super.call(userInfo);
        Message message = new Message();
        message.what = MessageWhat.MSG_REGISTER;
        message.obj = userInfo;
        mHandler.sendMessage(message);
    }
}
