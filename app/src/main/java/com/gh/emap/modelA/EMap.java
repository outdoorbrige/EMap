package com.gh.emap.modelA;

/**
 * Created by GuHeng on 2016/11/9.
 * 配置文件
 */
public class EMap {
    private String mNameSpace; // 命名空间
    private String mServer; // 服务器
    private String mPort; // 端口
    private String mProtocol; // 协议
    private String mSoapVersion; // SOAP协议版本

    private String mRegisterMethodName; // 注册服务名称
    private String mRegisterUrlPath; // 注册服务路径
    private String mRegisterMethodParam1Name; // 注册服务参数名称
    private String mRegisterMethodReturnParamName; // 注册服务返回值参数名称

    private String mLoginMethodName; // 登录服务名称
    private String mLoginUrlPath; // 登录服务路径
    private String mLoginMethodParam1Name; // 登录服务参数名称
    private String mLoginMethodReturnParamName; // 登录服务返回值参数名称

    private String mLogoutMethodName; // 注销服务名称
    private String mLogoutUrlPath; // 注销服务路径
    private String mLogoutMethodParam1Name; // 注销服务参数名称
    private String mLogoutMethodReturnParamName; // 注销服务返回值参数名称

    public String getNameSpace() {
        return mNameSpace;
    }

    public void setNameSpace(String nameSpace) {
        mNameSpace = nameSpace;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String server) {
        mServer = server;
    }

    public String getPort() {
        return mPort;
    }

    public void setPort(String port) {
        mPort = port;
    }

    public String getProtocol() {
        return mProtocol;
    }

    public void setProtocol(String protocol) {
        mProtocol = protocol;
    }

    public String getSoapVersion() {
        return mSoapVersion;
    }

    public void setSoapVersion(String soapVersion) {
        mSoapVersion = soapVersion;
    }

    public String getRegisterMethodName() {
        return mRegisterMethodName;
    }

    public void setRegisterMethodName(String registerMethodName) {
        mRegisterMethodName = registerMethodName;
    }

    public String getRegisterUrlPath() {
        return mRegisterUrlPath;
    }

    public void setRegisterUrlPath(String registerUrlPath) {
        mRegisterUrlPath = registerUrlPath;
    }

    public String getRegisterMethodParam1Name() {
        return mRegisterMethodParam1Name;
    }

    public void setRegisterMethodParam1Name(String registerMethodParam1Name) {
        mRegisterMethodParam1Name = registerMethodParam1Name;
    }

    public String getRegisterMethodReturnParamName() {
        return mRegisterMethodReturnParamName;
    }

    public void setRegisterMethodReturnParamName(String registerMethodReturnParamName) {
        mRegisterMethodReturnParamName = registerMethodReturnParamName;
    }

    public String getLoginMethodName() {
        return mLoginMethodName;
    }

    public void setLoginMethodName(String loginMethodName) {
        mLoginMethodName = loginMethodName;
    }

    public String getLoginUrlPath() {
        return mLoginUrlPath;
    }

    public void setLoginUrlPath(String loginUrlPath) {
        mLoginUrlPath = loginUrlPath;
    }

    public String getLoginMethodParam1Name() {
        return mLoginMethodParam1Name;
    }

    public void setLoginMethodParam1Name(String loginMethodParam1Name) {
        mLoginMethodParam1Name = loginMethodParam1Name;
    }

    public String getLoginMethodReturnParamName() {
        return mLoginMethodReturnParamName;
    }

    public void setLoginMethodReturnParamName(String loginMethodReturnParamName) {
        mLoginMethodReturnParamName = loginMethodReturnParamName;
    }

    public String getLogoutMethodName() {
        return mLogoutMethodName;
    }

    public void setLogoutMethodName(String logoutMethodName) {
        mLogoutMethodName = logoutMethodName;
    }

    public String getLogoutUrlPath() {
        return mLogoutUrlPath;
    }

    public void setLogoutUrlPath(String logoutUrlPath) {
        mLogoutUrlPath = logoutUrlPath;
    }

    public String getLogoutMethodParam1Name() {
        return mLogoutMethodParam1Name;
    }

    public void setLogoutMethodParam1Name(String logoutMethodParam1Name) {
        mLogoutMethodParam1Name = logoutMethodParam1Name;
    }

    public String getLogoutMethodReturnParamName() {
        return mLogoutMethodReturnParamName;
    }

    public void setLogoutMethodReturnParamName(String logoutMethodReturnParamName) {
        mLogoutMethodReturnParamName = logoutMethodReturnParamName;
    }

    @Override
    public String toString() {
        return "EMap{" +
                "mNameSpace='" + mNameSpace + '\'' +
                ", mServer='" + mServer + '\'' +
                ", mPort='" + mPort + '\'' +
                ", mProtocol='" + mProtocol + '\'' +
                ", mSoapVersion='" + mSoapVersion + '\'' +
                ", mRegisterMethodName='" + mRegisterMethodName + '\'' +
                ", mRegisterUrlPath='" + mRegisterUrlPath + '\'' +
                ", mRegisterMethodParam1Name='" + mRegisterMethodParam1Name + '\'' +
                ", mRegisterMethodReturnParamName='" + mRegisterMethodReturnParamName + '\'' +
                ", mLoginMethodName='" + mLoginMethodName + '\'' +
                ", mLoginUrlPath='" + mLoginUrlPath + '\'' +
                ", mLoginMethodParam1Name='" + mLoginMethodParam1Name + '\'' +
                ", mLoginMethodReturnParamName='" + mLoginMethodReturnParamName + '\'' +
                ", mLogoutMethodName='" + mLogoutMethodName + '\'' +
                ", mLogoutUrlPath='" + mLogoutUrlPath + '\'' +
                ", mLogoutMethodParam1Name='" + mLogoutMethodParam1Name + '\'' +
                ", mLogoutMethodReturnParamName='" + mLogoutMethodReturnParamName + '\'' +
                '}';
    }
}
