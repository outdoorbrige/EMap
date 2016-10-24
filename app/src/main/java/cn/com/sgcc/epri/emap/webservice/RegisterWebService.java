package cn.com.sgcc.epri.emap.webservice;

import android.os.Handler;
import android.os.Message;

import org.apache.log4j.Logger;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.ConfigInfo;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.MessageWhat;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/14.
 * 用户注册WEBSERVICE
 */
public class RegisterWebService extends UserWebService implements Runnable {
    private Handler handler;
    private UserInfo userinfo;

    // 构造函数
    public RegisterWebService(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        super.init();
    }

    // 预处理
    public void prepare(Handler handler, UserInfo userinfo) {
        super.prepare(MessageWhat.MSG_REGISTER, userinfo);
        this.handler = handler;
        this.userinfo = userinfo;
    }

    @Override
    public void run() {
        super.call(userinfo);
        Message message = new Message();
        message.what = MessageWhat.MSG_REGISTER;
        message.obj = userinfo;
        handler.sendMessage(message);
    }
}
