package cn.com.sgcc.epri.emap.listener;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.Algorithm;
import cn.com.sgcc.epri.emap.util.MessageWhat;
import cn.com.sgcc.epri.emap.util.PhoneResources;
import cn.com.sgcc.epri.emap.util.MainActivityContext;
import cn.com.sgcc.epri.emap.util.UserType;

/**
 * Created by GuHeng on 2016/10/11.
 */
public class RegisterListener extends MainActivityContext implements View.OnClickListener {
    private UserInfo mUserInfo; // 用户信息
    private Handler mHandler; // 消息句柄
    private UserInfo mReturnUserInfo; // 返回的用户信息

    // 构造函数
    public RegisterListener(MainActivity context) {
        super(context);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.register_user_button:
                onClickedRegister();
                break;
            case R.id.register_cancel_button:
                onClickedReturn();
                break;
            default:
                break;
        }
    }

    // 注册
    private void onClickedRegister() {
        String userName = ((EditText)(context.getDialogManger().getRegisterDialog().getAlertDialog().findViewById(R.id.register_name))).getText().toString();
        String password = ((EditText)(context.getDialogManger().getRegisterDialog().getAlertDialog().findViewById(R.id.register_pwd))).getText().toString();
        String mConfirmPassword = ((EditText)(context.getDialogManger().getRegisterDialog().getAlertDialog().findViewById(R.id.register_confirm_pwd))).getText().toString();
        String nickName = ((EditText)(context.getDialogManger().getRegisterDialog().getAlertDialog().findViewById(R.id.nick_name))).getText().toString();
        String telNumber = ((EditText)(context.getDialogManger().getRegisterDialog().getAlertDialog().findViewById(R.id.tel_number))).getText().toString();
        String eMail = ((EditText)(context.getDialogManger().getRegisterDialog().getAlertDialog().findViewById(R.id.email))).getText().toString();

        if(userName.isEmpty()) {
            Toast.makeText(context, "错误:用户名不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.isEmpty()) {
            Toast.makeText(context, "错误:密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!password.equals(mConfirmPassword)) {
            Toast.makeText(context, "错误:两次输入的密码不一致，请重新输入!", Toast.LENGTH_SHORT).show();
            return;
        }

        mUserInfo = new UserInfo();
        mUserInfo.setUserName(userName.toUpperCase());
        mUserInfo.setPassword(Algorithm.md5(password));
        mUserInfo.setNickName(nickName);
        mUserInfo.setTelNumber(telNumber);
        mUserInfo.setEMail(eMail);
        mUserInfo.setUserType(UserType.mNormalType);
        mUserInfo.setCreateDate(PhoneResources.getNowTimeString());

        Logger.getLogger(this.getClass()).info(Algorithm.md5("admin"));

        if(nickName.isEmpty()) {
            mUserInfo.setNickName(mUserInfo.getUserName());
        }

        Logger.getLogger(this.getClass()).info(mUserInfo.toString());

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if(message.what == MessageWhat.MSG_REGISTER) {
                    mReturnUserInfo = (UserInfo) message.obj;

                    if(mReturnUserInfo.isSuccess()) {
                        Toast.makeText(context, String.format("恭喜%s注册成功！", mUserInfo.getUserName()), Toast.LENGTH_SHORT).show();
                        onClickedReturn(); // 关闭注册窗口
                    } else {
                        // 注册失败
                        Toast.makeText(context, mReturnUserInfo.getErrorString(), Toast.LENGTH_SHORT).show();
                        Logger.getLogger(this.getClass()).info(mReturnUserInfo.getErrorString());
                    }
                }
            }
        };

        context.getWebServiceManger().RegisterService(mHandler, mUserInfo);
    }

    // 返回
    private void onClickedReturn() {
        context.getDialogManger().getRegisterDialog().hide();
    }
}
