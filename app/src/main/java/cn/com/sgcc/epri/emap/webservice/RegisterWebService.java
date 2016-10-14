package cn.com.sgcc.epri.emap.webservice;

import org.apache.log4j.Logger;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.util.SoapInfo;
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

    public boolean Register(String username, String password, String nickname, String telnumber, String email) {
        boolean bReturn = false;

        try {
            // 设置WebService的命名空间和调用的方法名
            SoapObject object = new SoapObject(SoapInfo.namespace, SoapInfo.RegisterMethod);

            // 设置调用WebService接口需要传入的参数
            object.addAttribute(SoapInfo.RegisterMethodVarName1, username);
            object.addAttribute(SoapInfo.RegisterMethodVarName2, password);
            object.addAttribute(SoapInfo.RegisterMethodVarName3, nickname);
            object.addAttribute(SoapInfo.RegisterMethodVarName4, telnumber);
            object.addAttribute(SoapInfo.RegisterMethodVarName5, email);

            // 生成调用WebService方法的SOAP请求消息，并指定SOAP的版本
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);

            // 设置是否调用的dotNet开放的WebService
            envelope.dotNet = true;

            envelope.setOutputSoapObject(object);

            HttpTransportSE transport = new HttpTransportSE(SoapInfo.RegisterEndPoint);

            // 调用WebService
            transport.call(SoapInfo.RegisterSoapAction, envelope);

            // 获取返回的数据
            bReturn = (boolean)((SoapObject)(envelope.bodyIn)).getProperty(SoapInfo.RegisterMethodResultName);
        } catch (Exception e) {
            bReturn = false;
            Logger.getLogger(this.getClass()).error(String.format("调用WebService：%s出错，错误原因：%s", SoapInfo.RegisterMethod, e.getStackTrace().toString()));
            throw(e); // 抛出异常
        }
        finally {
            return bReturn;
        }
    }
}
