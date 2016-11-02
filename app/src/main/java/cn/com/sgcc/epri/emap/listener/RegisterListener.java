package cn.com.sgcc.epri.emap.listener;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.Algorithm;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.MessageWhat;
import cn.com.sgcc.epri.emap.util.PhoneResources;
import cn.com.sgcc.epri.emap.base.MainActivityContext;
import cn.com.sgcc.epri.emap.util.UserType;

/**
 * Created by GuHeng on 2016/10/11.
 */
public class RegisterListener extends MainActivityContext implements View.OnClickListener {
    private UserInfo mUserInfo; // 用户信息
    private Handler mHandler; // 消息句柄
    private UserInfo mReturnUserInfo; // 返回的用户信息

    // 构造函数
    public RegisterListener(MainActivity mainActivity) {
        super(mainActivity);
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
        String userName = ((EditText)(mMainActivity.getDialogManager().getRegisterDialog().getAlertDialog().findViewById(R.id.register_name))).getText().toString();
        String password = ((EditText)(mMainActivity.getDialogManager().getRegisterDialog().getAlertDialog().findViewById(R.id.register_pwd))).getText().toString();
        String mConfirmPassword = ((EditText)(mMainActivity.getDialogManager().getRegisterDialog().getAlertDialog().findViewById(R.id.register_confirm_pwd))).getText().toString();
        String nickName = ((EditText)(mMainActivity.getDialogManager().getRegisterDialog().getAlertDialog().findViewById(R.id.nick_name))).getText().toString();
        String telNumber = ((EditText)(mMainActivity.getDialogManager().getRegisterDialog().getAlertDialog().findViewById(R.id.tel_number))).getText().toString();
        String eMail = ((EditText)(mMainActivity.getDialogManager().getRegisterDialog().getAlertDialog().findViewById(R.id.email))).getText().toString();

        if(userName.isEmpty()) {
            mMainActivity.getLog4jManager().show("用户名不能为空!");
            return;
        }

        if(password.isEmpty()) {
            mMainActivity.getLog4jManager().show("密码不能为空!");
            return;
        }

        if(!password.equals(mConfirmPassword)) {
            mMainActivity.getLog4jManager().show("两次输入的密码不一致，请重新输入!");
            return;
        }

        mUserInfo = new UserInfo();
        mUserInfo.setUserName(userName.toUpperCase());
        mUserInfo.setPassword(Algorithm.md5(password));
        mUserInfo.setNickName(nickName);
        mUserInfo.setTelNumber(telNumber);
        mUserInfo.setEMail(eMail);
        mUserInfo.setUserType(UserType.mNormalType);
        mUserInfo.setCreateDate(PhoneResources.getCurrentDate());

        if(nickName.isEmpty()) {
            mUserInfo.setNickName(mUserInfo.getUserName());
        }

        mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mDebug, mUserInfo.toString());

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if(message.what == MessageWhat.MSG_REGISTER) {
                    mReturnUserInfo = (UserInfo) message.obj;

                    if(mReturnUserInfo.isSuccess()) {
                        mMainActivity.getLog4jManager().show("注册成功！");
                        onClickedCancel(); // 关闭注册窗口
                    } else {
                        // 注册失败
                        mMainActivity.getLog4jManager().show(mReturnUserInfo.getErrorString());
                        mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError, mReturnUserInfo.getErrorString());
                    }
                }
            }
        };

        mMainActivity.getWebServiceManager().RegisterService(mHandler, mUserInfo);
    }

    // 取消
    private void onClickedCancel() {
        mMainActivity.getDialogManager().getRegisterDialog().hide();
    }
}
