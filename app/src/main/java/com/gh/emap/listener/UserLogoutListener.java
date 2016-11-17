package com.gh.emap.listener;

import android.content.Context;
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
    private Context mContext;
    private UserInfo mUserInfo; // 用户信息
    private Handler mHandler; // 消息句柄
    private UserInfo mReturnUserInfo; // 返回的用户信息

    // 构造函数
    public UserLogoutListener(Context context) {
        this.mContext = context;
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
        String userName = ((MainActivity) this.mContext).getMainManager().getUserManager().getUserInfo().getUserName();

        mUserInfo = new UserInfo();
        mUserInfo.setUserName(userName);
        mUserInfo.setLogoutDate(((MainActivity)this.mContext).getCurrentDate());
        mUserInfo.setOnline(0);

        ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
                String.format("用户注销：%s", mUserInfo.getUserName()));

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if (message.what == WebServiceManager.WebServiceMsgType.WS_MSG_LOGOUT) {
                    mReturnUserInfo = (UserInfo) message.obj;

                    if (mReturnUserInfo.isSuccess()) {
                        ((MainActivity)mContext).getMainManager().getUserManager().setUserInfo(null);

                        // 更改离线图标
                        Button loginButton = ((Button)(((MainActivity)mContext).findViewById(R.id.user_login_button)));
                        loginButton.setBackgroundResource(R.mipmap.offline_bg);

                        onClickedCancel(); // 关闭注销窗口

                        ((MainActivity)mContext).getMainManager().getLogManager().show("注销成功!");
                    } else {
                        // 注销失败
                        ((MainActivity)mContext).getMainManager().getLogManager().show("注销失败!");
                        ((MainActivity) mContext).getMainManager().getLogManager().show(mReturnUserInfo.getErrorString());
                        ((MainActivity) mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                                mReturnUserInfo.getErrorString());
                    }
                }
            }
        };

        ((MainActivity) mContext).getMainManager().getWebServiceManager().userLogoutWebService(mHandler, mUserInfo);
    }

    // 返回
    private void onClickedCancel() {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserLogoutLayout().hide();
    }
}
