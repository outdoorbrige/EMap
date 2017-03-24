package com.gh.emap.listenerA;

import android.os.Handler;
import android.os.Message;
import android.view.View;
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
public class UserRegisterListener implements View.OnClickListener {
    private MainActivity mMainActivity;
    private UserInfo mUserInfo; // 用户信息
    private Handler mHandler; // 消息句柄
    private UserInfo mReturnUserInfo; // 返回的用户信息

    // 构造函数
    public UserRegisterListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.register_user_button:
                onClickedRegister();
                break;
            case R.id.register_cancel_button:
                onClickedCancel();
                break;
            default:
                break;
        }
    }

    // 注册
    private void onClickedRegister() {
        String userName = ((EditText)mMainActivity.getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.register_name)).getText().toString();
        String password = ((EditText)mMainActivity.getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.register_pwd)).getText().toString();
        String mConfirmPassword = ((EditText)mMainActivity.getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.register_confirm_pwd)).getText().toString();
        String nickName = ((EditText)mMainActivity.getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.nick_name)).getText().toString();
        String telNumber = ((EditText)mMainActivity.getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.tel_number)).getText().toString();
        String eMail = ((EditText)mMainActivity.getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.email)).getText().toString();

        if(userName.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort("用户名不能为空!");
            return;
        }

        if(password.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort("密码不能为空!");
            return;
        }

        if(!password.equals(mConfirmPassword)) {
            mMainActivity.getMainManager().getLogManager().toastShowShort("两次输入的密码不一致，请重新输入!");
            return;
        }

        mUserInfo = new UserInfo();
        mUserInfo.setUserName(userName);
        mUserInfo.setPassword(MD5.getMD5(password));
        mUserInfo.setNickName(nickName);
        mUserInfo.setTelNumber(telNumber);
        mUserInfo.setEMail(eMail);
        mUserInfo.setUserType(UserManager.UserType.mNormalType);
        mUserInfo.setCreateDate(mMainActivity.getCurrentDateF2());
        mUserInfo.setOnline(0);

        if(nickName.isEmpty()) {
            mUserInfo.setNickName(mUserInfo.getUserName());
        }

        mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo,
                String.format("用户注册：%s", mUserInfo.getUserName()));

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if(message.what == WebServiceManager.WebServiceMsgType.WS_MSG_REGISTER) {
                    mReturnUserInfo = (UserInfo) message.obj;

                    if(mReturnUserInfo.isSuccess()) {
                        mMainActivity.getMainManager().getLogManager().toastShowShort("注册成功！");
                        onClickedCancel(); // 关闭注册窗口
                    } else {
                        // 注册失败
                        mMainActivity.getMainManager().getLogManager().toastShowShort(mReturnUserInfo.getErrorString());
                        mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError,
                                mReturnUserInfo.getErrorString());
                    }
                }
            }
        };

        mMainActivity.getMainManager().getWebServiceManager().userRegisterWebService(mHandler, mUserInfo);
    }

    // 取消
    private void onClickedCancel() {
        mMainActivity.getMainManager().getLayoutManager().getUserRegisterLayout().hide();
    }
}
