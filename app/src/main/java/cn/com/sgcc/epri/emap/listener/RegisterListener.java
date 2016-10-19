package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.PhoneResources;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/11.
 */
public class RegisterListener extends TransmitContext implements View.OnClickListener {

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

        UserInfo userinfo = new UserInfo();
        userinfo.setUsername(username);
        userinfo.setPassword(password);
        userinfo.setNickname(nickname);
        userinfo.setTelnumber(telnumber);
        userinfo.setEmail(email);
        userinfo.setCreatetime(PhoneResources.getNowTimeString());

        Logger.getLogger(this.getClass()).info(userinfo.toString());

        if(context.getServiceMgr().RegisterService(userinfo)) {
            userinfo.setEmail(String.format("恭喜%d注册成功！", username));
        } else {
            // 注册失败
        }

        Logger.getLogger(this.getClass()).info(userinfo.getEmsg());
        Toast.makeText(context, userinfo.getEmsg(), Toast.LENGTH_SHORT).show();
    }

    // 返回
    private void onClickedReturn() {
        context.getDlgMgr().getRegisterDlg().hide();
    }
}
