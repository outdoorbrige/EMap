package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.BaseAnimation;

/**
 * Created by GuHeng on 2016/10/31.
 */
public class EditListener extends BaseAnimation implements View.OnClickListener {

    // 构造函数
    public EditListener(MainActivity mainActivity, ArrayList<Button> arrayListButtons) {
        super(mainActivity);
        init(arrayListButtons);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_edit_menu: // 菜单按钮
                startMenuAnimation(view);
                break;
            case R.id.menu_line: // 画线按钮
                break;
            case R.id.menu_shap: // 图形按钮
                break;
            case R.id.menu_mapping: // 测绘按钮
                break;
            case R.id.menu_delete: // 删除按钮
                break;
            case R.id.menu_exit: // 退出按钮
                mMainActivity.getLayoutManger().getEditLayout().hide();
                mMainActivity.getLayoutManger().getMenuLayout().show();
                break;
            default:
                break;
        }
    }
}
