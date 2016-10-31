package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.BaseAnimation;

/**
 * Created by GuHeng on 2016/9/27.
 */
public class MenuListener extends BaseAnimation implements View.OnClickListener {

    // 构造函数
    public MenuListener(MainActivity mainActivity, ArrayList<Button> arrayListButtons) {
        super(mainActivity);
        init(arrayListButtons);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_main: // 主菜单按钮
                startMenuAnimation(view);
                break;
            case R.id.menu_edit: // 编辑按钮
                mMainActivity.getLayoutManger().getMenuLayout().hide();
                mMainActivity.getLayoutManger().getEditLayout().show();
                break;
            case R.id.menu_setting: // 设置按钮
                break;
            case R.id.menu_download: // 下载按钮
                break;
            default:
                break;
        }
    }
}
