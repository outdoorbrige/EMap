package com.gh.emap.layoutA;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.modelA.UserInfo;

/**
 * Created by GuHeng on 2016/11/11.
 * 用户注销布局
 */
public class UserLogoutLayout {
    private MainActivity mMainActivity;
    private AlertDialog mAlertDialog; // 注册对话框
    private View mLayout; // 布局
    private TextView mUserName; // 用户名
    private TextView mNickName; // 昵称
    private TextView mUserType; // 用户类型
    private Button mLogoutButton; // 注销按钮
    private Button mCancelButton; // 取消按钮

    public UserLogoutLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        View layout = mMainActivity.getLayoutInflater().inflate(R.layout.user_logout, null, false);

        mLayout = layout.findViewById(R.id.user_logout);
        mUserName = (TextView)layout.findViewById(R.id.logout_name);
        mNickName = (TextView)layout.findViewById(R.id.logout_nickname);
        mUserType = (TextView)layout.findViewById(R.id.logout_type);
        mLogoutButton = (Button)layout.findViewById(R.id.logout_button);
        mCancelButton = (Button)layout.findViewById(R.id.logout_cancel_button);

        mLogoutButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserLogoutListener());
        mCancelButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserLogoutListener());

        mAlertDialog = new AlertDialog.Builder(mMainActivity).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(false); // 点击对话框外地方是否不消失
    }

    // 显示对话框
    public void show() {
        mAlertDialog.show();

        UserInfo userInfo = mMainActivity.getMainManager().getUserManager().getUserInfo();
        if(userInfo == null || !userInfo.isSuccess()) { // 用户离线
            hide();
        } else { // 用户在线
            mUserName.setText(userInfo.getUserName());
            mNickName.setText(userInfo.getNickName());
            mUserType.setText(userInfo.getUserType());
        }
    }

    // 隐藏对话框
    public void hide() {
        mAlertDialog.hide();
    }

    // 销毁对话框
    public void dismiss() {
        mAlertDialog.dismiss();
    }

    // 获取对话框句柄
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }
}
