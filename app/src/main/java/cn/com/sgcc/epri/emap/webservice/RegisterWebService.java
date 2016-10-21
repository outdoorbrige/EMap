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
public class RegisterWebService extends TransmitContext implements Runnable {
    private Handler handler;
    private ConfigInfo config_info;
    private String endpoint;
    private String action;
    private int envelope_ver;
    private SoapSerializationEnvelope envelope;
    private HttpTransportSE transport;
    private UserInfo return_userinfo;

    // 构造函数
    public RegisterWebService(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        config_info = context.getConfigInfo();
        endpoint = config_info.getProtocol() + config_info.getServer() + ":" + config_info.getPort() + config_info.getRegister_path();
        action = config_info.getNamespace() + config_info.getRegister_name();
        envelope_ver = getSoapEnvelopeVer(config_info.getSoapversion());
    }

    public void setUserInfo(UserInfo userinfo, Handler handler) {
        this.handler = handler;

        PropertyInfo property_info = new PropertyInfo();
        property_info.setName(config_info.getRegister_var1_name());
        property_info.setValue(userinfo);
        property_info.setType(UserInfo.class);

        SoapObject soap_object = new SoapObject(config_info.getNamespace(), config_info.getRegister_name());
        soap_object.addProperty(property_info);

        transport = new HttpTransportSE(endpoint);
        transport.debug = true;

        envelope = new SoapSerializationEnvelope(envelope_ver);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soap_object);
        envelope.addMapping(config_info.getNamespace(), UserInfo.class.getSimpleName(), UserInfo.class);
    }

    public void Register() {
        return_userinfo = new UserInfo();
        try {
            transport.call(action, envelope);
            parseResponse(return_userinfo);
        } catch (Exception e) {
            return_userinfo.setSuccessed(false);
            return_userinfo.setEmsg(e.toString());
        }
    }

    // 获取SOAP版本对应的SoapEnvelope常量
    private int getSoapEnvelopeVer(String soapversion) {
        int ver = 0;
        if("1.0".equals(soapversion)) {
            ver = SoapEnvelope.VER10;
        } else if("1.1".equals(soapversion)) {
            ver = SoapEnvelope.VER11;
        }else if("1.2".equals(soapversion)) {
            ver = SoapEnvelope.VER12;
        } else {
            Logger.getLogger(this.getClass()).error(String.format("SOAP协议版本%s号无效", soapversion));
        }

        return ver;
    }

    // 解析WEBSERVICE返回的数据
    private void parseResponse(UserInfo userinfo) {

        SoapObject response = (SoapObject) envelope.bodyIn;
        PropertyInfo property_info = new PropertyInfo();
        response.getPropertyInfo(0, property_info);
        SoapObject body = (SoapObject) property_info.getValue();

        String id = body.getProperty("id").toString();
        if(!id.isEmpty()) {
            userinfo.setId(id);
        }

        userinfo.setUsername(body.getProperty("username").toString());
//        userinfo.setPassword(body.getProperty("password").toString());
//        userinfo.setNickname(body.getProperty("nickname").toString());
//        userinfo.setTelnumber(body.getProperty("telnumber").toString());
//        userinfo.setEmail(body.getProperty("email").toString());
//        userinfo.setViplevel(body.getProperty("viplevel").toString());
//        userinfo.setCreatetime(body.getProperty("createtime").toString());
//        userinfo.setModifytime(body.getProperty("modifytime").toString());
//        userinfo.setLogintime(body.getProperty("logintime").toString());
//        userinfo.setSuccessed(Boolean.getBoolean(body.getProperty("successed").toString()));
//        userinfo.setEmsg(body.getProperty("emsg").toString());
    }

    @Override
    public void run() {
        Register();
        Message message = new Message();
        message.what = MessageWhat.MSG_REGISTER;
        message.obj = return_userinfo;
        handler.sendMessage(message);
    }
}
