package cn.com.sgcc.epri.emap.manager;

import android.app.AlertDialog;

import org.apache.log4j.chainsaw.Main;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.dialog.LoginDialog;
import cn.com.sgcc.epri.emap.dialog.RegisterDialog;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/11.
 * 对话框管理类
 */
public class DialogMgr extends TransmitContext {
    private LoginDialog login_dlg; // 登录对话框
    private RegisterDialog register_dlg; // 注册对话框

    // 构造函数
    public DialogMgr(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        login_dlg = new LoginDialog(context);
        login_dlg.init();

        register_dlg = new RegisterDialog(context);
        register_dlg.init();
    }

    // 反初始化
    public void uninit() {
        login_dlg.dimiss();
        register_dlg.dimiss();
    }

    // 获取登录对话框句柄
    public LoginDialog getLoginDlg() {
        return login_dlg;
    }

    // 获取注册对话框句柄
    public RegisterDialog getRegisterDlg() {
        return register_dlg;
    }
}
