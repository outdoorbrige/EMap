package cn.com.sgcc.epri.emap.layout;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.base.SingleListLayout;
import cn.com.sgcc.epri.emap.listener.ShapListViewListener;
import cn.com.sgcc.epri.emap.util.Log4jLevel;

/**
 * Created by GuHeng on 2016/11/1.
 * 地物编辑布局
 */
public class ShapLayout extends SingleListLayout {
    private String[] mListItems = {"画点", "画线", "画面"};

    // 构造函数
    public ShapLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        setLayout(mMainActivity.findViewById(R.id.edit_layout));
        setListItems(mListItems);
        setOnItemClickListener(new ShapListViewListener(mMainActivity));

        mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mInfo,
                String.format("%s,%s,%s", mListItems[0], mListItems[1], mListItems[2]));
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
