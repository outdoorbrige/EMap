package cn.com.sgcc.epri.emap.dialog;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/11.
 * 用户注册对话框
 */
public class RegisterDialog extends TransmitContext {
    private AlertDialog register_dlg; // 注册对话框
    private LinearLayout layout; // 布局

    // 构造函数
    public RegisterDialog(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.emap_view_login_register, (ViewGroup)context.findViewById(R.id.emap_view_login_register), false);
        register_dlg = new AlertDialog.Builder(context).create();
        register_dlg.setView(layout);
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
            layout = (LinearLayout) register_dlg.findViewById(R.id.emap_view_login_register);

        } else { // 清空所有控件的内容

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
    public AlertDialog getRegisterDlg() {
        return register_dlg;
    }
}
