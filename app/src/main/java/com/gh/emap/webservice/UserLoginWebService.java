package com.gh.emap.webservice;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.gh.emap.MainActivity;
import com.gh.emap.manager.LogManager;
import com.gh.emap.model.EMap;
import com.gh.emap.model.UserInfo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by GuHeng on 2016/11/10.
 * 用户登录服务
 */
public class UserLoginWebService implements Runnable {
    private Context mContext;
    private Handler mHandler;
    private EMap mEMap;
    private String mSoapEndPoint;
    private String mSoapAction;
    private int mSoapEnvelopeVer;
    private SoapSerializationEnvelope mSoapSerializationEnvelope;
    private HttpTransportSE mHttpTransportSE;

    public static final int MSG_LOGIN = 2;

    public UserLoginWebService(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mEMap = ((MainActivity) this.mContext).getMainManager().getFileManager().getEMapFile().getEMap();
        this.mSoapEnvelopeVer = getSoapEnvelopeVer(this.mEMap.getSoapVersion());
    }

    // 预处理
    public void prepare(Handler handler, UserInfo userInfo) {
        this.mHandler = handler;

        this.mSoapEndPoint = this.mEMap.getProtocol() + this.mEMap.getServer() + ":" + this.mEMap.getPort() + this.mEMap.getLoginUrlPath();
        this.mSoapAction = this.mEMap.getNameSpace() + this.mEMap.getLoginMethodName();

        SoapObject soapObject = new SoapObject(this.mEMap.getNameSpace(), this.mEMap.getLoginMethodName());

        PropertyInfo property_info = new PropertyInfo();
        property_info.setName(this.mEMap.getLoginMethodParam1Name());
        property_info.setValue(userInfo);
        property_info.setType(UserInfo.class);

        soapObject.addProperty(property_info);

        this.mHttpTransportSE = new HttpTransportSE(this.mSoapEndPoint);
        this.mHttpTransportSE.debug = true;

        this.mSoapSerializationEnvelope = new SoapSerializationEnvelope(this.mSoapEnvelopeVer);
        this.mSoapSerializationEnvelope.dotNet = true;
        this.mSoapSerializationEnvelope.setOutputSoapObject(soapObject);
        this.mSoapSerializationEnvelope.addMapping(this.mEMap.getNameSpace(), UserInfo.class.getSimpleName(), UserInfo.class);
    }

    @Override
    public void run() {
        UserInfo userInfo = new UserInfo();
        call(userInfo);
        Message message = new Message();
        message.what = MSG_LOGIN;
        message.obj = userInfo;
        this.mHandler.sendMessage(message);
    }

    // 调用服务
    private boolean call(UserInfo userInfo) {
        boolean success = false;
        try {
            this.mHttpTransportSE.call(this.mSoapAction, this.mSoapSerializationEnvelope);
            success = parseResponse(userInfo);
        } catch (Exception e) {
            success = false;
            userInfo.setSuccess(false);
            userInfo.setErrorString(e.toString());
            ((MainActivity) this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                    e.getStackTrace().toString());
        } finally {
            return success;
        }
    }

    // 解析WEBSERVICE返回的数据
    private boolean parseResponse(UserInfo userInfo) {
        boolean success = false;
        SoapObject object = (SoapObject)(((SoapObject) mSoapSerializationEnvelope.bodyIn).getProperty(0));
        if(object != null) {
            userInfo.setId(parseProperty(object, "mId"));
            userInfo.setUserName(parseProperty(object, "mUserName"));
            userInfo.setPassword(parseProperty(object, "mPassword"));
            userInfo.setNickName(parseProperty(object, "mNickName"));
            userInfo.setTelNumber(parseProperty(object, "mTelNumber"));
            userInfo.setEMail(parseProperty(object, "mEMail"));
            userInfo.setVipLevel(parseProperty(object, "mVipLevel"));
            userInfo.setUserType(parseProperty(object, "mUserType"));
            userInfo.setCreateDate(parseProperty(object, "mCreateDate"));
            userInfo.setModifyDate(parseProperty(object, "mModifyDate"));
            userInfo.setLoginDate(parseProperty(object, "mLoginDate"));
            userInfo.setSuccess(Boolean.valueOf(parseProperty(object, "mSuccess")));
            userInfo.setErrorString(parseProperty(object, "mErrorString"));
            success = true;
        } else {
            success = false;
            String error = String.format("解析服务返回结果失败!");
            userInfo.setErrorString(error);
            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                    error);
        }

        return success;
    }

    // 获取SOAP版本对应的SoapEnvelope常量
    private int getSoapEnvelopeVer(String soapVersion) {
        int ver = 0;
        if("1.0".equals(soapVersion)) {
            ver = SoapEnvelope.VER10;
        } else if("1.1".equals(soapVersion)) {
            ver = SoapEnvelope.VER11;
        }else if("1.2".equals(soapVersion)) {
            ver = SoapEnvelope.VER12;
        } else {
            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                    String.format("SOAP协议版本号:%s无效！", soapVersion));
        }

        return ver;
    }

    // 解析属性
    private String parseProperty(SoapObject soapObject, String key) {
        String value = "";
        Object object = soapObject.getProperty(key);
        if (object != null) {
            String temp_value = object.toString();
            if (!"anyType{}".equals(temp_value)) {
                value = temp_value;
            }
        } else {
            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                    String.format("属性:%s不存在！", key));
        }

        return value;
    }
}
