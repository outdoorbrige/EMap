package cn.com.sgcc.epri.emap.webservice;

import android.os.Handler;

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
 * Created by GuHeng on 2016/10/24.
 * 用户服务(注册，登录)的父类
 */
public class UserWebService extends TransmitContext {
    private ConfigInfo config_info;
    private String endpoint;
    private String action;
    private int envelope_ver;
    private SoapSerializationEnvelope envelope;
    private HttpTransportSE transport;

    // 构造函数
    protected UserWebService(MainActivity context) {
        super(context);
    }

    // 初始化
    protected void init() {
        config_info = context.getConfigInfo();
        envelope_ver = getSoapEnvelopeVer(config_info.getSoapversion());
    }

    // 预处理
    protected void prepare(int what, UserInfo userinfo) {
        SoapObject soap_object = null;

        if(what == MessageWhat.MSG_REGISTER) { // 用户注册
            prepareRegister(userinfo);
        } else if(what == MessageWhat.MSG_LOGIN) { // 用户登录
            prepareLogin(userinfo);
        } else {
        }
    }

    // 调用服务
    protected boolean call(UserInfo userinfo) {
        boolean bSuccessed = false;
        try {
            transport.call(action, envelope);
            bSuccessed = parseResponse(userinfo);
        } catch (Exception e) {
            bSuccessed = false;
            userinfo.setSuccessed(false);
            userinfo.setEmsg(e.toString());
            Logger.getLogger(this.getClass()).error(e.toString());
        }
        return bSuccessed;
    }

    // 注册预处理
    private void prepareRegister(UserInfo userinfo) {
        endpoint = config_info.getProtocol() + config_info.getServer() + ":" + config_info.getPort() + config_info.getRegister_path();
        action = config_info.getNamespace() + config_info.getRegister_name();

        SoapObject soap_object = new SoapObject(config_info.getNamespace(), config_info.getRegister_name());

        PropertyInfo property_info = new PropertyInfo();
        property_info.setName(config_info.getRegister_var1_name());
        property_info.setValue(userinfo);
        property_info.setType(UserInfo.class);

        soap_object.addProperty(property_info);

        transport = new HttpTransportSE(endpoint);
        transport.debug = true;

        envelope = new SoapSerializationEnvelope(envelope_ver);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soap_object);
        envelope.addMapping(config_info.getNamespace(), UserInfo.class.getSimpleName(), UserInfo.class);
    }

    // 登录预处理
    private void prepareLogin(UserInfo userinfo) {
        endpoint = config_info.getProtocol() + config_info.getServer() + ":" + config_info.getPort() + config_info.getLogin_path();
        action = config_info.getNamespace() + config_info.getLogin_name();

        SoapObject soap_object = new SoapObject(config_info.getNamespace(), config_info.getLogin_name());

        PropertyInfo property_info = new PropertyInfo();
        property_info.setName(config_info.getLogin_var1_name());
        property_info.setValue(userinfo);
        property_info.setType(UserInfo.class);

        soap_object.addProperty(property_info);

        transport = new HttpTransportSE(endpoint);
        transport.debug = true;

        envelope = new SoapSerializationEnvelope(envelope_ver);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soap_object);
        envelope.addMapping(config_info.getNamespace(), UserInfo.class.getSimpleName(), UserInfo.class);
    }

    // 解析WEBSERVICE返回的数据
    private boolean parseResponse(UserInfo userinfo) {
        boolean bSuccessed = false;
        SoapObject object = (SoapObject)(((SoapObject)envelope.bodyIn).getProperty(0));
        if(object != null) {
            userinfo.setId(parseProperty(object, "id"));
            userinfo.setUsername(parseProperty(object, "username"));
            userinfo.setPassword(parseProperty(object, "password"));
            userinfo.setNickname(parseProperty(object, "nickname"));
            userinfo.setTelnumber(parseProperty(object, "telnumber"));
            userinfo.setEmail(parseProperty(object, "email"));
            userinfo.setViplevel(parseProperty(object, "viplevel"));
            userinfo.setCreatetime(parseProperty(object, "createtime"));
            userinfo.setModifytime(parseProperty(object, "modifytime"));
            userinfo.setLogintime(parseProperty(object, "logintime"));
            userinfo.setSuccessed(Boolean.valueOf(parseProperty(object, "successed")));
            userinfo.setEmsg(parseProperty(object, "emsg"));
            bSuccessed = true;
        } else {
            bSuccessed = false;
            String e = String.format("解析服务返回结果失败!");
            userinfo.setEmsg(e);
            Logger.getLogger(this.getClass()).error(e);
        }

        return bSuccessed;
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

    // 解析属性
    private String parseProperty(SoapObject object, String key) {
        String value = "";
        Object object1 = object.getProperty(key);
        if (object1 != null) {
            String temp_value = object1.toString();
            if (!"anyType{}".equals(temp_value)) {
                value = temp_value;
            }
        } else {
            Logger.getLogger(this.getClass()).error(String.format("属性%s不存在!", key));
        }
        return value;
    }
}
