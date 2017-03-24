package com.gh.emap.webserviceA;

import android.os.Handler;
import android.os.Message;

import com.gh.emap.MainActivity;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.managerA.WebServiceManager;
import com.gh.emap.modelA.EMap;
import com.gh.emap.modelA.UserInfo;

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
    private MainActivity mMainActivity;
    private Handler mHandler;
    private EMap mEMap;
    private String mSoapEndPoint;
    private String mSoapAction;
    private int mSoapEnvelopeVer;
    private SoapSerializationEnvelope mSoapSerializationEnvelope;
    private HttpTransportSE mHttpTransportSE;

    public UserLoginWebService(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mEMap = mMainActivity.getMainManager().getFileManager().getEMapFile().getEMap();
        mSoapEnvelopeVer = getSoapEnvelopeVer(mEMap.getSoapVersion());
    }

    // 预处理
    public void prepare(Handler handler, UserInfo userInfo) {
        mHandler = handler;

        mSoapEndPoint = mEMap.getProtocol() + mEMap.getServer() + ":" + mEMap.getPort() + mEMap.getLoginUrlPath();
        mSoapAction = mEMap.getNameSpace() + mEMap.getLoginMethodName();

        SoapObject soapObject = new SoapObject(mEMap.getNameSpace(), mEMap.getLoginMethodName());

        PropertyInfo property_info = new PropertyInfo();
        property_info.setName(mEMap.getLoginMethodParam1Name());
        property_info.setValue(userInfo);
        property_info.setType(UserInfo.class);

        soapObject.addProperty(property_info);

        mHttpTransportSE = new HttpTransportSE(mSoapEndPoint);
        mHttpTransportSE.debug = true;

        mSoapSerializationEnvelope = new SoapSerializationEnvelope(mSoapEnvelopeVer);
        mSoapSerializationEnvelope.dotNet = true;
        mSoapSerializationEnvelope.setOutputSoapObject(soapObject);
        mSoapSerializationEnvelope.addMapping(mEMap.getNameSpace(), UserInfo.class.getSimpleName(), UserInfo.class);
    }

    @Override
    public void run() {
        UserInfo userInfo = new UserInfo();
        call(userInfo);
        Message message = new Message();
        message.what = WebServiceManager.WebServiceMsgType.WS_MSG_LOGIN;
        message.obj = userInfo;
        mHandler.sendMessage(message);
    }

    // 调用服务
    private boolean call(UserInfo userInfo) {
        boolean success = false;
        try {
            mHttpTransportSE.call(mSoapAction, mSoapSerializationEnvelope);
            success = parseResponse(userInfo);
        } catch (Exception e) {
            success = false;
            userInfo.setSuccess(false);
            userInfo.setErrorString(e.toString());
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError,
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
            userInfo.setOnline(Integer.valueOf(parseProperty(object, "mOnline")));
            userInfo.setLogoutDate(parseProperty(object, "mLogoutDate"));
            userInfo.setSuccess(Boolean.valueOf(parseProperty(object, "mSuccess")));
            userInfo.setErrorString(parseProperty(object, "mErrorString"));
            success = true;
        } else {
            success = false;
            String error = String.format("解析服务返回结果失败!");
            userInfo.setErrorString(error);
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError,
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
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError,
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
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError,
                    String.format("属性:%s不存在！", key));
        }

        return value;
    }
}
