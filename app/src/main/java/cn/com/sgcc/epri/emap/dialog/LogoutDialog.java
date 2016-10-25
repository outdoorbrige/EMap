package cn.com.sgcc.epri.emap.dialog;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/25.
 * 用户注销对话框
 */
public class LogoutDialog extends MainActivityContext {
    private AlertDialog mAlertDialog; // 注销对话框
    private View mLayout; // 布局

    // 构造函数
    public LogoutDialog(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.login, (ViewGroup)context.findViewById(R.id.login), false);
        mAlertDialog = new AlertDialog.Builder(context).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(false); // 点击对话框外地方不消失
    }

    // 显示对话框
    public void show() {
        // AlertDialog自定义setView
        // 必须show对话框，然后才能查找控件
        mAlertDialog.show();
        initWidget();
    }

    // 初始化控件
    private void initWidget() {
        if(mLayout == null) { // 只初始化一次控件对象

        } else { // 清空所有控件的内容

        }
    }

    // 隐藏对话框
    public void hide() {
        mAlertDialog.hide();
    }

    // 销毁对话框
    public void dimiss() {
        mAlertDialog.dismiss();
    }

    // 获取对话框句柄
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }
}
