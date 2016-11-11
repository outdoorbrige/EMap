package com.gh.emap.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.listener.UserLoginListener;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class UserLoginLayout {
    private Context mContext;
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


    public UserLoginLayout(Context context) {
        this.mContext = context;
    }

    public void init() {
        View layout = ((MainActivity)this.mContext).getLayoutInflater().inflate(R.layout.user_login, null, false);
        this.mAlertDialog = new AlertDialog.Builder(this.mContext).create();
        this.mAlertDialog.setView(layout);
        this.mAlertDialog.setCancelable(false); // 点击对话框外地方是否不消失
    }

    // 显示对话框
    public void show() {
        this.mAlertDialog.show();

        // AlertDialog自定义setView
        // 必须show对话框，然后才能查找控件
        initWidget();
    }

    // 初始化控件
    private void initWidget() {
        if(this.mLayout == null) { // 只初始化一次控件对象
            this.mLayout = ((MainActivity)this.mContext).findViewById(R.id.user_login);
            this.mUserName = (EditText) this.mAlertDialog.findViewById(R.id.login_name);
            this.mPassword = (EditText) this.mAlertDialog.findViewById(R.id.login_pwd);
            this.mKeepPassword = (CheckBox) this.mAlertDialog.findViewById(R.id.keep_pwd);
            this.mAutoLogin = (CheckBox) this.mAlertDialog.findViewById(R.id.auto_login);
            this.mRegisterButton = (Button) this.mAlertDialog.findViewById(R.id.register_button);
            this.mLoginButton = (Button) this.mAlertDialog.findViewById(R.id.login_button);
            this.mCloseButton = (Button) this.mAlertDialog.findViewById(R.id.login_cancel_button);
            this.mForgetPassword = (TextView) this.mAlertDialog.findViewById(R.id.forget_pwd);

            this.mRegisterButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getUserLoginListener());
            this.mLoginButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getUserLoginListener());
            this.mCloseButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getUserLoginListener());
        } else { // 清空所有控件的内容
            this.mUserName.setText("");
            this.mPassword.setText("");
            this.mKeepPassword.setChecked(false);
            this.mAutoLogin.setChecked(false);
        }
    }

    // 隐藏对话框
    public void hide() {
        this.mAlertDialog.hide();
    }

    // 销毁对话框
    public void dimiss() {
        this.mAlertDialog.dismiss();
    }

    // 获取对话框句柄
    public AlertDialog getAlertDialog() {
        return this.mAlertDialog;
    }
}
