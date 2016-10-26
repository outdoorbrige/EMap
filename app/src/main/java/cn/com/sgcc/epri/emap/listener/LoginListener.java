package cn.com.sgcc.epri.emap.listener;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.Algorithm;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.MessageWhat;
import cn.com.sgcc.epri.emap.util.MainActivityContext;
import cn.com.sgcc.epri.emap.util.UserType;

/**
 * Created by GuHeng on 2016/10/11.
 */
public class LoginListener extends MainActivityContext implements View.OnClickListener {
    private UserInfo mUserInfo; // 用户信息
    private Handler mHandler; // 消息句柄
    private UserInfo mReturnUserInfo; // 返回的用户信息

    // 构造函数
    public LoginListener(MainActivity mainActivity) {
        super(mainActivity);
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
        mMainActivity.getDialogManger().getRegisterDialog().show();
    }

    // 登录
    private void onClickedLogin() {
        String userName = ((EditText)(mMainActivity.getDialogManger().getLoginDialog().getAlertDialog().findViewById(R.id.login_name))).getText().toString();
        String password = ((EditText)(mMainActivity.getDialogManger().getLoginDialog().getAlertDialog().findViewById(R.id.login_pwd))).getText().toString();
        boolean isKeepPassword = ((CheckBox)(mMainActivity.getDialogManger().getLoginDialog().getAlertDialog().findViewById(R.id.keep_pwd))).isChecked();
        boolean isAutoLogin = ((CheckBox)(mMainActivity.getDialogManger().getLoginDialog().getAlertDialog().findViewById(R.id.auto_login))).isChecked();

        if(userName.isEmpty()) {
            mMainActivity.getLog4jManger().show("错误:用户名不能为空!");
            return;
        }

        if(password.isEmpty()) {
            mMainActivity.getLog4jManger().show("错误:密码不能为空!");
            return;
        }

        mUserInfo = new UserInfo();
        mUserInfo.setUserName(userName.toUpperCase());
        mUserInfo.setPassword(Algorithm.md5(password));

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if(message.what == MessageWhat.MSG_LOGIN) {
                    mReturnUserInfo = (UserInfo) message.obj;

                    if(mReturnUserInfo.isSuccess()) {
                        mMainActivity.getLog4jManger().show(String.format("恭喜%s登录成功！", mUserInfo.getUserName()));
                        onClickedCancel(); // 关闭登录窗口

                        mMainActivity.getUserManager().setUserInfo(mUserInfo);

                        Button loginButton = ((Button)(mMainActivity.findViewById(R.id.user_login_button)));
                        if(UserType.mNormalType.equals(mUserInfo.getUserType())) {
                            loginButton.setBackgroundResource(R.mipmap.online_bg);
                        } else if(UserType.mAdminType.equals(mUserInfo.getUserType())) {
                            loginButton.setBackgroundResource(R.mipmap.online1_bg);
                        } else {

                        }
                    } else {
                        // 登录失败
                        mMainActivity.getLog4jManger().show(mReturnUserInfo.getErrorString());
                        mMainActivity.getLog4jManger().log(this.getClass(), Log4jLevel.mError, mReturnUserInfo.getErrorString());
                    }
                }
            }
        };

        mMainActivity.getWebServiceManger().LoginService(mHandler, mUserInfo);
    }

    // 取消
    private void onClickedCancel() {
        mMainActivity.getDialogManger().getLoginDialog().hide();
    }
}
