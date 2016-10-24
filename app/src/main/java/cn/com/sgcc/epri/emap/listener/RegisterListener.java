package cn.com.sgcc.epri.emap.listener;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.Algorithm;
import cn.com.sgcc.epri.emap.util.MessageWhat;
import cn.com.sgcc.epri.emap.util.PhoneResources;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/11.
 */
public class RegisterListener extends TransmitContext implements View.OnClickListener {
    UserInfo userinfo; // 用户信息
    private Handler handler; // 消息句柄
    private UserInfo return_userinfo; // 返回的用户信息

    // 构造函数
    public RegisterListener(MainActivity context) {
        super(context);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.emap_view_register_register_btn:
                onClickedRegister();
                break;
            case R.id.emap_view_register_return_btn:
                onClickedReturn();
                break;
            default:
                break;
        }
    }

    // 注册
    private void onClickedRegister() {
        String username = ((EditText)(context.getDlgMgr().getRegisterDlg().getDlg().findViewById(R.id.emap_view_register_user_text))).getText().toString();
        String password = ((EditText)(context.getDlgMgr().getRegisterDlg().getDlg().findViewById(R.id.emap_view_register_password_text))).getText().toString();
        String confirm_password = ((EditText)(context.getDlgMgr().getRegisterDlg().getDlg().findViewById(R.id.emap_view_register_confirm_password_text))).getText().toString();
        String nickname = ((EditText)(context.getDlgMgr().getRegisterDlg().getDlg().findViewById(R.id.emap_view_register_nickname_text))).getText().toString();
        String telnumber = ((EditText)(context.getDlgMgr().getRegisterDlg().getDlg().findViewById(R.id.emap_view_register_telnumber_text))).getText().toString();
        String email = ((EditText)(context.getDlgMgr().getRegisterDlg().getDlg().findViewById(R.id.emap_view_register_email_text))).getText().toString();

        if(username.isEmpty()) {
            Toast.makeText(context, "错误:用户名不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.isEmpty()) {
            Toast.makeText(context, "错误:密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!password.equals(confirm_password)) {
            Toast.makeText(context, "错误:两次输入的密码不一致，请重新输入!", Toast.LENGTH_SHORT).show();
            return;
        }

        userinfo = new UserInfo();
        userinfo.setUsername(username.toUpperCase());
        userinfo.setPassword(Algorithm.md5(password));
        userinfo.setNickname(nickname);
        userinfo.setTelnumber(telnumber);
        userinfo.setEmail(email);
        userinfo.setCreatetime(PhoneResources.getNowTimeString());

        if(nickname.isEmpty()) {
            userinfo.setNickname(userinfo.getUsername());
        }

        Logger.getLogger(this.getClass()).info(userinfo.toString());

        handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if(message.what == MessageWhat.MSG_REGISTER) {
                    return_userinfo = (UserInfo) message.obj;

                    if(return_userinfo.isSuccessed()) {
                        Toast.makeText(context, String.format("恭喜%s注册成功！", userinfo.getUsername()), Toast.LENGTH_SHORT).show();
                        onClickedReturn(); // 关闭注册窗口
                    } else {
                        // 注册失败
                        Toast.makeText(context, return_userinfo.getEmsg(), Toast.LENGTH_SHORT).show();
                        Logger.getLogger(this.getClass()).info(return_userinfo.getEmsg());
                    }
                }
            }
        };

        context.getServiceMgr().RegisterService(handler, userinfo);
    }

    // 返回
    private void onClickedReturn() {
        context.getDlgMgr().getRegisterDlg().hide();
    }
}
