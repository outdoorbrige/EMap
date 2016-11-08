package cn.com.sgcc.epri.emap.layout;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.base.BaseLayout;
import cn.com.sgcc.epri.emap.listener.DrawPointListener;

/**
 * Created by GuHeng on 2016/11/2.
 * 地物编辑-画点顶部布局
 */
public class DrawPointTopLayout extends BaseLayout {
    private TextView mPointType; // 画点的类型
    private EditText mPointName; // 画点的名称
    private Button mPointSave; // 保存

    // 构造函数
    public DrawPointTopLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        setLayout(mMainActivity.findViewById(R.id.draw_point_top_layout));
        mPointType = (TextView)mMainActivity.findViewById(R.id.point_type);
        mPointName = (EditText)mMainActivity.findViewById(R.id.point_name);
        mPointSave = (Button)mMainActivity.findViewById(R.id.point_save);

        DrawPointListener listener = new DrawPointListener(mMainActivity);

        mPointType.setOnClickListener(listener);
        mPointName.setOnClickListener(listener);
        mPointSave.setOnClickListener(listener);
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
        mPointType.setText("");
        mPointName.setText("");
    }
}
