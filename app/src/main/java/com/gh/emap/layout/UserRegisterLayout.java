package com.gh.emap.layout;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2016/11/10.
 * 用户注册布局
 */
public class UserRegisterLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private AlertDialog mAlertDialog; // 弹出式对话框
    private EditText mUserName; // 用户名
    private EditText mPassword; // 密码
    private EditText mConfirmPassword; // 密码确认
    private EditText mNickName; // 昵称
    private EditText mTelNumber; // 电话号码
    private EditText mEMail; // 邮箱
    private Button mRegisterButton; // 注册按钮
    private Button mReturnButton; // 返回按钮

    public UserRegisterLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    // 初始化
    public void init() {
        View layout = mMainActivity.getLayoutInflater().inflate(R.layout.user_register, null, false);

        mLayout = layout.findViewById(R.id.user_register);
        mUserName = (EditText) layout.findViewById(R.id.register_name);
        mPassword = (EditText) layout.findViewById(R.id.register_pwd);
        mConfirmPassword = (EditText) layout.findViewById(R.id.register_confirm_pwd);
        mNickName = (EditText) layout.findViewById(R.id.nick_name);
        mTelNumber = (EditText) layout.findViewById(R.id.tel_number);
        mEMail = (EditText) layout.findViewById(R.id.email);
        mRegisterButton = (Button) layout.findViewById(R.id.register_user_button);
        mReturnButton = (Button) layout.findViewById(R.id.register_cancel_button);

        mRegisterButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserRegisterListener());
        mReturnButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserRegisterListener());

        mAlertDialog = new AlertDialog.Builder(mMainActivity).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(false); // 点击对话框外地方是否不消失
    }

    // 清空旧数据
    private void clear() {
        mUserName.setText("");
        mPassword.setText("");
        mConfirmPassword.setText("");
        mNickName.setText("");
        mTelNumber.setText("");
        mEMail.setText("");
    }

    // 显示对话框
    public void show() {
        mAlertDialog.show();

        clear();
    }

    // 隐藏对话框
    public void hide() {
        mAlertDialog.hide();
    }

    // 销毁对话框
    public void dimiss() {
        mAlertDialog.dismiss();
    }

    // 获取对话框句柄
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }
}
