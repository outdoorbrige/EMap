package com.gh.emap.layout;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2016/11/10.
 * 用户登录布局
 */
public class UserLoginLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private AlertDialog mAlertDialog; // 弹出式对话框
    private EditText mUserName; // 用户名
    private EditText mPassword; // 密码
    private CheckBox mKeepPassword; // 记住密码
    private CheckBox mAutoLogin; // 自动登录
    private Button mRegisterButton; // 注册
    private Button mLoginButton; // 登录
    private Button mCloseButton; // 关闭
    private TextView mForgetPassword; // 忘记密码


    public UserLoginLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        View layout = mMainActivity.getLayoutInflater().inflate(R.layout.user_login, null, false);

        mLayout = layout.findViewById(R.id.user_login);
        mUserName = (EditText) layout.findViewById(R.id.login_name);
        mPassword = (EditText) layout.findViewById(R.id.login_pwd);
        mKeepPassword = (CheckBox) layout.findViewById(R.id.keep_pwd);
        mAutoLogin = (CheckBox) layout.findViewById(R.id.auto_login);
        mRegisterButton = (Button) layout.findViewById(R.id.register_button);
        mLoginButton = (Button) layout.findViewById(R.id.login_button);
        mCloseButton = (Button) layout.findViewById(R.id.login_cancel_button);
        mForgetPassword = (TextView) layout.findViewById(R.id.forget_pwd);

        mRegisterButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserLoginListener());
        mLoginButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserLoginListener());
        mCloseButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserLoginListener());

        mAlertDialog = new AlertDialog.Builder(mMainActivity).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(false); // 点击对话框外地方是否不消失
    }

    // 清空旧数据
    private void clear() {
        mUserName.setText("");
        mPassword.setText("");
        mKeepPassword.setChecked(false);
        mAutoLogin.setChecked(false);
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
