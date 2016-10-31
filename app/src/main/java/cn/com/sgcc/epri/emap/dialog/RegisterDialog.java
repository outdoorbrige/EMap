package cn.com.sgcc.epri.emap.dialog;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.RegisterListener;
import cn.com.sgcc.epri.emap.util.BaseAlertDialog;

/**
 * Created by GuHeng on 2016/10/11.
 * 用户注册对话框
 */
public class RegisterDialog extends BaseAlertDialog {
    private View mLayout; // 布局
    private EditText mUserName; // 用户名
    private EditText mPassword; // 密码
    private EditText mConfirmPassword; // 密码确认
    private EditText mNickName; // 昵称
    private EditText mTelNumber; // 电话号码
    private EditText mEMail; // 邮箱
    private Button mRegisterButton; // 注册按钮
    private Button mReturnButton; // 返回按钮

    // 构造函数
    public RegisterDialog(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        super.init(R.layout.register, (ViewGroup) mMainActivity.findViewById(R.id.register), false, false);
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
            mLayout = getAlertDialog().findViewById(R.id.register);
            mUserName = (EditText) getAlertDialog().findViewById(R.id.register_name);
            mPassword = (EditText) getAlertDialog().findViewById(R.id.register_pwd);
            mConfirmPassword = (EditText) getAlertDialog().findViewById(R.id.register_confirm_pwd);
            mNickName = (EditText) getAlertDialog().findViewById(R.id.nick_name);
            mTelNumber = (EditText) getAlertDialog().findViewById(R.id.tel_number);
            mEMail = (EditText) getAlertDialog().findViewById(R.id.email);
            mRegisterButton = (Button) getAlertDialog().findViewById(R.id.register_user_button);
            mReturnButton = (Button) getAlertDialog().findViewById(R.id.register_cancel_button);

            RegisterListener listener = new RegisterListener(mMainActivity);

            mRegisterButton.setOnClickListener(listener);
            mReturnButton.setOnClickListener(listener);
        } else { // 清空所有控件的内容
            mUserName.setText("");
            mPassword.setText("");
            mConfirmPassword.setText("");
            mNickName.setText("");
            mTelNumber.setText("");
            mEMail.setText("");
        }
    }
}
