package com.gh.emap.model;

/**
 * Created by GuHeng on 2016/11/9.
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

    public String getNameSpace() {
        return mNameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.mNameSpace = nameSpace;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String server) {
        this.mServer = server;
    }

    public String getPort() {
        return mPort;
    }

    public void setPort(String port) {
        this.mPort = port;
    }

    public String getProtocol() {
        return mProtocol;
    }

    public void setProtocol(String protocol) {
        this.mProtocol = protocol;
    }

    public String getSoapVersion() {
        return mSoapVersion;
    }

    public void setSoapVersion(String soapVersion) {
        this.mSoapVersion = soapVersion;
    }

    public String getRegisterMethodName() {
        return mRegisterMethodName;
    }

    public void setRegisterMethodName(String registerMethodName) {
        this.mRegisterMethodName = registerMethodName;
    }

    public String getRegisterUrlPath() {
        return mRegisterUrlPath;
    }

    public void setRegisterUrlPath(String registerUrlPath) {
        this.mRegisterUrlPath = registerUrlPath;
    }

    public String getRegisterMethodParam1Name() {
        return mRegisterMethodParam1Name;
    }

    public void setRegisterMethodParam1Name(String registerMethodParam1Name) {
        this.mRegisterMethodParam1Name = registerMethodParam1Name;
    }

    public String getRegisterMethodReturnParamName() {
        return mRegisterMethodReturnParamName;
    }

    public void setRegisterMethodReturnParamName(String registerMethodReturnParamName) {
        this.mRegisterMethodReturnParamName = registerMethodReturnParamName;
    }

    public String getLoginMethodName() {
        return mLoginMethodName;
    }

    public void setLoginMethodName(String loginMethodName) {
        this.mLoginMethodName = loginMethodName;
    }

    public String getLoginUrlPath() {
        return mLoginUrlPath;
    }

    public void setLoginUrlPath(String loginUrlPath) {
        this.mLoginUrlPath = loginUrlPath;
    }

    public String getLoginMethodParam1Name() {
        return mLoginMethodParam1Name;
    }

    public void setLoginMethodParam1Name(String loginMethodParam1Name) {
        this.mLoginMethodParam1Name = loginMethodParam1Name;
    }

    public String getLoginMethodReturnParamName() {
        return mLoginMethodReturnParamName;
    }

    public void setLoginMethodReturnParamName(String loginMethodReturnParamName) {
        this.mLoginMethodReturnParamName = loginMethodReturnParamName;
    }

    public String toString() {
        return String.format(
                "NameSpace:%s, " +
                        "Server:%s, " +
                        "Port:%s, " +
                        "Protocol:%s, " +
                        "SoapVersion:%s, " +
                        "RegisterMethodName:%s, " +
                        "RegisterUrlPath:%s, " +
                        "RegisterMethodParam1Name:%s, " +
                        "RegisterMethodReturnParamName:%s, " +
                        "LoginMethodName:%s, " +
                        "LoginUrlPath:%s, " +
                        "LoginMethodParam1Name:%s, " +
                        "LoginMethodReturnParamName:%s",

                mNameSpace,
                mServer,
                mPort,
                mProtocol,
                mSoapVersion,

                mRegisterMethodName,
                mRegisterUrlPath,
                mRegisterMethodParam1Name,
                mRegisterMethodReturnParamName,

                mLoginMethodName,
                mLoginUrlPath,
                mLoginMethodParam1Name,
                mLoginMethodReturnParamName);
    }
}
