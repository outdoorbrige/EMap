package com.gh.emap.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.listener.UserLogoutListener;
import com.gh.emap.model.UserInfo;

/**
 * Created by GuHeng on 2016/11/11.
 */
public class UserLogoutLayout {
    private Context mContext;
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

    public UserLogoutLayout(Context context) {
        this.mContext = context;
    }

    public void init() {
        View layout = ((MainActivity)this.mContext).getLayoutInflater().inflate(R.layout.user_logout, null, false);
        this.mAlertDialog = new AlertDialog.Builder(this.mContext).create();
        this.mAlertDialog.setView(layout);
        this.mAlertDialog.setCancelable(false); // 点击对话框外地方是否不消失
    }

    // 显示对话框
    public void show() {
        this.mAlertDialog.show();

        // AlertDialog自定义setView
        // 必须show对话框，然后才能查找控件
        initWidget();
    }

    // 初始化控件
    private void initWidget() {
        if(this.mLayout == null) { // 只初始化一次控件对象
            this.mLayout = getAlertDialog().findViewById(R.id.user_logout);
            mUserName = (TextView)getAlertDialog().findViewById(R.id.logout_name);
            mNickName = (TextView)getAlertDialog().findViewById(R.id.logout_nickname);
            mUserType = (TextView)getAlertDialog().findViewById(R.id.logout_type);
            mLogoutButton = (Button)getAlertDialog().findViewById(R.id.logout_button);
            mCancelButton = (Button)getAlertDialog().findViewById(R.id.logout_cancel_button);

            mLogoutButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getUserLogoutListener());
            mCancelButton.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getUserLogoutListener());
        }

        UserInfo userInfo = ((MainActivity)this.mContext).getMainManager().getUserManager().getUserInfo();
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
        this.mAlertDialog.hide();
    }

    // 销毁对话框
    public void dimiss() {
        this.mAlertDialog.dismiss();
    }

    // 获取对话框句柄
    public AlertDialog getAlertDialog() {
        return this.mAlertDialog;
    }
}