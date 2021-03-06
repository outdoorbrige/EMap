package com.gh.emap.listenerA;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.managerA.UserManager;
import com.gh.emap.managerA.WebServiceManager;
import com.gh.emap.modelA.UserInfo;
import com.tianditu.maps.Utils.MD5;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class UserLoginListener implements View.OnClickListener {
    private MainActivity mMainActivity;
    private UserInfo mUserInfo; // 用户信息
    private Handler mHandler; // 消息句柄
    private UserInfo mReturnUserInfo; // 返回的用户信息

    public UserLoginListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.register_button: // 注册
                onClickedRegister(view);
                break;
            case R.id.login_button: // 登录
                onClickedLogin(view);
                break;
            case R.id.login_cancel_button: // 关闭
                onClickedCancel(view);
                break;
            default:
                break;
        }
    }

    // 注册
    private void onClickedRegister(View view) {
        mMainActivity.getMainManager().getLayoutManager().getUserRegisterLayout().show();
    }

    // 登录
    private void onClickedLogin(final View view) {
        String userName = ((EditText)(mMainActivity.getMainManager().getLayoutManager().getUserLoginLayout().getAlertDialog().findViewById(R.id.login_name))).getText().toString();
        String password = ((EditText)(mMainActivity.getMainManager().getLayoutManager().getUserLoginLayout().getAlertDialog().findViewById(R.id.login_pwd))).getText().toString();
        boolean isKeepPassword = ((CheckBox)(mMainActivity.getMainManager().getLayoutManager().getUserLoginLayout().getAlertDialog().findViewById(R.id.keep_pwd))).isChecked();
        boolean isAutoLogin = ((CheckBox)(mMainActivity.getMainManager().getLayoutManager().getUserLoginLayout().getAlertDialog().findViewById(R.id.auto_login))).isChecked();

        if (userName.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort("错误:用户名不能为空!");
            return;
        }

        if (password.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort("错误:密码不能为空!");
            return;
        }

        mUserInfo = new UserInfo();
        mUserInfo.setUserName(userName);
        mUserInfo.setPassword(MD5.getMD5(password));
        mUserInfo.setLoginDate(mMainActivity.getCurrentDateF2());
        mUserInfo.setOnline(1);

        mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo,
                String.format("用户登录：%s", mUserInfo.getUserName()));

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if (message.what == WebServiceManager.WebServiceMsgType.WS_MSG_LOGIN) {
                    mReturnUserInfo = (UserInfo) message.obj;

                    if (mReturnUserInfo.isSuccess()) {
                        mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("登录成功！"));
                        onClickedCancel(view); // 关闭登录窗口

                        mMainActivity.getMainManager().getUserManager().setUserInfo(mReturnUserInfo);

                        Button loginButton = ((Button) (mMainActivity.findViewById(R.id.user_login_button)));
                        if (UserManager.UserType.mNormalType.equals(mReturnUserInfo.getUserType())) {
                            loginButton.setBackgroundResource(R.mipmap.online_bg);
                        } else if (UserManager.UserType.mAdminType.equals(mReturnUserInfo.getUserType())) {
                            loginButton.setBackgroundResource(R.mipmap.online1_bg);
                        } else {

                        }
                    } else {
                        // 登录失败
                        mMainActivity.getMainManager().getLogManager().toastShowShort(mReturnUserInfo.getErrorString());
                        mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError,
                                mReturnUserInfo.getErrorString());
                    }
                }
            }
        };

        mMainActivity.getMainManager().getWebServiceManager().userLoginWebService(mHandler, mUserInfo);
    }

    // 取消
    private void onClickedCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getUserLoginLayout().hide();
    }
}
