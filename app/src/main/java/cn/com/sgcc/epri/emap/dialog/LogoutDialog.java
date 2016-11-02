package cn.com.sgcc.epri.emap.dialog;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.LogoutListener;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.base.BaseAlertDialog;

/**
 * Created by GuHeng on 2016/10/25.
 * 用户注销对话框
 */
public class LogoutDialog extends BaseAlertDialog {
    private View mLayout; // 布局
    private TextView mUserName; // 用户名
    private TextView mNickName; // 昵称
    private TextView mUserType; // 用户类型
    private Button mLogoutButton; // 注销按钮
    private Button mCancelButton; // 取消按钮

    private final String mUserNamePrefix = "用户名称:";
    private final String mUserNickPrefix = "用户昵称:";
    private final String mUserTypePrefix = "用户类型:";

    // 构造函数
    public LogoutDialog(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        super.init(R.layout.logout, (ViewGroup) mMainActivity.findViewById(R.id.logout), false, false);
    }

    // 显示对话框
    public void show() {
        super.show();
        // AlertDialog自定义setView
        // 必须show对话框，然后才能查找控件
        initWidget();
    }

    // 初始化控件
    private void initWidget() {
        if(mLayout == null) { // 只初始化一次控件对象
            mLayout = getAlertDialog().findViewById(R.id.logout);
            mUserName = (TextView)getAlertDialog().findViewById(R.id.logout_name);
            mNickName = (TextView)getAlertDialog().findViewById(R.id.logout_nickname);
            mUserType = (TextView)getAlertDialog().findViewById(R.id.logout_type);
            mLogoutButton = (Button)getAlertDialog().findViewById(R.id.logout_button);
            mCancelButton = (Button)getAlertDialog().findViewById(R.id.logout_cancel_button);

            LogoutListener listener = new LogoutListener(mMainActivity);

            mLogoutButton.setOnClickListener(listener);
            mCancelButton.setOnClickListener(listener);
        }

        UserInfo userInfo = mMainActivity.getUserManager().getUserInfo();
        if(userInfo == null || !userInfo.isSuccess()) { // 用户离线
            hide();
        } else { // 用户在线
            mUserName.setText(mUserNamePrefix + userInfo.getUserName());
            mNickName.setText(mUserNickPrefix + userInfo.getNickName());
            mUserType.setText(mUserTypePrefix + userInfo.getUserType());
        }
    }
}
