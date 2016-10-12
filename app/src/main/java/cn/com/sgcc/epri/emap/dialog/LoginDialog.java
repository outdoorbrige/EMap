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
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/11.
 * 用户登录对话框
 */
public class LoginDialog extends TransmitContext {
    private AlertDialog login_dlg; // 登录对话框
    private View layout; // 布局
    private EditText user_name; // 用户名
    private EditText password; // 密码
    private CheckBox keep_password; // 记住密码
    private CheckBox auto_login; // 自动登录
    private Button register_btn; // 注册
    private Button login_btn; // 登录
    private Button close_btn; // 关闭
    private TextView forget_password; // 忘记密码

    // 构造函数
    public LoginDialog(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.emap_view_login, (ViewGroup)context.findViewById(R.id.emap_view_login), false);
        login_dlg = new AlertDialog.Builder(context).create();
        login_dlg.setView(layout);
        login_dlg.setCancelable(false); // 点击对话框外地方不消失
    }

    // 显示对话框
    public void show() {
        // AlertDialog自定义setView
        // 必须show对话框，然后才能查找控件
        login_dlg.show();
        initWidget();
    }

    // 初始化控件
    private void initWidget() {
        if(layout == null) { // 只初始化一次控件对象
            layout = login_dlg.findViewById(R.id.emap_view_login);
            user_name = (EditText)login_dlg.findViewById(R.id.emap_view_login_user_text);
            password = (EditText)login_dlg.findViewById(R.id.emap_view_login_password_text);
            keep_password = (CheckBox)login_dlg.findViewById(R.id.emap_view_login_kepp_password);
            auto_login = (CheckBox)login_dlg.findViewById(R.id.emap_view_login_auto_login);
            register_btn = (Button)login_dlg.findViewById(R.id.emap_view_login_register);
            login_btn = (Button)login_dlg.findViewById(R.id.emap_view_login_login);
            close_btn = (Button)login_dlg.findViewById(R.id.emap_view_login_close);
            forget_password = (TextView)login_dlg.findViewById(R.id.emap_view_login_forget_password);

            LoginListener listener = new LoginListener(context);

            register_btn.setOnClickListener(listener);
            login_btn.setOnClickListener(listener);
            close_btn.setOnClickListener(listener);
        } else { // 清空所有控件的内容
            user_name.setText("");
            password.setText("");
            keep_password.setChecked(false);
            auto_login.setChecked(false);
        }
    }

    // 隐藏对话框
    public void hide() {
        login_dlg.hide();
    }

    // 销毁对话框
    public void dimiss() {
        login_dlg.dismiss();
    }

    // 获取对话框句柄
    public AlertDialog getLoginDlg() {
        return login_dlg;
    }
}
