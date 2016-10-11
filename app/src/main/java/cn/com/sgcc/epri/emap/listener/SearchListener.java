package cn.com.sgcc.epri.emap.listener;

import android.view.View;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/9/27.
 */
public class SearchListener extends TransmitContext implements View.OnClickListener {

    // 构造函数
    public SearchListener(MainActivity context) {
        super(context);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.emap_search_bar_login_btn:
                onClickedLogin();
                break;
            case R.id.emap_search_bar_route_btn:
                onClickedRoute();
                break;
            default:
                break;
        }
    }

    // 点击登录按钮
    private void onClickedLogin() {
        // 弹出登录对话框
        context.getDlgMgr().getLoginDlg().show();
    }

    // 点击搜索按钮
    private void onClickedRoute() {

    }
}
