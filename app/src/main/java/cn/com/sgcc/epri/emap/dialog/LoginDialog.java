package cn.com.sgcc.epri.emap.dialog;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.LoginListener;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/11.
 * 用户登录对话框
 */
public class LoginDialog extends MainActivityContext {
    private AlertDialog mAlertDialog; // 登录对话框
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
    public LoginDialog(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.login, (ViewGroup)context.findViewById(R.id.login), false);
        mAlertDialog = new AlertDialog.Builder(context).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(false); // 点击对话框外地方不消失
    }

    // 显示对话框
    public void show() {
        // AlertDialog自定义setView
        // 必须show对话框，然后才能查找控件
        mAlertDialog.show();
        initWidget();
    }

    // 初始化控件
    private void initWidget() {
        if(mLayout == null) { // 只初始化一次控件对象
            mLayout = mAlertDialog.findViewById(R.id.login);
            mUserName = (EditText) mAlertDialog.findViewById(R.id.login_name);
            mPassword = (EditText) mAlertDialog.findViewById(R.id.login_pwd);
            mKeepPassword = (CheckBox) mAlertDialog.findViewById(R.id.keep_pwd);
            mAutoLogin = (CheckBox) mAlertDialog.findViewById(R.id.auto_login);
            mRegisterButton = (Button) mAlertDialog.findViewById(R.id.register_button);
            mLoginButton = (Button) mAlertDialog.findViewById(R.id.login_button);
            mCloseButton = (Button) mAlertDialog.findViewById(R.id.login_cancel_button);
            mForgetPassword = (TextView) mAlertDialog.findViewById(R.id.forget_pwd);

            LoginListener listener = new LoginListener(context);

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
