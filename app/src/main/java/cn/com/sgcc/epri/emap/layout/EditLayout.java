package cn.com.sgcc.epri.emap.layout;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.EditListener;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/31.
 * 地图编辑布局
 */
public class EditLayout extends MainActivityContext {
    private View mLayout; // 布局
    private Button mLineButton; // 画线按钮
    private Button mShapButton; // 图形按钮
    private Button mMappingButton; // 测绘按钮
    private Button mDeleteButton; // 删除按钮
    private Button mExitButton; // 退出按钮
    private Button mEditButton; // 编辑按钮
    private ArrayList<Button> mArrayListButtons = new ArrayList<Button>(); // 展开和折叠菜单中控制的按钮集合

    // 构造函数
    public EditLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mLayout = mMainActivity.findViewById(R.id.edit_menu);
        mLineButton = (Button) mMainActivity.findViewById(R.id.menu_line);
        mShapButton = (Button) mMainActivity.findViewById(R.id.menu_shap);
        mMappingButton = (Button) mMainActivity.findViewById(R.id.menu_mapping);
        mDeleteButton = (Button) mMainActivity.findViewById(R.id.menu_delete);
        mExitButton = (Button) mMainActivity.findViewById(R.id.menu_exit);
        mEditButton = (Button) mMainActivity.findViewById(R.id.main_edit_menu);

        // 注意：
        // 按钮排列数序是从下向上排列的
        // 底部的按钮在List的队首，顶部的按钮在List的队尾
        mArrayListButtons.add(mExitButton);
        mArrayListButtons.add(mDeleteButton);
        mArrayListButtons.add(mMappingButton);
        mArrayListButtons.add(mShapButton);
        mArrayListButtons.add(mLineButton);

        EditListener listener = new EditListener(mMainActivity, mArrayListButtons);

        mLineButton.setOnClickListener(listener);
        mShapButton.setOnClickListener(listener);
        mMappingButton.setOnClickListener(listener);
        mDeleteButton.setOnClickListener(listener);
        mExitButton.setOnClickListener(listener);
        mEditButton.setOnClickListener(listener);
    }

    // 显示布局
    public void show() {
        mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置
        mLayout.setVisibility(View.GONE);
    }
}
