package cn.com.sgcc.epri.emap.util;

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
    public static final int mSuccessIndex = mLoginDateIndex + 1;
    public static final int mErrorStringIndex = mSuccessIndex + 1;

    // 返回变量的个数
    public static int count() {
        return mErrorStringIndex + 1;
    }
}
