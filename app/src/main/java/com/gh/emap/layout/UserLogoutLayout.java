package com.gh.emap.layout;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.model.UserInfo;

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


    private final String mUserNamePrefix = "用户名称:";
    private final String mUserNickPrefix = "用户昵称:";
    private final String mUserTypePrefix = "用户类型:";

    public UserLogoutLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        View layout = mMainActivity.getLayoutInflater().inflate(R.layout.user_logout, null, false);
        mAlertDialog = new AlertDialog.Builder(mMainActivity).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(false); // 点击对话框外地方是否不消失
    }

    // 显示对话框
    public void show() {
        mAlertDialog.show();

        // AlertDialog自定义setView
        // 必须show对话框，然后才能查找控件
        initWidget();
    }

    // 初始化控件
    private void initWidget() {
        if(mLayout == null) { // 只初始化一次控件对象
            mLayout = getAlertDialog().findViewById(R.id.user_logout);
            mUserName = (TextView)getAlertDialog().findViewById(R.id.logout_name);
            mNickName = (TextView)getAlertDialog().findViewById(R.id.logout_nickname);
            mUserType = (TextView)getAlertDialog().findViewById(R.id.logout_type);
            mLogoutButton = (Button)getAlertDialog().findViewById(R.id.logout_button);
            mCancelButton = (Button)getAlertDialog().findViewById(R.id.logout_cancel_button);

            mLogoutButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserLogoutListener());
            mCancelButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getUserLogoutListener());
        }

        UserInfo userInfo = mMainActivity.getMainManager().getUserManager().getUserInfo();
        if(userInfo == null || !userInfo.isSuccess()) { // 用户离线
            hide();
        } else { // 用户在线
            mUserName.setText(mUserNamePrefix + userInfo.getUserName());
            mNickName.setText(mUserNickPrefix + userInfo.getNickName());
            mUserType.setText(mUserTypePrefix + userInfo.getUserType());
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
