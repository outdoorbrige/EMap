package cn.com.sgcc.epri.emap.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

import cn.com.sgcc.epri.emap.util.UserInfoParamIndex;

/**
 * Created by GuHeng on 2016/10/14.
 * 用户信息
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
                    boolean success,
                    String errorString) {
        this.mId = id;
        this.mUserName = userName;
        this.mPassword = password;
        this.mNickName = nickName;
        this.mTelNumber = telNumber;
        this.mEMail = eMail;
        this.mVipLevel = vipLevel;
        this.mUserType = userType;
        this.mCreateDate = createDate;
        this.mModifyDate = modifyDate;
        this.mLoginDate = loginDate;
        this.mSuccess = success;
        this.mErrorString = errorString;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getNickName() {
        return mNickName;
    }

    public void setNickName(String nickName) {
        this.mNickName = nickName;
    }

    public String getTelNumber() {
        return mTelNumber;
    }

    public void setTelNumber(String telNumber) {
        this.mTelNumber = telNumber;
    }

    public String getEMail() {
        return mEMail;
    }

    public void setEMail(String eMail) {
        this.mEMail = eMail;
    }

    public String getVipLevel() {
        return mVipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.mVipLevel = vipLevel;
    }

    public String getUserType() {
        return mUserType;
    }

    public void setUserType(String userType) {
        this.mUserType = userType;
    }

    public String getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(String createDate) {
        this.mCreateDate = createDate;
    }

    public String getModifyDate() {
        return mModifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.mModifyDate = modifyDate;
    }

    public String getLoginDate() {
        return mLoginDate;
    }

    public void setLoginDate(String loginDate) {
        this.mLoginDate = loginDate;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public void setSuccess(boolean success) {
        this.mSuccess = success;
    }

    public String getErrorString() {
        return mErrorString;
    }

    public void setErrorString(String errorString) {
        this.mErrorString = errorString;
    }

    public String toString() {
        return String.format(
                "Id:%s, " +
                "UserName:%s, " +
                "Password:%s, " +
                "NickName:%s, " +
                "TelNumber:%s, " +
                "EMail:%s, " +
                "VipLevel:%s, " +
                "UserType:%s, " +
                "CreateDate:%s, " +
                "ModifyDate:%s, " +
                "LoginDate:%s, " +
                "Success:%s, " +
                "ErrorString:%s",

                mId,
                mUserName,
                mPassword,
                mNickName,
                mTelNumber,
                mEMail,
                mVipLevel,
                mUserType,
                mCreateDate,
                mModifyDate,
                mLoginDate,
                mSuccess,
                mErrorString);
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

        return UserInfoParamIndex.count();
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
}
