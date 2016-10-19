package cn.com.sgcc.epri.emap.webservice;

import org.apache.log4j.Logger;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.ConfigInfo;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/14.
 * 用户注册WEBSERVICE
 */
public class RegisterWebService extends TransmitContext {

    // 构造函数
    public RegisterWebService(MainActivity context) {
        super(context);
    }

    public boolean Register(UserInfo userinfo) {
        boolean bSuccessed = false;
        String eMsg = "";
        ConfigInfo config_info = context.getConfigInfo();

        try {
            // 设置WebService的命名空间和调用的方法名
            SoapObject object = new SoapObject(config_info.getNamespace(), config_info.getRegister_name());

            // 设置调用WebService接口需要传入的参数
            object.addAttribute(config_info.getLogin_var1_name(), userinfo);

            // 生成调用WebService方法的SOAP请求消息，并指定SOAP的版本
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);

            // 设置是否调用的dotNet开放的WebService
            envelope.dotNet = true;

            envelope.setOutputSoapObject(object);

            String endpoint = config_info.getProtocol() + config_info.getServer() + config_info.getRegister_path();
            HttpTransportSE transport = new HttpTransportSE(endpoint);

            String action = config_info.getNamespace() + config_info.getRegister_name();
            // 调用WebService
            transport.call(action, envelope);

            // 获取返回的数据
            bSuccessed = (boolean)((SoapObject)(envelope.bodyIn)).getProperty(config_info.getRegister_result_name());
        } catch (Exception e) {
            Logger.getLogger(this.getClass()).error(String.format("调用WebService：%s出错，错误原因：%s", config_info.getRegister_name(), e.getStackTrace().toString()));
            eMsg = e.toString(); // 抛出异常
        }
        finally {
            return bSuccessed;
        }
    }
}
