package com.gh.emap.listener;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.manager.LogManager;
import com.gh.emap.manager.UserManager;
import com.gh.emap.model.UserInfo;
import com.gh.emap.webservice.UserRegisterWebService;
import com.tianditu.maps.Utils.MD5;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class UserRegisterListener implements View.OnClickListener {
    private Context mContext;
    private UserInfo mUserInfo; // 用户信息
    private Handler mHandler; // 消息句柄
    private UserInfo mReturnUserInfo; // 返回的用户信息

    // 构造函数
    public UserRegisterListener(Context context) {
        this.mContext = context;
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
        String userName = ((EditText)((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.register_name)).getText().toString();
        String password = ((EditText)((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.register_pwd)).getText().toString();
        String mConfirmPassword = ((EditText)((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.register_confirm_pwd)).getText().toString();
        String nickName = ((EditText)((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.nick_name)).getText().toString();
        String telNumber = ((EditText)((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.tel_number)).getText().toString();
        String eMail = ((EditText)((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserRegisterLayout().getAlertDialog().findViewById(R.id.email)).getText().toString();

        if(userName.isEmpty()) {
            ((MainActivity)this.mContext).getMainManager().getLogManager().show("用户名不能为空!");
            return;
        }

        if(password.isEmpty()) {
            ((MainActivity)this.mContext).getMainManager().getLogManager().show("密码不能为空!");
            return;
        }

        if(!password.equals(mConfirmPassword)) {
            ((MainActivity)this.mContext).getMainManager().getLogManager().show("两次输入的密码不一致，请重新输入!");
            return;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        mUserInfo = new UserInfo();
        mUserInfo.setUserName(userName.toUpperCase());
        mUserInfo.setPassword(MD5.getMD5(password));
        mUserInfo.setNickName(nickName);
        mUserInfo.setTelNumber(telNumber);
        mUserInfo.setEMail(eMail);
        mUserInfo.setUserType(UserManager.UserType.mNormalType);
        mUserInfo.setCreateDate(simpleDateFormat.format(date));

        if(nickName.isEmpty()) {
            mUserInfo.setNickName(mUserInfo.getUserName());
        }

        ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
                mUserInfo.toString());

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if(message.what == UserRegisterWebService.MSG_REGISTER) {
                    mReturnUserInfo = (UserInfo) message.obj;

                    if(mReturnUserInfo.isSuccess()) {
                        ((MainActivity)mContext).getMainManager().getLogManager().show("注册成功！");
                        onClickedCancel(); // 关闭注册窗口
                    } else {
                        // 注册失败
                        ((MainActivity)mContext).getMainManager().getLogManager().show(mReturnUserInfo.getErrorString());
                        ((MainActivity)mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                                mReturnUserInfo.getErrorString());
                    }
                }
            }
        };

        ((MainActivity)this.mContext).getMainManager().getWebServiceManager().UserRegisterService(mHandler, mUserInfo);
    }

    // 取消
    private void onClickedCancel() {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserRegisterLayout().hide();
    }
}