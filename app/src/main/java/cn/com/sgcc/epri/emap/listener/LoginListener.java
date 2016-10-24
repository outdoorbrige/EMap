package cn.com.sgcc.epri.emap.listener;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.Algorithm;
import cn.com.sgcc.epri.emap.util.MessageWhat;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/11.
 */
public class LoginListener extends TransmitContext implements View.OnClickListener {
    UserInfo userinfo; // 用户信息
    private Handler handler; // 消息句柄
    private UserInfo return_userinfo; // 返回的用户信息

    // 构造函数
    public LoginListener(MainActivity context) {
        super(context);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.emap_view_login_register: // 注册
                onClickedRegister();
                break;
            case R.id.emap_view_login_login: // 登录
                onClickedLogin();
                break;
            case R.id.emap_view_login_close: // 关闭
                onClickedClose();
                break;
            default:
                break;
        }
    }

    // 注册
    private void onClickedRegister() {
        context.getDlgMgr().getRegisterDlg().show();
    }

    // 登录
    private void onClickedLogin() {
        String username = ((EditText)(context.getDlgMgr().getLoginDlg().getDlg().findViewById(R.id.emap_view_login_user_text))).getText().toString();
        String password = ((EditText)(context.getDlgMgr().getLoginDlg().getDlg().findViewById(R.id.emap_view_login_password_text))).getText().toString();
        boolean is_keep_password = ((CheckBox)(context.getDlgMgr().getLoginDlg().getDlg().findViewById(R.id.emap_view_login_kepp_password))).isChecked();
        boolean is_auto_login = ((CheckBox)(context.getDlgMgr().getLoginDlg().getDlg().findViewById(R.id.emap_view_login_auto_login))).isChecked();

        if(username.isEmpty()) {
            Toast.makeText(context, "错误:用户名不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.isEmpty()) {
            Toast.makeText(context, "错误:密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        userinfo = new UserInfo();
        userinfo.setUsername(username.toUpperCase());
        userinfo.setPassword(Algorithm.md5(password));

        handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if(message.what == MessageWhat.MSG_LOGIN) {
                    return_userinfo = (UserInfo) message.obj;

                    if(return_userinfo.isSuccessed()) {
                        Toast.makeText(context, String.format("恭喜%s登录成功！", userinfo.getUsername()), Toast.LENGTH_SHORT).show();
                        onClickedClose(); // 关闭登录窗口
                    } else {
                        // 登录失败
                        Toast.makeText(context, return_userinfo.getEmsg(), Toast.LENGTH_SHORT).show();
                        Logger.getLogger(this.getClass()).info(return_userinfo.getEmsg());
                    }
                }
            }
        };

        context.getServiceMgr().LoginService(handler, userinfo);
    }

    // 关闭
    private void onClickedClose() {
        context.getDlgMgr().getLoginDlg().hide();
    }
}
