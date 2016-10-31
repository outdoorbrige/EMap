package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.Button;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/26.
 */
public class LogoutListener extends MainActivityContext implements View.OnClickListener {

    // 构造函数
    public LogoutListener(MainActivity mainActivity) {
        super(mainActivity);
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
        mMainActivity.getUserManager().setUserInfo(null);

        // 更改离线图标
        Button loginButton = ((Button)(mMainActivity.findViewById(R.id.user_login_button)));
        loginButton.setBackgroundResource(R.mipmap.offline_bg);

        onClickedCancel(); // 关闭注销窗口

        mMainActivity.getLog4jManager().show("注销成功!");
    }

    // 返回
    private void onClickedCancel() {
        mMainActivity.getDialogManager().getLogoutDialog().hide();
    }
}
