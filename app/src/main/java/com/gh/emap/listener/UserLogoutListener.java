package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2016/11/11.
 */
public class UserLogoutListener implements View.OnClickListener {
    private Context mContext;

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
        ((MainActivity)this.mContext).getMainManager().getUserManager().setUserInfo(null);

        // 更改离线图标
        Button loginButton = ((Button)(((MainActivity)this.mContext).findViewById(R.id.user_login_button)));
        loginButton.setBackgroundResource(R.mipmap.offline_bg);

        onClickedCancel(); // 关闭注销窗口

        ((MainActivity)this.mContext).getMainManager().getLogManager().show("注销成功!");
    }

    // 返回
    private void onClickedCancel() {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getUserLogoutLayout().hide();
    }
}
