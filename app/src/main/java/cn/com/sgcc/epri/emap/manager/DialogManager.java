package cn.com.sgcc.epri.emap.manager;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.dialog.LoginDialog;
import cn.com.sgcc.epri.emap.dialog.LogoutDialog;
import cn.com.sgcc.epri.emap.dialog.RegisterDialog;
import cn.com.sgcc.epri.emap.base.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/11.
 * 对话框管理类
 */
public class DialogManager extends MainActivityContext {
    private LoginDialog mLoginDialog; // 登录对话框
    private RegisterDialog mRegisterDialog; // 注册对话框
    private LogoutDialog mLogoutDialog; // 注销对话框

    // 构造函数
    public DialogManager(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mLoginDialog = new LoginDialog(mMainActivity);
        mLoginDialog.init();

        mRegisterDialog = new RegisterDialog(mMainActivity);
        mRegisterDialog.init();

        mLogoutDialog = new LogoutDialog(mMainActivity);
        mLogoutDialog.init();
    }

    // 反初始化
    public void unInit() {
        mLoginDialog.dimiss();
        mRegisterDialog.dimiss();
        mLogoutDialog.dimiss();
    }

    // 获取登录对话框句柄
    public LoginDialog getLoginDialog() {
        return mLoginDialog;
    }

    // 获取注册对话框句柄
    public RegisterDialog getRegisterDialog() {
        return mRegisterDialog;
    }

    // 获取注销对话框句柄
    public LogoutDialog getLogoutDialog() {
        return mLogoutDialog;
    }
}
