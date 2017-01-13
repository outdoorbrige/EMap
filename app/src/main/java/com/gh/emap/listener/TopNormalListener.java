package com.gh.emap.listener;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.model.UserInfo;

/**
 * Created by GuHeng on 2016/11/10.
 */
public class TopNormalListener implements View.OnClickListener {
    private MainActivity mMainActivity;

    // 构造函数
    public TopNormalListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.user_login_button:
                onClickedLogin();
                break;
            case R.id.search_button:
                onClickedSearch();
                break;
            default:
                break;
        }
    }

    // 点击登录按钮
    private void onClickedLogin() {
        UserInfo userInfo = mMainActivity.getMainManager().getUserManager().getUserInfo();
        if(userInfo == null || !userInfo.isSuccess()) { // 用户离线
            // 弹出登录对话框
            mMainActivity.getMainManager().getLayoutManager().getUserLoginLayout().show();
        } else { // 用户在线
            // 弹出注销对话框
            mMainActivity.getMainManager().getLayoutManager().getUserLogoutLayout().show();
        }
    }

    // 点击搜索按钮
    private void onClickedSearch() {

    }
}
