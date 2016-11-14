package com.gh.emap.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.listener.UserRegisterListener;

/**
 * Created by GuHeng on 2016/11/10.
 * 用户注册布局
 */
public class UserRegisterLayout {
    private Context mContext;
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

    public UserRegisterLayout(Context context) {
        this.mContext = context;
    }

    // 初始化
    public void init() {
        View layout = ((MainActivity)this.mContext).getLayoutInflater().inflate(R.layout.user_register, null, false);
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
            this.mLayout = ((MainActivity)this.mContext).findViewById(R.id.user_register);
            this.mUserName = (EditText) this.mAlertDialog.findViewById(R.id.register_name);
            this.mPassword = (EditText) this.mAlertDialog.findViewById(R.id.register_pwd);
            this.mConfirmPassword = (EditText) this.mAlertDialog.findViewById(R.id.register_confirm_pwd);
            this.mNickName = (EditText) this.mAlertDialog.findViewById(R.id.nick_name);
            this.mTelNumber = (EditText) this.mAlertDialog.findViewById(R.id.tel_number);
            this.mEMail = (EditText) this.mAlertDialog.findViewById(R.id.email);
            this.mRegisterButton = (Button) this.mAlertDialog.findViewById(R.id.register_user_button);
            this.mReturnButton = (Button) this.mAlertDialog.findViewById(R.id.register_cancel_button);

            this.mRegisterButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getUserRegisterListener());
            this.mReturnButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getUserRegisterListener());
        } else { // 清空所有控件的内容
            this.mUserName.setText("");
            this.mPassword.setText("");
            this.mConfirmPassword.setText("");
            this.mNickName.setText("");
            this.mTelNumber.setText("");
            this.mEMail.setText("");
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
