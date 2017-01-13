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
        mAlertDialog = new AlertDialog.Builder(mMainActivity).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(false); // 点击对话框外地方是否不消失
    }

    // 显示对话框
    public void show() {
        mAlertDialog.show();

        // AlertDialog自定义setView
        // 必须show对话框，然后才能查找控件
        initWidget();
    }

    // 初始化控件
    private void initWidget() {
        if(mLayout == null) { // 只初始化一次控件对象
            mLayout = mMainActivity.findViewById(R.id.user_login);
            mUserName = (EditText) mAlertDialog.findViewById(R.id.login_name);
            mPassword = (EditText) mAlertDialog.findViewById(R.id.login_pwd);
            mKeepPassword = (CheckBox) mAlertDialog.findViewById(R.id.keep_pwd);
            mAutoLogin = (CheckBox) mAlertDialog.findViewById(R.id.auto_login);
            mRegisterButton = (Button) mAlertDialog.findViewById(R.id.register_button);
            mLoginButton = (Button) mAlertDialog.findViewById(R.id.login_button);
            mCloseButton = (Button) mAlertDialog.findViewById(R.id.login_cancel_button);
            mForgetPassword = (TextView) mAlertDialog.findViewById(R.id.forget_pwd);

            mRegisterButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserLoginListener());
            mLoginButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserLoginListener());
            mCloseButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserLoginListener());
        } else { // 清空所有控件的内容
            mUserName.setText("");
            mPassword.setText("");
            mKeepPassword.setChecked(false);
            mAutoLogin.setChecked(false);
        }
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
