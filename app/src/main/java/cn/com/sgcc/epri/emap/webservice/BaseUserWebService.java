package cn.com.sgcc.epri.emap.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.ConfigInfo;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.MessageWhat;
import cn.com.sgcc.epri.emap.base.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/24.
 * 用户服务(注册，登录)的父类
 */
public class BaseUserWebService extends MainActivityContext {
    private ConfigInfo mConfigInfo;
    private String mSoapEndPoint;
    private String mSoapAction;
    private int mSoapEnvelopeVer;
    private SoapSerializationEnvelope mSoapSerializationEnvelope;
    private HttpTransportSE mHttpTransportSE;

    // 构造函数
    protected BaseUserWebService(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    protected void init() {
        mConfigInfo = mMainActivity.getFileManager().getConfigInfo();
        mSoapEnvelopeVer = getSoapEnvelopeVer(mConfigInfo.getSoapVersion());
    }

    // 预处理
    protected void prepare(int what, UserInfo userInfo) {
        SoapObject soapObject = null;

        if(what == MessageWhat.MSG_REGISTER) { // 用户注册
            prepareRegister(userInfo);
        } else if(what == MessageWhat.MSG_LOGIN) { // 用户登录
            prepareLogin(userInfo);
        } else {
        }
    }

    // 调用服务
    protected boolean call(UserInfo userInfo) {
        boolean success = false;
        try {
            mHttpTransportSE.call(mSoapAction, mSoapSerializationEnvelope);
            success = parseResponse(userInfo);
        } catch (Exception e) {
            success = false;
            userInfo.setSuccess(false);
            userInfo.setErrorString(e.toString());
            mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError,
                    e.toString());
        } finally {
            return success;
        }
    }

    // 注册预处理
    private void prepareRegister(UserInfo userInfo) {
        mSoapEndPoint = mConfigInfo.getProtocol() + mConfigInfo.getServer() + ":" + mConfigInfo.getPort() + mConfigInfo.getRegisterUrlPath();
        mSoapAction = mConfigInfo.getNameSpace() + mConfigInfo.getRegisterMethodName();

        SoapObject soapObject = new SoapObject(mConfigInfo.getNameSpace(), mConfigInfo.getRegisterMethodName());

        PropertyInfo property_info = new PropertyInfo();
        property_info.setName(mConfigInfo.getRegisterMethodParam1Name());
        property_info.setValue(userInfo);
        property_info.setType(UserInfo.class);

        soapObject.addProperty(property_info);

        mHttpTransportSE = new HttpTransportSE(mSoapEndPoint);
        mHttpTransportSE.debug = true;

        mSoapSerializationEnvelope = new SoapSerializationEnvelope(mSoapEnvelopeVer);
        mSoapSerializationEnvelope.dotNet = true;
        mSoapSerializationEnvelope.setOutputSoapObject(soapObject);
        mSoapSerializationEnvelope.addMapping(mConfigInfo.getNameSpace(), UserInfo.class.getSimpleName(), UserInfo.class);
    }

    // 登录预处理
    private void prepareLogin(UserInfo userInfo) {
        mSoapEndPoint = mConfigInfo.getProtocol() + mConfigInfo.getServer() + ":" + mConfigInfo.getPort() + mConfigInfo.getLoginUrlPath();
        mSoapAction = mConfigInfo.getNameSpace() + mConfigInfo.getLoginMethodName();

        SoapObject soapObject = new SoapObject(mConfigInfo.getNameSpace(), mConfigInfo.getLoginMethodName());

        PropertyInfo property_info = new PropertyInfo();
        property_info.setName(mConfigInfo.getLoginMethodParam1Name());
        property_info.setValue(userInfo);
        property_info.setType(UserInfo.class);

        soapObject.addProperty(property_info);

        mHttpTransportSE = new HttpTransportSE(mSoapEndPoint);
        mHttpTransportSE.debug = true;

        mSoapSerializationEnvelope = new SoapSerializationEnvelope(mSoapEnvelopeVer);
        mSoapSerializationEnvelope.dotNet = true;
        mSoapSerializationEnvelope.setOutputSoapObject(soapObject);
        mSoapSerializationEnvelope.addMapping(mConfigInfo.getNameSpace(), UserInfo.class.getSimpleName(), UserInfo.class);
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
            String e = String.format("解析服务返回结果失败!");
            userInfo.setErrorString(e);
            mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError,
                    e);
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
            mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError,
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
            mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError,
                    String.format("属性:%s不存在！", key));
        }

        return value;
    }
}
