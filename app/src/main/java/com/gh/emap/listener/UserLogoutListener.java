package com.gh.emap.listener;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.manager.LogManager;
import com.gh.emap.manager.WebServiceManager;
import com.gh.emap.model.UserInfo;

/**
 * Created by GuHeng on 2016/11/11.
 */
public class UserLogoutListener implements View.OnClickListener {
    private MainActivity mMainActivity;
    private UserInfo mUserInfo; // 用户信息
    private Handler mHandler; // 消息句柄
    private UserInfo mReturnUserInfo; // 返回的用户信息

    // 构造函数
    public UserLogoutListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_button:
                onClickedLogout();
                break;
            case R.id.logout_cancel_button:
                onClickedCancel();
                break;
            default:
                break;
        }
    }

    // 注销
    private void onClickedLogout() {
        String userName = mMainActivity.getMainManager().getUserManager().getUserInfo().getUserName();

        mUserInfo = new UserInfo();
        mUserInfo.setUserName(userName);
        mUserInfo.setLogoutDate(mMainActivity.getCurrentDate());
        mUserInfo.setOnline(0);

        mMainActivity.getMainManager().getLogManager().log(getClass(), LogManager.LogLevel.mInfo,
                String.format("用户注销：%s", mUserInfo.getUserName()));

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if (message.what == WebServiceManager.WebServiceMsgType.WS_MSG_LOGOUT) {
                    mReturnUserInfo = (UserInfo) message.obj;

                    if (mReturnUserInfo.isSuccess()) {
                        mMainActivity.getMainManager().getUserManager().setUserInfo(null);

                        // 更改离线图标
                        Button loginButton = ((Button)(mMainActivity.findViewById(R.id.user_login_button)));
                        loginButton.setBackgroundResource(R.mipmap.offline_bg);

                        onClickedCancel(); // 关闭注销窗口

                        mMainActivity.getMainManager().getLogManager().show("注销成功!");
                    } else {
                        // 注销失败
                        mMainActivity.getMainManager().getLogManager().show("注销失败!");
                        mMainActivity.getMainManager().getLogManager().show(mReturnUserInfo.getErrorString());
                        mMainActivity.getMainManager().getLogManager().log(getClass(), LogManager.LogLevel.mError,
                                mReturnUserInfo.getErrorString());
                    }
                }
            }
        };

        mMainActivity.getMainManager().getWebServiceManager().userLogoutWebService(mHandler, mUserInfo);
    }

    // 返回
    private void onClickedCancel() {
        mMainActivity.getMainManager().getLayoutManager().getUserLogoutLayout().hide();
    }
}
