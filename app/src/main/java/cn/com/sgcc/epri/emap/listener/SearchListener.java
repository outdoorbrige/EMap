package cn.com.sgcc.epri.emap.listener;

import android.view.View;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/27.
 */
public class SearchListener extends MainActivityContext implements View.OnClickListener {

    // 构造函数
    public SearchListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.user_login_button:
                onClickedLogin();
                break;
            case R.id.search_button:
                onClickedRoute();
                break;
            default:
                break;
        }
    }

    // 点击登录按钮
    private void onClickedLogin() {
        UserInfo userInfo = mMainActivity.getUserManager().getUserInfo();
        if(userInfo == null || !userInfo.isSuccess()) { // 用户离线
            // 弹出登录对话框
            mMainActivity.getDialogManager().getLoginDialog().show();
        } else { // 用户在线
            // 弹出注销对话框
            mMainActivity.getDialogManager().getLogoutDialog().show();
        }
    }

    // 点击搜索按钮
    private void onClickedRoute() {

    }
}
