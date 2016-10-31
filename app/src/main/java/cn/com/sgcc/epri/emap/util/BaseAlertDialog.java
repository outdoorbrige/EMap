package cn.com.sgcc.epri.emap.util;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/26.
 * 弹出式对话框基类
 */
public class BaseAlertDialog extends MainActivityContext {
    private AlertDialog mAlertDialog; // 注册对话框

    // 构造函数
    protected BaseAlertDialog(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    protected void init(int resource, ViewGroup root, boolean attachToRoot, boolean flag) {
        LayoutInflater inflater = mMainActivity.getLayoutInflater();
        View layout = inflater.inflate(resource, root, attachToRoot);
        mAlertDialog = new AlertDialog.Builder(mMainActivity).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(flag); // 点击对话框外地方是否不消失
    }

    // 显示对话框
    protected void show() {
        mAlertDialog.show();
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
