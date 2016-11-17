package com.gh.emap.listener;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.manager.LogManager;
import com.gh.emap.manager.UserManager;
import com.gh.emap.manager.WebServiceManager;
import com.gh.emap.model.UserInfo;
import com.tianditu.maps.Utils.MD5;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class UserLoginListener implements View.OnClickListener {
    private Context mContext;
    private UserInfo mUserInfo; // 用户信息
    private Handler mHandler; // 消息句柄
    private UserInfo mReturnUserInfo; // 返回的用户信息

    public UserLoginListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.register_button: // 注册
                onClickedRegister();
                break;
            case R.id.login_button: // 登录
                onClickedLogin();
                break;
            case R.id.login_cancel_button: // 关闭
                onClickedCancel();
                break;
            default:
                break;
        }
    }

    // 注册
    private void onClickedRegister() {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserRegisterLayout().show();
    }

    // 登录
    private void onClickedLogin() {
        String userName = ((EditText) (((MainActivity) this.mContext).getMainManager().getLayoutManager().getUserLoginLayout().getAlertDialog().findViewById(R.id.login_name))).getText().toString();
        String password = ((EditText) (((MainActivity) this.mContext).getMainManager().getLayoutManager().getUserLoginLayout().getAlertDialog().findViewById(R.id.login_pwd))).getText().toString();
        boolean isKeepPassword = ((CheckBox) (((MainActivity) this.mContext).getMainManager().getLayoutManager().getUserLoginLayout().getAlertDialog().findViewById(R.id.keep_pwd))).isChecked();
        boolean isAutoLogin = ((CheckBox) (((MainActivity) this.mContext).getMainManager().getLayoutManager().getUserLoginLayout().getAlertDialog().findViewById(R.id.auto_login))).isChecked();

        if (userName.isEmpty()) {
            ((MainActivity) this.mContext).getMainManager().getLogManager().show("错误:用户名不能为空!");
            return;
        }

        if (password.isEmpty()) {
            ((MainActivity) this.mContext).getMainManager().getLogManager().show("错误:密码不能为空!");
            return;
        }

        mUserInfo = new UserInfo();
        mUserInfo.setUserName(userName);
        mUserInfo.setPassword(MD5.getMD5(password));
        mUserInfo.setLoginDate(((MainActivity)this.mContext).getCurrentDate());
        mUserInfo.setOnline(1);

        ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
                String.format("用户登录：%s", mUserInfo.getUserName()));

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if (message.what == WebServiceManager.WebServiceMsgType.WS_MSG_LOGIN) {
                    mReturnUserInfo = (UserInfo) message.obj;

                    if (mReturnUserInfo.isSuccess()) {
                        ((MainActivity) mContext).getMainManager().getLogManager().show(String.format("登录成功！"));
                        onClickedCancel(); // 关闭登录窗口

                        ((MainActivity) mContext).getMainManager().getUserManager().setUserInfo(mReturnUserInfo);

                        Button loginButton = ((Button) (((MainActivity) mContext).findViewById(R.id.user_login_button)));
                        if (UserManager.UserType.mNormalType.equals(mReturnUserInfo.getUserType())) {
                            loginButton.setBackgroundResource(R.mipmap.online_bg);
                        } else if (UserManager.UserType.mAdminType.equals(mReturnUserInfo.getUserType())) {
                            loginButton.setBackgroundResource(R.mipmap.online1_bg);
                        } else {

                        }
                    } else {
                        // 登录失败
                        ((MainActivity) mContext).getMainManager().getLogManager().show(mReturnUserInfo.getErrorString());
                        ((MainActivity) mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                                mReturnUserInfo.getErrorString());
                    }
                }
            }
        };

        ((MainActivity) mContext).getMainManager().getWebServiceManager().userLoginWebService(mHandler, mUserInfo);
    }

    // 取消
    private void onClickedCancel() {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserLoginLayout().hide();
    }
}
