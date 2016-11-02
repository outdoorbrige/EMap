package cn.com.sgcc.epri.emap.layout;

import android.widget.TextView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.base.SingleListLayout;
import cn.com.sgcc.epri.emap.listener.ShapListViewListener;
import cn.com.sgcc.epri.emap.listener.ShapListener;
import cn.com.sgcc.epri.emap.util.Log4jLevel;

/**
 * Created by GuHeng on 2016/11/1.
 * 地物编辑布局
 */
public class ShapLayout extends SingleListLayout {
    private TextView mShapEdit; // 地物编辑
    private String[] mListItems = {"点", "线", "面"};

    // 构造函数
    public ShapLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        setLayout(mMainActivity.findViewById(R.id.edit));
        mShapEdit = (TextView)mMainActivity.findViewById(R.id.menu_shap);
        mShapEdit.setOnClickListener(new ShapListener(mMainActivity, this));

        super.init(mListItems, 0, new ShapListViewListener(mMainActivity, this));

        mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mInfo, String.format("%s,%s,%s", mListItems[0], mListItems[1], mListItems[2]));
    }
}
