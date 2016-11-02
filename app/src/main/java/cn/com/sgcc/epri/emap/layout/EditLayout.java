package cn.com.sgcc.epri.emap.layout;

import android.widget.TextView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.EditListener;
import cn.com.sgcc.epri.emap.base.BaseLayout;

/**
 * Created by GuHeng on 2016/10/31.
 * 地图编辑布局
 */
public class EditLayout extends BaseLayout {
    private TextView mShapEditView; // 地物编辑
    private TextView mLineEditView; // 线路编辑
    private TextView mMappingView; // 测绘
    private TextView mExitEditView; // 退出编辑
    private ShapLayout mShapLayout; // 地物编辑菜单
    private DrawPointLayout mDrawPointLayout; // 画点信息布局

    // 构造函数
    public EditLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        setLayout(mMainActivity.findViewById(R.id.edit));
        mShapEditView = (TextView)mMainActivity.findViewById(R.id.menu_shap);
        mLineEditView = (TextView)mMainActivity.findViewById(R.id.menu_line);
        mMappingView = (TextView)mMainActivity.findViewById(R.id.menu_mapping);
        mExitEditView = (TextView)mMainActivity.findViewById(R.id.menu_exit);

        EditListener listener = new EditListener(mMainActivity);

        mShapEditView.setOnClickListener(listener);
        mLineEditView.setOnClickListener(listener);
        mMappingView.setOnClickListener(listener);
        mExitEditView.setOnClickListener(listener);

        mShapLayout = new ShapLayout(mMainActivity);
        mShapLayout.init();

        mDrawPointLayout = new DrawPointLayout(mMainActivity);
        mDrawPointLayout.init();
    }

    // 获取地物编辑布局
    public ShapLayout getShapLayout() {
        return mShapLayout;
    }

    // 获取画点信息布局
    public DrawPointLayout getDrawPointLayout() {
        return mDrawPointLayout;
    }

    // 布局显示
    public void show() {
        super.show();
    }

    // 布局隐藏
    public void hide() {
        super.hide();
        clear();
    }

    // 布局数据清理
    private void clear() {
    }
}
