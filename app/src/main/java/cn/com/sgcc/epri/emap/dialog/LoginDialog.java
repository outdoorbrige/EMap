package cn.com.sgcc.epri.emap.dialog;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.LoginListener;
import cn.com.sgcc.epri.emap.base.BaseAlertDialog;

/**
 * Created by GuHeng on 2016/10/11.
 * 用户登录对话框
 */
public class LoginDialog extends BaseAlertDialog {
    private View mLayout; // 布局
    private EditText mUserName; // 用户名
    private EditText mPassword; // 密码
    private CheckBox mKeepPassword; // 记住密码
    private CheckBox mAutoLogin; // 自动登录
    private Button mRegisterButton; // 注册
    private Button mLoginButton; // 登录
    private Button mCloseButton; // 关闭
    private TextView mForgetPassword; // 忘记密码

    // 构造函数
    public LoginDialog(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        super.init(R.layout.login, (ViewGroup) mMainActivity.findViewById(R.id.login), false, false);
    }

    // 显示对话框
    public void show() {
        super.show();
        // AlertDialog自定义setView
        // 必须show对话框，然后才能查找控件
        initWidget();
    }

    // 初始化控件
    private void initWidget() {
        if(mLayout == null) { // 只初始化一次控件对象
            mLayout = getAlertDialog().findViewById(R.id.login);
            mUserName = (EditText) getAlertDialog().findViewById(R.id.login_name);
            mPassword = (EditText) getAlertDialog().findViewById(R.id.login_pwd);
            mKeepPassword = (CheckBox) getAlertDialog().findViewById(R.id.keep_pwd);
            mAutoLogin = (CheckBox) getAlertDialog().findViewById(R.id.auto_login);
            mRegisterButton = (Button) getAlertDialog().findViewById(R.id.register_button);
            mLoginButton = (Button) getAlertDialog().findViewById(R.id.login_button);
            mCloseButton = (Button) getAlertDialog().findViewById(R.id.login_cancel_button);
            mForgetPassword = (TextView) getAlertDialog().findViewById(R.id.forget_pwd);

            LoginListener listener = new LoginListener(mMainActivity);

            mRegisterButton.setOnClickListener(listener);
            mLoginButton.setOnClickListener(listener);
            mCloseButton.setOnClickListener(listener);
        } else { // 清空所有控件的内容
            mUserName.setText("");
            mPassword.setText("");
            mKeepPassword.setChecked(false);
            mAutoLogin.setChecked(false);
        }
    }
}
