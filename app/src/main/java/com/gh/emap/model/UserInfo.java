package com.gh.emap.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by GuHeng on 2016/11/10.
 * 用户信息类
 */
public class UserInfo implements KvmSerializable {
    private String mId; // ID
    private String mUserName; // 用户名
    private String mPassword; // 密码
    private String mNickName; // 昵称
    private String mTelNumber; // 电话号码
    private String mEMail; // 邮箱
    private String mVipLevel; // VIP等级
    private String mUserType; // 用户类型
    private String mCreateDate; // 创建时间
    private String mModifyDate; // 最后修改时间
    private String mLoginDate; // 登录时间
    private int mOnline; // 在线状态
    private String mLogoutDate; // 注销时间
    private boolean mSuccess; // 操作是否成功
    private String mErrorString; // 错误信息

    public UserInfo() {
    }

    public UserInfo(String id,
                    String userName,
                    String password,
                    String nickName,
                    String telNumber,
                    String eMail,
                    String vipLevel,
                    String userType,
                    String createDate,
                    String modifyDate,
                    String loginDate,
                    int online,
                    String logoutDate,
                    boolean success,
                    String errorString) {
        mId = id;
        mUserName = userName;
        mPassword = password;
        mNickName = nickName;
        mTelNumber = telNumber;
        mEMail = eMail;
        mVipLevel = vipLevel;
        mUserType = userType;
        mCreateDate = createDate;
        mModifyDate = modifyDate;
        mLoginDate = loginDate;
        mOnline = online;
        mLogoutDate = logoutDate;
        mSuccess = success;
        mErrorString = errorString;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getNickName() {
        return mNickName;
    }

    public void setNickName(String nickName) {
        mNickName = nickName;
    }

    public String getTelNumber() {
        return mTelNumber;
    }

    public void setTelNumber(String telNumber) {
        mTelNumber = telNumber;
    }

    public String getEMail() {
        return mEMail;
    }

    public void setEMail(String eMail) {
        mEMail = eMail;
    }

    public String getVipLevel() {
        return mVipLevel;
    }

    public void setVipLevel(String vipLevel) {
        mVipLevel = vipLevel;
    }

    public String getUserType() {
        return mUserType;
    }

    public void setUserType(String userType) {
        mUserType = userType;
    }

    public String getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(String createDate) {
        mCreateDate = createDate;
    }

    public String getModifyDate() {
        return mModifyDate;
    }

    public void setModifyDate(String modifyDate) {
        mModifyDate = modifyDate;
    }

    public String getLoginDate() {
        return mLoginDate;
    }

    public void setLoginDate(String loginDate) {
        mLoginDate = loginDate;
    }

    public int getOnline() {
        return mOnline;
    }

    public void setOnline(int online) {
        mOnline = online;
    }

    public String getLogoutDate() {
        return mLogoutDate;
    }

    public void setLogoutDate(String logoutDate) {
        mLogoutDate = logoutDate;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public void setSuccess(boolean success) {
        mSuccess = success;
    }

    public String getErrorString() {
        return mErrorString;
    }

    public void setErrorString(String errorString) {
        mErrorString = errorString;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "mId='" + mId + '\'' +
                ", mUserName='" + mUserName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mNickName='" + mNickName + '\'' +
                ", mTelNumber='" + mTelNumber + '\'' +
                ", mEMail='" + mEMail + '\'' +
                ", mVipLevel='" + mVipLevel + '\'' +
                ", mUserType='" + mUserType + '\'' +
                ", mCreateDate='" + mCreateDate + '\'' +
                ", mModifyDate='" + mModifyDate + '\'' +
                ", mLoginDate='" + mLoginDate + '\'' +
                ", mOnline=" + mOnline +
                ", mLogoutDate='" + mLogoutDate + '\'' +
                ", mSuccess=" + mSuccess +
                ", mErrorString='" + mErrorString + '\'' +
                '}';
    }

    @Override
    public Object getProperty(int index) {
        Object object = null;

        switch (index)
        {
            case UserInfoParamIndex.mIdIndex:
                object = mId;
                break;
            case UserInfoParamIndex.mUserNameIndex:
                object = mUserName;
                break;
            case UserInfoParamIndex.mPasswordIndex:
                object = mPassword;
                break;
            case UserInfoParamIndex.mNickNameIndex:
                object = mNickName;
                break;
            case UserInfoParamIndex.mTelNumberIndex:
                object = mTelNumber;
                break;
            case UserInfoParamIndex.mEMailIndex:
                object = mEMail;
                break;
            case UserInfoParamIndex.mVipLevelIndex:
                object = mVipLevel;
                break;
            case UserInfoParamIndex.mUserTypeIndex:
                object = mUserType;
                break;
            case UserInfoParamIndex.mCreateDateIndex:
                object = mCreateDate;
                break;
            case UserInfoParamIndex.mModifyDateIndex:
                object = mModifyDate;
                break;
            case UserInfoParamIndex.mLoginDateIndex:
                object = mLoginDate;
                break;
            case UserInfoParamIndex.mOnlineIndex:
                object = mOnline;
                break;
            case UserInfoParamIndex.mLogoutDateIndex:
                object = mLogoutDate;
                break;
            case UserInfoParamIndex.mSuccessIndex:
                object = mSuccess;
                break;
            case UserInfoParamIndex.mErrorStringIndex:
                object = mErrorString;
                break;
            default:
                object = null;
                break;
        }

        return object;
    }

    @Override
    public int getPropertyCount() {

        return UserInfoParamIndex.mStringCount;
    }

    @Override
    public void setProperty(int index, Object object) {
        switch (index)
        {
            case UserInfoParamIndex.mIdIndex:
                mId = object.toString();
                break;
            case UserInfoParamIndex.mUserNameIndex:
                mUserName = object.toString();
                break;
            case UserInfoParamIndex.mPasswordIndex:
                mPassword = object.toString();
                break;
            case UserInfoParamIndex.mNickNameIndex:
                mNickName = object.toString();
                break;
            case UserInfoParamIndex.mTelNumberIndex:
                mTelNumber = object.toString();
                break;
            case UserInfoParamIndex.mEMailIndex:
                mEMail = object.toString();
                break;
            case UserInfoParamIndex.mVipLevelIndex:
                mVipLevel = object.toString();
                break;
            case UserInfoParamIndex.mUserTypeIndex:
                mUserType = object.toString();
                break;
            case UserInfoParamIndex.mCreateDateIndex:
                mCreateDate = object.toString();
                break;
            case UserInfoParamIndex.mModifyDateIndex:
                mModifyDate = object.toString();
                break;
            case UserInfoParamIndex.mLoginDateIndex:
                mLoginDate = object.toString();
                break;
            case UserInfoParamIndex.mOnlineIndex:
                mOnline = Integer.getInteger(object.toString());
                break;
            case UserInfoParamIndex.mLogoutDateIndex:
                mLogoutDate = object.toString();
                break;
            case UserInfoParamIndex.mSuccessIndex:
                mSuccess = Boolean.getBoolean(object.toString());
                break;
            case UserInfoParamIndex.mErrorStringIndex:
                mErrorString = object.toString();
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable hashtable, PropertyInfo propertyinfo) {
        switch (index)
        {
            case UserInfoParamIndex.mIdIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mId");
                break;
            case UserInfoParamIndex.mUserNameIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mUserName");
                break;
            case UserInfoParamIndex.mPasswordIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mPassword");
                break;
            case UserInfoParamIndex.mNickNameIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mNickName");
                break;
            case UserInfoParamIndex.mTelNumberIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mTelNumber");
                break;
            case UserInfoParamIndex.mEMailIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mEMail");
                break;
            case UserInfoParamIndex.mVipLevelIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mVipLevel");
                break;
            case UserInfoParamIndex.mUserTypeIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mUserType");
                break;
            case UserInfoParamIndex.mCreateDateIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mCreateDate");
                break;
            case UserInfoParamIndex.mModifyDateIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mModifyDate");
                break;
            case UserInfoParamIndex.mLoginDateIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mLoginDate");
                break;
            case UserInfoParamIndex.mOnlineIndex:
                propertyinfo.setType(PropertyInfo.INTEGER_CLASS);
                propertyinfo.setName("mOnline");
                break;
            case UserInfoParamIndex.mLogoutDateIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mLogoutDate");
                break;
            case UserInfoParamIndex.mSuccessIndex:
                propertyinfo.setType(PropertyInfo.BOOLEAN_CLASS);
                propertyinfo.setName("mSuccess");
                break;
            case UserInfoParamIndex.mErrorStringIndex:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("mErrorString");
                break;
            default:
                break;
        }
    }

    /**
     * Created by GuHeng on 2016/10/25.
     * 用户信息类(UserInfo)变量位置索引
     */
    public class UserInfoParamIndex {
        public static final int mIdIndex = 0;
        public static final int mUserNameIndex = mIdIndex + 1;
        public static final int mPasswordIndex = mUserNameIndex + 1;
        public static final int mNickNameIndex = mPasswordIndex + 1;
        public static final int mTelNumberIndex = mNickNameIndex + 1;
        public static final int mEMailIndex = mTelNumberIndex + 1;
        public static final int mVipLevelIndex = mEMailIndex + 1;
        public static final int mUserTypeIndex = mVipLevelIndex + 1;
        public static final int mCreateDateIndex = mUserTypeIndex + 1;
        public static final int mModifyDateIndex = mCreateDateIndex + 1;
        public static final int mLoginDateIndex = mModifyDateIndex + 1;
        public static final int mOnlineIndex = mLoginDateIndex + 1;
        public static final int mLogoutDateIndex = mOnlineIndex + 1;
        public static final int mSuccessIndex = mLogoutDateIndex + 1;
        public static final int mErrorStringIndex = mSuccessIndex + 1;

        public static final int mStringCount = mErrorStringIndex + 1; // 变量的总个数
    }
}

