package cn.com.sgcc.epri.emap.listener;

import android.view.View;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/11.
 */
public class RegisterListener extends TransmitContext implements View.OnClickListener {

    // 构造函数
    public RegisterListener(MainActivity context) {
        super(context);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.emap_view_register_register_btn:
                onClickedRegister();
                break;
            case R.id.emap_view_register_return_btn:
                onClickedReturn();
                break;
            default:
                break;
        }
    }

    // 注册
    private void onClickedRegister() {

    }

    // 返回
    private void onClickedReturn() {
        context.getDlgMgr().getRegisterDlg().hide();
    }
}
