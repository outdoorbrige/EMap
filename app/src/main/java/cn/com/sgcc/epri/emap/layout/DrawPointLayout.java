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
 * 地物编辑-画点布局
 */
public class DrawPointLayout extends BaseLayout {
    private TextView mPointTypes; // 画点的类型
    private EditText mPointName; // 画点的名称
    private Button mPointSave; // 保存

    // 构造函数
    public DrawPointLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        setLayout(mMainActivity.findViewById(R.id.draw_point));
        mPointTypes = (TextView)mMainActivity.findViewById(R.id.point_type);
        mPointName = (EditText)mMainActivity.findViewById(R.id.point_name);
        mPointSave = (Button)mMainActivity.findViewById(R.id.point_save);

        DrawPointListener listener = new DrawPointListener(mMainActivity);

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
        mPointTypes.setText("");
        mPointName.setText("");
    }
}
