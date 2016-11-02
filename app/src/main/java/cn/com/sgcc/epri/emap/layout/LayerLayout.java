package cn.com.sgcc.epri.emap.layout;

import android.widget.Button;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.LayerListViewListener;
import cn.com.sgcc.epri.emap.listener.LayerListener;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.base.SingleListLayout;

/**
 * Created by GuHeng on 2016/9/27.
 * 地图类型切换布局类
 */
public class LayerLayout extends SingleListLayout {
    private Button mLayerButton; // 地图类型切换按钮
    private String[] mListItems = {"影像图", "矢量图", "地形图"};

    // 构造函数
    public LayerLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        setLayout(mMainActivity.findViewById(R.id.layer));
        setListItems(mListItems);
        setDefaultSelectItemIndex(1); // 默认选中矢量图
        setOnItemClickListener(new LayerListViewListener(mMainActivity));

        mLayerButton = (Button) mMainActivity.findViewById(R.id.layer_button);
        mLayerButton.setOnClickListener(new LayerListener(mMainActivity));

        mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mInfo, String.format("%s,%s,%s", mListItems[0], mListItems[1], mListItems[2]));
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
