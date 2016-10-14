package cn.com.sgcc.epri.emap.dialog;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.RegisterListener;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/11.
 * 用户注册对话框
 */
public class RegisterDialog extends TransmitContext {
    private AlertDialog register_dlg; // 注册对话框
    private View layout; // 布局
    private EditText username; // 用户名
    private EditText password; // 密码
    private EditText confirm_password; // 密码确认
    private EditText nickname; // 昵称
    private EditText telnumber; // 电话号码
    private EditText email; // 邮箱
    private Button register_btn; // 注册按钮
    private Button return_btn; // 返回按钮

    // 构造函数
    public RegisterDialog(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.emap_view_register, (ViewGroup)context.findViewById(R.id.emap_view_register), false);
        register_dlg = new AlertDialog.Builder(context).create();
        register_dlg.setView(layout);
        register_dlg.setCancelable(false); // 点击对话框外地方不消失
    }

    // 显示对话框
    public void show() {
        // AlertDialog自定义setView
        // 必须show对话框，然后才能查找控件
        register_dlg.show();
        initWidget();
    }

    // 初始化控件
    private void initWidget() {
        if(layout == null) { // 只初始化一次控件对象
            layout = register_dlg.findViewById(R.id.emap_view_register);
            username = (EditText)register_dlg.findViewById(R.id.emap_view_register_user_text);
            password = (EditText)register_dlg.findViewById(R.id.emap_view_register_password_text);
            confirm_password = (EditText)register_dlg.findViewById(R.id.emap_view_register_confirm_password_text);
            nickname = (EditText)register_dlg.findViewById(R.id.emap_view_register_nickname_text);
            telnumber = (EditText)register_dlg.findViewById(R.id.emap_view_register_telnumber_text);
            email = (EditText)register_dlg.findViewById(R.id.emap_view_register_email_text);
            register_btn = (Button)register_dlg.findViewById(R.id.emap_view_register_register_btn);
            return_btn = (Button)register_dlg.findViewById(R.id.emap_view_register_return_btn);

            RegisterListener listener = new RegisterListener(context);

            register_btn.setOnClickListener(listener);
            return_btn.setOnClickListener(listener);
        } else { // 清空所有控件的内容
            username.setText("");
            password.setText("");
            confirm_password.setText("");
            nickname.setText("");
            telnumber.setText("");
            email.setText("");
        }
    }

    // 隐藏对话框
    public void hide() {
        register_dlg.hide();
    }

    // 销毁对话框
    public void dimiss() {
        register_dlg.dismiss();
    }

    // 获取对话框句柄
    public AlertDialog getDlg() {
        return register_dlg;
    }
}
