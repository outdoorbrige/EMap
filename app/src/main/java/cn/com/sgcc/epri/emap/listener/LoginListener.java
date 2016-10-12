package cn.com.sgcc.epri.emap.listener;

import android.view.View;

import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/11.
 */
public class LoginListener extends TransmitContext implements View.OnClickListener {

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

    }

    // 关闭
    private void onClickedClose() {
        context.getDlgMgr().getLoginDlg().hide();
    }
}
